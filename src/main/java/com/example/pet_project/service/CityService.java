package com.example.pet_project.service;

import com.example.pet_project.model.dto.CityDto;
import com.example.pet_project.model.entities.City;

import java.util.List;

public interface CityService {
    City createCity(City city);
    City getCityById(Long id);
    List<CityDto> getAllCities();
    City updateCity(City city);
    void deleteCity(Long id);
}
