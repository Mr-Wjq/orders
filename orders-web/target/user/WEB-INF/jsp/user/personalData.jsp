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
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/static/js/user/personalData.js" ></script>
</head>
<body>
	<div id="personal_edit_dialog" >
		<form id="personal_form">
			<input type="hidden" name="personal_userId" id="personal_userId">
			<div style="float: left;height: 290px;margin: 10px;">
				<table class="layui-table">
					<tbody>
					<tr>
						<td>登录名</td>
						<td><input name="personal_loginName" id="personal_loginName" style="width: 200px;height: 35px"
								   data-options="required:true,validType:'loginName'"
								   class="easyui-textbox easyui-validatebox"></td>
					</tr>
					<tr>
						<td>用户姓名</td>
						<td><input name="personal_zhName" type="text" id="personal_zhName"
								   style="width: 200px;height: 35px"
								   required="true"
								   class="easyui-textbox easyui-validatebox"></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input name="personal_phone" type="text" id="personal_phone"
								   style="width: 200px;height: 35px"
								   required="true"
								   class="easyui-textbox easyui-validatebox"></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input name="personal_email" type="text" id="personal_email"
								   style="width: 200px;height: 35px"
								   required="true"
								   class="easyui-textbox easyui-validatebox"></td>
					</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div style="float: left;height: 290px;margin: 10px;">
			<table class="layui-table">
				<tbody>
				<tr>
					<td>单位名称</td>
					<td colspan="3" >
						<input id="personal_unit_name" name="personal_unit_name" style="width: 340px;height: 35px"
							   data-options="required:true" readonly unselectable="on"
							   class="easyui-textbox easyui-validatebox">
						<input type="hidden" id="personal_unit_id" name="personal_unit_id" >
					</td>
				</tr>
				<tr>
					<td>行业类别</td>
					<td colspan="3" >
						<input id="personal_industry" name="personal_industry" style="width: 340px;height: 35px"
							   data-options="required:true" readonly unselectable="on"
							   class="easyui-textbox easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td>市</td>
					<td>
						<input id="personal_unit_city" style="width: 118px;height: 35px"
							   data-options="required:true" readonly unselectable="on"
							   class="easyui-textbox easyui-validatebox">
					</select>
					</td>
					<td>区县</td>
					<td>
						<input id="personal_unit_county" style="width: 118px;height: 35px"
							   data-options="required:true" readonly unselectable="on"
							   class="easyui-textbox easyui-validatebox">
					</select>
					</td>
				</tr>
				<tr>
					<td>单位地址</td>
					<td colspan="3" ><input id="personal_unit_address" name="personal_unit_address" style="width: 340px;height: 35px"
											data-options="required:true" readonly unselectable="on"
											class="easyui-textbox easyui-validatebox"></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div  style="margin-top:10px;">
		<a  onclick="updatePersonalData();">修改</a>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/skinChange.js"></script>
