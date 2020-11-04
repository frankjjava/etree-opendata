package com.etree.opendata.common.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.etree.commons.dao.AbstractDaoImpl;
import com.etree.opendata.common.OpendataConstants;

public abstract class AbstractOpendataDao extends AbstractDaoImpl implements OpendataDao {
	
	public ResultSetExtractor<JSONArray> createResultSetExtractor(Map<String, Object> entityConfig) {
		return new ResultSetExtractor<JSONArray>() {
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException, DataAccessException {
				JSONArray jsonArray = new JSONArray();
				JSONObject rootJsonObject = new JSONObject();
				try {
					ResultSetMetaData rsMetaData = rs.getMetaData();
					Map<String, String> propInfo = (Map<String, String>) entityConfig.get(OpendataConstants.PROP_INFO);
					while (rs.next()) {
						JSONObject jsonObject = new JSONObject();
						for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
							String columnName = rsMetaData.getColumnName(i + 1);
							if (propInfo != null) {
								columnName = propInfo.get(columnName);
							}
							String columnValue = rs.getString(columnName);
							if (columnValue != null) {
								jsonObject.put(columnName, columnValue);
							}
						}
						jsonArray.put(jsonObject);
					}
				} catch (SQLException ex) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("result", "failure");
					jsonObject.put("error", ex.getMessage());
					jsonArray.put(jsonObject);
				}
//				if (jsonArray.length() > 0) {
//					rootJsonObject.put("", jsonArray);
//					return rootJsonObject;
//				}
				return jsonArray;
			}
		};
	}
}
