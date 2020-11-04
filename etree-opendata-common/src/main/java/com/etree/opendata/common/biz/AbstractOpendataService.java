/**
* Copyright © 2016 etree Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2016-01-15 
*/
package com.etree.opendata.common.biz;

import java.util.Map;

import com.etree.commons.core.AbstractBaseService;
import com.etree.opendata.common.dao.OpendataDao;

public abstract class AbstractOpendataService  extends AbstractBaseService implements OpendataService {

	protected OpendataDao opendataDao;

	public OpendataDao getOpenDataDao() {
		return opendataDao;
	}

	public void setOpenDataDao(OpendataDao openDataDao) {
		this.opendataDao = openDataDao;
	}

	public Map<String, Object> getUtilityConfig(String keyName) {
		Map<String, Object> prop = (Map) configParams.get(keyName.toLowerCase());
		return prop;
		
	}
}
