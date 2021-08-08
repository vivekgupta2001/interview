package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.BuyPriceListenerImpl;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@AllArgsConstructor
public class TradingStrategy {
    private PriceSource priceSource;
    private PriceListener priceListener;

    public void enableAutoOrderMonitor() {
        priceSource.addPriceListener(priceListener);
    }

    public void disableAutoOrderMonitor() {
        priceSource.removePriceListener(priceListener);
    }
}
