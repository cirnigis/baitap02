package doranie.com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionProduct {

    private final String serverName = "localhost";
    private final String dbName = "ServletCRUDMVC";
    private final String portNumber = "1433";
    private final String instance = "SQLEXPRESS02";

    private final String userID = "sa";
    private final String password = "khongnhomatkhau";

    public Connection getConnection() throws Exception {

        String url = "jdbc:sqlserver://"
                + serverName + ":"
                + portNumber
                + ";databaseName=" + dbName
                + ";encrypt=true;trustServerCertificate=true";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(url, userID, password);
    }
}