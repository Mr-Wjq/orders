unit_tool = {
	    form_clear: function () {
	        $("#unit_form").form('reset');
	        $("#unit_form").form('clear');
	        $("#unit_search_form").form('reset');
	        $("#unit_search_form").form('clear');
	        $('#industry_other').textbox("disable");
	    	$('#industry_other').validatebox("disableValidation");
	        $("#unitUser_grid").treegrid("uncheckAll");
	        $("#parentUnitTable").treegrid("uncheckAll");
	    },
	    init_main_view: function () {
	    	var unitName = $("#search_unit_unitName").val();
	    	var industry = $("#search_industry").combobox('getValue');
	    	var cityAreaCode = $("#search_unit_city").combobox('getValue');
	    	var countryAreaCode = $("#search_unit_country").combobox('getValue');
	        $("#unitUser_grid").treegrid({
	            url: getRootPath() + '/unit/selectSysUnit',
	            method: 'get',
	            idField: "id",
	            treeField:"unitName",
	            fitColumns: true,
	            toolbar: '#unit_tool_bar',
	            rownumbers: true,
	            animate: true,
	            fit: true,
	            border: false,
	            nowrap: false,
                striped: true,
	            queryParams: {
	                unitName: unitName,
	                industry: industry,
	                cityAreaCode: cityAreaCode,
	                countryAreaCode: countryAreaCode,

	            },
	            columns: [[
	                {title: "选择", field: "id","align":"center", checkbox: true},
	                {title: "单位名称", field: "unitName", width: 124},
	                {title: "市", field: "unitCityName","align":"center", width: 130},
	            	{title: "区县", field: "unitCountryName","align":"center", width: 130},
	                {title: "行业", field: "baseIndustryName","align":"center", width: 130},
	                {title: 'industryType',"align":"center", field: 'industryType',hidden:true },
	                {title: "单位地址", field: "unitAddress","align":"center", width: 130},
	                {title: "能否修改", field: "isFinal","align":"center",formatter: function (value, row, index) {
		                if(value==1){
		                	return "可以";
		                }else{
		                	return "不可以";
		                }
	                	return common_tool.timeDate(value);
		                }, width: 130
	                },
	                {title: "创建时间", field: "createTime","align":"center",formatter: function (value, row, index) {
                            return common_tool.timeDate(value);
                        }, width: 130
                    },
			        {title: "修改时间", field: "updateTime","align":"center",formatter: function (value, row, index) {
                            return common_tool.timeDate(value);
                        }, width: 130
                    },
	            ]] 
	        });
	    },
	    init_edit_view: function (type) {
	    	var title = '';
	    	if ('1' == type) {
				 title = '添加单位';
			}else {
				 
				 title = '修改单位';
			}
	    	layer.open({
				area: ['49%', '400px']
				,icon:3
				,zIndex:1
				,title:title
				,resize:false
                ,type: 1
				,closeBtn:1
				,content:$("#unit_edit_dialog")
				,success:function(){
					unit_tool.search_industry(2,"");
					unit_tool.getSubjectionUnit();
	                if (type == 2) {
	                	var users = $("#unitUser_grid").datagrid('getChecked');
	                	$('#unit_city').combobox('select',users[0].unitCityAreaCode);
	                	$('#unit_county').combobox('select',users[0].unitCountryAreaCode);
	                	$("#unit_form").form('load', {
                            unitId: users[0].id,
	                        unit_name: users[0].unitName,
	                        unit_address: users[0].unitAddress
	                    });
	                	$("#industry").combobox("enable");
	                	if (users[0].baseIndustryType == "99") {
	                		unit_tool.search_industry(2,users[0].baseIndustryName);
	                		$('#industry').combobox('setValue',users[0].baseIndustryType);
	                		$('#industry_other').textbox('setValue',users[0].baseIndustryName);
						}else {
							$('#industry').combobox('setValue',users[0].baseIndustryType);
						};
						//回显隶属单位
	                	var ztreeObj = $.fn.zTree.getZTreeObj("parentUnitZtree");
	                	var enableNode = ztreeObj.getNodeByParam("id",users[0]._parentId,null);
	                	ztreeObj.checkNode(enableNode,true,false);
	                	//禁用修改的单位
	                	var disableNode = ztreeObj.getNodeByParam("id",users[0].id,null);
	                	ztreeObj.setChkDisabled(disableNode,true,false,true);
	                }else{
	                	$("#industry").combobox("enable");
	                	$('#industry').combobox('setValue','');
	                }
				}
				,btn: ['保存','清除', '取消']
				,yes: function(index){
					 if (type == 1) {
                         unit_tool.save();
                     }
                     if (type == 2) {
                         unit_tool.update();
                     }
				}
				,btn2: function(){
					if (type == 1) {
						$("#unit_city").combobox('clear');
				    	$("#unit_county").combobox('clear');
				    	$("#industry").combobox('clear');
						unit_tool.form_clear();
						return false;
	                }
	                if (type == 2) {
	                	common_tool.messager_show("修改方法不可清除");
	                	return false;
	                }
				}
				,end: function(){
					unit_tool.form_clear();
					$('#unit_edit_dialog').hide();
					$('.maskLayer').css('display','none');
				}
			});
	    },

	    save: function () {
	    	var form_isValid = $("#unit_form").form('validate');
	        if (!form_isValid) {
	            common_tool.messager_show("请检查输入项是否都正确!")
	        }
	        else {
	        	var baseIndustryName;
	        	var industryType = $('#industry').combobox('getValue');
	        	if(industryType == "99"){
	        		baseIndustryName = $("#industry_other").val();
	        	}else{
	        		baseIndustryName = $('#industry').combobox('getText');
	        	}
	        	var unitCityAreaCode = $('#unit_city').combobox('getValue');
	        	var unitCityName = $('#unit_city').combobox('getText');
	        	var unitCountryAreaCode = $('#unit_county').combobox('getValue');
	        	var unitCountryName = $('#unit_county').combobox('getText');
	            var unitName = $('#unit_name').val();
	            var unitAddress = $('#unit_address').val();
                var parentUnitId;
                var ztreeObj = $.fn.zTree.getZTreeObj("parentUnitZtree");
                var unitNodes = ztreeObj.getCheckedNodes(true);
                if(unitNodes.length == 0){
                    layer.msg("请选择所属隶属单位");
                    return;
                }
                parentUnitId = unitNodes[0].id;
				
	            $.ajax({
	                data: {
	   				 	industryType:industryType,
	                	baseIndustryName:baseIndustryName,
	                	unitCityAreaCode:unitCityAreaCode,
	                	unitCityName:unitCityName,
	                	unitCountryAreaCode:unitCountryAreaCode,
	                	unitCountryName:unitCountryName,
	                	unitName:unitName,
	                	unitAddress:unitAddress,
                        parentUnitId:parentUnitId
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/unit/unitInsert',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                    	layer.msg(result.msg,{time: 1000},function(){
	                    		layer.closeAll();
	                    		unit_tool.form_clear();
	                    		unit_tool.init_main_view();
	                    		document.execCommand('Refresh');
	                    	});
	                        return false;
	                    }
	                    else {
	                        common_tool.messager_show(result.msg);
	                    }
	                },
	                error: function(){
	                	common_tool.messager_show("服务异常");
	                },
	            });
	        }
	    },
	    update: function () {
	    	var form_isValid = $("#unit_form").form('validate');
	        if (!form_isValid) {
	            common_tool.messager_show("请检查输入项是否都正确!")
	        }

	        else {
	        	var baseIndustryName;
	        	var industryType = $('#industry').combobox('getValue');
	        	if(industryType == "99"){
	        		baseIndustryName = $("#industry_other").val();
	        	}else{
	        		baseIndustryName = $('#industry').combobox('getText');
	        	}
	        	var unitCityAreaCode = $('#unit_city').combobox('getValue'); 
	        	var unitCityName = $('#unit_city').combobox('getText'); 
	        	var unitCountryAreaCode = $('#unit_county').combobox('getValue'); 
	        	var unitCountryName = $('#unit_county').combobox('getText'); 
	            var unitName = $('#unit_name').val();
	            var unitId = $('#unitId').val();
	            var unitAddress = $('#unit_address').val();
	            var parentUnitId;
                var ztreeObj = $.fn.zTree.getZTreeObj("parentUnitZtree");
                var unitNodes = ztreeObj.getCheckedNodes(true);
                if(unitNodes.length == 0){
                    layer.msg("请选择所属隶属单位");
                    return;
                }
                parentUnitId = unitNodes[0].id;
	            $.ajax({
	                data: {
                        unitId:unitId,
                        industryType:industryType,
                        baseIndustryName:baseIndustryName,
                        unitCityAreaCode:unitCityAreaCode,
                        unitCityName:unitCityName,
                        unitCountryAreaCode:unitCountryAreaCode,
                        unitCountryName:unitCountryName,
                        unitName:unitName,
                        unitAddress:unitAddress,
                        parentUnitId:parentUnitId
	                },
	                method: 'post',
	                url: getRootPath() + '/unit/updateUnit',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                    	layer.msg(result.msg,{time: 1000},function(){
	                    		layer.closeAll();
	                    		unit_tool.form_clear();
	                    		unit_tool.init_main_view();
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
	    delete: function (ids) {
	        $.ajax({
	            data: {
	                ids: ids,
	            },
	            traditional: true,
	            method: 'get',
	            url: getRootPath() + '/unit/deleteUnit',
	            async: false,
	            dataType: 'json',
	            success: function (result) {
	                if (result.code == 10000) {
	                    unit_tool.form_clear();
	                    unit_tool.init_main_view();
	                    common_tool.messager_show(result.msg);
	                    return false;
	                }
	                else {
	                    common_tool.messager_show(result.msg);
	                }
	            },
	        });
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
	    				unit_tool.search_country(record.id,type);
	    			},
	    			onUnselect:function(record){
	    				unit_tool.search_country(-2,type);
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
				    	unit_tool.search_country(record.id,type);
				    },
				    onUnselect:function(record){
				    	unit_tool.search_country(-2,type);
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
	    getSubjectionUnit : function(){
	    	/* 用于展现 zTree 的 DOM 容器 */
	        var obj = $("#parentUnitZtree");
	    	
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
	    	 				var treeObj = $.fn.zTree.getZTreeObj("parentUnitZtree");
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
	    	var treeObj = $.fn.zTree.getZTreeObj("parentUnitZtree");
	    	treeObj.expandAll(true);
	    }
	};
	$(document).ready(function () {
		unit_tool.search_industry(1);
		unit_tool.search_city(1);
	    unit_tool.init_main_view();
        unit_tool.search_city(2);
	    $("#unit_save_btn").click(function () {
	        unit_tool.init_edit_view(1);
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
	        if(users[0].isFinal!=1){
	        	common_tool.messager_show("该条记录不可修改");
	        	return;
	        }
	        unit_tool.init_edit_view(2);
	    });
	    $("#unit_delete_btn").click(function () {
	        var users = $("#unitUser_grid").datagrid('getChecked');
	        if (users.length == 0) {
	            common_tool.messager_show("请选择一条记录");
	            return false;
	        }
	        if(users[0].isFinal!=1){
	        	common_tool.messager_show("该条记录不可修改");
	        	return;
	        }
	        layer.confirm("删除该单位会删除相关单位用户,您确认要删除吗? ", {icon: 3, title:'删除'}, function(){
	        	var ids = "";
            	for (var i = 0; i < users.length; i++) {
					ids +=","+users[i].id
				}
            	unit_tool.delete(ids.substring(1));
	        });
	    });
	    $("#unit_flash_btn").click(function () {
	        unit_tool.form_clear();
	        unit_tool.init_main_view();
	    });
	    $("#unit_select_btn").click(function () {
	        unit_tool.init_main_view();
	    });
	    $("#search_unitName_btn").click(function () {
	    	unit_tool.getSubjectionUnit();
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
