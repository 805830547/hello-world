package com.sixstar.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBUtil {
    private static PoolConfig config = new PoolConfig();
    
    static {
        Properties properties = new Properties();
        try {
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            config.setDriverName(properties.getProperty("jdbc.driverName"));
            config.setUrl(properties.getProperty("jdbc.url"));
            config.setUser(properties.getProperty("jdbc.user"));
            config.setPassword(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static ConnectionPool connPool = new ConnectionPool(config);
    
    public static Connection getConnection() {
        Connection conn = null;
        conn = connPool.getConnection();
        return conn;
    }

    public static Connection getCurrentConnection(){
        Connection conn = null;
        conn = connPool.getCurrentConnection();
        return conn;
    }

    public static void closeConnection(Connection conn) {
        connPool.releaseConnection(conn);
    }
    
    public static void main(String[] args) {
		DBUtil db= new DBUtil();
		System.out.println(db.getConnection());
	}
}

