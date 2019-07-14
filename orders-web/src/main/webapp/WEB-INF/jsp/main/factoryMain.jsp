<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<link rel="stylesheet" type="text/css" href="static/css/main.css"/>
</head>
<body>
<div class="main-layout" >
<!-- 左侧导航 -->
<div class="left-nav" >
	<div class="nav-logo">
		<img alt="logo" src="static/images/logo_1.png">
	</div>
	<div class="user-photo" >
  	 	<a class="img" title="我的头像" onclick="sm.pageSkip('page/factoryPerson')">
  	 		<img src="${sessionScope.loginInfo.userPhoto }">
  	 	</a>
  	 	<p>你好！<span>${sessionScope.loginInfo.zhName }</span>, 欢迎登录</p>
	</div>
	<ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline" lay-filter="demo">
	  <li onclick="sm.pageSkip('page/toFactoryOrders')" class="layui-nav-item layui-this"><a href="javascript:;">订单管理</a></li>
	  <li onclick="sm.pageSkip('page/toFactoryStatistics')" class="layui-nav-item"><a href="javascript:;">订单统计</a></li>
	  <li onclick="sm.pageSkip('page/toFactoryProduct')" class="layui-nav-item"><a href="javascript:;">产品管理</a></li>
	  <shiro:hasPermission name="factory:user">
	  	<li onclick="sm.pageSkip('page/toFactoryUserForFactory')" class="layui-nav-item"><a href="javascript:;">人员管理</a></li>
	  </shiro:hasPermission>
	</ul>
</div>
<!-- 右侧导航 -->
<div class="main-layout-container" >
	<div class="main-layout-header">
		<ul class="layui-nav" >
		  <li class="layui-nav-item">
		    <a href="javascript:;">${sessionScope.loginInfo.zhName }</a>
		    <dl class="layui-nav-child">
		      <dd onclick="sm.pageSkip('page/factoryPerson')"><a href="javascript:void(0);">个人中心</a></dd>
		      <dd id="updatePwd"><a href="javascript:void(0);">重置密码</a></dd>
		    </dl>
		  </li>
		  <li id="logout" class="layui-nav-item"><a href="javascript:void(0);">立即退出</a></li>
		</ul>
	</div>
	
    <div class="main-layout-body">
		<!--tab 切换-->
		<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
		  <div class="layui-tab-content">
		    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
		    	<!--1-->
		    	<iframe id="systemIframe" src="page/toFactoryOrders" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
		    	<!--1end-->
		    </div>
		  </div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/system/footer.jsp" %>
</div>
</div>

<div id="updatePasswordDiv" style="display: none;" >
	<form class="layui-form" action="">
		<input type="hidden" name="id"  value="${sessionScope.loginInfo.id }"> 
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
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/main/factoryMain.js?v=<%=version %>"></script>
</body>