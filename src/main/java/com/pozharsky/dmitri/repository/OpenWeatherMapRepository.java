package com.pozharsky.dmitri.repository;

import com.pozharsky.dmitri.pojo.OpenWeatherMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OpenWeatherMapRepository implements BaseRepository<OpenWeatherMap> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save (OpenWeatherMap weatherMap){
        mongoTemplate.save(weatherMap);
    }
}
