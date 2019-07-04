<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui-v2.2.2/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/themes/commonStyles.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/themes/default.css" media="all" id="skit" kit-skin/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.i18n.properties-1.0.9.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/datagrid-groupview.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/customValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree_v3/js/jquery.ztree.all.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/js/system/header.js" ></script>
	<style type="text/css">
		.layui-layer-shade{z-index:999 !important;}
		#changeId p{font-size:14px;margin:5px;}
	</style>
</head>
<body>
	<!-- 顶部 -->
	<div class="layui-header">
	    <div class="layui-logo"><img src="${pageContext.request.contextPath }/static/images/logo_name.png" alt="" /></div>
	    <ul class="layui-nav layui-layout-left kit-nav">
	        <li class="layui-header-title"><a href="javascript:;" id="internalTitle"></a></li>
	    </ul>
	    <ul class="layui-nav layui-layout-right kit-nav">
	        <li class="layui-nav-item">
	            <p style="font-size: 18px;color: red;" id="dayNum"></p>
	        </li>
	        <li class="layui-nav-item">
	            <a href="javascript:;"><i class="fa fa-circle-thin"></i>皮肤</a>
	            <dl class="layui-nav-child skin">
	                <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i class="layui-icon">&#xe658;</i> 默认</a></dd>
	                <dd><a href="javascript:;" data-skin="blue" style="color:#00c0ef;"><i class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
	                <dd><a href="javascript:;" data-skin="red" style="color: #dd4b39;"><i class="layui-icon">&#xe658;</i> 严肃红</a></dd>
	            </dl>
	        </li>
	        <li class="layui-nav-item">
	            <a href="javascript:;" onclick="toMain()" id="backHome">
	            	<i class="layui-icon">&#xe68e;</i>
	                <span>返回主页</span>
	            </a>
	        </li>
	        <li class="layui-nav-item">
	            <a href="javascript:;">
	                <span class="fa fa-user-o"></span>
		        	<span>${sessionScope.get("loginInfo").zhName}</span>
	            </a>
	            <dl class="layui-nav-child">
					<!-- <dd><a href="javascript:;" onclick="personalDate()"><i class="fa fa-user"></i>&nbsp&nbsp个人中心</a></dd> -->
					<dd><hr class="layui-bg-gray"></dd>
	                <dd><a href="javascript:;" onclick="change()"><i class="fa fa-unlock"></i>&nbsp&nbsp修改密码</a></dd>
	                <dd><hr class="layui-bg-gray"></dd>
	                <dd><a href="javascript:;" onclick="dropOut()"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp&nbsp安全退出</a></dd>
	            </dl>
	        </li>
	    </ul>
	</div>

	<div id='changeId' style='width:350px;display: none;' > 
		<div style='width:320px;margin-left: 3%;'> 
			<p>请输入旧密码</p> 
			<input class='layui-input' id='oldPassword' type='password' name='oldPassword' value=''/> 
			<span id='oldInfo' style='color:red;'></span>
		</div> 
	    <div style='width:320px;margin-left: 3%;'> 
	        <p>请输入新密码</p> 
	        <input class='layui-input' id='newPassword' maxlength='12' minlength='6' type='password' name='newPassword' value=''/> 
	        <span id='newPasswordInfo' style='color:red;'></span>
	    </div>
	    <div style='width:320px;margin-left: 3%;'> 
	        <p>请再次输入新密码</p> 
	        <input class='layui-input' id='secondPassword' maxlength='12' minlength='6' type='password' name='secondPassword' value=''/> 
	        <span id='rePassword' style='color:red;'></span>
	    </div>
	</div>
</body>
<input type="hidden" value="${sessionScope.get('loginInfo').loginName}" id="login_name"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/skinChange.js"></script>
