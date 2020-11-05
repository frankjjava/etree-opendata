package com.etree.opendata.common.dto;

public class CountriesDto implements OpendataDtoBase {
	private String isoCodeAlpha2;
	private String isoCodeAlpha3;
	private String isoCodeNumeric;
	private String countryName;
	private String isdCode;
	private String status;
	private String currencyCode;
	private String regionCode;


	public String getIsoCodeAlpha2() {
		return isoCodeAlpha2;
	}

	public void setIsoCodeAlpha2(String isoCodeAlpha2) {
		this.isoCodeAlpha2 = isoCodeAlpha2;
	}

	public String getIsoCodeAlpha3() {
		return isoCodeAlpha3;
	}

	public void setIsoCodeAlpha3(String isoCodeAlpha3) {
		this.isoCodeAlpha3 = isoCodeAlpha3;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getIsoCodeNumeric() {
		return isoCodeNumeric;
	}

	public void setIsoCodeNumeric(String isoCodeNumeric) {
		this.isoCodeNumeric = isoCodeNumeric;
	}
	public String getIsdCode() {
		return isdCode;
	}

	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}

}
