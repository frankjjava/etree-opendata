/**
* Copyright © 2020 eTree Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2020-11-04 
*/
package com.etree.opendata.common.dao;

import java.util.Map;

import com.etree.opendata.common.dto.OpendataDto;

public interface OpendataDao {

	public String retrieveAsJsonString(OpendataDto opendataDto, Map<String, Object> entityConfig);
}
