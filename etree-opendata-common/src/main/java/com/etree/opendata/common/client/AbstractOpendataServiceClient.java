package com.etree.opendata.common.client;

import com.etree.commons.core.web.client.AbstractRestConnector;
import com.etree.opendata.common.biz.OpendataService;

public abstract class AbstractOpendataServiceClient extends AbstractRestConnector implements OpendataServiceClient {

	protected static final String OPENDATA_JSON_GET_URL = "opendata.jsonrequest.get.url";
	
	protected OpendataService openDataService;

	public OpendataService getOpenDataService() {
		return openDataService;
	}

	public void setOpenDataService(OpendataService openDataService) {
		this.openDataService = openDataService;
	}

}