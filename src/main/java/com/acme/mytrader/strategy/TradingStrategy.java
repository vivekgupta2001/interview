package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.BuyPriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@AllArgsConstructor
public class TradingStrategy {
    private ExecutionService executionService ;
    private PriceSource priceSource;

    public void autoOrderMonitor(List<Security> securities) {
        securities.forEach( security -> {
            priceSource.addPriceListener(new BuyPriceListenerImpl(executionService, security.getSecurityName(),
                    security.getVolume(), security.getPriceThreshHold()));
        });
    }
}

@Getter
@AllArgsConstructor
class Security {
    private final String securityName;
    private final int volume;
    private final double priceThreshHold;
}
