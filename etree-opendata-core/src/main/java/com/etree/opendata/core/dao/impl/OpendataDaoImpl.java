/**
 * Copyright © 2016 etree Technologies Pvt. Ltd.
 *
 * @author  Franklin Joshua
 * @version 1.0
 * @since   2016-01-15 
 */
package com.etree.opendata.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etree.opendata.common.OpendataConstants;
import com.etree.opendata.common.dao.AbstractOpendataDao;
import com.etree.opendata.common.dto.OpendataServiceDto;
import com.etree.opendata.common.exception.OpendataException;

public class OpendataDaoImpl extends AbstractOpendataDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpendataDaoImpl.class);

	public JSONArray retrieveAsJson(OpendataServiceDto utilServiceDto, Map<String, Object> entityConfig) {
		String sql = buildQuery(utilServiceDto, entityConfig);
		JSONArray jsonArray = executeQuery(sql, createResultSetExtractor(entityConfig));
		return jsonArray;
	}

	private String buildQuery(OpendataServiceDto opendataServiceDto, Map<String, Object> entityConfig) {
		List<String> keys = null;
		if (opendataServiceDto.getKeys() != null) {
			keys = opendataServiceDto.getKeys();
		}
		if (keys == null) {
			throw new OpendataException("", "Error! Unavailable Field information.");
		}
		// -- build select query
		StringBuilder sql = null;
		for (String key : keys) {
			if (sql == null) {
				sql = new StringBuilder("Select distinct ");
			} else {
				sql.append(",");
			}
			sql.append(entityConfig.get(key)).append(" as ").append(key);
		}
		// -- build from clause
		sql.append(" from ");
		sql.append(entityConfig.get(OpendataConstants.KEY_ENTITIES_NAME));
		// -- build where clause
		if (opendataServiceDto.getCriteria() != null && !opendataServiceDto.getCriteria().isEmpty()) {
			sql.append(" where ");
			StringBuilder whereClause = null;
			for (Entry<String, List<String>> entry : opendataServiceDto.getCriteria().entrySet()) {
				if (whereClause == null) {
					whereClause = new StringBuilder();
				} else {
					whereClause.append(" AND ");
				}
				String likeClause = buildLikeClause(entityConfig, (String) entityConfig.get(entry.getKey()), entry.getValue());
				if (likeClause != null) {
					whereClause.append(likeClause);
				} else {
					whereClause.append(entityConfig.get(entry.getKey()));
					whereClause.append(buildInClause(entry.getValue()));
				}
			}
			sql.append(whereClause);
		}
		return sql.toString();
	}

	private String buildLikeClause(Map<String, Object> entityConfig, String columnName, List<String> values) {
		StringBuilder key = null;
		String likeClause = null;
		if (!entityConfig.containsKey(OpendataConstants.DAO_INFO)) {
			return null;
		}
		ArrayList<Map> daoInfos = (ArrayList) entityConfig.get(OpendataConstants.DAO_INFO);
		if (daoInfos == null || daoInfos.isEmpty()) {
			return null;
		}
		for (Map<String, String> daoInfoMap : daoInfos) {
			if (daoInfoMap.containsKey(OpendataConstants.CLAUSE)
					&& daoInfoMap.containsKey(OpendataConstants.CRITERIA)
					&& daoInfoMap.get(OpendataConstants.KEY).equalsIgnoreCase(columnName)) {
				String criteria = daoInfoMap.get(OpendataConstants.CRITERIA);
				String logicalOperator = daoInfoMap.get(OpendataConstants.LOGICAL_OPERATOR);
				if (OpendataConstants.LIKE.equalsIgnoreCase(criteria)) {
					for (String value : values) {
						if (key == null) {
							key = new StringBuilder();
						} else {
							if (logicalOperator != null) {
								key.append(logicalOperator);
							} else {
								key.append(" or ");
							}
						}
						key.append(columnName).append(" like '%").append(value).append("%' ");
					}
				}
			}
		}
		if (key != null) {
			likeClause = key.toString();
		}
		return likeClause;
	}
}
