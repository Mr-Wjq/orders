<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<style type="text/css">
		.user_right {text-align: center;float: left;}
		.layui-code, .layui-upload-list {
		    margin: 10px 0;
		}
		.user_right img#userFace {
		    width: 200px;
		    height: 200px;
		    margin-top: 20px;
		    cursor: pointer;
		    box-shadow: 0 0 50px #44576b;
		}
		.layui-btn-primary {
		    border: 1px solid #C9C9C9;
		    background-color: #fff;
		    color: #555;
		}
		.layui-btn .layui-icon {
		    margin-right: 3px;
		    font-size: 18px;
		    vertical-align: bottom;
		    vertical-align: middle\9;
		}
	</style>
</head>
<body>
<div class="layui-col-md3 layui-col-xs12 user_right">
		<div class="layui-upload-list">
			<img class="layui-upload-img layui-circle userFaceBtn" id="userFace" src="${sessionScope.loginInfo.userPhoto }">
		</div>
		<button type="button" class="layui-btn layui-btn-primary userFaceBtn"><i class="layui-icon">
		</i> 更换头像</button>
		<p id="demoText" style="margin-top: 10px;"></p>
</div>
<div id="updateUserDiv" style="margin-top:20px;padding: 10px;float: left;" >
	<form class="layui-form" action="" lay-filter="updateUserFilter">
	  	<input type="hidden" name="userId" id="userId" value="${sessionScope.loginInfo.id }"> 
	  	<input type="hidden" name="unitId" id="unitId" value="${sessionScope.loginInfo.unitId }"> 
	  	<shiro:hasPermission name="doctor:user">
	  	<div class="layui-form-item">
	      <label class="layui-form-label">单位名称<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="unitName" lay-verify="required|zhName" placeholder="请输入单位名称" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-form-item">
		   <label class="layui-form-label">单位类型<span class="required-sign">☀</span></label>
		   <div class="layui-input-block">
		   		<input type="radio" name="unitType" value="3" title="个体门诊" checked>
		     	<input type="radio" name="unitType" value="1" title="医院" >
		   		<input type="radio" name="unitType" value="2" title="连锁门诊">
		   </div>
		 </div>
		<div class="layui-form-item">
			<label class="layui-form-label">选择地区<span class="required-sign">☀</span></label>
		    <div class="layui-input-inline">
		      <select id="u_unitProvinceId" name="unitProvinceId" lay-verify="required" lay-filter="u_unitProvinceId">
		        <option value="">请选择省</option>
		        
		      </select>
		    </div>
		    <div class="layui-input-inline">
		      <select id="u_unitCityId" name="unitCityId" lay-verify="required" lay-filter="u_unitCityId">
		        <option value="">请选择市</option>
		        
		      </select>
		    </div>
		    <div class="layui-input-inline">
		      <select id="u_unitDistrictId" name="unitDistrictId">
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
	  	</shiro:hasPermission>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">账号<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="loginName" disabled  value="${sessionScope.loginInfo.loginName }" lay-verify="required|loginName"  autocomplete="off" placeholder="请输入账号" class="layui-input layui-disabled">
		    </div>
		 </div>
		
		<div class="layui-form-item">
	      <label class="layui-form-label">姓名<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="zhName"  value="${sessionScope.loginInfo.zhName }" lay-verify="required|zhName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		
	    <div class="layui-form-item">
	      <label class="layui-form-label">手机<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" value="${sessionScope.loginInfo.phone }" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">邮箱</label>
	      <div class="layui-input-block">
	        <input type="text" name="email" value="${sessionScope.loginInfo.email }" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="userUpdate">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div> 
	</form>
</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/user/doctorPerson.js?v=<%=version %>"></script>
</body>