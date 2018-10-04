package by.makhavenka.task.poolconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectorDB {
     static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DbConfig.dbUrl,DbConfig.dbUser,DbConfig.dbPassword);
    }
}
