package com.jsp.WeatherWebService.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.WeatherWebService.DTO.Weather;
import com.jsp.WeatherWebService.repository.WeatherRespository;

@Service
public class WeatherService {
@Autowired
WeatherRespository respository;
//to insert or store object to db
public Weather insertWeather(Weather weather)
{
	return respository.save(weather);
	//or
	//Weather w=respository.save(weather);
	//return w;
	//again storing in seperate variable it is waste of memory
}

//to fetch the data from db
public List<Weather> getAllWeather()
{
	 return respository.findAll();
}
//TO FETCH AND FIND THE WEATHER BASED ON ID
public Weather getWeatherbyId(int id)
{
	Optional<Weather> opt=respository.findById(id);
	if(opt.isPresent())
	{
		return opt.get();
	}
	else
		return null;
}
}
