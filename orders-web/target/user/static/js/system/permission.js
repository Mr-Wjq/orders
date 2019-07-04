permission_tool = {
    form_clear: function () {
        $("#save-permission-form").form('clear');
    },
    delte_permission: function () {
        if ($("#permission_grid").treegrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
        } else {
        	layer.confirm('确定要删除该节点以及子节点的数据吗？', {icon: 3, title:'删除'}, function(index){
        		var id = $("#permission_grid").treegrid("getChecked")[0].id;
                $.ajax({
                    data: {
                        id: id,
                    },
                    method: 'get',
                    url: getRootPath() + '/permission/delete',
                    async: false,
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == 10000) {
                            permission_tool.init_main_view();
                            return false;
                        }
                        else {
                            common_tool.messager_show(result.msg);
                        }
                    },
                });
        		layer.close(index);
        	});
        }
    },
    init_main_view: function () {
        $('#permission_grid').treegrid({
            url:getRootPath() + "/permission/list",
            method: 'get',
            idField:'id',
            treeField:'name',
            rownumbers: true,
            fitColumns: true,
            singleSelect:true,
            columns: [[
                {title: "选择", field: "id", checkbox: true},
                {title: "名称", field: "name", width: 200},
                {title: "编码", field: "code", width: 200},
                {title: "说明", field: "description", width: 400},
                {
                    title: "是否可修改", field: "isFinal", formatter: function (value, row, index) {
                    if (value == 1) {
                        return "是";
                    }
                    if (value == 2) {
                        return "否";
                    }
                }, width: 60
                },
                {
                    title: "创建时间", field: "createTime", formatter: function (value, row, index) {
                    return common_tool.timestampToDateTime(value);
                }, width: 100
                },
                {
                    title: "更新时间", field: "updateTime", formatter: function (value, row, index) {
                    return common_tool.timestampToDateTime(value);
                }, width: 100
                },
            ]],
            onLoadSuccess :function(){
            	$('#permission_grid').datagrid('clearSelections');
            }
        });
    },
}

$(document).ready(function () {
    permission_tool.init_main_view();
    $("#flash-permission").click(function () {
        permission_tool.form_clear();
        permission_tool.init_main_view();
    });
    $("#delete-permission").click(function () {
        permission_tool.delte_permission();
    });
    /*$("#update-permission").click(function () {
        if ($("#permission_grid").treegrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
            return false;
        }
        var permission = $("#permission_grid").datagrid('getChecked')[0];
        $("#save-permission-form").form('load', {
            'id': permission.id,
            'permission_name': permission.name,
            'permission_code': permission.code,
            'permission_description': permission.description,
        });
        permission_tool.init_edit_view(2, permission.sysPermissionGroupId);
    });*/

});
function showPermission(id){
	$('#'+id).treegrid({
        url:getRootPath() + "/permission/list",
        method: 'get',
        height:600,
        idField:'id',
        treeField:'name',
        rownumbers: true,
        fitColumns: true,
        singleSelect:true,
        columns: [[
            {title: "选择", field: "id", checkbox: true},
            {title: "选择所属权限", field: "name", width: 200},
        ]]
    });
}

function insertPermissionShow(){
	permission_tool.form_clear();
	layer.open({
        type: 1
        ,title: '添加权限' //不显示标题栏
        ,area: ['50%','80%']
        /*,id: 'LAY_layuipro' //设定一个id，防止重复弹出*/
        ,btn: ['添加', '取消']
        ,btnAlign: 'c'
        ,zIndex:0
        ,moveType: 1 //拖拽模式，0或者1
        ,content: $("#save-permission-dialog")
        ,yes: function(index, layero){
        	insertPermission(index);
        }
        ,btn2: function(index, layero){
        	$("#save-permission-dialog").hide();
        	    //return false 开启该代码可禁止点击该按钮关闭
        }
        ,cancel: function(index, layero){ 
        	$("#save-permission-dialog").hide();
        	/*if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
        	    layer.close(index)
        	  }
        	  return false; */
        }  
      });
	showPermission('permission_list');
	$('#permission_list').datagrid("clearSelections");  
}

