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
    <label class="layui-form-label">查询</label>
    
    <div class="layui-input-inline">
       <input type="text" id="factoryName" placeholder="工厂名称" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <select id="search_cureList">
        <option value="">治疗类型</option>
      </select>
    </div>
    <div class="layui-input-inline">
       <input type="text" id="textureName" placeholder="材质" class="layui-input">
    </div>
    <div class="layui-input-inline">
       <input type="text" id="brandName" placeholder="品牌" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <input type="text" id="productName" placeholder="产品名称" class="layui-input">
    </div>
    
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>

<!-- 数据表格 -->
<table class="layui-hide" id="productAddTableId" lay-filter="productAddTableFilter"></table>
		
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/baseData/factoryProductForDoctor.js?v=<%=version %>"></script>
</body>