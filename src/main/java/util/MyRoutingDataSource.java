package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource {
	private static int numberDB = 1;

	@Override
	protected Object determineCurrentLookupKey() {
		numberDB = order(numberDB);

		return numberDB;
	}


	private int order(int numberDB) {
		int activeDB = 0;
		switch (numberDB) {
		case 1:
			activeDB = getDB(1);
			if(activeDB == 0){
				activeDB = getDB(2);
				if (activeDB == 0) {
					activeDB = getDB(3);
				}
			}
			break;
		case 2:
			activeDB = getDB(2);
			if(activeDB == 0){
				activeDB = getDB(3);
				if (activeDB == 0) {
					activeDB = getDB(1);
				}
			}
			break;
		case 3:
			activeDB = getDB(3);
			if(activeDB == 0){
				activeDB = getDB(2);
				if (activeDB == 0) {
					activeDB = getDB(1);
				}
			}
			break;
		}
		
		return activeDB;
	}

	private int getDB(int numberDB) {
		
		switch (numberDB) {
		case 1:
			try {
				Connection conn = DriverManager.getConnection(Constants.DB1, Constants.DB_USERNAME, Constants.DB_PASSWORD);
				logger.info("Database 1 is up!");
				return 1;
			} catch (SQLException e) {
				logger.error("Database 1 is down.");
			}
			break;
			
		case 2:
			try {
				Connection conn = DriverManager.getConnection(Constants.DB2, Constants.DB_USERNAME, Constants.DB_PASSWORD);
				logger.info("Database 2 is up!");
				return 2;
			} catch (SQLException e) {
				logger.error("Database 2 is down.");
			}
			break;
			
		case 3:
			try {
				Connection conn = DriverManager.getConnection(Constants.DB3, Constants.DB_USERNAME, Constants.DB_PASSWORD);
				logger.info("Database 3 is up!");
				return 3;
			} catch (SQLException e) {
				logger.error("Database 3 is down.");
			}
			break;
		}
		return 0;
	}
}
