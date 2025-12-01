package com.jsp.WeatherWebService.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.WeatherWebService.DTO.Weather;
import com.jsp.WeatherWebService.SERVICE.WeatherService;

@RestController
public class WeatherController {
@Autowired
WeatherService service;
@PostMapping("/weather")
public Weather saveWeatherReport(@RequestBody Weather weather)
{
	return service.insertWeather(weather);
}
@GetMapping("/weather")
public List<Weather> getAllWeatherReports()
{
	return service.getAllWeather();
}
@GetMapping("/byid")
public Weather getWeatherReportById(@RequestParam int id)
{
	return service.getWeatherbyId(id);
}
}
