/**
 * Copyright © 2016 Verteil Technologies Pvt. Ltd.
 *
 * @author  Franklin Joshua
 * @version 1.0
 * @since   2016-01-15 
 */
package com.etree.opendata.common.dto;

import java.util.List;
import java.util.Map;

public class OpendataServiceDto {

	private String entityName;
	private String entitiesKeyName;
	private List<String> keys;
	private Map<String, List<String>> criteria;
	boolean lookup;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(Object object) {
		this.entityName = (String) object;
	}

	public String getEntitiesKeyName() {
		return entitiesKeyName;
	}

	public void setEntitiesKeyName(String entitiesKeyName) {
		this.entitiesKeyName = entitiesKeyName;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> fields) {
		this.keys = fields;
	}

	public Map<String, List<String>> getCriteria() {
		return criteria;
	}

	public void setCriteria(Map<String, List<String>> criteria) {
		this.criteria = criteria;
	}

	public boolean isLookup() {
		return lookup;
	}

	public void setLookup(boolean lookup) {
		this.lookup = lookup;
	}
}
