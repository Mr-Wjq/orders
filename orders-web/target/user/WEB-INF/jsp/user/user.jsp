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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/datagrid-groupview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/customValidation.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree_v3/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/user.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
    	.textbox .textbox-text{
    		height:36px !important;
    		line-hegiht:36px !important;
    	}
    	#roleZtree li span.button.ico_open {
		    margin-right: 2px;
		    background: url(/static/css/images/group.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
		#roleZtree li span.button.ico_docu {
		    margin-right: 2px;
		    background: url(/static/css/images/user_gray.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
		#unitZtree li span.button.ico_open {
		    margin-right: 2px;
		    background: url(/static/css/images/building.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
		#unitZtree li span.button.ico_docu {
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
			   	<label class="layui-form-label">登录名:</label>
			   	<div class="layui-input-inline">
			     	<input type="text" name="search_unit_userName" id="search_unit_userName" class="layui-input">
			   	</div>
			</div>
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
		    <div class="layui-btn layui-btn-primary" id="unit_flash_btn"><i class="layui-icon">&#x1002;</i>刷新</div>
		    <shiro:hasPermission name="unitUser:insert">
			    <div class="layui-btn layui-btn-primary" id="unit_save_btn"><i class="layui-icon">&#xe654;</i>新增</div>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="unitUser:delete">
			    <div class="layui-btn layui-btn-primary" id="unit_delete_btn"><i class="layui-icon">&#xe640;</i>删除</div>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="unitUser:update">
			    <div class="layui-btn layui-btn-primary" id="unit_update_btn"><i class="layui-icon">&#xe642;</i>修改</div>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="unitUser:updatePassword">
			    <div class="layui-btn layui-btn-primary" id="unit_password_btn"><i class="layui-icon">&#xe631;</i>重置密码</div>
		    </shiro:hasPermission>
		</div>
   	</form>
</div>

<div id="unitUser_grid" style="display:none;"></div>

<div id="user_edit_dialog" style="display:none;">
    <form id="user_form">
        <input type="hidden" name="userId" id="userId">
        <div style="float: left;height: 290px;margin: 10px;">
	        <table class="layui-table">
				  <tbody>
				    <tr>
				      <td>登录名</td>
				      <td><input name="loginName" id="loginName" style="width: 200px;height: 35px" autocomplete="off"
	                                                       data-options="required:true,validType:'loginName'"
	                                                       class="easyui-textbox easyui-validatebox"></td>
				    </tr>
				    <tr>
				      <td>密码</td>
				      <td><input name="password" type="password" id="password" autocomplete="off"
	                                                             style="width: 200px;height: 35px"
	                                                             data-options="required:true,validType:'pwd'"
	                                                             class="easyui-textbox easyui-validatebox"></td>
				    </tr>
				    <tr>
				      <td>确认密码</td>
				      <td><input name="equalToPwd" type="password" id="equalToPwd" autocomplete="off"
	                                                             style="width: 200px;height: 35px"
	                                                             data-options="required:true,validType:'equalToPwd[\'#password\']'"
	                                                             class="easyui-textbox easyui-validatebox"></td>
				    </tr>
				    <tr>
				      <td>用户姓名</td>
				      <td><input name="zhName" type="text" id="zhName" autocomplete="off"
	                                                             style="width: 200px;height: 35px"
	                                                             required="true"
	                                                             class="easyui-textbox easyui-validatebox"></td>
				    </tr>
					<tr>
				      <td>电话</td>
				      <td><input name="phone" type="text" id="phone" autocomplete="off"
	                                                             style="width: 200px;height: 35px"
	                                                             class="easyui-textbox easyui-validatebox"></td>
				    </tr>
					<tr>
				      <td>邮箱</td>
				      <td><input name="email" type="text" id="email"
	                                                             style="width: 200px;height: 35px"
	                                                             class="easyui-textbox easyui-validatebox"></td>
			    </tr>
			  </tbody>
			</table>
		</div>

		<div style="float: left;width:30%;height: 290px;margin: 10px;">
		   	选择单位
			<hr class="layui-bg-red">
		   	<div class="layui-input-inline">
		     	<input type="text" name="search_edit_unitName" id="search_edit_unitName" placeholder="单位名称" class="layui-input">
		   	</div>
		   	<div class="layui-input-inline">
				<div class="layui-btn layui-btn-normal" id="search_unitName_btn"><i class="layui-icon">&#xe615;</i>搜索</div>
			</div>
			<div id="unitZtree" class="zTree"></div>
		</div>
		<div style="float: right;width:30%;height: 290px;margin: 10px 20px 0 0;">
			选择角色
			<hr class="layui-bg-red">
			<div id="roleZtree" class="zTree"></div>
		</div>
		<!-- <div class="maskLayer"></div> -->
    </form>
</div>


<div id="unit_password_edit_dialog">
    <form id="init_unit_Password_form">
        <div style="margin: 10px;">
           <table class="layui-table">
			  <tbody>
			    <tr>
			      <td>密码</td>
			      <td><input name="unit_new_password" type="password" id="unit_new_password" 
                                                             style="width: 300px;height: 35px"
                                                             data-options="required:true,validType:'pwd'"
                                                             class="easyui-textbox easyui-validatebox"></td>
			    </tr>
			    <tr>
			      <td>重复密码</td>
			      <td><input name="equal_unit_new_password" type="password" id="equal_unit_new_password" 
                                                             style="width: 300px;height: 35px"
                                                             required="true" validType="equalToPwd['#unit_new_password']"
                                                             class="easyui-textbox easyui-validatebox"></td>
			    </tr>
			  </tbody>
			</table>
        </div>
    </form>
</div>
</body>
<input type="hidden" id="roleIdFromUrl" value="${roleIdFromUrl }" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script type="text/javascript">
$.fn.combobox.defaults.icons=[{
    iconCls:'icon-clear',
    handler:function(e){
        //alert($(e.handleObj.data.target).combobox('getValue'));
        $(e.handleObj.data.target).combobox('clear');
    }
}];
</script>
</html>