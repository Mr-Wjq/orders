role_tool = {
    form_clear: function () {
        $("#role_edit_form").form("clear");
        $("#role-permissions").datagrid("uncheckAll");
        $("#role_grid").datagrid("uncheckAll");
    },
    //初始化页面+加载数据
    init_main_view: function () {
        $('#role_grid').treegrid({
            url:getRootPath() + "/role/selectRoleTreeList",
            method: 'get',
            idField: "id",
            treeField: 'name',
            fitColumns: true,
            toolbar: '#role-tool-bar',
            rownumbers: true,
            animate: true,
            singleSelect: true,
            fit: true,
            border: false,
            pagination: false,
            striped: true,
            pagePosition: "bottom",
            pageNumber: 1,
            pageSize: 15,
            pageList: [15, 30, 45, 60],
            columns: [[
                {title: "选择", field: "ck", checkbox: true},
                {title: "名称", field: "name", width: 270},
                {title: "首页", field: "firstPage", width: 270},
                {title: "说明", field: "description", width: 400},
                {
                    title: "是否可修改", field: "isFinal", formatter: function (value) {
                    if (value == 1) {
                        return "是";
                    }
                    if (value == 2) {
                        return "否";
                    }
                }, width: 80
                },
                {
                    title: "创建时间", field: "createTime", formatter: function (value) {
                    return common_tool.timestampToDateTime(value);
                }, width: 150
                },
                {
                    title: "更新时间", field: "updateTime", formatter: function (value) {
                    return common_tool.timestampToDateTime(value);
                }, width: 150
                },
            ]],
            onLoadSuccess :function(){
            	$('#role_grid').datagrid('clearSelections');
            }
        });
    },
    delete: function () {
        if ($("#role_grid").datagrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
        }
        var roleId = $("#role_grid").datagrid("getChecked")[0].id;
        $.ajax({
            data: {
                id: roleId,
            },
            traditional: true,
            method: 'post',
            url: getRootPath() + '/role/delete',
            async: false,
            dataType: 'json',
            success: function (result) {
                if (result.code == 10000) {
                    role_tool.init_main_view();
                    $("#user_role").treegrid("reload");
                    common_tool.messager_show(result.msg);
                    layer.confirm('是否现在刷新网页,以便获取最新数据?', {icon: 3, title:'提示'}, function(index){
              		  top.window.location.reload();
              		  layer.close(index);
                    });
                    return false;
                }
                else {
                    common_tool.messager_show(result.msg);
                }
            },
        });
    },
    save: function () {
    	var roleZtreeObj = $.fn.zTree.getZTreeObj("role_ztree");
    	var permissionsTreeObj = $.fn.zTree.getZTreeObj("permissions_ztree");
    	var roleNodes = roleZtreeObj.getCheckedNodes(true);
    	var permissionsNodes = permissionsTreeObj.getCheckedNodes(true);
    	if (!$("#role_edit_form input[id='name']").validatebox('isValid')) {
            common_tool.messager_show("请输入角色名称");
        } else if (!$("#role_edit_form input[id='description']").validatebox('isValid')) {
            common_tool.messager_show("请输入角色描述");
        } else if (roleNodes.length == 0) {
             common_tool.messager_show('请为该角色分配父级角色');
        } else if (permissionsNodes.length == 0) {
            common_tool.messager_show("请为该角色选择权限");
        } else {
            var name = $("#role_edit_form input[id='name']").val();
            var description = $("#role_edit_form input[id='description']").val();
            var firstPageId = document.getElementById("firstPageId").value;
            var permission_ids = new Array();
            for (var i = 0; i < permissionsNodes.length; i++) {
                permission_ids[i] = permissionsNodes[i].id;
            }
            var parentId  = roleNodes[0].id;
            $.ajax({
                data: {
                    name: name,
                    description: description,
                    firstPageId: firstPageId,
                    parentId: parentId,
                    permissionIds: permission_ids.toString(),
                },
                traditional: true,
                method: 'post',
                url: getRootPath() + '/role/insert',
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 10000) {
                    	layer.msg(result.msg,{time: 1000},function(){
                    		layer.closeAll();
                    		role_tool.form_clear();
                    		role_tool.init_main_view();
                    		$("#user_role").treegrid("reload");
                    		layer.confirm('是否现在刷新网页,以便获取最新数据?', {icon: 3, title:'提示'}, function(index){
                        		  top.window.location.reload();
                        		  layer.close(index);
                          	});
                    	});
                    	
                        return false;
                    }
                    else {
                        common_tool.messager_show(result.msg);
                    }
                },
            });
        }
    },
    update: function () {
    	var roleZtreeObj = $.fn.zTree.getZTreeObj("role_ztree");
    	var permissionsTreeObj = $.fn.zTree.getZTreeObj("permissions_ztree");
    	var roleNodes = roleZtreeObj.getCheckedNodes(true);
    	var permissionsNodes = permissionsTreeObj.getCheckedNodes(true);
        if (!$("#role_edit_form input[id='name']").validatebox('isValid')) {
            common_tool.messager_show("请输入角色名称");
        } else if (!$("#role_edit_form input[id='description']").validatebox('isValid')) {
            common_tool.messager_show("请输入角色描述");
        } else if (roleNodes.length == 0 && $("#role_edit_form input[id='id']").val()!=1) {
             common_tool.messager_show('请为该角色分配父级角色');
        } else if (permissionsNodes.length == 0) {
            common_tool.messager_show("请为该角色选择权限");
        }  else {
        	var id = $("#role_edit_form input[id='id']").val();
            var name = $("#role_edit_form input[id='name']").val();
            var description = $("#role_edit_form input[id='description']").val();
            var firstPageId = document.getElementById("firstPageId").value;
            var permission_ids = new Array();
            for (var i = 0; i < permissionsNodes.length; i++) {
                permission_ids[i] = permissionsNodes[i].id;
            }
            var parentId  = "";
            if(id==1){
            	 parentId  = "0";
            }else{
            	 parentId  = roleNodes[0].id
            }
            $.ajax({
                data: {
                    id: id,
                    name: name,
                    parentId: parentId,
                    description: description,
                    firstPageId: firstPageId,
                    permissionIds: permission_ids.toString(),
                },
                traditional: true,
                method: 'post',
                url: getRootPath() + '/role/update',
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 10000) {
                    	layer.msg(result.msg,{time: 1000},function(){
                    		layer.closeAll();
                    		role_tool.form_clear();
                    		role_tool.init_main_view();
                    		$("#user_role").treegrid("reload");
                    		layer.confirm('是否现在刷新网页,以便获取最新数据?', {icon: 3, title:'提示'}, function(index){
                      		  top.window.location.reload();
                      		  layer.close(index);
                        	});
                    	});
                        return;
                    }
                    else {
                        common_tool.messager_show(result.msg);
                    }
                },
            });
        }
    },
    init_edit_view: function (type) {
    	if ('1' == type) {
			var title = '添加角色';
		}else {
			var title = '修改角色';
		}
    	layer.open({
			area: ['890px', '620px']
			,skin: 'yourclass'
			,icon:3
			,zIndex:1
			,title:title
			,type: 1
			,closeBtn:1
			,content:$("#role_edit_dialog")
			,success:function(){
            	permissionZtree();
            	roleZtree();
            	if (type == 2) {
            		var role = $("#role_grid").datagrid("getChecked")[0];
            		//禁用节点  选中父节点
                	var roleZtreeObj = $.fn.zTree.getZTreeObj("role_ztree");
                	var node = roleZtreeObj.getNodeByParam("id",role.id,null);
                	var parentNode = roleZtreeObj.getNodeByParam("id",role.parentId,null);
                	roleZtreeObj.setChkDisabled(node,true,false,true);
                	roleZtreeObj.selectNode(node);
                	if(role.parentId!=0){
                 		roleZtreeObj.checkNode(parentNode);
                 	}
                	//回显权限按钮
                	var permissionsZtreeObj = $.fn.zTree.getZTreeObj("permissions_ztree");
                	$.ajax({
             	       type:"get",
             	       url:getRootPath() + "/role/selectPermissionByRoleId",
             	       async:false,
             	       data:{roleId:role.id},
             	       dataType:"json",
             	       success:function(mes){
             	    	  for (var i = 0; i < mes.length; i++) {
             	    		 var node2 = permissionsZtreeObj.getNodeByParam("id",mes[i].id,null);
             	    		 permissionsZtreeObj.checkNode(node2,true,false);
             	    	  }
             	       }
             	    });
                	//回显首页信息
                	$.ajax({
             	       type:"post",
             	       url:getRootPath() + "/role/selectFirstPageByRoleId",
             	       async:false,
             	       data:{roleId:role.id},
             	       dataType:"json",
             	       success:function(mes){
                           var val= mes.firstPageId;//根据后台传值来动态改变它
                           var last = $("#firstPageId>option[value='"+val+"']");
                           last.attr("selected","selected");
             	       }
             	    });
                }
			}
			,btn: ['保存','清除', '取消']
			,yes: function(index){
				if (type == 1) {
                    role_tool.save();
                }
                if (type == 2) {
                    role_tool.update();
                }
			}
			,btn2: function(){
				role_tool.form_clear();
				return false;
			}
			,end: function(){
				role_tool.form_clear();
				$('#role_edit_dialog').hide();
			}
		});
    },
    selectFirstPage : function () {
        $.ajax({
            type:"post",
            url:getRootPath() + "/system/selectFirstPage",
            async:false,
            data:{},
            dataType:"json",
            success:function(mes){
                $("#firstPageId>option").remove();
                var data = mes.rows;
                for (var i = 0; i < data.length; i++) {
                    $("#firstPageId").append("<option value= "+data[i].id +">"+data[i].firstPageName+"</option>");
                }
            }
        });
    }
};
$(document).ready(function () {
    role_tool.init_main_view();
    $("#role-select-btn").click(function () {
        role_tool.form_clear();
        role_tool.init_main_view();
    });

    $("#role-save-btn").click(function () {
        role_tool.init_edit_view(1);
        role_tool.selectFirstPage();
    });

    $("#role-update-btn").click(function () {
        if ($("#role_grid").datagrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
            return false;
        }
        var role = $("#role_grid").datagrid("getChecked")[0];
        $("#role_edit_form").form('load', {
            id: role.id,
            name: role.name,
            description: role.description,
        })
        role_tool.selectFirstPage();
        role_tool.init_edit_view(2);


    });
    $("#role-delete-btn").click(function () {
        if ($("#role_grid").datagrid("getChecked").length == 0) {
            common_tool.messager_show("请选择一条记录");
            return false;
        }
        layer.confirm("您确认删除该条记录吗? ", {icon: 3, title:'删除'}, function(index){
        	role_tool.delete();
        });
    });
})

