package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.httpclient.WeatherHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherHttpClient weatherHttpClient;

    @Scheduled(cron = "${schedule.cron}")
    public void getWeather(){
        HttpResponse<String> response = weatherHttpClient.getResponse();
        if(response == null) throw new NullPointerException("Response is null");
        logger.info("Response status code: " + response.statusCode());
        logger.info("Response body: "+response.body());
    }
}