function insertPermission(index){
	var b = $('#save-permission-form').form("enableValidation").form('validate');
	if(!b){
		common_tool.messager_show('必输项不能为空！');
		return;
	}
	if ($("#permission_list").treegrid("getChecked").length == 0) {
        common_tool.messager_show("请选择所属权限");
        return;
    }
	var name = $("#permission_name").val();
    var code = $("#permission_code").val();
    var description = $("#permission_description").val();
    var pid = $("#permission_list").treegrid("getChecked")[0].id;
    $.ajax({
        data: {
            name: name,
            code: code,
            description: description,
            pid: pid,
        },
        method: 'post',
        url: getRootPath() + '/permission/insert',
        async: false,
        dataType: 'json',
        success: function (result) {
            if (result.code == 10000) {
            	layer.close(index);
                permission_tool.form_clear();
                permission_tool.init_main_view();
                common_tool.messager_show('添加成功');
                $("#save-permission-dialog").hide();
            }
            else {
                common_tool.messager_show(result.msg);
            }
        },
    });
}


function updatePermissionShow(){
	if ($("#permission_grid").treegrid("getChecked").length == 0) {
        common_tool.messager_show("请选择一条记录");
        return false;
    }else{
    	var permission = $("#permission_grid").datagrid('getChecked')[0];
    	if(2==permission.isFinal){
    		 common_tool.messager_show("该记录不可进行操作");
    		 return false;
    	}
    }
	layer.open({
        type: 1
        ,title: '修改权限' //不显示标题栏
        ,area: ['50%','80%']
        /*,id: 'LAY_layuipro' //设定一个id，防止重复弹出*/
        ,btn: ['修改', '取消']
        ,btnAlign: 'c'
        ,zIndex:0
        ,moveType: 1 //拖拽模式，0或者1
        ,content: $("#save-permission-dialog")
        ,yes: function(index, layero){
        	updatePermission(index);
        }
        ,btn2: function(index, layero){
        	$("#save-permission-dialog").hide();
        	    //return false 开启该代码可禁止点击该按钮关闭
        }
        ,cancel: function(index, layero){ 
        	$("#save-permission-dialog").hide();
        	/*if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
        	    layer.close(index)
        	  }
        	  return false; */
        }  
        ,success: function(layero, index){
            var permission = $("#permission_grid").datagrid('getChecked')[0];
            $("#save-permission-form").form('load', {
                'permission_id': permission.id,
                'permission_name': permission.name,
                'permission_code': permission.code,
                'permission_description': permission.description,
            });
            $('#permission_list').treegrid({
                url:getRootPath() + "/permission/list",
                method: 'get',
                height:600,
                idField:'id',
                treeField:'name',
                rownumbers: true,
                fitColumns: true,
                singleSelect:true,
                columns: [[
                    {title: "选择", field: "id", checkbox: true},
                    {title: "选择所属权限", field: "name", width: 200},
                ]],
                onLoadSuccess:function(){
                	$('#permission_list').treegrid('select',permission.pid);
                	$('#permission_list').treegrid('remove',permission.id);
                }
            });
        }
    });
}

function updatePermission(index){
	var b = $('#save-permission-form').form("enableValidation").form('validate');
	if(!b){
		common_tool.messager_show('必输项不能为空！');
		return;
	}
	if ($("#permission_list").treegrid("getChecked").length == 0) {
        common_tool.messager_show("请选择所属权限");
        return;
    }
	var id = $("#permission_id").val();
	var name = $("#permission_name").val();
    var code = $("#permission_code").val();
    var description = $("#permission_description").val();
    var pid = $("#permission_list").treegrid("getChecked")[0].id;
    $.ajax({
        data: {
        	id:id,
            name: name,
            code: code,
            description: description,
            pid: pid,
        },
        method: 'post',
        url: getRootPath() + '/permission/update',
        async: false,
        dataType: 'json',
        success: function (result) {
            if (result.code == 10000) {
            	layer.close(index);
            	permission_tool.form_clear();
                permission_tool.init_main_view();
                common_tool.messager_show('修改成功');
                $("#save-permission-dialog").hide();
            }
            else {
                common_tool.messager_show(result.msg);
            }
        },
    });
}