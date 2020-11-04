package com.etree.opendata.common.dto;

public class CitiesDto implements OpendataService {
	String isoCode;
	String cityName;
	String description;
	String isMetro;
	String status;
	String countryCode;
	String timezoneCode;
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsMetro() {
		return isMetro;
	}
	public void setIsMetro(String isMetro) {
		this.isMetro = isMetro;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getTimezoneCode() {
		return timezoneCode;
	}
	public void setTimezoneCode(String timezoneCode) {
		this.timezoneCode = timezoneCode;
	}

}
