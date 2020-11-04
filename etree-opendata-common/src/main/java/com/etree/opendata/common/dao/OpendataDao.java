/**
* Copyright © 2016 eTree Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2016-01-15 
*/
package com.etree.opendata.common.dao;

import java.util.Map;

import org.json.JSONArray;

import com.etree.opendata.common.dto.OpendataServiceDto;

public interface OpendataDao {

	public JSONArray retrieveAsJson(OpendataServiceDto opendataServiceDto, Map<String, Object> entityConfig);
}
