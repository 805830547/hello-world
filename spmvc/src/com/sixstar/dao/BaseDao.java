package com.sixstar.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sixstar.dto.DataDto;
import com.sixstar.jdbc.DBUtil;


public class BaseDao {
	
	// 定义个查询方法,返回一个对象集合
	public <T> List<T> executeQuery(String sql, Class<T> clazz, Object...params){
		List<T> results = new ArrayList<T>();
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pr.setObject(i + 1, params[i]);
				}
			}
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getMethods();
			Map<String, Method> methodsMap = new HashMap<String, Method>();
			for (Method m : methods) {
				methodsMap.put(m.getName(), m);
			}
			ResultSet  rs = pr.executeQuery();
			
			while(rs.next()) {
				T t = clazz.newInstance();
				for (int i = 0; i < fields.length; i++){
					String columName = fields[i].getName();
					String methodName = "set" + columName.substring(0, 1).toUpperCase() + columName.substring(1);
					String typeName = fields[i].getType().getSimpleName();
					Method method = methodsMap.get(methodName);
					if("int".equals(typeName)||"Integer".equals(typeName)){
						method.invoke(t, rs.getInt(columName));
					}else{
						method.invoke(t, rs.getObject(columName));
					}
				}
				results.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return results;
	}
	
	
	
	// 定义个查询方法,返回一个对象
	public <T>  T getObject(String sql, Class<T> clazz, Object...params){
		T t = null;
		Connection conn = DBUtil.getConnection();
		try {
			t = clazz.newInstance();
			PreparedStatement pr = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pr.setObject(i + 1, params[i]);
				}
			}
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getMethods();
			Map<String, Method> methodsMap = new HashMap<String, Method>();
			for (Method m : methods){
				methodsMap.put(m.getName(), m);
			}
			ResultSet  rs = pr.executeQuery();
			if(rs.next()) {
				for (int i = 0; i < fields.length; i++){
					String columName = fields[i].getName();
					String methodName = "set" +columName.substring(0,1).toUpperCase() + columName.substring(1);
					String typeName = fields[i].getType().getSimpleName();
					Method method = methodsMap.get(methodName);
					if("int".equals(typeName)||"Integer".equals(typeName)){
						method.invoke(t, rs.getInt(columName));
					}else{
						method.invoke(t, rs.getObject(columName));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return t;
	}
	
	
	//返回查询总数
	public int getCount(String sql,Object...params){
		Connection conn = DBUtil.getConnection();
		int result = 0;
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			if(params !=null){
				for(int i =0; i<params.length; i++){
					pr.setObject(i+1, params[i]);
				}
			}
			ResultSet rs = pr.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   DBUtil.closeConnection(conn);
		}
		
		return result;
	} 

	
	 // 返回查询列表，非对象
	public List<Map<String,Object>> executeQuery(String sql,Object...params){
		Connection conn = DBUtil.getConnection();
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			if(params !=null){
				for(int i = 0; i < params.length; i++){
					pr.setObject(i+1, params[i]);
				}
			}
			ResultSet rs = pr.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				Map<String, Object> row = new HashMap<String, Object>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++){
//					System.out.println(rsmd.getColumnName(i));
//					System.out.println(rs.getObject(i));
					row.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				results.add(row);
//				System.out.println(results.size());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   DBUtil.closeConnection(conn);
	    }
		return results;

		
	}
	
	
	//返回单个对象，非对象
	public Map<String,Object> getObject(String sql,Object...params){
		Map<String, Object> results = null;
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			if(params !=null){
				for(int i =0; i<params.length; i++){
					pr.setObject(i+1, params[i]);
				}
			}
			ResultSet rs = pr.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println(rsmd.getColumnCount());
			System.out.println(rsmd.getCatalogName(3));
			if(rs.next()){
				results = new HashMap<String, Object>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++){
					results.put(rsmd.getColumnName(i), rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   DBUtil.closeConnection(conn);
		}
		return results;
	}
	
	
	//执行update操作
	public int executeUpdate(String sql,Object...params){
		int result = 0;
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			if(params !=null){
				for(int i = 0; i < params.length; i++){
					pr.setObject(i+1, params[i]);
				}
			}
			result = pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   DBUtil.closeConnection(conn);
		}
		return result;

	} 
	
	
	//保存操作
	public int save(String sql,Object...params){
		int result = 0;
		Connection conn = DBUtil.getConnection(); 
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			if(params != null){
				for(int i = 0; i < params.length; i++){
					pr.setObject(i+1, params[i]);
				} 
			}
			result = pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			   DBUtil.closeConnection(conn);
		}
		return result;

	} 
	
	
	
	// 一个对象
	 public int save(String tableName,Object obj){
		  Connection conn = DBUtil.getConnection();
		  int results = -1;
		  try{
		   Class c= obj.getClass();
		   //insert into tableName() values()
		   StringBuffer strSqlBefore = new StringBuffer("insert into "+tableName+"(");
		   StringBuffer strSqlAfter = new StringBuffer(" values (");
		   //拿到这个类的所有属性数组
		   Field[] fields =  c.getDeclaredFields();
		   //获取public的方法
		   Method[] methods = c.getMethods();
		   Map<String,Method> methMap = new HashMap<String, Method>();
		   for(Method m : methods){
		    methMap.put(m.getName(), m);
		   }
		   List<Object> values = new ArrayList<Object>(); 
		   for(int i = 0 ; i < fields.length ; i++){
    		    Field  field = fields[i];
    		    //拿到当前属性的get方法
    		    Method method = methMap.get("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
    		    Object fieldValue = method.invoke(obj);
    		    //如果这个属性值为空的话。我们不需要这个属性值
    		    if(fieldValue == null||fieldValue.equals("")){
    		    	continue;
    		    }
    		    if(i>0){
        		     strSqlBefore.append(",");
        		     strSqlAfter.append(",");
    		    }
    		    //拼接字段名
    		    strSqlBefore.append(field.getName());
    		    values.add(fieldValue);
    		    //拼接值
    		    strSqlAfter.append("?");
		   }
		   //拼接字段名
		   strSqlBefore.append(")");
		   //拼接值
		   strSqlAfter.append(")");
		   System.out.println(strSqlBefore.toString() + strSqlAfter.toString());
		   PreparedStatement ps = conn.prepareStatement(strSqlBefore.toString() + strSqlAfter.toString());
		   for(int i = 0 ; i < values.size(); i++){
		    ps.setObject(i+1, values.get(i));
		   }
		   results = ps.executeUpdate();
		  }catch(Exception ex){
		   ex.printStackTrace();
		  }finally{
		   DBUtil.closeConnection(conn);
		  }
		  return results;
		 }
	
	
	 
	
	
	
	public static void main(String[] args) {
		BaseDao bd = new BaseDao();
		
		
		
	    DataDto bdto = new DataDto();
	    List<Map<String,Object>> results = bd.executeQuery("select Name, Position, Office, Age, StartDate, Salary "
												+ "from test_liu_data ");
	    System.out.println(results.size());
//		Iterator<Map<String, Object>> it = results.iterator();
//		while(it.hasNext()){
//			Map<String,Object> b= it.next();
//			System.out.println(b.get("Id"));
//		}
//	
//		
//		
//		Map<String,Object> map = bd.getObject("select id, logic_type, category_type, logic_type_name "
//				+ "from matr_logic_pattern_master "
//				+ "where category_type = ?", "SCategoryType06");
//		System.out.println(map.size());
//		System.out.println(map.get(""));
//		
//		
//		
//		
//		
//		
//		bdto.setId(200);
//		bdto.setCategory_type("123456");
//		bdto.setLogic_type("2222222");
//		bdto.setLogic_type_name("33333333333");
//		bdto.setAdmin_only_flag("444444444");
//		int result = bd.save("matr_logic_pattern_master", bdto);
//		System.out.println(result);
	}


}
