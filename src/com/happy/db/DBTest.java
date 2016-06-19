package com.happy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Created by Phoebe on 15/12/29.
		 */
		String className = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/happy";
		String username = "root";
		String userpass = "123456";
		String tableName = "ksc_info";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url, username, userpass);
			pstmt = con.prepareStatement("select*from " + tableName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> result = getDataByResultSet(rs);
				Gson gson = new Gson();
				System.out.println(gson.toJson(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {

			}
			try {
				pstmt.close();
			} catch (Exception e) {

			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据ResultSet获取数据库返回的数据
	 * 
	 * @param rs
	 * @return
	 */
	private static Map<String, Object> getDataByResultSet(ResultSet rs) {
		Map<String, Object> result = null;
		ResultSetMetaData m;
		try {
			result = new HashMap<String, Object>();
			m = rs.getMetaData();
			int columns = m.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				String key = m.getColumnName(i);
				if (m.getColumnType(i) == Types.INTEGER) {
					result.put(key, rs.getInt(i));
				} else if (m.getColumnType(i) == Types.FLOAT) {
					result.put(key, rs.getFloat(i));
				} else {
					result.put(key, rs.getString(i));
				}
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
