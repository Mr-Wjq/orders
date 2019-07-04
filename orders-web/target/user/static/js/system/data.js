data_tool = {
    form_clear: function () {
        $("#save-data-form").form('clear');
        $("#save-data-group-form").form('clear');
        $("#data_grid").treegrid("uncheckAll");
        $("#data-group").datagrid("uncheckAll");
    },
    delete_data: function () {
        if ($("#data_grid").treegrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
        } else {
            var id = $("#data_grid").treegrid("getChecked")[0].id;
            $.ajax({
                data: {
                    id: id,
                },
                method: 'get',
                url: getRootPath() + '/system/data/delete',
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 10000) {
                        data_tool.init_main_view();
                        return false;
                    }
                    else {
                        common_tool.messager_show(result.msg);
                    }
                },
            });
        }
    },
    validatebox: function () {
        if (!$("#save-data-dialog input[id='data_keyName']").validatebox('isValid')) {
            common_tool.messager_show("请输入权限名称");
            return false;
        } else if (!$("#save-data-dialog input[id='data_keyValue']").validatebox('isValid')) {
            common_tool.messager_show("请输入权限编码");
            return false;
        }
        else if (!$("#save-data-dialog input[id='data_description']").validatebox('isValid')) {
            common_tool.messager_show("请输入权限描述");
            return false;
        }
        else if ($("#data-group").treegrid("getChecked").length == 0) {
            common_tool.messager_show("请选择权限组");
            return false;
        }
        return true;
    },
    save_data: function () {
        if (!data_tool.validatebox()) {
            return false;
        }
        var name = $("#save-data-dialog input[id='data_keyName']").val();
        var code = $("#save-data-dialog input[id='data_keyValue']").val();
        var description = $("#save-data-dialog input[id='data_description']").val();
        var groupId = $("#data-group").treegrid("getChecked")[0].id;
        $.ajax({
            data: {
                key: name,
                value: code,
                description: description,
                groupId: groupId,
            },
            method: 'post',
            url: getRootPath() + '/system/data/insert',
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.code == 10000) {
                    layer.closeAll();
                    data_tool.form_clear();
                    data_tool.init_main_view();
                    return false;
                }
                else {
                    common_tool.messager_show(result.msg);
                }
            },
        });
    },
    update_data: function () {
        if (!data_tool.validatebox()) {
            return false;
        }
        var id = $("#save-data-dialog input[id='id']").val();
        var key = $("#save-data-dialog input[id='data_keyName']").val();
        var value = $("#save-data-dialog input[id='data_keyValue']").val();
        var description = $("#save-data-dialog input[id='data_description']").val();
        var groupId = $("#data-group").treegrid("getChecked")[0].id;
        $.ajax({
            data: {
                id: id,
                key: key,
                value: value,
                description: description,
                groupId: groupId,
            },
            method: 'post',
            url: getRootPath() + '/system/data/update',
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.code == 10000) {
                    layer.closeAll();
                    data_tool.form_clear();
                    data_tool.init_main_view();
                    return false;
                }
                else {
                    common_tool.messager_show(result.msg);
                }
            },
        });
    },
    insert_data_group: function () {
        if (!$("#save-data-group-dialog input[id='data_group_name']").validatebox('isValid')) {
            common_tool.messager_show("请输入名称");
        } else if (!$("#save-data-group-dialog input[id='data_group_description']").validatebox('isValid')) {
            common_tool.messager_show("请输入描述");
        } else {
            var group_name = $("#save-data-group-dialog  input[id='data_group_name']").val();
            var group_description = $("#save-data-group-dialog input[id='data_group_description']").val();
            $.ajax({
                data: {
                    name: group_name,
                    description: group_description,
                },
                method: 'post',
                url: getRootPath() + '/system/dataGroup/insert',
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 10000) {
                    	layer.closeAll();
                    	data_tool.form_clear();
                        data_tool.init_main_view();
                        return false;
                    }
                    else {
                        common_tool.messager_show(result.msg);
                    }
                },
            });
        }
    },
    init_edit_group_view: function () {
    	layer.open({
			area: ['395px', '322px']
			,skin: 'yourclass'
			,icon:3
			,title:'新增字典组'
			,type: 1
			,zIndex:1
			,closeBtn:1
			,content:$("#save-data-group-dialog")
			,success:function(){
				
			}
			,btn: ['保存','清除', '取消']
			,yes: function(index){
				data_tool.insert_data_group();
			}
			,btn2: function(){
				data_tool.form_clear();
				return false;
			}
			,end: function(){
				data_tool.form_clear();
				$('#save-data-group-dialog').hide();
			}
		});
    },
    init_edit_view: function (type, groupId) {
    	data_tool.getDataGroup();
    	if (type == "1") {
			var title = "新增字典";
		}else{
			var title = "修改字典";
		}
    	layer.open({
			area: ['790px', '430px']
			,skin: 'yourclass'
			,icon:3
			,zIndex:1
			,title:title
			,type: 1
			,closeBtn:1
			,content:$("#save-data-dialog")
			,success:function(){
				data_tool.getDataGroup();
			}
			,btn: ['保存','清除', '取消']
			,yes: function(index){
				if (type == 1) {
                    data_tool.save_data();
                }
                if (type == 2) {
                    data_tool.update_data();
                }
			}
			,btn2: function(){
				data_tool.form_clear();
				return false;
			}
			,end: function(){
				data_tool.form_clear();
				$('#save-data-dialog').hide();
			}
		});
    },
    init_main_view: function () {
        $("#data_grid").datagrid({
            url: getRootPath() + "/system/data/list",
            method: 'get',
            view: groupview,
            groupField: 'sysDataGroupId',
            groupFormatter: function (value, rows) {
                return rows[0].sysDataGroupName;
            },

            toolbar: '#data-tool-bar',
            rownumbers: true,
            singleSelect: true,
            animate: true,
            fitColumns: true,
            fit: true,
            border: false,
            pagePosition: "bottom",
            pageNumber: 1,
            pageSize: 25,
            pageList: [25, 50, 75, 100],
            pagination: true,
            striped: true,
            columns: [[
                {title: "选择", field: "ck", checkbox: true},
                {title: "key", field: "keyName", width: 200},
                {title: "value", field: "keyValue", width: 200},
                {title: "说明", field: "description", width: 200},
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
        });
    },
    getDataGroup:function(){ //获取角色
    	$('#data-group').datagrid({
            url:getRootPath() + '/system/dataGroup/list',
            method: 'get',
            rownumbers: true,
            fitColumns: true,
            singleSelect:true,
            columns: [[
                {title: "选择", field: "id", checkbox: true},
                {title: "字典组名称", field: "name", width: 200},
            ]]
        });
    }
};
$(document).ready(function () {
    data_tool.init_main_view();
    $("#flash-data").click(function () {
        data_tool.form_clear();
        data_tool.init_main_view();
    });
    $("#save-data-data").click(function () {
        data_tool.init_edit_view(1);
    });
    $("#save-data-group").click(function () {
        data_tool.init_edit_group_view()
    });
    $("#delete-data").click(function () {
        data_tool.delete_data();
    });
    $("#update-data").click(function () {
        if ($("#data_grid").treegrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
            return false;
        }
        var data = $("#data_grid").datagrid('getChecked')[0];
        $("#save-data-form").form('load', {
            'id': data.id,
            'data_keyName': data.keyName,
            'data_keyValue': data.keyValue,
            'data_description': data.description,
        });
        data_tool.init_edit_view(2, data.sysDataGroupId);
    });
});