package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.pojo.OpenWeatherMap;
import com.pozharsky.dmitri.repository.OpenWeatherMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenWeatherMapService {

    @Autowired
    private OpenWeatherMapRepository weatherMapRepository;

    @Transactional
    public void saveOpenWeatherMap(OpenWeatherMap openWeatherMap){
        if(openWeatherMap == null) throw new IllegalArgumentException("Save object of OpenWeatherMap must be not null");
        weatherMapRepository.save(openWeatherMap);
    }
}
