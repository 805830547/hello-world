package com.sixstar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ConnectionPool {
    private PoolConfig config;
    private int connCount = 0;
    private boolean isActive = false;
   
    private Vector<Connection> freeConn = new Vector<Connection>();
    private Vector<Connection> usedConn = new Vector<Connection>();
    
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    
    public ConnectionPool(PoolConfig config) {
        this.config = config;
        init();
    }
    /** 初始化方法  **/
    private void init() {
        try {
            Class.forName(config.getDriverName());
            for (int i = 0; i< config.getInitConnection(); i++) {
                Connection conn = getNewConnection();
                freeConn.add(conn);
                connCount++;
            }
            isActive = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** 获取新Connection **/
    private synchronized Connection getNewConnection() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        return conn;
    }
    /** 获取当前线程中连接对象 **/
    public Connection getCurrentConnection() {
        Connection conn = null;
        conn = threadLocal.get();
        if (!isEnable(conn)) {
            conn = getConnection();
        }
        return conn;
    }
    /** 提供外部从当前连接池中获取Connection **/
    public synchronized Connection getConnection(){
        Connection conn = null;
        try {
            if (connCount < config.getMaxActiveConnection()) {
                if (freeConn.size() > 0) {
                    conn = freeConn.get(0);
                    threadLocal.set(conn);
                    freeConn.remove(0);
                } else {
                    conn = getNewConnection();
                    connCount++;
                }
            } else {
                wait(config.getWaitTime());
                conn = getConnection();
            }

            if (isEnable(conn)) {
                usedConn.add(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    /** 释放连接，还回Connection对象 **/
    public synchronized void releaseConnection(Connection conn) {
        if (isEnable(conn)) {
            if (freeConn.size() < config.getMaxConnection()) {
                freeConn.add(conn);
            } else {
                try {
                    conn.close();
                    connCount--;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            usedConn.remove(conn);
            threadLocal.remove();
            notifyAll();
        }
    }
    
    public synchronized void destroy(){
        /** 关闭空闲集合中的Connection **/
        for (Connection conn : freeConn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /** 关闭已经使用集合中的Connection **/
        for (Connection conn : usedConn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        isActive = false;
        connCount = 0;
    }
    
    /** 判断当前连接对象是否有效 **/
    private boolean isEnable(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public void checkPool() {
        if (config.isCheck()) {
            new Timer().schedule(new TimerTask() {
                
                @Override
                public void run() {
                    System.out.println("ConnectionPoll中空闲连接： " + freeConn.size() + "个， 已经使用的连接： " + usedConn.size() + "个");
                }
            }, 1000, config.getCheckPeriod());
        }
    }
}
