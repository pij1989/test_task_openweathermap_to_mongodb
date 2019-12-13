package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.httpclient.WeatherHttpClient;
import com.pozharsky.dmitri.pojo.OpenWeatherMap;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;
;

@Service
public class WeatherService {

    private static final String TIME_ZONE = "Europe/Minsk";
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherHttpClient weatherHttpClient;

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Scheduled(cron = "${schedule.cron}")
    public void mapWeatherToDataBase(){
        HttpResponse<String> response = weatherHttpClient.getResponse();
        if(response == null) throw new NullPointerException("Response is null");
        String responseDate = response.headers().firstValue("date").orElseThrow();
        int responseStatus = response.statusCode();
        String responseBody = response.body();
        logger.info("Response date: "+ responseDate);
        logger.info("Response status code: " + responseStatus);
        logger.info("Response body: "+ responseBody);
        OpenWeatherMap weatherMap = new OpenWeatherMap();
        weatherMap.setResponseStatus(response.statusCode());
        weatherMap.setResponseBody(Document.parse(responseBody));
        weatherMap.setResponseDate(parseResponseDate(responseDate));
        openWeatherMapService.saveOpenWeatherMap(weatherMap);

    }

    private LocalDateTime parseResponseDate(String responseDate){
        if (responseDate == null) throw new IllegalArgumentException("Response date can not be null");
        return LocalDateTime.ofInstant(
                Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(responseDate)),ZoneId.of(TIME_ZONE));
    }
}
