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
<div><img  class="head-title" alt="logo" src="static/images/logo_1.png"><span style="font-size: 20px;font-weight: bold;" >注册</span></div>
<!-- 导航 -->
<div class="rebinding-box">
	<div class="box-timeline">
		<ul class="text-center" style="width: 800px;" >
			<li>
				填写单位信息
				<div class="box-num1">
					1
				</div>
			</li>
			<li class="ml45">
				填写基本信息
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
	      <label class="layui-form-label">单位名称<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="unitName" lay-verify="required|zhName" placeholder="请输入单位名称" autocomplete="off" maxlength="50" class="layui-input">
	      </div>
	    </div>
		<div class="layui-form-item">
		   <label class="layui-form-label">单位类型<span class="required-sign">☀</span></label>
		   <div class="layui-input-block">
		   		<input type="radio" name="unitType" value="3" lay-filter="unitTypeFilter" title="个体门诊">
		     	<input type="radio" name="unitType" value="1" lay-filter="unitTypeFilter" title="医院" >
		   		<input type="radio" name="unitType" value="2" lay-filter="unitTypeFilter" title="连锁门诊">
		   		<input type="radio" name="unitType" value="4" lay-filter="unitTypeFilter" title="工厂">
		   </div>
	 	</div>
	 	<div class="layui-form-item">
			<label class="layui-form-label">选择地区<span class="required-sign">☀</span></label>
		    <div class="layui-input-inline" style="margin-left: 12px;">
		      <select id="unitProvinceId" name="unitProvinceId" lay-verify="required" lay-filter="unitProvinceId">
		        <option value="">请选择省</option>
		        
		      </select>
		    </div>
		    <div class="layui-input-inline">
		      <select id="unitCityId" name="unitCityId" lay-verify="required" lay-filter="unitCityId">
		        <option value="">请选择市</option>
		        
		      </select>
		    </div>
		    <div class="layui-input-inline">
		      <select id="unitDistrictId" name="unitDistrictId">
		        <option value="">请选择县/区</option>
		        
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
	      <label class="layui-form-label">详细地址</label>
	      <div class="layui-input-block">
	        <textarea placeholder="请输出详细地址" name="unitAddress" class="layui-textarea" maxlength="100"></textarea>
	      </div>
	    </div>
	    <div class="btnZu">
			<button class="layui-btn" type="button" style="width: 200px;" onclick="nextTwo()">下一步</button>
	    </div>
	</div>
		<!--第二步-->
		<div id="twoDiv" style="display: none;">
	  	  <div class="layui-form-item">
		    <label class="layui-form-label">账号<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="loginName" lay-verify="required|loginName"  autocomplete="off" placeholder="请输入账号" class="layui-input">
		    </div>
		  </div>
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
		
		<div class="layui-form-item">
	      <label class="layui-form-label">姓名<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="zhName" lay-verify="required|zhName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		  
	    <div class="layui-form-item">
	      <label class="layui-form-label">手机<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">邮箱</label>
	      <div class="layui-input-block">
	        <input type="text" name="email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
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
		      <button class="layui-btn" type="button" onclick="canalOne()">上一步</button> 
		      <button class="layui-btn" lay-submit="" lay-filter="userInsert">立即提交</button>
		</div>
		</div>
		<!--第三步-->
		<div id="threeDiv" style="display: none;">
			<div class="symbol"></div>
			<p>注册成功！<span><a href="/" style="color: #39f;font-weight: bolder;">立即登录</a></span></p>
			
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/system/register.js?v=<%=version %>"></script>
</body>