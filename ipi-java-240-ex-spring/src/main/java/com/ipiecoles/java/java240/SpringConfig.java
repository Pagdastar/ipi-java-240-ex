package com.ipiecoles.java.java240;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;


@SpringBootApplication
public class SpringConfig {

    @Value("${bitcoinService.forceRefresh}")
    boolean forceRefresh;

    //beans
    @Bean(name="bitcoinServiceWithoutCache")
    @Qualifier("withoutCache")
    @Scope("singleton")//Facultatif car scope singleton par défaut
    public BitcoinService bitcoinServiceWithoutCache() {
        BitcoinService bitcoinService = new BitcoinService();
        bitcoinService.setForceRefresh(true);
        //bitcoinService.setWebPageManager(webPageManager());
        return bitcoinService;
    }

    public static void main(String[] args){
        SpringApplication.run(SpringConfig.class, args);
    }


    /*
    @Bean(name="bitcoinServiceWithCache")
    public BitcoinService bitcoinServiceWithCache() {
        BitcoinService bitcoinService = new BitcoinService();
        bitcoinService.setForceRefresh(false);
        //bitcoinService.setWebPageManager(webPageManager());
        return bitcoinService;
    }
    */

}