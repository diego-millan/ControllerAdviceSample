package com.example.controlleradvice.service;

import com.example.controlleradvice.model.City;
import java.util.List;

public interface ICityService {

    City findById(Long id);
    City save(City city);
    List<City> findAll();
}