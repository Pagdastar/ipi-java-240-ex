package com.ipiecoles.java.java240;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

//Test en JUnit4

@RunWith(MockitoJUnitRunner.class)
public class BitcoinServiceTest {

    @InjectMocks
    BitcoinService bitcoinService;

    @Mock
    WebPageManager webPageManager;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this.getClass()); }


    @Test
    public void getBitcoinRate() throws IOException {
        //On ne charge pas le contexte Spring, on est dans un test
        //Test mocké
        //Given
        Mockito.when(webPageManager.getPageContents(null)).thenReturn("{\"EUR\":7308.73}");

        //When
        Double rate = bitcoinService.getBitcoinRate();

        //Then
        Assertions.assertThat(rate).isNotNull();
        Assertions.assertThat(rate).isEqualTo(7308.73d);
    }

}