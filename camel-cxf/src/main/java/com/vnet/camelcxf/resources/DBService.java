package com.vnet.camelcxf.resources;

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

class DBService {
    final private Log logger = LogFactory.getLog(init());

    private String url;
    private String username;
    private String password;

    static private final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    static public Class init() {
        try {
            Class.forName(MYSQL_DRIVER_CLASS_NAME).newInstance();
            return DBService.class;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(MYSQL_DRIVER_CLASS_NAME, e);
        }

    }

    private void setConnectionParams() {
        url = Utils.getenv("DEMO_DB_URL");
        username = Utils.getenv("DEMO_DB_USERNAME");
        password = Utils.getenv("DEMO_DB_PASSWORD");
    }

    DBService() {
        setConnectionParams();
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

    private static void cleanUp(Statement statement) {
        try {if (statement != null) statement.close();}
        catch (SQLException ignore) {}
    }

    private static void cleanUp(Connection connection) {
        try {if (connection != null) connection.close();}
        catch (SQLException ignore) {}
    }

}
