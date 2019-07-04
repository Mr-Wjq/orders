setting_tool = {
	datagrid_height:function(){
		var height = ($(window).height()-250)+"px";
		$("#setting_grid_height").height(height);
	},
	init_grid:function(){
		$('#setting_grid').datagrid({
            url:getRootPath() + "/system/selectFirstPage",
            method: 'post',
            idField: "id",
            fitColumns: true,
            nowrap:false,
            toolbar: '#confing-tool-bar',
            rownumbers: true,
            animate: true,
            singleSelect: true,
            fit: true,
            border: false,
            pagination: false,
            striped: true,
            /*pagePosition: "bottom",
            pageNumber: 1,
            pageSize: 20,
            pageList: [20, 30, 45, 60],*/
            columns: [[
                {title: "选择", field: "id", checkbox: true},
                {title: "首页名称", field: "firstPageName", width: 200},
                {title: "首页路径", field: "firstPagePath", width: 400},
                {title: "配置说明", field: "configDetails", width: 400},
                {title: "创建时间", field: "createTime", formatter: function (value) {
                        return common_tool.timeDate(value);
                    }, width: 100
				},
                {title: "修改时间", field: "updateTime", formatter: function (value) {
                        return common_tool.timeDate(value);
                    }, width: 100
                },
            ]]
        });
	},
	config_edit_view:function(type){
		var title;
		if (type == 1){
            title = "添加"
		} else {
            title = "修改"
		}
		layer.open({
			area: ['720px', '400px']
			,skin: 'yourclass'
			,icon:3
			,title:title
			,type: 1
			,closeBtn:1
			,content:$("#edit_config")
			,success:function(){
				if(type==2){
					var datas = $("#setting_grid").datagrid('getChecked');
					$("#setting_edit_form").form('load', {
						id: datas[0].id,
						firstPageName: datas[0].firstPageName,
						firstPagePath: datas[0].firstPagePath,
						configDetails: datas[0].configDetails
					});
				}
			}
			,btn: ['保存','清除', '取消']
			,yes: function(index){
				if (type == 1) {
                	setting_tool.save();
                	layer.close(index);
                }
                if (type == 2) {
                	setting_tool.update();
                	layer.close(index);
                }
			}
			,btn2: function(){
				setting_tool.form_clear();
				return false;
			}
			,end: function(){
				setting_tool.form_clear();
				$('#edit_config').hide();
			}
		});
	},

	save:function(){
		var form_isValid = $("#setting_edit_form").form('validate');
		if(!form_isValid){
			common_tool.messager_show("请检查输入项是否都正确!")
		}else{
			var firstPageName = $("#firstPageName").val();
			var firstPagePath = $("#firstPagePath").val();
			var configDetails = $("#configDetails").val();
			 $.ajax({
	                data: {
                        firstPageName: firstPageName,
                        firstPagePath: firstPagePath,
	                	configDetails: configDetails
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/system/insertFirstPage',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                    	layer.closeAll();
	                        setting_tool.form_clear();
	                        setting_tool.init_grid();
	                        common_tool.messager_show(result.msg);
	                        return false;
	                    }
	                    else {
	                        common_tool.messager_show(result.msg);
	                    }
	                },
	            });
		}
	},
	update:function(){
		var form_isValid = $("#setting_edit_form").form('validate');
		if(!form_isValid){
			common_tool.messager_show("请检查输入项是否都正确!")
		}else{
			var id = $('#edit_config input[id="id"]').val();
			var firstPageName = $("#firstPageName").val();
			var firstPagePath = $("#firstPagePath").val();
			var configDetails = $("#configDetails").val();
			 $.ajax({
	                data: {
	                	id:id,
	                	firstPageName: firstPageName,
	                	firstPagePath: firstPagePath,
	                	configDetails: configDetails
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/system/updateFirstPage',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                    	layer.closeAll();
	                        setting_tool.form_clear();
	                        setting_tool.init_grid();
	                        common_tool.messager_show(result.msg);
	                        return false;
	                    }
	                    else {
	                        common_tool.messager_show(result.msg);
	                    }
	                },
	            });
		}
	},
	delete:function(ids){
		 $.ajax({
	            data: {
	                ids: ids,
	            },
	            traditional: true,
	            method: 'get',
	            url: getRootPath() + '/system/deletFirstPage',
	            async: false,
	            dataType: 'json',
	            success: function (result) {
	                if (result.code == 10000) {
	                	setting_tool.form_clear();
                        setting_tool.init_grid();
                        common_tool.messager_show(result.msg);
	                    return false;
	                }
	                else {
	                    common_tool.messager_show(result.msg);
	                }
	            },
	        });
	},
	form_clear:function(){
		$("#setting_edit_form").form('reset');
        $("#setting_edit_form").form('clear');
	}
}

$(document).ready(function () {
	setting_tool.datagrid_height();
	setting_tool.init_grid();
	//刷新
	$("#confing-flash-btn").click(function () {
        setting_tool.init_grid();
	});
	//新增
	$("#confing-save-btn").click(function () {
		$('#firstPageName').textbox('readonly',false);
		setting_tool.config_edit_view(1);
    });
	//修改
	$("#confing-update-btn").click(function () {
		$('#firstPageName').textbox('readonly',true);
		var datas = $("#setting_grid").datagrid('getChecked');
		if (datas.length == 0) {
	            common_tool.messager_show("请选择一条记录");
	            return false;
	    }
        setting_tool.config_edit_view(2);
	});

	//删除
	$("#confing-delete-btn").click(function () {
        var users = $("#setting_grid").datagrid('getChecked');
        if (users.length == 0) {
            common_tool.messager_show("请选择一条记录");
            return false;
        }
        layer.confirm("您确认删除该条记录吗? ", {icon: 3, title:'删除'}, function(index){
        	var ids = "";
        	for (var i = 0; i < users.length; i++) {
				ids +=","+users[i].id
			}
        	setting_tool.delete(ids.substring(1));
        });
    });
})
