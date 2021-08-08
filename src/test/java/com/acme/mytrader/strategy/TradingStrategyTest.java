package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.BuyPriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @Mock
    private ExecutionService executionService;

    @Mock
    private PriceSource priceSource;


    @Test
    public void autoOrderMonitorCheckListenerAttachedWithOneSecurity() {
        TradingStrategy tradingStrategy = new TradingStrategy(executionService, priceSource);
        Security ibmSecurity = new Security("IBM", 100, 55);
        tradingStrategy.autoOrderMonitor(Arrays.asList(ibmSecurity));
        verify(priceSource, times(1))
                .addPriceListener(any(BuyPriceListenerImpl.class));
    }

    @Test
    public void autoOrderMonitorCheckListenerAttachedForEachSecurity() {
        TradingStrategy tradingStrategy = new TradingStrategy(executionService, priceSource);
        Security ibmSecurity = new Security("IBM", 100, 55);
        Security tataSecurity = new Security("TATA", 100, 40);
        tradingStrategy.autoOrderMonitor(Arrays.asList(ibmSecurity, tataSecurity));
        verify(priceSource, times(2))
                .addPriceListener(any(BuyPriceListenerImpl.class));
    }
}
