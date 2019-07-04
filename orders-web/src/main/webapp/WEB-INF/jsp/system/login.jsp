<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<style type="text/css">
	 .login-bg{
			width: 100%;
			background: url(static/images/bg.png) no-repeat;
			background-size: cover; 
			background-position: center center;
			background-attachment: fixed;
			background-color: #CCCCCC;
	 	}
	
	 /* .login-bg .login-cont .form-box{width: 300px; height: 390px; border:1px solid #d9dadc; background: #fff; position: absolute; top: 196px; right: 90px; padding:0 30px;} */
	 .login-bg .loginLogo{ margin: 30px; }
	 .login-bg .loginLogo img{ width: 200px; }
	 .w1200{width: 400px;margin: 5% 0 0 10%; text-align: center;}
	 .login-bg .login-cont .form-box{width: 300px; height: 390px; border:1px solid #d9dadc;border-radius:30px;background-color:rgba(0,0,0,0);margin:0 auto; padding:0 30px;}
	 .login-bg .login-cont .form-box legend{font-size: 30px;color: #f2f2f2; text-align: center; padding:20px 0 30px 0;}
	 .login-bg .login-cont .layui-inline, .login-bg .login-cont .layui-input-inline{width: 100%; position: relative;}
	 .login-bg .login-cont .layui-input-inline .iphone-icon{position: absolute; top: 8px; left: 10px; color: #d3d3d3; font-size: 20px;}
	 .login-bg .login-cont .layui-input-inline .password-icon{position: absolute; top: 8px; left: 10px; color: #d3d3d3; font-size: 20px;}
	 .login-bg .login-cont .iphone{margin-bottom: 20px;}
	 .login-bg .login-cont .iphone .layui-input{padding-left: 40px;}
	 .login-bg .login-cont .veri-code .layui-btn{width: 102px; background: #eeeeee; text-align: center; color: #333; height: 36px; line-height: 36px; display: inline-block; border:none; cursor: pointer; position: absolute; top: 1px; right: 1px; font-size: 12px; padding:0 14px;}
	 .login-bg .login-cont .login-btn .layui-input-block{margin-left: 0; padding-top: 5px;}
	 .login-bg .login-cont .login-btn .layui-input-block button{width: 100%; background: #138ccd; font-size: 18px;}
	 .footer {display: none;}
	</style>
</head>
<body class="login-bg" onkeydown="if(event.keyCode==13){login.login();}">
		
<div class="loginLogo" ><img src="static/images/loginLogo.png"></div>	  
<div class="login-cont w1200">
        <div class="form-box">
          <form class="layui-form" action="">
            <legend>Hi , 欢迎回来 !</legend>
            <div class="layui-form-item">
              <div class="layui-inline iphone">
                <div class="layui-input-inline">
                  <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                  <!-- autocomplete="off" -->
                  <input type="tel" name="loginName" id="loginName" lay-verify="required" placeholder="请输账号/手机号" autocomplete="on"  class="layui-input">
                </div>
              </div>
              <div class="layui-inline iphone">
                <div class="layui-input-inline">
                  <i class="layui-icon layui-icon-password password-icon"></i>
                  <input type="password" name="password" id="password" lay-verify="required" placeholder="请输入密码" autocomplete="on" class="layui-input">
                </div>
              </div>
              <div class="layui-inline veri-code">
                <div class="layui-input-inline">
                  <input id="code" type="text" name="code" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input"
                  style="width: 180px; float: left;">
                  <img id="imgObj" alt="验证码" src="verification/getCode" style="width: 100px;height:38px; float: right;"/>
                </div>
              </div>
            </div>
            <div class="layui-form-item login-btn">
              <div class="layui-input-block">
                <button class="layui-btn layui-btn-radius" id="submit-btn" type="button">登录</button>
              </div>
            </div>
          </form>
          <div>
	          <div style="float: left;">
		          	<a href="page/toRegister" style="color: #f2f2f2;">立即注册</a>
	          </div>
	          <div style="float: right;">
	          		<a href="page/toForgetPassword" style="color: #f2f2f2;">忘记密码</a>
	          </div>
         </div>
        </div>
        <div style="font-size: 20px;color: #f3f4fa;margin-top: 20px;"><span style="font-weight: bolder;">牙智云</span> &nbsp;&nbsp;|&nbsp;&nbsp; 
	<span style="font-weight: lighter;">口腔数字化数据交互平台</span></div>
</div>
	
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/system/login.js?v=<%=version %>"></script>
<script language="JavaScript"> 
if (window != top) 
top.location.href = location.href; 
</script>
</body>