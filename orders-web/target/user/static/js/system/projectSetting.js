setting_tool = {
	datagrid_height:function(){
		var height = ($(window).height()-250)+"px";
		$("#setting_grid_height").height(height);
	},
	init_grid:function(type){
		$('#setting_grid').datagrid({
            url:getRootPath() + "/system/selectProjectSetting",
            method: 'get',
            idField: "id",
            fitColumns: true,
            queryParams:{
            	type:type
            },
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
                {title: "配置说明", field: "configDetails", width: 400},
                {title: "配置数据", field: "configData", width: 400},
                {title: "配置项", field: "configName", width: 200},
                {title: "创建时间", field: "createTime", width: 100},
                {title: "修改时间", field: "updateTime", width:100},
                {title: "所属配置", field: "queryType", hidden:true}
            ]]/*,
            onLoadSuccess :function(){
            	$('#permission_grid').datagrid('clearSelections');
            }*/
        });
		$.ajax({
	        traditional: true,
	        method: 'get',
	        url: getRootPath() + '/system/getProjectCountry',
	        async: false,
	        dataType: 'json',
	        success: function (result) {
	            if (result.code == 10000) {
	                $('#countryShow').html(result.data);
	            }
	            else {
	                common_tool.messager_show(result.msg);
	            }
	        },
	    });
	},
	config_edit_view:function(type){
		layer.open({
			area: ['720px', '400px']
			,skin: 'yourclass'
			,icon:3
			,title:'系统配置'
			,type: 1
			,closeBtn:1
			,content:$("#edit_config")
			,success:function(){
				if(type==2){
					var datas = $("#setting_grid").datagrid('getChecked');
					var select = document.getElementById("queryType");
					$("#setting_edit_form").form('load', {
						id: datas[0].id,
						configName: datas[0].configName,
						configData: datas[0].configData,
						queryType: select.selectedIndex = datas[0].queryType,
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
	config_edit_country:function(type){
		layer.open({
			area: ['640px', '380px']
			,skin: 'yourclass'
			,icon:3
			,title:'系统配置'
			,type: 1
			,closeBtn:1
			,content:$("#selectCountry")
			,success:function(){
				if (type == '1') {
					setting_tool.getProvince();
					var datas = $("#setting_grid").datagrid('getChecked');
					var select = document.getElementById("queryType2");
					$("#country_form").form('load', {
						id2: datas[0].id,
						configName2: datas[0].configName,
						queryType2: select.selectedIndex = datas[0].queryType,
						configDetails2: datas[0].configDetails
					});
					$('#configName2').textbox("disable");
				}else {
					$.ajax({
						url:getRootPath() + '/system/getProjectConfigByConfigName'
						,type:'post'
						,data:{configName:'countryId'}
						,dataType:'json'
						,success:function(data){
							setting_tool.getProvince();
							var select = document.getElementById("queryType2");
							$("#country_form").form('load', {
								id2: data.id,
								configName2: data.configName,
								queryType2: select.selectedIndex = data.queryType,
								configDetails2: data.configDetails
							});
							$('#configName2').textbox("disable");
						}
					})
				}
			}
			,btn: ['保存', '取消']
			,yes: function(index){
				setting_tool.countryUpdate()
				
			}
			,end: function(){
				setting_tool.form_clear();
				$('#selectCountry').hide();
			}
		});
	},
	save:function(){
		var form_isValid = $("#setting_edit_form").form('validate');
		if(!form_isValid){
			common_tool.messager_show("请检查输入项是否都正确!")
		}else{
			var configName = $("#configName").val();
			var configData = $("#configData").val();
			var queryType = $("#queryType").val();
			var configDetails = $("#configDetails").val();
			 $.ajax({
	                data: {
	                	configName: configName,
	                	configData: configData,
	                	queryType: queryType,
	                	configDetails: configDetails
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/system/insertProjectSetting',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	              //          $("#edit_config").dialog("close");
	                    	layer.closeAll();
	                        setting_tool.form_clear();
	                        setting_tool.init_grid('1');
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
			var configName = $("#configName").val();
			var configData = $("#configData").val();
			var queryType = $("#queryType").val();
			var configDetails = $("#configDetails").val();
			 $.ajax({
	                data: {
	                	id:id,
	                	configName: configName,
	                	configData: configData,
	                	queryType : queryType,
	                	configDetails: configDetails
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/system/updateProjectSetting',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                 //       $("#edit_config").dialog("close");
	                    	layer.closeAll();
	                        setting_tool.form_clear();
	                        setting_tool.init_grid('1');
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
	            url: getRootPath() + '/system/deleteProjectSetting',
	            async: false,
	            dataType: 'json',
	            success: function (result) {
	                if (result.code == 10000) {
	                	setting_tool.form_clear();
                        setting_tool.init_grid('1');
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
	},
	getProvince:function(){
		$.ajax({
            traditional: true,
            method: 'get',
            url: getRootPath() + '/system/selectCountry',
            async: false,
            data:{pid:1},
            dataType: 'json',
            success: function (result) {
            	if (result.code == 10000) {
            		var data = result.data;
            		$("#province").empty();
    				$("#province").append("<option value='-1'>请选择省</option>");
					for (var i = 0; i < data.length; i++) {
						$("#province").append("<option value='"+data[i].id+"'>"+data[i].country+"</option>");
					}
                }
                else {
                    common_tool.messager_show(result.msg);
                }
            },
        });
	},
	getCity:function(pid){
		$.ajax({
            traditional: true,
            method: 'get',
            data:{pid:pid},
            url: getRootPath() + '/system/selectCountry',
            async: false,
            dataType: 'json',
            success: function (result) {
				if (result.code == 10000) {
            		var data = result.data;
					$("#city").empty();
    				if(data.length != 1){
    					$("#city").append("<option value='-1'>请选择市</option>");
    				}
    				for (var i = 0; i < data.length; i++) {
						$("#city").append("<option value='"+data[i].id+"'>"+data[i].country+"</option>");
					}
                }
                else {
                    common_tool.messager_show(result.msg);
                }
            },
        });
	},
	countryUpdate:function(){
		var form_isValid = $("#country_form").form('validate');
		var provinceId = $("#province").val();
		if(!form_isValid){
			common_tool.messager_show("请检查输入项是否都正确!")
		}else if(provinceId=="-1"){
			common_tool.messager_show("省不能为空!")
		}else{
			var configData = "";
			var cityId = $("#city").val();
			if(cityId != "-1"){
				configData = cityId;
			}else{
				configData = provinceId;
			};
			var id = $('#id2').val();
			var configName = $("#configName2").val();
			var queryType = $("#queryType2").val();
			var configDetails = $("#configDetails2").val();
			 $.ajax({
	                data: {
	                	id:id,
	                	configName: configName,
	                	configData: configData,
	                	queryType: queryType,
	                	configDetails: configDetails
	                },
	                traditional: true,
	                method: 'post',
	                url: getRootPath() + '/system/updateProjectSetting',
	                async: false,
	                dataType: 'json',
	                success: function (result) {
	                    if (result.code == 10000) {
	                       /* $("#selectCountry").dialog('close');*/
	                        setting_tool.form_clear();
	                        setting_tool.init_grid('1');
	                   //   common_tool.messager_show(result.msg);
	                        layer.msg(result.msg, {time : 2000},function(){
	                        	layer.closeAll();
	                        });
	                        return false;
	                    }
	                    else {
	                        common_tool.messager_show(result.msg);
	                    }
	                },
	            });
		}
	}
}

$(document).ready(function () {
	setting_tool.datagrid_height();
	setting_tool.init_grid('1');
	//刷新
	$("#confing-flash-btn").click(function () {
        setting_tool.init_grid('1');
	});
	//新增
	$("#confing-save-btn").click(function () {
		$('#configName').textbox('readonly',false);
		/*$('#configName').textbox("enable");*/
		setting_tool.config_edit_view(1);
    });
	//修改
	$("#confing-update-btn").click(function () {
		$('#configName').textbox('readonly',true);
		var datas = $("#setting_grid").datagrid('getChecked');
		if (datas.length == 0) {
	            common_tool.messager_show("请选择一条记录");
	            return false;
	    }
		if(datas[0].configName=='countryId'){
			setting_tool.config_edit_country('1');
		}else{
			setting_tool.config_edit_view(2);
		}
	});
	//修改项目部署地区
	$("#edit").click(function () {
		setting_tool.config_edit_country('0');
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
	//根据不同的配置项显示相应的数据 
	$("#projectUrl").click(function () {
		setting_tool.init_grid('1');
	});
	$("#pageShow").click(function () {
		setting_tool.init_grid('2');
	});
	$("#otherConfiguration").click(function () {
		setting_tool.init_grid('0');
	});
	
	$("#province").change(function(){
		var pid = $("#province").val();
		if(pid != "-1" && pid !=null && pid !=""){
			setting_tool.getCity(pid);
		}
	})
})    
