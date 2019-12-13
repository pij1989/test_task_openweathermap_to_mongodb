package com.pozharsky.dmitri;

import com.mongodb.MongoClientURI;
import com.pozharsky.dmitri.httpclient.WeatherHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;

@SpringBootApplication
@PropertySource("${path.configure}")
@EnableScheduling
public class Application {

    @Autowired
    private Environment env;

    @Autowired
    private MongoMappingContext mongoMappingContext;

    @Bean
    public MongoDbFactory mongoDbFactory(){
        return new SimpleMongoDbFactory(new MongoClientURI(Objects.requireNonNull(env.getProperty("mongodb.uri"))));
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory(),mappingMongoConverter());
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    @Bean
    public WeatherHttpClient weatherHttpClient(){
        return new WeatherHttpClient(Objects.requireNonNull(env.getProperty("api.request.url"))
                .replace("{APIKEY}", Objects.requireNonNull(env.getProperty("api.key"))));
    }
    public static void main(String[] args) {
//        System.setProperty("path.configure","classpath:configuration.properties");
        SpringApplication.run(Application.class);
    }
}
