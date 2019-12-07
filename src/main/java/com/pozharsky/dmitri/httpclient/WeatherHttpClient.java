package com.pozharsky.dmitri.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WeatherHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(WeatherHttpClient.class);
    private HttpClient client;
    private HttpRequest request;

    public WeatherHttpClient(String uri) {
        client = HttpClient.newBuilder().build();
        request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .build();
    }

    public HttpResponse<String> getResponse(){
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return response;
    }

}
