package com.kopo.oaiaGamLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class GameDB {
	
	   final static String DB_URL = "jdbc:oracle:thin:@db202107051757_medium?TNS_ADMIN=/OracleWallet";
	   final static String DB_USER = "admin";
	   final static String DB_PASSWORD = "Oaiagame2021";
	

	//게임 랭킹 조회
		public String rankData() throws SQLException {
			
	        Properties info = new Properties();     
	        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
	        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
	        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
	        OracleDataSource ods = new OracleDataSource();
	        ods.setURL(DB_URL);    
	        ods.setConnectionProperties(info);
			
			String resultString = "";
			try {
				// open
				OracleConnection connection = (OracleConnection) ods.getConnection();
				Statement statement = null;
				ResultSet resultset = null;

				// use
				String query = "SELECT * FROM ranking order by score desc";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String nickname = resultSet.getString("nickname");
					int score = resultSet.getInt("score");
					int index = resultSet.getRow();
						resultString = resultString + "<tr>";
						resultString = resultString + "<td>" + index + "</td><td>" + nickname + "</td><td>" + score + "</td>";
						resultString = resultString + "</tr>";
				}

				// close
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultString;
		}
		
		//게임 랭킹 조회
		public String rank123Data() throws SQLException {
			
		       Properties info = new Properties();     
		        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
		        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
		        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
		        OracleDataSource ods = new OracleDataSource();
		        ods.setURL(DB_URL);    
		        ods.setConnectionProperties(info);
			
			String resultString = "";
			try {
				// open
				OracleConnection connection = (OracleConnection) ods.getConnection();
				Statement statement = null;
				ResultSet resultset = null;

				// use
				String query = "select * from (select * from ranking order by score desc) where rownum <= 3";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String nickname = resultSet.getString("nickname");
					int score = resultSet.getInt("score");
					int index = resultSet.getRow();
						resultString = resultString + "<div class='ranking1'>";
						resultString = resultString + "<span>" + index+"<br>"+nickname + "<br> [ " + score + " ]</span>";
						resultString = resultString + "</div>";
				}

				// close
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultString;
		}


}
