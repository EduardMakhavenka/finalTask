package by.makhavenka.task.poolconnection;

import java.util.ResourceBundle;

/**
 * initialization of variables for the database
 */
class DbConfig {

    private static final String RESOURCE_PATH="databaseParameters";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_POOL_SIZE = "db.pool.size";

    static {
        ResourceBundle dbProperties=ResourceBundle.getBundle(RESOURCE_PATH);
        dbUrl=dbProperties.getString(DB_URL);
        dbUser=dbProperties.getString(DB_USER);
        dbPassword=dbProperties.getString(DB_PASSWORD);
        dbPoolSize=Integer.parseInt(dbProperties.getString(DB_POOL_SIZE));
    }
    static String dbUrl;
    static String dbUser;
    static String dbPassword;
    static int dbPoolSize;

}
