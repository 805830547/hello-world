<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String webContent = request.getScheme() + ":" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
   System.out.println(webContent);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<link rel="stylesheet" href="/spmvc/manage/css/bootstrap.css">
<link rel = "stylesheet" href="/spmvc/manage/css/login.css" type="text/css">
<script type="text/javascript" src="/spmvc/manage/js/jquery-3.1.1.min.js"></script>

<style type="text/css">
    .form-signin {
	    max-width: 330px;
	    padding: 15px;
	    margin: 0 auto;
    }
    .container{
        margin-top: 190px;
    }
    .code{
        background-image: url(/spmvc/manage/123.jpg);
        font-style: italic;
        font-size: x-large;
        color: Red;
        border:0;
        padding: 2px 3px;
        letter-spacing: 3px;
        font-weight: bolder;
        height: 38px;
        text-align: center;
        float: left;
    }
    .unchanged{
        border: 0;
    }
    .Button2{
        color: #fff;
        background-color: #0275d8;
        border-color: #0275d8;
        line-height: 1.25;
        border: 1px solid transparent;
    }
    #information{
        text-align: right;
        color: Red;
        font-size: larger;
        float: right;
        width: 100%;
        height: 32px; 
    }
</style>
<script type="text/javascript" language="javascript">
	function updateTips( t ) {
	    $("#information").html(t);
	}

	function checkLength( o, n, min, max ) {
	    if ( o.length > max || o.length < min ) {
	      updateTips(n + "长度必须为3到16位!" );
	      return false;
	    } else {
	      return true;
	    }
	}
	  
	function checkValidateCode(o,n){
		var result = false;
		if(o.length<=0){
			 updateTips(n + "不能为空!" );
	    }else{
	         $.ajax({
	             type : "POST",
	             url : "checkValidateCode.do",
	             data: {"ValidateCode" : o},
	             dataType:"json",
	             async: false,
	             success: function(data){
	                 if (data == 1) {
	                     $("#information").html("");
	                    result = true; 
	                 } else{
	                     $("#vcode").attr('src',"getValidateCode.do" + "?code=" + Math.random());
	                     updateTips(n + "错误" );
	                 }
	             }
	        });
	    }
	    return result;
	}
	  
	function check(){
		  var valid = true;
	      valid = valid && checkLength( $("#inputUsername").val(), "用户名", 3, 16 );
	      valid = valid && checkLength( $("#inputPassword").val(), "密码", 6, 16 );
	      valid = valid && checkValidateCode($("#input1").val(),"验证码");
	      if(valid){
	    	  $.ajax({
	    		  type : "POST",
	    		  url : "select.do",
	    		  data: {"username" : $("#inputUsername").val(),
	    			     "password" : $("#inputPassword").val()},
	              dataType : "json",
	              async : false,
	              success : function(data){
	            	  if(data ==1){
	            		  location = "manage.do";
 	            	  }else{
 	            		  $("#information").html("用户名或密码错误!");
	            		 return false;
	            	  }
	              }
	    	  })
	      }
	}

   $(function(){
    	$("#inputUsername").blur(function(){
    		checkLength( $("#inputUsername").val(), "用户名", 3, 16 )
    	});
    	$("#inputPassword").blur(function(){
    		checkLength( $("#inputPassword").val(), "密码", 6, 16 );
        });
    	$("#input1").blur(function(){
            checkValidateCode($("#input1").val(),"验证码");
        });
        $("#change1").click(function(){
        	$("#vcode").attr('src', "getValidateCode.do" + "?code=" + Math.random());
        });
    })
</script>
</head>
<body>
<div style="text-align:right; width: 105px;margin-left: 94%;"><a href="index.do"><h2>返回首页</h2></a></div>
    <div class="container">
	    <section id="content">
	        <form  class="form-signin" id = "form"  method="post">
	            <h2 class ="form-signin-heading">
	                <font>
	                    <font>请登录</font>
	                </font>
	            </h2>
	            <input type="text" name ="username" id="inputUsername" class="form-control" style="width:100%;" placeholder="用户名" required autofocus >
	            <input type="password" name="password" id ="inputPassword" class="form-control" style="width:100%;" placeholder="密码" required>
	            <input type = "text" id ="input1" class="form-control"  style="width:150px; float:left" placeholder="验证码" required/>
	            <img id= "vcode" src="getValidateCode.do" style="width: 80px; height: 47px;" >
	            <!-- <div id="checkCode" class = "unchanged" style="width: 80px ;"></div> -->
	            <input type="button" value="换 一张" id = "change1"
	                   class="btn btn-lg btn-primary btn-block" style="float:right; height: 47px; width: 70px; padding: 0rem 0rem; font-size: inherit;"/>
	            <div style="clear: both"></div>
	            <div id = "information" ></div>
	            <div style="clear: both"></div>
	            <input type="button" class="btn btn-lg btn-primary btn-block" id="submit1" onclick="return check();" value="登录" />
	            <button class="btn btn-lg btn-primary btn-block" type ="reset">
	                 <font>
	                     <font>重置</font>
	                 </font>
	            </button>
	        </form>
       </section>
    </div>
</body>
</html>