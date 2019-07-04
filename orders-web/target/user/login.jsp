<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui-v2.2.2/css/layui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/login.js"></script>
<style type="text/css">

	/*
**过期提示弹框**
*/
.layer{
	position: absolute;
    right: 0;
    left: 0;
    top: 0;
    bottom: 0;
    background: rgba(0,0,0,0.7);
    display:none;
}
.overdue{
	width: 900px;
    background: rgba(239,239,239,1);
    text-align: center;
    margin: 0 auto;
    padding: 30px 0;
    border-radius: 3px;
    border: 3px solid rgba(129,129,129,1);
    margin-top:12%;
    z-index: 999999;
    position: absolute;
    left: 50%;
    margin-left: -450px;
}
.overdue .tip{
	color: red;
	margin: 20px 0;
	font-size:14px;
}
.overdue .btnfile{
	float:left;
	background:rgba(8,155,215,1);
	margin: 5px;
    padding: 5px 20px;
    color: #fff;
    border-radius:3px;
    cursor:pointer;
}
.overdue .btnfile:hover{
	background:rgba(8,155,215,0.8);
}
.overdue .btn{
	overflow: hidden;
    margin-top: 20px;
    display: inline-block;
}
.a-upload {
	float:left;
	background:rgba(8,155,215,1);
	margin: 5px;
    padding: 5px 20px;
    color: #fff;
    border-radius:3px;
    cursor:pointer;
    position: relative;
    border: 1px solid #ddd;
    overflow: hidden;
    display: inline-block;
    *display: inline;
    *zoom: 1;
}

.a-upload  input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}

.a-upload:hover {
    color: #fff;
    background:rgba(8,155,215,0.8);
}
</style>
</head>

<body class="login_bg" onkeydown="if(event.keyCode==13){login_tool.login();}">
<form action="login" method="post">
<div class="container">
    <div class="name">
    	<span id="logTitle" style="font-family:'宋体';font-weight:bold;text-shadow:2px 2px 2px #000;"></span>
        <p class="name_title">没有网络安全就没有国家安全</p>
    </div>
    <div class="maintext">
      <div class="login_txt"><b>登录</b><br>Login</div>
      <div class="text"><input id="userNameId" name="userName" type="text" placeholder="请输入用户名" class="text-input1"/></div>
      <!-- <div class="text"><input id="ukeyId" name="ukey" type="text" placeholder="请输入U-Key" class="text-input1"/></div> -->
      <div class="text"><input id="userPwdId" name="userPwd" type="password" placeholder="请输入密码"   class="text-input2"/></div>
      <div class="text"><input id="code" name="code" type="text" placeholder="请输入验证码" class="text-input1"/>
      	 <img id="imgObj" alt="验证码" src="${pageContext.request.contextPath }/verification/getCode3" />  
         <a id="changeImg" style="color:white;margin-top: 23px;margin-left: 10px;text-decoration:none; float:left;">换一张</a>
      </div>
      <div><input type="button" value="登 录" id="loginButton" class="button01"></div>
      
     <div class="title" style="margin-top: 156px;width:85%;">
     	<p class="title1" style="text-align: center;font-size: 20px;"><!-- 网络与信息安全信息通报中心 --><span id="logTitle2"></span></p>
     	<p style="text-align: center;"><span id="logTitle3"></span><p>
     </div>
     </div>
	
</div>
<%-- <input type="hidden" value="${securityReminder}" id="securityReminder"/> --%>
</form>
<!-- 过期提醒弹框 -->
<div class="layer">
	<div class="overdue">
		<img src="${pageContext.request.contextPath }/static/images/date.jpg"/>
		<p class="tip">错误提示：证书失效！</p>
		<p>操作提示：点击"获取机器码"按钮，将机器码发送给管理员，索要授权文件并上传后，即可正常访问系统。</p>
		<div class="btn">
			<div class="btnfile">获取机器码</div>
			<div style="float: left;">
				<a href="javascript:;" class="a-upload">
    				<input type="file" name="uploadFile" id="selectUploadFile">上传授权文件
				</a>
			</div>
		</div>
		<div class="showFileName"></div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script language="JavaScript"> 
if (window != top) 
top.location.href = location.href; 
</script>
</body>
</html>
