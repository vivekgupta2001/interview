package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BuyPriceListenerImplTest {

    @Mock
    private ExecutionService executionService;

    @Test
    public void priceUpdateWhenPriceIsBelowThreshHold() {
        ArgumentCaptor<String> securityCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> priceCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Integer> volumeCaptor = ArgumentCaptor.forClass(Integer.class);

        final PriceListener priceListener = new BuyPriceListenerImpl(executionService, "IBM", 100, 55);
        priceListener.priceUpdate("IBM", 50);
        verify(executionService, times(1)).buy(securityCaptor.capture(), priceCaptor.capture(), volumeCaptor.capture());

        assertThat("IBM", equalTo(securityCaptor.getValue()));
        assertThat(50.0, equalTo(priceCaptor.getValue()));
        assertThat(100, equalTo(volumeCaptor.getValue()));
    }

    @Test
    public void priceUpdateWhenPriceIsAboveThreshHold() {
        final PriceListener priceListener = new BuyPriceListenerImpl(executionService, "IBM", 100, 60);
        priceListener.priceUpdate("IBM", 70);
        verify(executionService, times(0)).buy("IBM", 70, 100);
    }
}