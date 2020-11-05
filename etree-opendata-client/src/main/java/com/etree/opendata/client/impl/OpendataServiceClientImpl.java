/**
 * Copyright © 2020 eTree Technologies Pvt. Ltd.
 *
 * @author  Franklin Joshua
 * @version 1.0
 * @since   2020-11-04 
 */
package com.etree.opendata.client.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etree.commons.core.dto.RequestDto;
import com.etree.opendata.common.OpendataConstants;
import com.etree.opendata.common.client.AbstractOpendataServiceClient;
import com.etree.opendata.common.dto.AirportsDto;
import com.etree.opendata.common.dto.CitiesDto;
import com.etree.opendata.common.dto.CountriesDto;
import com.etree.opendata.common.dto.CurrenciesDto;
import com.etree.opendata.common.dto.LookupCityDto;
import com.etree.opendata.common.dto.LookupCountryDto;
import com.etree.opendata.common.dto.OpendataDto;
import com.etree.opendata.common.dto.OpendataDtoBase;
import com.etree.opendata.common.dto.RegionsDto;
import com.etree.opendata.common.dto.TimezoneDstDto;
import com.etree.opendata.common.dto.TimezonesCountriesDto;
import com.etree.opendata.common.exception.OpendataException;

public class OpendataServiceClientImpl extends AbstractOpendataServiceClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpendataServiceClientImpl.class);

	@Override
	public Object fetchData(RequestDto requestWrapper) {
		Object request = requestWrapper.getRequest();
		if (request == null) {
			throw new OpendataException("", "Invalid params! Nothing to process.");
		}
		Object object = callRemote(requestWrapper);
		return object;
	}

	@Override
	public Object fetchData(Map<String, List<String>> criteria, String entitiesKeyName, String ... arrKeys) {
		OpendataDto opendataDto = new OpendataDto();
		opendataDto.setEntitiesKeyName(entitiesKeyName);
		List<String> keys = null;
		if (arrKeys != null) {
			for (String key : arrKeys) {
				if (keys == null) {
					keys = new ArrayList<>();
				}
				keys.add(key);
			}
			opendataDto.setKeys(keys);
		}
		opendataDto.setCriteria(criteria);
		RequestDto requestWrapperDto = new RequestDto();
		requestWrapperDto.setRequest(opendataDto);
		requestWrapperDto.setContentType(MediaType.APPLICATION_JSON);
		Object response = null;
		try {
			response = fetchData(requestWrapperDto);
		} catch (Exception e) {
			throw new OpendataException("", e.getMessage());
		}
		return response;
	}
	
	@Override
	public Object callRemote(RequestDto requestWrapper) {
		Object request = requestWrapper.getRequest();
		if (request == null) {
			throw new OpendataException("", "Invalid params! Nothing to process");
		}
		String baseUrl = (String) configParams.get(OPENDATA_JSON_GET_URL);
		if (baseUrl == null) {
			throw new OpendataException("", "Cannot make remote call! URL is not set.");
		}
		OpendataDto opendataServiceDto = (OpendataDto) request;
		StringBuilder url = new StringBuilder(baseUrl);
		if (opendataServiceDto.isLookup()) {
			url.append("lookup");
		}
        String entitiesKeyName = opendataServiceDto.getEntitiesKeyName();
        url.append(entitiesKeyName);
		if (opendataServiceDto.getKeys() != null) {
			StringBuilder fieldsBuilder = null;
			for (String field : opendataServiceDto.getKeys()) {
				if (fieldsBuilder == null) {
					fieldsBuilder = new StringBuilder();
				} else {
					fieldsBuilder.append(",");
				}
				fieldsBuilder.append(field);
			}
			url.append("/").append(fieldsBuilder.toString());
		}
		URI uri;
        try {
            uri = new URI(url.toString());
        } catch (URISyntaxException e) {
            throw new OpendataException("", e);
        }
		WebTarget target = createWebTarget(uri, false, false);
		Map<String, List<String>> criteria = opendataServiceDto.getCriteria();
		if (criteria != null) {
			for (Entry<String, List<String>> entry : criteria.entrySet()) {
				String key = entry.getKey();
				for (String value : entry.getValue()) {
					target = target.queryParam(key, value); // This line should be properly testing.
				}
			}
		}
		Builder builder = target.request();
		String entities = builder.get(String.class);
		List<OpendataDtoBase> openDataList = convert(opendataServiceDto, entities);
		if (openDataList != null) {
			return openDataList;
		}
		return entities;
	}

	private List<OpendataDtoBase> convert(OpendataDto opendataServiceDto, String entities) {
		if (entities == null || entities.trim().isEmpty()) {
			return null;
		}
		String entity = opendataServiceDto.getEntitiesKeyName();
		Class<?> cls = null;
		if (OpendataConstants.AIRPORTS.equalsIgnoreCase(entity)) {
			cls = AirportsDto.class;
		} else if (OpendataConstants.CITIES.equalsIgnoreCase(entity)) {
			cls = CitiesDto.class;
		} else if (OpendataConstants.COUNTRIES.equalsIgnoreCase(entity)) {
			cls = CountriesDto.class;
		} else if (OpendataConstants.CURRENCIES.equalsIgnoreCase(entity)) {
			cls = CurrenciesDto.class;
		} else if (OpendataConstants.LOOKUP_CITY.equalsIgnoreCase(entity)) {
			cls = LookupCityDto.class;
		} else if (OpendataConstants.LOOKUP_COUNTRY.equalsIgnoreCase(entity)) {
			cls = LookupCountryDto.class;
		} else if (OpendataConstants.REGIONS.equalsIgnoreCase(entity)) {
			cls = RegionsDto.class;
		} else if (OpendataConstants.TIMEZONE_DST.equalsIgnoreCase(entity)) {
			cls = TimezoneDstDto.class;
		} else if (OpendataConstants.TIMEZONES_COUNTRIES.equalsIgnoreCase(entity)) {
			cls = TimezonesCountriesDto.class;
		}
		List<OpendataDtoBase> openDataList = null;
		if (cls != null) {
			openDataList = (List<OpendataDtoBase>) convertJsonToPojo(entities, cls, true,false,true); 
		}
		return openDataList;
	}
	
}