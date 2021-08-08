package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuyPriceListenerImpl implements PriceListener {

    private final ExecutionService executionService;
    private final String security;
    private final int volume;
    private final double priceThreshHold;

    @Override
    public void priceUpdate(String security, double price) {
        if(isSecurityPriceBelowThreshHold(security, price)) {
            autoOrder(security, price);
        }
    }

    private void autoOrder(String security, double price) {
        executionService.buy(security, price, this.volume);
    }

    private boolean isSecurityPriceBelowThreshHold(String security, double price) {
        return this.security.equals(security) && price < this.priceThreshHold;
    }
}
