<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>${internalTitle}</title>
		<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/resetIndex.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/mainIndex.css" />
	    <link rel="stylesheet" type="text/css" title="eleven"  href="${pageContext.request.contextPath}/static/css/iconStyles.css"/>
	    <link rel="stylesheet" type="text/css" title="nine_ten"  href="${pageContext.request.contextPath}/static/css/nine_ten.css" disabled="disabled"/>
	    <link rel="stylesheet" type="text/css" title="six_eight"  href="${pageContext.request.contextPath}/static/css/six_eight.css" disabled="disabled"/>
	    <link rel="stylesheet" type="text/css" title="five" href="${pageContext.request.contextPath}/static/css/five.css" disabled="disabled"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/screenFull.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main/platformMain.js"></script>
	</head>
	<body class="homeW">
		<%@ include file="../system/header.jsp" %>
		<div class="index_body"  id="index_body">
			<div class="container">
				<shiro:hasPermission name="system:manage">
					<div class="module secondMenu">
		                <div class="icon_center icon5"></div>
		                <div class="module_name left"><span>等保管理<i class="dbDown"></i></span></div>
	                	<shiro:hasPermission name="system:repository">
						    <li><a href="${pageContext.request.contextPath}/platform/getRepository">知识库</a></li>
	                	</shiro:hasPermission>
	                	<shiro:hasPermission name="system:gpcheckresultimport">
		                	<li><a href="${pageContext.request.contextPath}/platform/getGpcheckresultimport">等保检查</a></li>
	                	</shiro:hasPermission>
	                	<shiro:hasPermission name="system:toolbox2">
						    <li><a href="${pageContext.request.contextPath}/platform/getToolbox2">等保自查</a></li>
	                	</shiro:hasPermission>
	                	<shiro:hasPermission name="system:gradeprotection">
						    <li><a href="${pageContext.request.contextPath}/platform/getGradeprotection">等保备案</a></li>
	                	</shiro:hasPermission>
		            </div>
	            </shiro:hasPermission>
	            <shiro:hasPermission name="system:websiteMonitor">
		            <a href="${pageContext.request.contextPath}/platform/getWebsiteMonitor" class="module">
		                <div class="icon_center icon6"></div>
		                <div class="module_name right"><span>网站监测</span></div>
		            </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="system:assetsMonitor">
					<a href="${pageContext.request.contextPath}/platform/platform" class="module">
		                <div class="icon_center icon4"></div>
		                <div class="module_name right"><span>资产监测</span></div>
		            </a>
				</shiro:hasPermission>
				<%-- <shiro:hasPermission name="system:probe">
		            <a href="${pageContext.request.contextPath}/platform/getProbe" class="module">
		                <div class="icon_center icon7"></div>
		                <div class="module_name left"><span>探针管理</span></div>
		            </a>
				</shiro:hasPermission> --%>
				<shiro:hasPermission name="system:thirdParty">
					<a href="${pageContext.request.contextPath}/platform/getThirdParty" class="module">
		                <div class="icon_center icon10"></div>
		                <div class="module_name left"><span>第三方数据</span></div>
		            </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="system:rapidDealing">
					<a href="${pageContext.request.contextPath}/platform/speedDispose" class="module">
		                <div class="icon_center icon1"></div>
		                <div class="module_name right"><span>快速处置</span></div>
		            </a>
				</shiro:hasPermission>
				
				
				<shiro:hasPermission name="system:user">
					<a href="${pageContext.request.contextPath}/system/userManage" class="module">
		                <div class="icon_center icon9"></div>
		                <div class="module_name right"><span>用户信息管理</span></div>
		            </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="system:situationAwareness">
					<a href="${pageContext.request.contextPath}/platform/getSituationAwareness" class="module">
		                <div class="icon_center icon2"></div>
		                <div class="module_name left"><span>态势感知</span></div>
		            </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="system:tongBaoYuJing">
					<a href="${pageContext.request.contextPath}/platform/getTongBaoYuJing" class="module">
		                <div class="icon_center icon8"></div>
		                <div class="module_name right"><span>通报预警</span></div>
		            </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="system:dongxunqbfb">
					<a href="${pageContext.request.contextPath}/platform/getDongxunqbfb" class="module">
		                <div class="icon_center icon3"></div>
		                <div class="module_name right"><span>情报风暴</span></div>
		            </a>
				</shiro:hasPermission>
				
	            <shiro:hasPermission name="system:dongxuntech">
					<a href="${pageContext.request.contextPath}/platform/getDongxuntech" class="module">
		                <div class="icon_center icon11"></div>
		                <div class="module_name right"><span>情报查询</span></div>
		            </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="system:spySurvey">
				<a href="${pageContext.request.contextPath}/platform/spySurvey" class="module">
	                <div class="icon_center icon7"></div>
	                <div class="module_name left"><span>侦查调查</span></div>
	            </a>
	            </shiro:hasPermission>
	            <shiro:hasPermission name="system:flowMonitor">
				<a href="${pageContext.request.contextPath}/platform/flowMonitor" class="module">
	                <div class="icon_center icon12"></div>
	                <div class="module_name left"><span>流量监测</span></div>
	            </a>
	            </shiro:hasPermission>
				<shiro:hasPermission name="system:findSource">
	            <a href="${pageContext.request.contextPath}/platform/findSource" class="module">
	                <div class="icon_center icon14"></div>
	                <div class="module_name right"><span>追踪溯源</span></div>
	            </a>
	            </shiro:hasPermission>
	            <shiro:hasPermission name="system:permeateUse">
	            <a href="${pageContext.request.contextPath}/platform/permeateUse" class="module">
	                <div class="icon_center icon13"></div>
	                <div class="module_name left"><span>渗透利用</span></div>
	            </a>
	            </shiro:hasPermission>
			</div>
		</div>
	</body>
</html>