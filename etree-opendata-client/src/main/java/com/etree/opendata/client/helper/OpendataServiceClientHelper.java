package com.etree.opendata.client.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etree.opendata.common.OpendataConstants;
import com.etree.opendata.common.client.OpendataServiceClient;
import com.etree.opendata.common.dto.AirportsDto;
import com.etree.opendata.common.dto.TimezonesCountriesDto;

public class OpendataServiceClientHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpendataServiceClientHelper.class);

	private OpendataServiceClient opendataServiceClient;
	private static OpendataServiceClientHelper opendataServiceClientHelper;

	public OpendataServiceClientHelper() {
		OpendataServiceClientHelper.opendataServiceClientHelper = this;		
	}
	
	public static OpendataServiceClientHelper getInstance() {
		// objective of this method is not a singleton.
		return OpendataServiceClientHelper.opendataServiceClientHelper;
	}
	
	public OpendataServiceClient getOpendataServiceClient() {
		return opendataServiceClient;
	}

	public void setOpendataServiceClient(OpendataServiceClient openDataServiceClient) {
		this.opendataServiceClient = openDataServiceClient;
	}

	public String getAirportsTimeZoneId(String airportCode) {
		AirportsDto airportsDto = getAirportData(airportCode);
		if (airportsDto == null) {
			return null;
		}
		String deptCity = airportsDto.getCity();
		String deptCountry = airportsDto.getCountry();
		String timezoneId = null;
		List<String> cityValues = new ArrayList<String>();
		cityValues.add(deptCity);
		List<String> countryValues = new ArrayList<String>();
		countryValues.add(deptCountry);
		Map<String, List<String>> criteria = new HashMap<String, List<String>>();
		criteria.put("city", cityValues);
		criteria.put("country", countryValues);
		Object response = null;
		try {
			response = opendataServiceClient.fetchData(criteria,OpendataConstants.TIMEZONES_COUNTRIES, "timezoneId");
			if (response == null) {
				return null;
			}
			List<TimezonesCountriesDto> timezonesCountriesDtos = (List<TimezonesCountriesDto>) response;
			if (timezonesCountriesDtos == null || timezonesCountriesDtos.isEmpty()) {
				return null;
			}
			timezoneId = timezonesCountriesDtos.get(0).getTimezoneId();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return timezoneId;
	}

	public AirportsDto getAirportData(String airportCode) {
		List<String> criteriaValues = new ArrayList<String>();
		criteriaValues.add(airportCode);
		Map<String, List<String>> criteria = new HashMap<String, List<String>>();
		criteria.put("code", criteriaValues);
		Object response = null;
		AirportsDto airportsDto = null;
		try {
			response = opendataServiceClient.fetchData(criteria,OpendataConstants.AIRPORTS, "country", "city");
			if (response == null) {
				return null;
			}
			List<AirportsDto> airportsDtos = (List<AirportsDto>) response;
			if (airportsDtos == null || airportsDtos.isEmpty()) {
				return null;
			}
			airportsDto = airportsDtos.get(0);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return airportsDto;
	}
}
