package com.pozharsky.dmitri;

import com.pozharsky.dmitri.httpclient.WeatherHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;

@SpringBootApplication
@PropertySource("classpath:configuration.properties")
@EnableScheduling
public class Application {

    @Autowired
    private Environment env;

    @Bean
    public WeatherHttpClient weatherHttpClient(){
        return new WeatherHttpClient(Objects.requireNonNull(env.getProperty("api.request.url"))
                .replace("{APIKEY}", Objects.requireNonNull(env.getProperty("api.key"))));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
