package com.core.utils;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDatabaseConnection {

	ResultSet executeStatment(String sqlcommand) throws Exception;
	
	void closeConnection() throws Exception;

	String getDbname();

	<T> T getResultsetAsJson(String sqlcommand,Class<T> clazz) throws Exception;
}
