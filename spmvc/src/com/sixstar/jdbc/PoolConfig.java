package com.sixstar.jdbc;

public class PoolConfig {
    private String driverName;
    
    private String url;
    
    private String user;
    
    private String password;
    
    /** 连接池实体的基本信息 **/
    private int minConnection = 5; //空闲Connection最小数目
    private int maxConnection = 20; // 空闲的Connection最大数目 [5, 20]
    private int initConnection = 6; // 定义一个初始化时 生成的Connection个数
    private int maxActiveConnection = 100;
    // 线程等待时间
    private long waitTime = 1000;
    // 是否允许检查当前连接池状态
    private boolean isCheck = true;
    // check时间间隔
    private long checkPeriod = 1000*60*30;
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getMinConnection() {
        return minConnection;
    }
    public void setMinConnection(int minConnection) {
        this.minConnection = minConnection;
    }
    public int getMaxConnection() {
        return maxConnection;
    }
    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }
    public int getInitConnection() {
        return initConnection;
    }
    public void setInitConnection(int initConnection) {
        this.initConnection = initConnection;
    }
    public int getMaxActiveConnection() {
        return maxActiveConnection;
    }
    public void setMaxActiveConnection(int maxActiveConnection) {
        this.maxActiveConnection = maxActiveConnection;
    }
    public long getWaitTime() {
        return waitTime;
    }
    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }
    public boolean isCheck() {
        return isCheck;
    }
    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
    public long getCheckPeriod() {
        return checkPeriod;
    }
    public void setCheckPeriod(long checkPeriod) {
        this.checkPeriod = checkPeriod;
    }
}


