package com.etree.opendata.common.dto;

public class TimezonesCountriesDto implements OpendataDtoBase {

	int timezoneCode;
	String timezoneId;
	String timezone;
	String description;
	String city;
	String stateOrTerritory;
	String country;
	public int getTimezoneCode() {
		return timezoneCode;
	}
	public void setTimezoneCode(int timezoneCode) {
		this.timezoneCode = timezoneCode;
	}
	public String getTimezoneId() {
		return timezoneId;
	}
	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateOrTerritory() {
		return stateOrTerritory;
	}
	public void setStateOrTerritory(String stateOrTerritory) {
		this.stateOrTerritory = stateOrTerritory;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
