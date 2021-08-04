package etree.refdata.common.client;

import etree.refdata.common.biz.RefdataService;
import lombok.Data;

@Data
public abstract class AbstractRefdataServiceClient implements RefdataServiceClient {

	protected static final String OPENDATA_JSON_GET_URL = "opendata.jsonrequest.get.url";
	
	protected RefdataService openDataService;

}