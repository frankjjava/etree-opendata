/**
* Copyright © 2020 eTree Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2020-11-04 
*/
package etree.refdata.common.dao;

import java.util.Map;

import etree.refdata.common.dto.OpendataDto;

public interface RefdataDao {

	public String retrieveAsJsonString(OpendataDto opendataDto, Map<String, Object> entityConfig);
}
