<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<html>
<head>
    <title>404页面未找到</title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui-v2.2.2/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
</head>
<body>
<center>
<div><i class="layui-icon" style="font-size: 700" >&#xe61c;</i></div>  
<div class="layui-btn-group">
  <button class="layui-btn" onClick="javascript:history.back(-1);">返回上一页</button>
  <button class="layui-btn" onClick="toMain()">返回首页</button>
</div>
</center>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script type="text/javascript">
function toMain(){
	top.location.href=getRootPath() +"/system/toMain";
}
</script>
</html>
