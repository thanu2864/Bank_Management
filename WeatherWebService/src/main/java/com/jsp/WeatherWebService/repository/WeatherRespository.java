package com.jsp.WeatherWebService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.WeatherWebService.DTO.Weather;

public interface WeatherRespository extends JpaRepository<Weather,Integer> {
//all methods of jpaRespository are inherited here
//during runtime,implementation class will be created implicitly
//and implementation all methods to be provided
}
