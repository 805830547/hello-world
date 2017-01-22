<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String webContent = request.getScheme() + ":" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
   System.out.println(webContent);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/spmvc/manage/css/bootstrap.css">
<script type="text/javascript" src="/spmvc/manage/js/jquery-3.1.1.min.js"></script>
<title></title>


<style type="text/css">
.nav-item {
    display: table-cell;
}
.nav-justified {
    max-height: 52px;
}

.nav-link {
    padding-top: 15px;
    padding-bottom: 15px;
    margin-bottom: 0;
    font-weight: bold;
    color: #777;
    text-align: center;
       background-repeat: repeat-x;
    border-bottom: 1px solid #d5d5d5;
}

</style>    
<script type="text/javascript" language="javascript">

</script>
</head>
<body>

<div class ="container" style="margin-top: 30px;">
        <div class = "masthead">
            <h1 class="text-muted">
                <font>
                    <font>熙妍羽毛球培训学校</font>
                </font>
            </h1>
            <nav>
                <ul class="nav nav-justified">
                    <li class="nav-item" style="width: 184px;text-align: center;background-color: #ddd;">
                        <a href="index.do" class="nav-link active" style=" border-bottom: 0px">
                            <font>
                                <font>首页</font>
                            </font> 
                        </a>
                    </li>

                    <li class="nav-item"  style="width: 184px;text-align: center;background-color: #ddd;">
                        <a href="#" class="nav-link" style=" border-bottom: 0px">
                            <font>
                                <font>校园风采</font>
                            </font> 
                        </a>
                    </li>

                    <li class="nav-item"  style="width: 184px;text-align: center;background-color: #ddd;">
                        <a href="#" class="nav-link" style=" border-bottom: 0px">
                            <font>
                                <font>教练团队</font>
                            </font> 
                        </a>
                    </li>

                    <li class="nav-item"  style="width: 184px;text-align: center;background-color: #ddd;">
                        <a href="#" class="nav-link" style=" border-bottom: 0px">
                            <font>
                                <font>学校公告</font>
                            </font> 
                        </a>
                    </li>

                    <li class="nav-item"  style="width: 184px;text-align: center;background-color: #ddd;">
                        <a href="#" class="nav-link active" style=" border-bottom: 0px">
                            <font>
                                <font>联系我们</font>
                            </font> 
                        </a>
                    </li>
                    <li class="nav-item"  style="width: 184px;text-align: center; background-color: #ddd;">
                        <a href="login.do" class="nav-link active" style=" border-bottom: 0px">
                            <font>
                                <font>管理员登录</font>
                            </font> 
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="jumbotron" style="background-color: white; background-image:url(/spmvc/manage/123.jpg);background-repeat:no-repeat;height:600px;">
            <h1>
                <font>
                    <font>学生课堂活动精彩瞬间</font>
                </font>
            </h1>
            <p class="lead">
                <font>
                    <font>学生的羽球训练精彩镜头</font><br>
                    <font>丰富的课堂活动</font><br>
                    <font>无线的活力</font><br>
                </font>
            </p>
            <p style="text-align: center;">
                <a href="#" class="btn btn-lg btn-success" role="button" >
                    <font>
                        <font>点击浏览</font>
                    </font>
                </a>
            </p>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <h2>
                    <font>
                        <font>校园近况</font>
                    </font> 
                </h2>
                
                <p>
                    <font>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                    </font>
                </p>
                <p style="text-align: center;">
                    <a href="#" class="btn btn-primary">
                        <font>
                            <font>查看更多</font>
                        </font> 
                    </a>
                </p>
            </div>
            <div class="col-lg-4">
                <h2>
                    <font>
                        <font>今日赛事</font>
                    </font> 
                </h2>
                <p>
                    <font>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                    </font>
                </p>
                <p style="text-align: center;">
                    <a href="#" class="btn btn-primary">
                        <font>
                            <font>查看更多</font>
                        </font> 
                    </a>
                </p>
            </div>
            <div class="col-lg-4">
                <h2>
                    <font>
                        <font>羽球咨询</font>
                    </font> 
                </h2>
                <p>
                    <font>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                        <font><a href="#" name ="s">Donec sed的奥迪奥DUI-----------2016.12.11</a></font><br>
                    </font>
                </p>
                <p style="text-align: center;">
                    <a href="#" class="btn btn-primary">
                        <font>
                            <font>查看更多</font>
                        </font> 
                    </a>
                </p>
            </div>
        </div>
        <footer class="footer">
            <p>
                <font>
                    <font style="color: #0275d8;font-size: x-large;">熙妍羽毛球</font>
                </font>
            </p>
        </footer>


    </div>

</body>
</html>