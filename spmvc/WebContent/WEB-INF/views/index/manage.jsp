<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String webContent = request.getScheme() + ":" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
   System.out.println(webContent);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理页面</title>
<link rel="stylesheet" href="/spmvc/manage/css/jquery.dataTables.css" />
<link rel = "stylesheet" href="/spmvc/manage/css/dataTables.bootstrap.css">
<link rel = "stylesheet" href="/spmvc/manage/css/bootstrap.css">
<link rel="stylesheet" href="/spmvc/manage/css/base/jquery.ui.all.css">
<link rel="stylesheet" href="/spmvc/manage/css/jquery-ui.css">
<link rel="stylesheet" href="/spmvc/manage/css/datepicker3.css">

<script language="javascript" type="text/javascript" src="/spmvc/manage/js/jquery-3.1.1.min.js"></script>
<script language="javascript" type="text/javascript" src = "/spmvc/manage/js/bootstrap.js"></script>
<script language="javascript" type="text/javascript" src="/spmvc/manage/js/jquery-ui.js"></script>
<script language="javascript" type="text/javascript" src="/spmvc/manage/js/jquery.dataTables.min.js"></script>
<script language="javascript" type="text/javascript" src = "/spmvc/manage/js/dataTables.bootstrap.js"></script>
<script language="javascript" type="text/javascript" src = "/spmvc/manage/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script language="javascript" type="text/javascript" src = "/spmvc/manage/js/test.js"></script>
<style type="text/css">
	.ui-state-error {
        padding: .3em; 
    }
	.displayed1{
        display: block;
	}
	.toolbar {
	    float:right;
	}
	.main-title {
	    font-size: 25px;
	    color: #4a290c;
	    font-weight: bold;
	    left: 3px;
	    bottom: 3px;
	}
    .searchTable {
	    padding: 7px;
	    border: 0;
	    box-shadow: 1px 1px 7px #dadada;
	    border-radius: 3px;
	    margin-bottom: 20px;
	    width: 80%;
	}
	.tbody1 {
	    text-align: center;
	    width: 10%;
	    background-color: rgba(221, 221, 221, 0.55);
	}
	.tbody2 {
	    width: 40%;
	}
	.tbody3 {
	    width: 99%;
	    margin: 2px;
	}
	.btn-28 {
	    height: 28px;
	    padding: 3px 8px;
	    border-radius: 3px;
	    outline: none;
	    font-size: 14px;
	    line-height: 1.428571429;
	        color: #333;
	    background-color: #fff;
	    border-color: #ccc;
	}
	.failure{
	    background: #FCE8E5;
	    border: 1px solid;
	    border-color: red;
	    height: 25px;
	}
	.ui-dialog-buttonpane button{
	    height: 28px;
	    padding: 0px 8px;
	    border-radius: 3px;
	    outline: none;
	    font-size: 14px;
	    line-height: 1.428571429;
	    color: #333;
	    background-color: #fff;
	    border-color: #ccc;
	    border-width: medium;
	}
