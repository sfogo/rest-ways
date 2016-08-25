package com.vnet.db;

import com.vnet.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SqlService {

    final private static String MYSQL_DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
    final private Log logger = LogFactory.getLog(SqlService.class);

    private String url;
    private String username;
    private String password;

    /**
     * Init from environment variables
     */
    public SqlService() {
        init(Utils.getenv("DEMO_DB_DRIVER_CLASSNAME", MYSQL_DRIVER_CLASSNAME));
        this.url = Utils.getenv("DEMO_DB_URL");
        this.username = Utils.getenv("DEMO_DB_USERNAME");
        this.password = Utils.getenv("DEMO_DB_PASSWORD");
    }

    /**
     * Init from constructor params
     * @param driverClassName driver classname
     * @param url url
     * @param username username
     * @param password password
     */
    public SqlService(String driverClassName, String url, String username, String password) {
        init(driverClassName);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private void init(String driverClassName) {
        try {
            Class.forName(driverClassName).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(driverClassName, e);
        }
    }

    private Connection getConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "ISO-8859-1");
        return DriverManager.getConnection(url, properties);
    }


    public List<Properties> query(String sql) throws SQLException {
        logger.info(sql);
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(sql);
            final ResultSetMetaData metaData = resultSet.getMetaData();
            final List<Properties> items = new LinkedList<>();
            while (resultSet.next()) {
                final Properties item = new Properties();
                for (int col=1; col<=metaData.getColumnCount(); col++) {
                    item.setProperty(metaData.getColumnName(col),resultSet.getString(col));
                }
                items.add(item);
            }
            return items;
        } finally {
            cleanUp(statement);
            cleanUp(connection);
        }
    }

    public int update(String sql) throws SQLException {
        logger.info(sql);
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } finally {
            cleanUp(statement);
            cleanUp(connection);
        }
    }

    private static void cleanUp(Statement statement) {
        try {if (statement != null) statement.close();}
        catch (SQLException ignore) {}
    }

    private static void cleanUp(Connection connection) {
        try {if (connection != null) connection.close();}
        catch (SQLException ignore) {}
    }

}
