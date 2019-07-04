<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>cloud-shield</title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link type="image/x-icon" rel="bookmark" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui-v2.2.2/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/system/index.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/customValidation.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree_v3/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/unit.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style type="text/css">
		.tree-folder-open {
		    background: url(/static/css/images/building.png) ;
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		}
		.tree-file {
		    background: url(/static/css/images/house.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		}
		.ztree li span.button.ico_open {
		    margin-right: 2px;
		    background: url(/static/css/images/building.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
		.ztree li span.button.ico_docu {
		    margin-right: 2px;
		    background: url(/static/css/images/house.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
	</style>
</head>
<body>
<div id="unit_tool_bar" class="tab_container">
   <form id="unit_search_form">
		<div class="layui-col-md12">

			<div class="layui-inline" style="background:#eee;">
			   	<label class="layui-form-label">单位名称:</label>
			   	<div class="layui-input-inline">
			     	<input type="text" name="search_unit_unitName" id="search_unit_unitName" class="layui-input">
			   	</div>
			</div>
			<div class="layui-inline" style="background:#eee;">
			   	<label class="layui-form-label">行业</label>
			   	<div class="layui-input-inline">
			     	<select class='easyui-combobox' id="search_industry" editable="false" style="width: 150px;height: 35px">
			              </select>
			   	</div>
			</div>

			<div class="layui-inline" style="background:#eee;">
			   	<label class="layui-form-label">市:</label>
			   	<div class="layui-input-inline">
			     	<select class='easyui-combobox' id="search_unit_city" editable="false" style="width: 150px;height: 35px">
			              </select>
			   	</div>
			</div>
			<div class="layui-inline" style="background:#eee;">
			   	<label class="layui-form-label">区县:</label>
			   	<div class="layui-input-inline">
			     	<select class='easyui-combobox' id="search_unit_country" editable="false" style="width: 150px;height: 35px">
			              </select>
			   	</div>
			</div>

	        <div class="layui-btn-group">
				<div class="layui-btn layui-btn-normal" id="unit_select_btn"><i class="layui-icon">&#xe615;</i>搜索</div>
			</div>
		</div>
		<hr class="layui-bg-gray">
   		<div class="layui-btn-group">
            <shiro:hasPermission name="unit:list">
                <div class="layui-btn layui-btn-primary" id="unit_flash_btn"><i class="layui-icon">&#x1002;</i>刷新</div>
            </shiro:hasPermission>
		    <shiro:hasPermission name="unit:insert">
			    <div class="layui-btn layui-btn-primary" id="unit_save_btn"><i class="layui-icon">&#xe654;</i>新增</div>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="unit:delete">
			    <div class="layui-btn layui-btn-primary" id="unit_delete_btn"><i class="layui-icon">&#xe640;</i>删除</div>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="unit:update">
			    <div class="layui-btn layui-btn-primary" id="unit_update_btn"><i class="layui-icon">&#xe642;</i>修改</div>
		    </shiro:hasPermission>

		</div>
   	</form>
</div>
<div id="unitUser_grid" style="display:none;">
</div>
<div id="unit_edit_dialog" style="display:none;">
    <form id="unit_form">
		<input type="hidden" name="unitId" id="unitId">
		<div style="float: left;margin: 10px;">
	        <table class="layui-table">
				  <tbody>
				    <tr>
				      <td>单位名称</td>
				      <td colspan="3" ><input id="unit_name" name="unit_name" style="height: 35px;width: inherit;"
	                                                       data-options="required:true"
	                                                       class="easyui-textbox easyui-validatebox"></td>
				    </tr>
				    <tr>
				      <td>行业类别</td>
				      <td><select class='easyui-combobox' id="industry" required="true" editable="false" style="height: 35px">
			              </select>
	                  </td>
	                  <td>其他</td>
	                  <td><input id="industry_other" style="height: 35px"
	                                                       data-options="required:true"
	                                                       class="easyui-textbox easyui-validatebox">
	                  </td>
				    </tr>
				    <tr>
				      <td>市</td>
				      <td><select class='easyui-combobox' id="unit_city" required="true" editable="false" style="height: 35px">
			              </select>
	                  </td>
	                  <td>区县</td>
	                  <td><select class='easyui-combobox' id="unit_county" required="true" editable="false" style="height: 35px">
			              </select>
	                  </td>
				    </tr>
				    <tr>
				      <td>单位地址</td>
				      <td colspan="3" ><input id="unit_address" name="unit_address" style="height: 35px"
	                                                       class="easyui-textbox easyui-validatebox"></td>
				    </tr>
				  </tbody>
				</table>
		</div>
		
		<div style="float: left;margin: 18px;height: 85%">
			   	<div class="layui-input-inline">
			     	<input type="text" name="search_edit_unitName" id="search_edit_unitName" placeholder="选择直管单位" class="layui-input">
			   	</div>
			   	<div class="layui-input-inline">
					<div class="layui-btn layui-btn-normal" id="search_unitName_btn"><i class="layui-icon">&#xe615;</i>搜索</div>
				</div>
				<div id="parentUnitZtree" class="zTree"></div>
		</div>
    </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script type="text/javascript">
$.fn.combobox.defaults.icons=[{
    iconCls:'icon-clear',
    handler:function(e){
        $(e.handleObj.data.target).combobox('clear');
    }
}];
</script>
</html>