package com.jsp.WeatherWebService.DTO;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Weather {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private int weatherId;
private String city;
private LocalDate date;
private String temprature;
private String conditions;
public int getWeatherId() {
	return weatherId;
}
public void setWeatherId(int weatherId) {
	this.weatherId = weatherId;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public String getTemprature() {
	return temprature;
}
public void setTemprature(String temprature) {
	this.temprature = temprature;
}
public String getConditions() {
	return conditions;
}
public void setConditions(String conditions) {
	this.conditions = conditions;
}

}
