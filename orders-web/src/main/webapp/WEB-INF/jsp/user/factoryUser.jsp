<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
</head>
<body>
<form class="layui-form" action="" style="margin-top: 20px;">
<div class="layui-form-item">
    <label class="layui-form-label">模糊查询</label>
    <div class="layui-input-inline">
       <input type="text" id="unitName" placeholder="工厂名称" class="layui-input">
    </div>
    <div class="layui-input-inline">
       <input type="text" id="zhName" placeholder="姓名" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <input type="text" id="loginName" placeholder="账号" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <input type="text" id="phone" placeholder="手机号" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <select id="status">
        <option value="">状态</option>
        <option value="1">正常</option>
        <option value="2">禁用</option>
      </select>
    </div>
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>
<!-- 数据表格 -->
<table class="layui-hide" id="userTableId" lay-filter="userTableFilter"></table>

<script type="text/html" id="userTableToolbar">
  <div class="layui-btn-container">
	<button class="layui-btn layui-btn-sm" lay-event="insert">新增</button>
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
  </div>
</script>

<script type="text/html" id="statusBtn">
  <input type="checkbox" name="status" lay-skin="switch" lay-filter="statusFilter" value="{{d.userId}}" lay-text="正常|禁用" {{d.status == 1 ? 'checked' : '' }}>
</script>

<script type="text/html" id="lineToolbar">
  	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update_password">重置密码</a>
</script>

<div id="insertUserDiv" style="display: none;padding: 10px;" >
	<form class="layui-form " action="">
	  	  
	  	  <div class="layui-form-item">
		    <label class="layui-form-label">工厂名称<span class="required-sign">☀</span></label>
		    <div class="layui-input-inline">
		      <select id="unitList" name="unitId" lay-verify="required" lay-search>
		        <option value="">请选择工厂名称</option>
		      </select>
		    </div>
		    <div class="layui-form-mid layui-word-aux">没有找到单位？<a href="javascript:void(0);" id="insertUnitBtn" style="color: #0098ff" >立即创建</a></div>
		  </div>
		  
	  	  <div class="layui-form-item">
		    <label class="layui-form-label">职位<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="radio" name="roleId" value="5" title="员工" checked>
      		  <input type="radio" name="roleId" value="4" title="负责人">
		    </div>
		  </div>
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
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="userInsert">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	</form>
</div>
<!-- 修改单位/用户信息 -->
<div id="updateUserDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="" lay-filter="updateUserFilter">
	  	<input type="hidden" name="userId" id="userId"> 
	  	<input type="hidden" name="unitId" id="unitId"> 
	  	<div class="layui-form-item">
	      <label class="layui-form-label">工厂名称<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="unitName" lay-verify="required|zhName" placeholder="请输入工厂名称" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		
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
		
		<div class="layui-form-item">
		   <label class="layui-form-label">单位类型<span class="required-sign">☀</span></label>
		   <div class="layui-input-block">
		   		<input type="radio" name="unitType" value="4" title="工厂" checked>
		   </div>
		 </div>

	    <div class="layui-form-item">
	      <label class="layui-form-label">详细地址</label>
	      <div class="layui-input-block">
	        <textarea placeholder="请输出详细地址" name="unitAddress" class="layui-textarea" maxlength="100"></textarea>
	      </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">账号<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="loginName" lay-verify="required|loginName"  autocomplete="off" placeholder="请输入账号" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
	      <label class="layui-form-label">姓名<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="zhName" lay-verify="required|zhName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">职位<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="radio" name="roleId" value="5" title="员工" checked>
      		  <input type="radio" name="roleId" value="4" title="负责人">
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
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="userUpdate">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div> 
	</form>
</div>
<div id="updatePasswordDiv" style="display: none; padding: 10px;"" >
	<form class="layui-form" action="">
		<input type="hidden" name="id" id="userId2"> 
		<div class="layui-form-item">
		    <label class="layui-form-label">密码<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="password" id="i_password2" name="password" lay-verify="required|password" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">确认密码<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="password" name="eq_password2" lay-verify="required|eq_password2" placeholder="请确认密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="updatePassword">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
	  </form>
</div>
<div id="insertUnitDiv" style="display: none;padding: 10px;"" >
	<form class="layui-form" action="">
		<div class="layui-form-item">
	      <label class="layui-form-label">工厂名称<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="unitName" lay-verify="required|zhName" placeholder="请输入工厂名称" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		
		<label class="layui-form-label">选择地区<span class="required-sign">☀</span></label>
	    <div class="layui-input-inline">
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
		
		<div class="layui-form-item">
		   <label class="layui-form-label">单位类型<span class="required-sign">☀</span></label>
		   <div class="layui-input-block">
		   		<input type="radio" name="unitType" value="4" title="工厂" checked>
		   </div>
		 </div>

	    <div class="layui-form-item">
	      <label class="layui-form-label">详细地址</label>
	      <div class="layui-input-block">
	        <textarea placeholder="请输出详细地址" name="unitAddress" class="layui-textarea" maxlength="100"></textarea>
	      </div>
	    </div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="unitInsert">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	  </form>
</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/user/factoryUser.js?v=<%=version %>"></script>
</body>