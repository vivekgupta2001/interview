package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.BuyPriceListenerImpl;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @Mock
    private ExecutionService executionService;

    @Mock
    private PriceSource priceSource;


    @Test
    public void enableAutoOrderMonitor() {

        PriceListener priceListener = new BuyPriceListenerImpl(executionService, "IBM",
                100, 50);
        TradingStrategy tradingStrategy = new TradingStrategy(priceSource, priceListener);

        tradingStrategy.enableAutoOrderMonitor();

        verify(priceSource, times(1))
                .addPriceListener(any(BuyPriceListenerImpl.class));
    }

    @Test
    public void disableAutoOrderMonitor() {
        PriceListener priceListener = new BuyPriceListenerImpl(executionService, "IBM",
                100, 50);
        TradingStrategy tradingStrategy = new TradingStrategy(priceSource, priceListener);

        tradingStrategy.disableAutoOrderMonitor();

        verify(priceSource, times(1))
                .removePriceListener(any(BuyPriceListenerImpl.class));
    }
}
