<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN">
<html lang="zh">
<head>
	<meta charset="utf-8" />
	<title>${internalTitle}</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<style>
		.systemInfo td{
			height:30px;
			line-height:30px;
			border:1px solid #e6e6e6;
			padding:5px 0 5px 20px;
		}
		.systemInfo{
			border-collapse:collapse;
			box-shadow: 0 2px 5px 0 rgba(0,0,0,.2);
		}
		.systemInfo tr:nth-child(odd){
			background:#eee;
		}
		.systemInfo tr:first-child{
			background:#DCDCDC;
			text-align:center;
		}
	</style>
</head>
<body>
<div id="wrapper">
	<table width="100%" border="1" class="systemInfo">
		<tr>
			<td colspan="2" text-align="center">基本信息</th>
		</tr>
		<!-- <tr>
			<td width="20%">产品名称：</td>
			<td width="80%">安全监测控制中心</td>
		</tr>
		<tr>
			<td>产品型号：</td>
			<td>YD HongCe 2</td>
		</tr> -->
		<tr>
			<td>系统版本：</td>
			<td>YD V2.0</td>
		</tr>
		<tr>
			<td>当前系统时间：</td>
			<td id="clock" ></td>
		</tr>
		<tr>
			<td>使用授权：</td>
			<td id="authorization" ></td>
		</tr>
	</table>
</div>

<script>
	$('.four').addClass('now').siblings('li').removeClass('.now');
	$(function(){
		clock();
		$.ajax({
			url: getRootPath() + "/authorization/verifyAuthorizationMes",
			type: "GET",
			dataType: "json",
			async: true,//异步请求
			success: function(data) {
				if("terminable" == data.verificationMes){
					$("#authorization").html("<p>试用期剩余：<span style='font-size: 20px;'>"+data.terminableNum+"</span>天</p>");
				}else
				if("ever"== data.verificationMes){
					$("#authorization").html("<p>永久授权</p>");
				}
			}
		});
	})
	
 function clock(){
	 var time = new Date();//获取系统当前时间
	 var year = time.getFullYear();
	 var month = time.getMonth()+1;
	 var date= time.getDate();//系统时间月份中的日
	 var day = time.getDay();//系统时间中的星期值
	 var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
	 var week = weeks[day];//显示为星期几
	 var hour = time.getHours();
	 var minutes = time.getMinutes();
	 var seconds = time.getSeconds();
	 if(month<10){
	 month = "0"+month; 
	 }
	 if(date<10){
	 date = "0"+date; 
	 }
	 if(hour<10){
	 hour = "0"+hour; 
	 }
	 if(minutes<10){
	 minutes = "0"+minutes; 
	 }
	 if(seconds<10){
	 seconds = "0"+seconds; 
	 }
	 //var newDate = year+"年"+month+"月"+date+"日"+week+hour+":"+minutes+":"+seconds;
	 document.getElementById("clock").innerHTML = year+"年"+month+"月"+date+"日&nbsp;&nbsp;"+week+"&nbsp;&nbsp;"+hour+":"+minutes+":"+seconds;
	 setTimeout('clock()',1000);
 }
</script>

</body>
</html>