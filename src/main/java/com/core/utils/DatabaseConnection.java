package com.core.utils;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class DatabaseConnection implements IDatabaseConnection{

    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/jdbc";
    private String username = "root";
    private String password = "localhost";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

	@Override
	public ResultSet executeStatment(String sqlcommand) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;
        if(!sqlcommand.isEmpty()){
         resultSet = statement.executeQuery(sqlcommand);
        }
        return resultSet;
	}

	@Override
	public void closeConnection() throws Exception {
		if(!connection.isClosed()){
		    connection.close();
        }
	}

	@Override
	public String getDbname() {
		return null;
	}

    @Override
    public <T> T getResultsetAsJson(String sqlcomand, Class<T> clazz) throws Exception {
        JSONArray json = new JSONArray();
        ResultSet rs= executeStatment(sqlcomand);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        while(rs.next()) {
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));
            }
            json.add(obj);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json.toString(), clazz);
    }
}