function permissionZtree(){
	/* 用于展现 zTree 的 DOM 容器 */
    var obj = $("#permissions_ztree");
	
	 /*配置节点信息 */    
	 var zSetting = {
	        data:{
	            simpleData:{
	                enable:true,
	                idKey:"id",
	                pIdKey:"pid"
	            },
	            key:{
	               url:'_url'
	           }
	        }
	 		,check:{
	 			enable: true,
	 			chkStyle: "checkbox",
	 			chkboxType: { "Y" : "ps", "N" : "ps" }
	 		}
	        /* 点击节点事件 */
	       /* ,callback: {
	          onClick: zTreeOnClick
	      }*/
	    };
	   
	   /*获取节点数据 */
	  // var uid = "${user.uid}";
	   $.ajax({
	       type:"get",
	       url:getRootPath() + "/permission/ztreeListByRoleId",
	       async:false,
	       //data:{uid:uid},
	       dataType:"json",
	       success:function(mes){
	           zNodes = mes;
	       }
	   });
	  
	  /* zTree 初始化方法 */ 
	  $.fn.zTree.init(obj, zSetting, zNodes);
	
	
	/* 展开全部节点 */
	var treeObj = $.fn.zTree.getZTreeObj("permissions_ztree");
	treeObj.expandAll(true);
}

function roleZtree(){
	/* 用于展现 zTree 的 DOM 容器 */
    var obj = $("#role_ztree");
	
	 /*配置节点信息 */    
	 var zSetting = {
	        data:{
	            simpleData:{
	                enable:true,
	                idKey:"id",
	                pIdKey:"parentId"
	            },
	            key:{
	               url:'_url'
	           }
	        }
	 		,check:{
	 			enable: true,
	 			chkStyle: "radio",
	 			radioType:"all"
	 		}
	        /* 点击节点事件 */
	       /* ,callback: {
	          onClick: zTreeOnClick
	      }*/
	    };
	   
	   /*获取节点数据 */
	  // var uid = "${user.uid}";
	   $.ajax({
	       type:"get",
	       url:getRootPath() + "/role/listIncludePermission",
	       async:false,
	       dataType:"json",
	       success:function(mes){
	           zNodes = mes;
	       }
	   });
	  
	  /* zTree 初始化方法 */ 
	  $.fn.zTree.init(obj, zSetting, zNodes);
	
	
	/* 展开全部节点 */
	var treeObj = $.fn.zTree.getZTreeObj("role_ztree");
	treeObj.expandAll(true);
}