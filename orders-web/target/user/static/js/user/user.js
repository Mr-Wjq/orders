user_tool = {
	    form_clear: function () {
	        $("#user_form").form('reset');
	        $("#user_form").form('clear');
	        $("#unit_search_form").form('reset');
	        $("#unit_search_form").form('clear');
	        $("#init_unit_Password_form").form('reset');
	        $("#init_unit_Password_form").form('clear');
	        $('#industry_other').textbox("disable");
	    	$('#industry_other').validatebox("disableValidation");
	        $("#unitUser_grid").treegrid("uncheckAll");
	    },
	    init_main_view: function () {
	        var loginName = $("#search_unit_userName").val();
	    	var unitName = $("#search_unit_unitName").val();
	    	var industry = $("#search_industry").combobox('getValue');
	    	var cityAreaCode = $("#search_unit_city").combobox('getValue');
	    	var countryAreaCode = $("#search_unit_country").combobox('getValue');
	    	var roleIdFromUrl = $("#roleIdFromUrl").val();
            $("#unitUser_grid").datagrid({
                 url: getRootPath() + '/user/unitUserList',
                 method: 'post',
                 idField: "id",
                 fitColumns: true,
                 toolbar: '#unit_tool_bar',
                 rownumbers: true,
                 singleSelect:true,
                 animate: true,
                 fit: true,
                 border: false,
                 nowrap: false,
                 pagination: true,
                 striped: true,
                 pagePosition: "bottom",
                 pageNumber: 1,
                 pageSize: 15,
                 pageList: [15, 30, 45, 60],
                 queryParams: {
                     loginName: loginName,
                     unitName: unitName,
                     industry: industry,
                     cityAreaCode: cityAreaCode,
                     countryAreaCode: countryAreaCode,
                     roleId: roleIdFromUrl
                 },
                 columns: [[
                     {title: "选择", field: "loginId","rowspan":2,"align":"center", checkbox: true},
                     {title: "用户姓名", field: "zhName","rowspan":2,"align":"center", width: 124},
                     {title: "登录账号", field: "loginName","rowspan":2,"align":"center", width: 124},
                     {title: "电话", field: "phone","rowspan":2,"align":"center", width: 124},
                     {title: "邮箱", field: "email","rowspan":2,"align":"center", width: 124},
                     {title: "单位名称", field: "unitName","rowspan":2,"align":"center", width: 124},
                     {title: "所属地区", field: "diqu","colspan":2,"align":"center", width: 130},
                     {title: "角色", field: "roleName","rowspan":2,"align":"center", width: 130},
                     {title: "行业", field: "baseIndustryName","rowspan":2,"align":"center", width: 130},
                     {title: "单位地址", field: "unitAddress","rowspan":2,"align":"center", width: 130},
                     {title: "创建时间", field: "createTime","rowspan":2,"align":"center",formatter: function (value) {
                             return common_tool.timeDate(value);
                         }, width: 130
                     },
                     {title: "修改时间", field: "updateTime","rowspan":2,"align":"center",formatter: function (value) {
                             return common_tool.timeDate(value);
                         }, width: 130
                     }
                 ],
                 [
                     {title: "市", field: "unitCityName","rowspan":1,"align":"center", width: 130},
                     {title: "区县", field: "unitCountryName","rowspan":1,"align":"center", width: 130},
                 ]]
             });
	    },
	    init_edit_view: function (type) {
	    	var title = '';
	    	if ('1' == type) {
				 title = '添加单位用户';
			}else {
				 title = '修改单位用户';
			}
	    	layer.open({
				area: ['58%', '465px']
				,skin: 'yourclass'
				,icon:3
				,zIndex:1
				,title:title
                ,resize:false
				,type: 1
				,closeBtn:1
				,content:$("#user_edit_dialog")
				,success:function(){
                    user_tool.search_industry(2,"");
                    var roleId = '';
                    var unitId = '';
	                if (type == 2) {
	                	var users = $("#unitUser_grid").datagrid('getChecked');
                        $("#user_form").form('load', {
                            userId: users[0].loginId,
                            loginName: users[0].loginName,
                            zhName: users[0].zhName,
                            phone: users[0].phone,
                            email: users[0].email
                        });
                        $('#loginName').textbox("disable");
                        $('#password').textbox("disable");
                        $('#password').validatebox("disableValidation");
                        $('#equalToPwd').textbox("disable");
                        $('#equalToPwd').validatebox("disableValidation");
                        $('#industry_other').textbox("disable");
                        $('#industry_other').validatebox("disableValidation");
                        $('#unit').combobox('select',users[0].unitId);
                        roleId =  users[0].roleId;
                        unitId =  users[0].unitId;
	                }else{
	                	$("#industry").combobox("enable");
	                	$('#industry').combobox('setValue','');
                        $('#password').textbox("enable");
                        $('#password').validatebox("enableValidation");
                        roleId = $("#roleIdFromUrl").val();
	                }
                    user_tool.getUserRole(roleId);
                    user_tool.getUnitTree(unitId);
				}
				,btn: ['保存','清除', '取消']
				,yes: function(){
					 if (type == 1) {
                         user_tool.save();
                     }
                     if (type == 2) {
                         user_tool.update();
                     }
				}
				,btn2: function(){
					if (type == 1) {
						$("#unit_city").combobox('clear');
				    	$("#unit_county").combobox('clear');
				    	$("#industry").combobox('clear');
                        user_tool.form_clear();
						return false;
	                }
	                if (type == 2) {
	                	common_tool.messager_show("修改方法不可清除");
	                	return false;
	                }
				}
				,end: function(){
                    user_tool.form_clear();
					$('#user_edit_dialog').hide();
					$('.maskLayer').css('display','none');
				}
			});
	    },

		save: function () {
			var form_isValid = $("#user_form").form('validate');
			//获取选中单位数据
			var unitZtreeObj = $.fn.zTree.getZTreeObj("unitZtree");
            var unitNodes = unitZtreeObj.getCheckedNodes(true);
            //获取选中角色数据
            var roleZtreeObj = $.fn.zTree.getZTreeObj("roleZtree");
            var roleNodes = roleZtreeObj.getCheckedNodes(true);
			if (!form_isValid) {
				common_tool.messager_show("请输入必填参数")
			}else if (unitNodes.length == 0) {
				common_tool.messager_show('请选择单位');
			}else if (roleNodes.length == 0) {
				common_tool.messager_show('请选择角色');
			}
			else {
				var loginName = $('#loginName').val();
				var zhName = $('#zhName').val();
				var email = $('#email').val();
				var phone = $('#phone').val();
				var password = $("#password").val();
				var unitId = unitNodes[0].id;
				var roleId = roleNodes[0].id;
				$.ajax({
					data: {
						loginName: loginName,
						zhName: zhName,
						email: email,
						phone: phone,
						password: password,
						roleId:roleId,
						unitId:unitId
					},
					traditional: true,
					method: 'post',
					url: getRootPath() + '/user/insertUser',
					async: false,
					dataType: 'json',
					success: function (result) {
						if (result.code == 10000) {
							layer.msg(result.msg,{time: 1000},function(){
								layer.closeAll();
								user_tool.form_clear();
								user_tool.init_main_view();
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
	    	var form_isValid = $("#user_form").form('validate');
			//获取选中单位数据
			var unitZtreeObj = $.fn.zTree.getZTreeObj("unitZtree");
            var unitNodes = unitZtreeObj.getCheckedNodes(true);
            //获取选中角色数据
            var roleZtreeObj = $.fn.zTree.getZTreeObj("roleZtree");
            var roleNodes = roleZtreeObj.getCheckedNodes(true);
			if (!form_isValid) {
				common_tool.messager_show("请输入必填参数")
			}else if (unitNodes.length == 0) {
				common_tool.messager_show('请选择单位');
			}else if (roleNodes.length == 0) {
				common_tool.messager_show('请选择角色');
			}
	        else {
                var userId = $('#userId').val();
                var loginName = $('#loginName').val();
				var zhName = $('#zhName').val();
				var email = $('#email').val();
				var phone = $('#phone').val();
				var unitId = unitNodes[0].id;
				var roleId = roleNodes[0].id;
	            $.ajax({
	                data: {
                        userId: userId,
                        loginName: loginName,
						zhName: zhName,
						email: email,
						phone: phone,
						roleId:roleId,
						unitId:unitId
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/user/unitUserUpdate',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                    	layer.msg(result.msg,{time: 1000},function(){
	                    		layer.closeAll();
	                    		user_tool.form_clear();
	                    		user_tool.init_main_view();
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
	    delete: function (id) {
	        $.ajax({
	            data: {
	                id: id,
	            },
	            method: 'get',
	            url: getRootPath() + '/user/delete',
	            async: false,
	            dataType: 'json',
	            success: function (result) {
	                if (result.code == 10000) {
	                    user_tool.form_clear();
	                    user_tool.init_main_view();
	                    common_tool.messager_show(result.msg);
	                    return false;
	                }
	                else {
	                    common_tool.messager_show(result.msg);
	                }
	            },
	        });
	    },
	    password_view: function (user) {
	    	layer.open({
				area: ['465px', '230px']
				,skin: 'yourclass'
				,icon:3
				,zIndex:1
				,title:'重置 ' + user.loginName + ' 密码'
				,type: 1
				,closeBtn:1
				,content:$("#unit_password_edit_dialog")
				,success:function(){

				}
				,btn: ['保存','清除', '取消']
				,yes: function(index){
					user_tool.update_password(user.loginId);
				}
				,btn2: function(){
					user_tool.form_clear();
					return false;
				}
				,end: function(){
					user_tool.form_clear();
					$('#unit_password_edit_dialog').hide();
				}
			});
	    },
	    update_password: function (id) {
	    	var form_isValid = $("#init_unit_Password_form").form('validate');
	    	var newPassword = $("#unit_new_password").val();
	    	var reg = new RegExp(/^[A-Za-z0-9]{6,20}$/);
	        if (!form_isValid) {
	            common_tool.messager_show("请检查输入项是否都正确!")
	        }
	        else if (!reg.test(newPassword)) {
	        	common_tool.messager_show('请输入正确的密码');
			}
	        else{
		        var repeatNewPassword = $("#equal_unit_new_password").val();
		        $.ajax({
		            data: {
		                id: id,
		                newPassword: newPassword,
		                repeatNewPassword: repeatNewPassword,
		            },
		            traditional: true,
		            method: 'post',
		            url: getRootPath() + '/user/unitUpdatePassword',
		            async: false,
		            dataType: 'json',
		            success: function (result) {
		                if (result.code == 10000) {
		               //     $("#unit_password_edit_dialog").dialog("close");
		                	layer.closeAll();
		                	user_tool.form_clear();
		                    user_tool.init_main_view();
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
	    userCountry: function(areaCode){
			$.ajax({
	    		url:getRootPath() + '/user/getCountryByAreaCode?areaCode='+areaCode
	    		,type:'get'
	    		,dataType:'json'
	    		,success:function(result){
	    			var baseCountry = result.data;
	    			$("#userCountry").empty();
	    			if (result.code == 10000) {
	    				for (var i = 0; i < baseCountry.length; i++) {
	    					$("#userCountry").append("<option value='"+baseCountry[i].areaCode+"'>"+baseCountry[i].country+"</option>");
	    				}
	    			}else {
	                    common_tool.messager_show(result.msg);
	                }
	    		}
	    	})
	    },

	    search_industry:function(type,name){
	    	if (type == "1") {
	    		//页面获取行业类别信息
	    		$('#search_industry').combobox({
	    			prompt:'请选择行业',
	    			url: getRootPath() + '/baseIndustry/getIndustry',
	    			method:'get',
	    			valueField:'industryType',
	    			textField:'industry'
	    		});
			}else {
				//修改/添加获取行业类别信息
            	$('#industry').combobox({
					prompt:'请选择行业',
					url: getRootPath() + '/baseIndustry/getIndustry',
					method:'get',
					valueField:'industryType',
					textField:'industry',
            		onChange: function (newValue,oldValue) {
            			if(newValue == "99"){
                            $('#industry_other').textbox("enable");
                            $('#industry_other').validatebox("enableValidation");
                            $('#industry_other').textbox('setValue',name);
            	    	}else{
            	    		$('#industry_other').textbox('setValue','');
            	    		$('#industry_other').textbox("disable");
            	        	$('#industry_other').validatebox("disableValidation");
            	    	}
            		}
				});
			}

	    },
	    search_city:function(type){
	    	if (type == "1") {
	    		//页面获取是市
	    		$('#search_unit_city').combobox({
	    			prompt:'请选择市',
	    			url: getRootPath() + '/baseCountry/getCountrys',
	    			method:'get',
	    			valueField:'areaCode',
	    			textField:'country',
	    			onSelect:function(record){
	    				user_tool.search_country(record.id,type);
	    			},
	    			onUnselect:function(record){
	    				user_tool.search_country(-2,type);
	    			}
	    		});
			}else {
				//添加/修改获取是市
            	$('#unit_city').combobox({
					prompt:'请选择市',
					url: getRootPath() + '/baseCountry/getCountrys',
					method:'get',
					valueField:'areaCode',
					textField:'country',
					async: false,
				    onSelect:function(record){
				    	user_tool.search_country(record.id,type);
				    },
				    onUnselect:function(record){
				    	user_tool.search_country(-2,type);
				    }
				});
			}
	    },
	    search_country:function(parentId,type){
	    	if (type == "1") {
	    		//页面获取区县
	    		$('#search_unit_country').combobox({
	    			prompt:'请选择区/县',
	    			url: getRootPath() + '/baseCountry/getCountryByPid',
	    			method:'get',
	    			queryParams: {
	    				"pid" : parentId
	    			},
	    			valueField:'areaCode',
	    			textField:'country'
	    		});
			}else {
				// 添加/修改获取区县
				$('#unit_county').combobox({
					prompt:'请选择区/县',
					url: getRootPath() + '/baseCountry/getCountryByPid',
					method:'get',
					queryParams: {
						"pid" : parentId
					},
					valueField:'areaCode',
					textField:'country'
				});
			}
	    },
		getUserRole:function(roleId){ //获取角色
			/* 用于展现 zTree 的 DOM 容器 */
	        var obj = $("#roleZtree");
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
	    	 		},
	    	 		callback: {
	    	 			//点击文字也选中单选框
	    	 			onClick: function (e, treeId, treeNode, clickFlag) { 
	    	 				var treeObj = $.fn.zTree.getZTreeObj("roleZtree");
		    	 			treeObj.checkNode(treeNode, !treeNode.checked, true); 
	    	 			} 
	    		    }
	    	    };
	    	   /*获取节点数据 */
		    	//获取隶属单位
		       var unitName = $("#search_edit_unitName").val();
	    	   $.ajax({
	    	       type:"get",
	    	       url:getRootPath() + '/role/list',
	    	       async:false,
	    	       //data:{unitName:unitName},
	    	       dataType:"json",
	    	       success:function(data){
	    	           zNodes = data;
	    	       }
	    	   });
	    	  
	    	  /* zTree 初始化方法 */ 
	    	  $.fn.zTree.init(obj, zSetting, zNodes);
	    	
	    	
	    	/* 展开全部节点 */
	    	var treeObj = $.fn.zTree.getZTreeObj("roleZtree");
	    	treeObj.expandAll(true);
	    	/*回显角色*/
	    	if (roleId != null && roleId != "" && roleId != undefined) {
	    		var enableNode = treeObj.getNodeByParam("id",roleId,null);
	    		treeObj.checkNode(enableNode,true,false);
            }else{
            	treeObj.checkAllNodes(false);
			}
		},
	    getUnitTree : function(unitId){
	    	/* 用于展现 zTree 的 DOM 容器 */
	        var obj = $("#unitZtree");
	    	
	    	 /*配置节点信息 */    
	    	 var zSetting = {
	    	        data:{
	    	            simpleData:{
	    	                enable:true,
	    	                idKey:"id",
	    	                pIdKey:"_parentId"
	    	            },
	    	            key:{
	    	               url:'_url',
	    	               name:'unitName'
	    	           }
	    	        }
	    	 		,check:{
	    	 			enable: true,
	    	 			chkStyle: "radio",
    		 			radioType:"all"
	    	 		},
	    	 		callback: {
	    	 			//点击文字也选中单选框
	    	 			onClick: function (e, treeId, treeNode, clickFlag) { 
	    	 				var treeObj = $.fn.zTree.getZTreeObj("unitZtree");
		    	 			treeObj.checkNode(treeNode, !treeNode.checked, true); 
	    	 			} 
	    		    }
	    	    };
	    	   
	    	   /*获取节点数据 */
		    	//获取隶属单位
		    	var unitName = $("#search_edit_unitName").val();
	    	   $.ajax({
	    	       type:"get",
	    	       url:getRootPath() + '/unit/selectSysUnit',
	    	       async:false,
	    	       data:{unitName:unitName},
	    	       dataType:"json",
	    	       success:function(mes){
	    	           zNodes = mes.rows;
	    	       }
	    	   });
	    	  
	    	  /* zTree 初始化方法 */ 
	    	  $.fn.zTree.init(obj, zSetting, zNodes);
	    	
	    	
	    	/* 展开全部节点 */
	    	var treeObj = $.fn.zTree.getZTreeObj("unitZtree");
	    	treeObj.expandAll(true);
	    	/*回显单位*/
	    	if (unitId != null && unitId != "" && unitId != undefined) {
	    		var enableNode = treeObj.getNodeByParam("id",unitId,null);
	    		treeObj.checkNode(enableNode,true,false);
            }else{
            	treeObj.checkAllNodes(false);
			}
	    }
	};
	$(document).ready(function () {

        user_tool.init_main_view();
		user_tool.search_industry(1);
        user_tool.search_city(1);
        user_tool.search_city(2);
    //   user_tool.initData();

	    
	    $("#unit_save_btn").click(function () {
            user_tool.init_edit_view(1);
	        $('.maskLayer').css('display','block');
	    });
	    $("#unit_update_btn").click(function () {
	        var users = $("#unitUser_grid").datagrid('getChecked');
	        if (users.length == 0) {
	            common_tool.messager_show("至少选择一条记录,进行修改");
	            return false;
	        }
	        if (users.length > 1) {
	            common_tool.messager_show("只能选择一条记录,进行修改");
	            return false;
	        }
            user_tool.init_edit_view(2);
	    });
	    $("#unit_delete_btn").click(function () {
	        var user = $("#unitUser_grid").datagrid('getChecked');
	        if (user.length == 0) {
	            common_tool.messager_show("请选择一条记录");
	            return false;
	        }
	        layer.confirm("您确认删除该条记录吗? ", {icon: 3, title:'删除'}, function(index){
	        	var id = user[0].loginId;
                user_tool.delete(id);
	        });
	    });
	    $("#unit_flash_btn").click(function () {
            user_tool.form_clear();
            user_tool.init_main_view();
	    });
	    $("#unit_select_btn").click(function () {
            user_tool.init_main_view();
	    });
	    $("#unit_password_btn").click(function () {
	        var users = $("#unitUser_grid").datagrid('getChecked');
	        if (users.length == 0) {
	            common_tool.messager_show("请选择一条记录");
	            return false;
	        }
	        if (users.length > 1) {
	            common_tool.messager_show("只能选择一条记录,进行修改");
	            return false;
	        }
            user_tool.password_view(users[0]);
	    });
	    $("#userCity").change(function () {
	    	var areaCode = $("#userCity").val();
            user_tool.userCountry(areaCode);
	    });
	    $("#search_unitName_btn").click(function () {
	    	user_tool.getUnitTree();
	    });
	    $("#industry_other_button").click(function () {
	    	var flag = $("#industry_other_button").prop('checked');
	    	if(flag){
	        	$('#industry_other').textbox("enable");
	        	$('#industry_other').validatebox("enableValidation");
	        	$('#industry').combobox('setValue','其他');
	        	$("#industry").combobox("disable");
	    	}else{
	    		$('#industry_other').textbox('setValue','');
	    		$('#industry_other').textbox("disable");
	        	$('#industry_other').validatebox("disableValidation");
	        	$("#industry").combobox("enable");
	        	$('#industry').combobox('setValue','');
	    	}
	    });
	    
	});