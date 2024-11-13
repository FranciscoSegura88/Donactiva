package com.donactiva.proyecto.service;

import io.github.cdimascio.dotenv.Dotenv;

public class GoogleMapsService {
    private final String apiKey;

    public GoogleMapsService() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("GOOGLE_MAPS_API_KEY");
        
    }

    public String getApiKey() {
        return apiKey;
    }
}
