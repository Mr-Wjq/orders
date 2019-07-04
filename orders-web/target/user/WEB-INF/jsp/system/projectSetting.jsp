<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户管理</title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link type="image/x-icon" rel="bookmark" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui-v2.2.2/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/system/index.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/datagrid-groupview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/customValidation.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree_v3/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/projectSetting.js"></script>
	
<body>
<div>
	<fieldset class="layui-elem-field">
		<legend>项目部署地区:</legend>
		<div class="layui-field-box" style="float: left; width: 270px;">
			<span id="countryShow" style="text-align: center;display:block;font-size: 20px;color: red;"></span>	
		</div>
		<div style="float: left;">
			<button id="edit" class="layui-btn layui-btn-radius">更改部署地</button>
		</div>
	</fieldset>
</div>
<hr class="layui-bg-red">
<div id="confing-tool-bar" style="padding: 10px 10px 10px 10px">
</div>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	<div id="user-tool-bar" class="tab_container">
        <div class="layui-btn-group">
        	<shiro:hasPermission name="projectSetting:list">
			    <div class="layui-btn layui-btn-primary delete-btn" id="confing-flash-btn"><i class="layui-icon">&#x1002;</i>刷新</div>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="projectSetting:insert">
			    <div class="layui-btn layui-btn-primary select-btn" id="confing-save-btn"><i class="layui-icon">&#xe654;</i>新增</div>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="projectSetting:update">
			    <div class="layui-btn layui-btn-primary update-btn" id="confing-update-btn"><i class="layui-icon">&#xe642;</i>修改</div>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="projectSetting:delete">
			    <div class="layui-btn layui-btn-primary save-btn" id="confing-delete-btn"><i class="layui-icon">&#xe640;</i>删除</div>
        	</shiro:hasPermission>
		</div>
	</div>
	<ul class="layui-tab-title">
		<li id="projectUrl" class="layui-this">项目路径配置</li>
		<li id="pageShow" >页面显示配置</li>
		<li id="otherConfiguration" >其它配置</li>
	</ul>
	<div class="layui-tab-content" id="setting_grid_height" >
		<div id="setting_grid" class="layui-tab-item layui-show"></div>
	</div>
</div> 
<div id="edit_config" style="display: none;">
	<form id="setting_edit_form" >
	<input type="hidden" name="id" id="id">
    <div style="padding: 10px">
       <table class="layui-table">
		 <tbody>
		   <tr>
		     <td>配置项:</td>
		     <td><input name="configName" id="configName" style="width: 480px;height: 35px"
		                                                   data-options="required:true"
		                                                   class="easyui-textbox easyui-validatebox"></td>
		   </tr>
		   <tr>
		     <td>配置数据</td>
		     <td><input name="configData" id="configData"  style="width: 480px;height: 35px"
		                                                         data-options="required:true"
		                                                         class="easyui-textbox easyui-validatebox"></td>
		   </tr>
		   <tr>
		   		<td>所属配置</td>
		   		<td>
			   		<select name="queryType" id="queryType" style="width:480px;height: 35px">
				        <option value="1">项目路径配置</option>
				        <option value="2">页面显示配置</option>
				        <option value="0">其它配置</option>
				     </select>	
		     	</td>
		   </tr>
		   <tr>
		     <td>配置说明</td>
		     <td>
		     	<textarea name="configDetails" id="configDetails" style="width: 480px;height: 75px"></textarea>
		     </td>
		   </tr>
		  </tbody>
		</table>
     </div>
     </form>
</div>
<div id="selectCountry" style="display: none;">
   <form id="country_form" >  
   <input type="hidden" name="id2" id="id2">  
     <table class="layui-table">
		  <tbody>
		  <tr>
		     <td>配置项:</td>
		     <td colspan="3"><input name="configName2" id="configName2" style="width: 500px;height: 35px"
		                                                   data-options="required:true"
		                                                   class="easyui-textbox easyui-validatebox"></td>
		   </tr>
		    <tr>
		      <td>省:</td>
		      <td><select name="province" id="province" style="width: 200px;height: 35px">
	              <option value='-1'>请选择省</option></select></td>
		      <td>市:</td>
		      <td><select name="city" id="city" style="width: 200px;height: 35px">
	              <option value='-1'>请选择市</option></select></td>
		    </tr>
		    <tr>
		   		<td>所属配置</td>
		   		<td>
		   			<select name="queryType2" id="queryType2" style="width: 200px;height: 35px">
				        <option value="1">项目路径配置</option>
				        <option value="2">页面显示配置</option>
				        <option value="0">其它配置</option>
				     </select>	
		     	</td>
		   </tr>
		    <tr>
		     <td>配置说明</td>
		     <td colspan="3">
		     	<textarea name="configDetails2" id="configDetails2" style="width: 500px;height: 75px"></textarea>
		     </td>
		   </tr>
		  </tbody>
	 </table>
	</form> 
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>