</style>
</head>
<body>
<div class="navbar navbar-dark navbar-fixed-top bg-inverse" style="position: fixed;">
                <a href="#" class="navbar-brand">
                    <font>
                        <font>熙妍羽毛球学校后台管理</font>
                    </font>
                </a>
                <div class="navbar" style="float: right; right: 70px;">
                    <div class="nav navbar-nav float-xs-left">
                        <a href="#" class="nav-item nav-link">
                            <font>
                                <font>管理员</font>
                            </font> 
                        </a>
                        <a href="#" class="nav-item nav-link">
                            <font>
                                <font>：</font>
                            </font> 
                        </a>
                        <a href="#" class="nav-item nav-link">
                            <font>
                                <font>${pd.name}</font>
                            </font> 
                        </a>
                        <a href="#" class="nav-item nav-link">
                            <font>
                                <font>日期</font>
                            </font> 
                        </a>
                        <a href="#" class="nav-item nav-link">
                            <font>
                                <font>：</font>
                            </font> 
                        </a>
                        <a href="#" class="nav-item nav-link">
                            <font>
                                <font>2016.12.12</font>
                            </font> 
                        </a>
                         <a href="index.do" class="nav-item nav-link">
                            <font>
                                <font>退出</font>
                            </font> 
                        </a>
                    </div>
                </div>
        </div>
        <div class="container-fluid" style="margin-top: 70px;">
                    <div class="row"style="float: left;position: fixed;background; width: 16.666667%; height: 100%;">
                        <div class="col-sm-3 col-md-2 sidebar" style="width:100%;background-color: #f5f5f5; height: 100%;">
                            <ul class="nav nav-sidebar" >
                                <li class="active">
                                    <a href="manage.do">
                                        <font>
                                            <font>后台首页</font>
                                        </font>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <font>
                                            <font>赛事日程管理</font>
                                        </font>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <font>
                                            <font>校园新闻管理</font>
                                        </font>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" id="click1">
                                        <font>
                                            <font>教练资料管理</font>
                                        </font>
                                    </a>
                                </li>
                            </ul><br>
                            <ul class="nav nav-sidebar">
                                <li>
                                    <a href="#">
                                        <font>
                                            <font>学生信息管理</font>
                                        </font>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <font>
                                            <font>羽球咨询管理</font>
                                        </font>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <font>
                                            <font>联系方式管理</font>
                                        </font>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <font>
                                            <font>更多管理</font>
                                        </font>
                                    </a>
                                </li>
                            </ul>
                            
                        </div>
                    </div>
                    <div class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 main">
                    <div id="msg" style="display: none;" class="failure"></div>
                       <div class="row placeholders" style="border-bottom: 1px solid #af9b8b;">
                           <div class="clearfix">
                                <div class="main-title">Data List</div>
                           </div>
                        </div> 
                        <div class="searchTable">
                        <table id="table2" border="1" style="width:100%">
                            <tbody><tr style="width:100%">
                                <td class="tbody1">Name</td>
                                <td class="tbody2"><input type="textbox" id="inputname" class="tbody3"></td>
                                <td class="tbody1">Position</td>
                                 <td class="tbody2"><input type="textbox" id="inputposition" class="tbody3"></td>
                            </tr>
                            <tr>
                                <td class="tbody1"> Age</td>
                                <td class="tbody2"><input type="textbox" id="inputage" class="tbody3"></td>
                                <td class="tbody1">StratDate </td>
                                <td class="tbody2"><input type="textbox" id="inputstartdate" class="tbody3"></td>
                            </tr>
                            <tr>
                                <td class="tbody1">Salary</td>
                                <td class="tbody2"><input type="textbox" id="inputsalary" class="tbody3"></td>
                            </tr>
                        </tbody></table>
                        <div style="margin-top: 6px;">
                            <div style="text-align: right;">
                                <input type="button" id="btn_sawebservice" class="btn-28" value="Search">
                            </div>
                        </div>
                       </div>
                        <h2>Table</h2>
	                   <div id="searchtable" style="display:none;">
	                     <div class="table table-striped" id="table1">
                            <table id="sexample" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" style="width:100%; border:hidden;" border =1>
                                <thead>
                                    <tr>
                                        <th style="width: 15%;">Name</th>
                                        <th style="width: 25%;">Position</th>
                                        <th style="width: 20%;">Office</th>
                                        <th style="width: 10%;">Age</th>
                                        <th style="width: 15%;">StartDate</th>
                                        <th style="width: 15%;">Salary</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>Name</th>
                                        <th>Position</th>
                                        <th>Office</th>
                                        <th>Age</th>
                                        <th>StartDate</th>
                                        <th>Salary</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                   
                                </tbody>
                             </table>
                        </div>
	                   
	                   </div>
                    </div>
                </div>
<div id="rank_dialog_form" style="display:none;">
    <form>
    <fieldset>
        <div id="dialogErrorField" style="display: none;"></div>
        <label for="MAE Rank Code" style=" width: 100%;">Name</label>
        <input type="text" name="Name" id="Name" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="30"/>
        <span class="neededcomment" >*</span>
        <label style=" width: 100%;">Position</label> <!-- we don't know the logic that get the employee_type_code,so we write it like this. -->
        <input type="text" name="Position" id="Position" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="30"/>
        </select>&nbsp;<span class="neededcomment">*</span>
        <label for="rank_name" style=" width: 100%;">Office</label>
        <input type="text" name="Office" id="Office" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="250"/>
        <span class="neededcomment">*</span>
        <label for="rank_name_en" style=" width: 100%;">Age</label>
        <input type="text" name="Age" id="Age" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="250"/>
        <span class="neededcomment">*</span>
        <label for="Price" style=" width: 100%;">StartDate</label>
        <input type="text" name="StartDate" id="StartDate" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="53"/>
        <span class="neededcomment">*</span>
        <label for="Invalid Flag" style=" width: 100%;">Salary</label>
        <input type="text" name="Salary" id="Salary" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="53"/>
    </fieldset>
    </form>
</div>
<div id="update" style="display:none;">
    <form>
    <fieldset>
        <div id="dialogErrorField" style="display: none;"></div>
        <label for="MAE Rank Code" style=" width: 100%;">Name</label>
        <input type="text" name="Name" id="Name" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="30"  readonly ="readonly"/>
        <span class="neededcomment" >*</span>
        <label style=" width: 100%;">Position</label> <!-- we don't know the logic that get the employee_type_code,so we write it like this. -->
        <input type="text" name="Position" id="Position" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="30"/>
        </select>&nbsp;<span class="neededcomment">*</span>
        <label for="rank_name" style=" width: 100%;">Office</label>
        <input type="text" name="Office" id="Office" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="250"/>
        <span class="neededcomment">*</span>
        <label for="rank_name_en" style=" width: 100%;">Age</label>
        <input type="text" name="Age" id="Age" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="250"/>
        <span class="neededcomment">*</span>
        <label for="Price" style=" width: 100%;">StartDate</label>
        <input type="text" name="StartDate" id="StartDate" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="53"/>
        <span class="neededcomment">*</span>
        <label for="Invalid Flag" style=" width: 100%;">Salary</label>
        <input type="text" name="Salary" id="Salary" value="" class="ui-widget-content ui-corner-all" style="height: 30px;" maxlength="53"/>
    </fieldset>
    </form>
</div>      
</body>
</html>