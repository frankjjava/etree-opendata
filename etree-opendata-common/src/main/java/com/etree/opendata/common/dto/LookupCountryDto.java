package com.etree.opendata.common.dto;

public class LookupCountryDto implements OpendataService {
	String isoCode;
	String countryName;
	
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
}
