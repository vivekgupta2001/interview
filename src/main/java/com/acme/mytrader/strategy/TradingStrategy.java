package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import lombok.AllArgsConstructor;

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
        /**
         * PriceSource will call priceUpdate on priceListener at regular interval
         * from here...
         */
    }

    public void disableAutoOrderMonitor() {
        priceSource.removePriceListener(priceListener);
    }
}
