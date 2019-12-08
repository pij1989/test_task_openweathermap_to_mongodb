package com.pozharsky.dmitri.httpclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.http.HttpResponse;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherHttpClientTest {

    @Autowired
    private WeatherHttpClient weatherHttpClient;

    @Test
    public void testGetResponse() {
        HttpResponse<String> response = weatherHttpClient.getResponse();
        assertNotNull(response);
    }
}