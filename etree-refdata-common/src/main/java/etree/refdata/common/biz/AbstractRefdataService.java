/**
* Copyright © 2020 eTree Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2020-11-04 
*/
package etree.refdata.common.biz;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import etree.refdata.common.dao.RefdataDao;
import lombok.Data;

@Data
public abstract class AbstractRefdataService implements RefdataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRefdataService.class);

	protected Map<Object, Object> configParams;

	protected RefdataDao opendataDao;

	public Map<String, Object> getUtilityConfig(String keyName) {
		if (configParams == null) {
			LOGGER.warn("Configuration data 'configParams' not available or set !!");
			return null;
		}
		Map<String, Object> prop = (Map) configParams.get(keyName.toLowerCase());
		return prop;
		
	}
}
