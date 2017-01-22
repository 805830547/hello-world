package com.sixstar.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sixstar.dao.BaseDao;
import com.sixstar.dto.DataDto;

public class DataService {
	BaseDao baseDao = new BaseDao();
	
	public List<JSONObject> DataSelect(DataDto dataDto){
		List<JSONObject> listJson = new ArrayList<JSONObject>();
		if(dataDto.getName() == null || "".equals(dataDto.getName())){
			if(dataDto.getPosition() == null || "".equals(dataDto.getPosition())){
				if(dataDto.getAge() == null || "".equals(dataDto.getAge())){
					if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
						if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
							String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data group by currentTime desc";
							List<Map<String,Object>> list = baseDao.executeQuery(sql);
							Iterator<Map<String,Object>> it = list.iterator();
							while(it.hasNext()){
								Map<String,Object> map = it.next();
								JSONObject json = new JSONObject(map);
								listJson.add(json);
							}
						}else{
							String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Salary= ? group by currentTime desc";
							List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getSalary());
							Iterator<Map<String,Object>> it = list.iterator();
							while(it.hasNext()){
								Map<String,Object> map = it.next();
								JSONObject json = new JSONObject(map);
								listJson.add(json);
							}
						}
					}else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
								String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where StartDate= ? group by currentTime desc";
								List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getStartDate());
								Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
						  }else{
							    String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where StartDate= ? and Salary= ? group by currentTime desc";
							    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getStartDate(),dataDto.getSalary());
							    Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
						  }
				}else if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
							if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
								String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Age = ? group by currentTime desc";
								 List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getAge());
								 Iterator<Map<String,Object>> it = list.iterator();
									while(it.hasNext()){
										Map<String,Object> map = it.next();
										JSONObject json = new JSONObject(map);
										listJson.add(json);
									}
							}else{
								String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Age = ? and Salary = ? group by currentTime desc";
								 List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getAge(),dataDto.getSalary());
								 Iterator<Map<String,Object>> it = list.iterator();
									while(it.hasNext()){
										Map<String,Object> map = it.next();
										JSONObject json = new JSONObject(map);
										listJson.add(json);
									}
							}
					  }else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
						  		String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Age = ? and Startdate = ? group by currentTime desc";
						  		List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getAge(),dataDto.getStartDate());
						  		Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
					  		}else{
					  			String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Age = ? and Startdate = ? and Salary = ? group by currentTime desc";
					  			List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getAge(),dataDto.getStartDate(),dataDto.getSalary());
					  			Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
					  		}
			}else if(dataDto.getAge() == null || "".equals(dataDto.getAge())){
    					if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
    						if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
    							String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? group by currentTime desc";
    							List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition());
    							Iterator<Map<String,Object>> it = list.iterator();
    							while(it.hasNext()){
    								Map<String,Object> map = it.next();
    								JSONObject json = new JSONObject(map);
    								listJson.add(json);
    							}
    						}else{
    							String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Salary = ? group by currentTime desc";
    							List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getSalary());
    							Iterator<Map<String,Object>> it = list.iterator();
    							while(it.hasNext()){
    								Map<String,Object> map = it.next();
    								JSONObject json = new JSONObject(map);
    								listJson.add(json);
    							}
    						}
    					}else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
    								String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Startdate = ? group by currentTime desc";
    								List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getStartDate());
    								Iterator<Map<String,Object>> it = list.iterator();
    								while(it.hasNext()){
    									Map<String,Object> map = it.next();
    									JSONObject json = new JSONObject(map);
    									listJson.add(json);
    								}
    						  }else{
    							    String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Startdate = ? and Salary = ? group by currentTime desc";
    							    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getStartDate(),dataDto.getSalary());
    							    Iterator<Map<String,Object>> it = list.iterator();
    								while(it.hasNext()){
    									Map<String,Object> map = it.next();
    									JSONObject json = new JSONObject(map);
    									listJson.add(json);
    								}
    						  }
				  }else if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
					  		if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
							    String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Age = ? group by currentTime desc";
							    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getAge());
							    Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
					  		}else{
					  			String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Age = ? and Salary = ? group by currentTime desc";
					  			 List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getAge(),dataDto.getSalary());
					  			Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
					  		}
				  		}else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
        				  			String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Age = ? and Startdate = ? group by currentTime desc";
        				  			List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getAge(),dataDto.getStartDate());
        				  			Iterator<Map<String,Object>> it = list.iterator();
        							while(it.hasNext()){
        								Map<String,Object> map = it.next();
        								JSONObject json = new JSONObject(map);
        								listJson.add(json);
        							}
				  			 }else{
				  				String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Position = ? and Age = ? and Startdate = ? and Salary = ? group by currentTime desc";
				  				List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getPosition(),dataDto.getAge(),dataDto.getStartDate(),dataDto.getSalary());
				  				Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
				  			 }
		}else if(dataDto.getPosition() == null || "".equals(dataDto.getPosition())){
					if(dataDto.getAge() == null || "".equals(dataDto.getAge())){
						if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
							if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
								String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? group by currentTime desc";
								List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName());
								Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
							}else{
								String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Salary = ? group by currentTime desc";
								List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getSalary());
								Iterator<Map<String,Object>> it = list.iterator();
								while(it.hasNext()){
									Map<String,Object> map = it.next();
									JSONObject json = new JSONObject(map);
									listJson.add(json);
								}
							}
						}else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
        							String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Startdate = ? group by currentTime desc";
        							List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getStartDate());
        							Iterator<Map<String,Object>> it = list.iterator();
        							while(it.hasNext()){
        								Map<String,Object> map = it.next();
        								JSONObject json = new JSONObject(map);
        								listJson.add(json);
        							}
							  }else{
								  String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Startdate = ? and Salary = ? group by currentTime desc";
      							  List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getStartDate(),dataDto.getSalary());
      							Iterator<Map<String,Object>> it = list.iterator();
    							while(it.hasNext()){
    								Map<String,Object> map = it.next();
    								JSONObject json = new JSONObject(map);
    								listJson.add(json);
    							}
							  }
					}else if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
								if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
									String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Age = ? group by currentTime desc";
	      							List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getAge());
	      							Iterator<Map<String,Object>> it = list.iterator();
	    							while(it.hasNext()){
	    								Map<String,Object> map = it.next();
	    								JSONObject json = new JSONObject(map);
	    								listJson.add(json);
	    							}
								}else{
									String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Age = ? and Salary = ? group by currentTime desc";
	      							List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getAge(),dataDto.getSalary());
	      							Iterator<Map<String,Object>> it = list.iterator();
	    							while(it.hasNext()){
	    								Map<String,Object> map = it.next();
	    								JSONObject json = new JSONObject(map);
	    								listJson.add(json);
	    							}
								}
						  }else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
        							  String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Age = ? and Startdate = ? group by currentTime desc";
            						  List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getAge(),dataDto.getStartDate());
            						  Iterator<Map<String,Object>> it = list.iterator();
              							while(it.hasNext()){
              								Map<String,Object> map = it.next();
              								JSONObject json = new JSONObject(map);
              								listJson.add(json);
              							}
						  		}else{
						  			String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Age = ? and Startdate = ? and Salary = ? group by currentTime desc";
          						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getAge(),dataDto.getStartDate(),dataDto.getSalary());
              						  Iterator<Map<String,Object>> it = list.iterator();
          							while(it.hasNext()){
          								Map<String,Object> map = it.next();
          								JSONObject json = new JSONObject(map);
          								listJson.add(json);
          							}
						  		}
			  }else if(dataDto.getAge() == null || "".equals(dataDto.getAge())){
				  		if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
				  			if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
				  				String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? group by currentTime desc";
      						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition());
          						  Iterator<Map<String,Object>> it = list.iterator();
      							while(it.hasNext()){
      								Map<String,Object> map = it.next();
      								JSONObject json = new JSONObject(map);
      								listJson.add(json);
      							}
				  			}else{
				  				String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Salary =? group by currentTime desc";
      						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getSalary());
          						  Iterator<Map<String,Object>> it = list.iterator();
      							while(it.hasNext()){
      								Map<String,Object> map = it.next();
      								JSONObject json = new JSONObject(map);
      								listJson.add(json);
      							}
				  			}
				  		}else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
        				  			String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Startdate =? group by currentTime desc";
          						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getStartDate());
              						  Iterator<Map<String,Object>> it = list.iterator();
          							while(it.hasNext()){
          								Map<String,Object> map = it.next();
          								JSONObject json = new JSONObject(map);
          								listJson.add(json);
          							}
				  			  }else{
				  				String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Startdate =? and Salary =? group by currentTime desc";
      						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getStartDate(),dataDto.getSalary());
          						  Iterator<Map<String,Object>> it = list.iterator();
      							while(it.hasNext()){
      								Map<String,Object> map = it.next();
      								JSONObject json = new JSONObject(map);
      								listJson.add(json);
      							}
				  			  }
			  		}else if(dataDto.getStartDate() == null || "".equals(dataDto.getStartDate())){
			  					if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
			  						String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Age =? group by currentTime desc";
	      						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getAge());
    	      						  Iterator<Map<String,Object>> it = list.iterator();
    	  							while(it.hasNext()){
    	  								Map<String,Object> map = it.next();
    	  								JSONObject json = new JSONObject(map);
    	  								listJson.add(json);
    	  							}
			  					}else{
			  						String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Age =? and Salary =? group by currentTime desc";
	      						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getAge(),dataDto.getSalary());
    	      						  Iterator<Map<String,Object>> it = list.iterator();
    	  							while(it.hasNext()){
    	  								Map<String,Object> map = it.next();
    	  								JSONObject json = new JSONObject(map);
    	  								listJson.add(json);
    	  							}
			  					}
			  			  }else if(dataDto.getSalary() == null || "".equals(dataDto.getSalary())){
        			  				String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Age =? and Startdate =? group by currentTime desc";
          						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getAge(),dataDto.getStartDate());
              						  Iterator<Map<String,Object>> it = list.iterator();
          							while(it.hasNext()){
          								Map<String,Object> map = it.next();
          								JSONObject json = new JSONObject(map);
          								listJson.add(json);
          							}
			  			  		}else{
			  			  			System.out.println("e");
    			  			  		String sql = "select Name, Position, Office, Age, StartDate, Salary, currentTime from test_liu_data where Name = ? and Position = ? and Age =? and Startdate =? and Salary =? group by currentTime desc";
          						    List<Map<String,Object>> list = baseDao.executeQuery(sql,dataDto.getName(),dataDto.getPosition(),dataDto.getAge(),dataDto.getStartDate(),dataDto.getSalary());
          						    Iterator<Map<String,Object>> it = list.iterator();
          							while(it.hasNext()){
          								Map<String,Object> map = it.next();
          								JSONObject json = new JSONObject(map);
          								listJson.add(json);
          							}
			  			  		}
		
		
		
		return listJson;
		
	}
	
	
	public int dataInsert(DataDto dataDto){
		int result = 0;
		result = baseDao.save("test_liu_data", dataDto);
		return result;
	}
	
	
	public int dataUpdate(DataDto dataDto){
		int result = 0;
		String sql = "UPDATE test_liu_data SET Position = ?, Office= ?, Age = ?, StartDate = ?, Salary = ?, currentTime = ? WHERE  Name = ?";
		result = baseDao.executeUpdate(sql, dataDto.getPosition(), dataDto.getOffice(), dataDto.getAge(), dataDto.getStartDate(), dataDto.getSalary(), dataDto.getCurrentTime(), dataDto.getName());
		return result;
	}
	
	
	public int dataDelete(DataDto dataDto){
		int result = 0;
		String sql = "DELETE FROM test_liu_data WHERE Name= ?";
		result = baseDao.executeUpdate(sql, dataDto.getName());
		return result;
		
	}
	
	
	
	public static void main(String[] args) {
		
//		System.out.println(df.format(day));
//		DataService d = new DataService();
//		System.out.println(d.DataSelect());
//		('liuyuhao', 'liu', 'ccccccc', '112', '2016-12-19', '23626345');
//		DataDto dataDto = new DataDto();
//		dataDto.setName("liyh");
//		dataDto.setPosition("dddddddddd");
//		dataDto.setOffice("ccccas");
//		dataDto.setAge("12");
//		dataDto.setStartDate("2014-12-19");
//		dataDto.setSalary("93626345");
//		System.out.println(d.dataInsert(dataDto));
		String sql ="select * from a where 1 = 1 ";
	}
}
