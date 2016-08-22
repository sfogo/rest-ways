package com.vnet.camelrest.service;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBService {

    final private Log logger = LogFactory.getLog(init());

    private String url;
    private String username;
    private String password;

    public void setUrl(String url) {this.url = Utils.property(url);}
    public void setUsername(String username) {this.username = Utils.property(username);}
    public void setPassword(String password) {this.password = Utils.property(password);}

    static private final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    static private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static public Class init() {
        try {
            Class.forName(MYSQL_DRIVER_CLASS_NAME).newInstance();
            return DBService.class;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(MYSQL_DRIVER_CLASS_NAME, e);
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

    static String formatMySqlTimestamp(Date date) {
        return simpleDateFormat.format(date);
    }

    static Date parse(String s) {
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return null;
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
