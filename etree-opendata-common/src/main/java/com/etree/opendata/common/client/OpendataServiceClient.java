/**
 * Copyright © 2016 Verteil Technologies Pvt. Ltd.
 *
 * @author  Franklin Joshua
 * @version 1.0
 * @since   2016-01-15 
 */
package com.etree.opendata.common.client;

import java.util.List;
import java.util.Map;

import com.etree.commons.core.BaseService;

public interface OpendataServiceClient extends BaseService {

	public Object fetchData(Map<String, List<String>> criteria, String entitiesKeyName, String ... arrKeys);
}
