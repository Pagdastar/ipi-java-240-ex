package com.ipiecoles.java.java240;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class BitcoinService {

    private Double rate = null;

    public void setForceRefresh(Boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
    }

    private Boolean forceRefresh = false;

    @Value("${bitcoinService.apiUrl}")
    private String apiUrl;

    @Autowired
    private WebPageManager webPageManager;

    /*
    //Optimisation de code : grâce au setter créé :
    public void setWebPageManager(WebPageManager webPageManager) {
        this.webPageManager = webPageManager;
    }
    /*
    /**
     * Méthode qui renvoie le cours du Bitcoin
     * @return le cours du bitcoin
     * @throws IOException si impossible d'accéder à la bourse
     */
    public Double getBitcoinRate() throws IOException {
        if(rate != null && !forceRefresh){
            System.out.println("Récupération du cours du bitcoin en cache...");
            return rate;
        }

        System.out.println("Récupération du cours du bitcoin sur site distant");

        //WebPageManager webPageManager = new WebPageManager();
        //--> au lieu de recréer à chaque fois, on le crée en attribut au début.

        String apiResponse = webPageManager.getPageContents(apiUrl);
        apiResponse = apiResponse.replace("{\"EUR\":","");
        apiResponse = apiResponse.replace("}","");
        rate = Double.parseDouble(apiResponse);
        return rate;
    }

    /**
     * Méthode qui renvoie l'équivalent en bitcoin du prix en euro passé en paramètre
     * @param prixEnEuro le prix à convertir
     * @return le prix en bitcoin au taux actuel
     * @throws IOException si impossible d'accéder à la bourse
     */
    public Double getBitcoinPrice(Double prixEnEuro) throws IOException {
        if(rate == null){
            getBitcoinRate();
        }
        return prixEnEuro / rate;
    }

}