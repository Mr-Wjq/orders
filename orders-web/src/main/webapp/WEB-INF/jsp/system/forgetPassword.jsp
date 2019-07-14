<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<link rel="stylesheet" type="text/css" href="static/css/linker.css"/>
	<style type="text/css">
		.head-title {width:230px;height:70px;margin: 30px;}
		.mesDiv{clear:both;margin:0 auto;width: 800px;padding: 30px;}
		.layui-form-label {width: 100px;}
		.bottomDiv{margin:0 auto;}
		.btnZu {text-align: center;}
	</style>
</head>
<body>
<div><a href="<%=basePath%>"><img  class="head-title" alt="logo" src="static/images/logo_blue.png"></a><span style="font-size: 20px;font-weight: bold;" >重置密码</span></div>
<!-- 导航 -->
<div class="rebinding-box">
	<div class="box-timeline">
		<ul class="text-center" style="width: 800px;" >
			<li>
				验证手机号
				<div class="box-num1">
					1
				</div>
			</li>
			<li class="ml45">
				重置密码
				<div class="box-outside1 outside1ab" id="outside1abs">
					<div class="box-num2 num2ab">
					  2
				    </div>
				</div>
			</li>
			<li class="ml45">
				完成
				<div class="box-outside2 outside2a" id="outside2as">
					<div class="box-num3 num3a" >
					 3
				   </div>
				</div>
			</li>
			<div class="clear">
				
			</div>
		</ul>
	</div>
</div>
<div class="mesDiv">
	<form class="layui-form">
		<!--第一步-->
	<div id="oneDiv" >
		<div class="layui-form-item">
	      <label class="layui-form-label">手机<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">验证码<span class="required-sign">☀</span></label>
	      <div class="layui-input-inline" style="margin-left: 10px;">
	        <input type="text" name="phoneCode" lay-verify="required" placeholder="请输入手机验证码" maxlength="4" autocomplete="off" class="layui-input">
	      </div>
	       <div class="layui-form-mid layui-word-aux"><div id="getPhoneCodeDiv"><a href="javascript:void(0);" onclick="getPhoneCode()" style="color: #0098ff" >获取手机验证码</a></div></div>
	    </div>
	    <div class="btnZu">
			<button class="layui-btn" type="button" style="width: 200px;" onclick="nextTwo()">下一步</button>
	    </div>
	</div>
		<!--第二步-->
		<div id="twoDiv" style="display: none;">
		  <div class="layui-form-item">
		    <label class="layui-form-label">密码<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="password" id="i_password" name="password" lay-verify="required|password" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">确认密码<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="password" name="eq_password" lay-verify="required|eq_password" placeholder="请确认密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
	
		<div class="btnZu">
		      <button class="layui-btn" type="button" onclick="canalOne()">上一步</button> 
		      <button class="layui-btn" lay-submit="" lay-filter="resetPassword">重置密码</button>
		</div>
		</div>
		<!--第三步-->
		<div id="threeDiv" style="display: none;">
			<div class="symbol"></div>
			<p>密码重置成功！<span><a href="/" style="color: #39f;font-weight: bolder;">立即登录</a></span></p>
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/system/forgetPassword.js?v=<%=version %>"></script>
</body>