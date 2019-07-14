layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  var tableIns = table.render({
    elem: '#userTableId'
    ,url:'user/selectDoctorUserAndUnit'
    ,headers:{
    	"X-Csrf-Token":csrf_token
    }
	,response: {
	    statusName: 'code'
	    ,statusCode: 200
	    ,msgName: 'msg'
	    ,countName: 'total'
	    ,dataName: 'rows'
	 } 
  	,limit:20
  	,limits:[20,30,50,100,200]
    ,toolbar: '#userTableToolbar'
    ,title: '数据表'
    ,cols: [[

       {type: 'numbers', fixed: 'left'}
      ,{type:'checkbox'}
      ,{field:'loginName', title:'账号' }
      ,{field:'unitName', title:'单位名称' }
      ,{field:'unitType', title:'类型',templet:function(row){
    	  switch (row.unitType) {
			case 1:
				return "医院"
				break;
			case 2:
				return "连锁门诊"
				break;
			case 3:
				return "个体门诊"
				break;
			}
       }}
      /*,{field:'cs', title:'城市',templet:function(row){
    	  return row.unitProvince+row.unitCity+row.unitDistrict
      }}
      ,{field:'unitAddress', title:'详细地址'}*/
      ,{field:'zhName', title:'姓名' }
      ,{field:'phone', title:'手机号' }
      ,{field:'email', title:'邮箱' }
      ,{field:'roleName', title:'职位',templet:function(row){
    	  if(row.roleId == 2){
    		  return "负责人";
    	  }else{
    		  return "员工";
    	  }
       }}
      ,{field:'status', title:'状态' ,width:100,templet: '#statusBtn'}
      /*,{field:'isFinal', title:'能否删除' ,templet:function(row){
    	  if(row.isFinal == 2){
    		  return "<span style='color: red;'>NO</span>";
    	  }else{
    		  return "<span>YES</span>";
    	  }
       }}*/
      ,{field:'createTime', title:'创建时间',templet:function(row){
    	  return common_tool.timeDate(row.createTime);
      }}
      ,{field:'cz',fixed: 'right',width:150, title:'操作',templet: '#lineToolbar'}
    ]]
    ,page: true
  });
  
  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
	  		unitName:$("#unitName").val(),
	  		zhName:$("#zhName").val(),
	  		loginName:$("#loginName").val(),
	  		phone:$("#phone").val(),
	  		_status:$("#status").val()
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
  
  //获取单位
  var unitList = function(){
	  $.ajax({
		  url:'unit/getDoctorUnitList'
		  ,type:'post'
		  ,dataType:'json'
		  ,success:function(result){
			  if(10000 == result.code){
				  $("#unitList").empty();
				  $("#unitList").append('<option value="">请选择单位名称</option>');
				  var data = result.data;
				  for (var i = 0; i < data.length; i++) {
					  $("#unitList").append('<option value="'+data[i].id+'">'+data[i].unitName+'</option>');
				  }
				  form.render();
			  }else{
				  layer.msg(result.msg);
			  }
		  }
	  })
  }
  
  //监听状态操作
  form.on('switch(statusFilter)', function(data){
	  var status = data.elem.checked ? 1 : 2;
	  $.ajax({
		  url:'user/systemUpdate'
		  ,type:'post'
		  ,data:{
			  id:data.value,
			  status:status
		  }
		  ,dataType:'json'
		  ,success:function(result){
			  if(10000 == result.code){
				  layer.closeAll();
				  tableIns.reload();
				  layer.msg("修改成功");
			  }else{
				  layer.msg(result.msg);
			  }
		  }
	  })
	  
  });

  //头工具栏事件
  table.on('toolbar(userTableFilter)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    //新增用户
    if('insert' == obj.event){
    	layer.open({
    		type:1
    		,title:'新增用户'
    		,area:['50%','70%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#insertUserDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			unitList();
    		}
    		,end:function(){
    			$("#insertUserDiv").hide();
    		}
    	})
    //删除用户
    }else if('delete' == obj.event){
    	var checkUsers = checkStatus.data;
    	if(checkUsers.length==0){
    		layer.msg("请选择要删除的数据");
    		return;
    	}
    	layer.confirm('确认删除？', {icon: 3, title:'提示'}, function(index){  
		    	var ids = '';
		    	var _length = checkUsers.length;
		    	for (var i = 0; i < _length; i++) {
		    		if(checkUsers[i].isFinal == 2){
		    			layer.msg(checkUsers[i].loginName+"不可删除");
		    			return false;
		    		}
		    		if((i+1) == _length){
		    			ids += checkUsers[i].userId
		    		}else{
		    			ids += checkUsers[i].userId + ",";
		    		}
				}
		    	
		    	$.ajax({
		  		  url:'user/systemDelete'
		  		  ,type:'post'
		  		  ,data:{ids:ids}
		  		  ,dataType:'json'
		  		  ,success:function(result){
		  			  if(10000 == result.code){
		  				  layer.closeAll();
		  				  tableIns.reload();
		  				  layer.msg("删除成功");
		  			  }else{
		  				  layer.msg(result.msg);
		  			  }
		  		  }
		  	  })
		  	  layer.close(index);
    	});
    }
  });
  
  //行工具栏
  table.on('tool(userTableFilter)', function(obj){
    var data = obj.data;
    //重置密码
    if(obj.event === 'update_password'){
    	layer.open({
    		type:1
    		,title:'重置密码'
    		,area:['30%','32%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updatePasswordDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			$("#userId2").val(data.userId);
    		}
    		,end:function(){
    			$("#updatePasswordDiv").hide();
    		}
    	})
    }
    //修改用户
    else if(obj.event === 'edit'){
    	layer.open({
    		type:1
    		,title:'修改用户'
    		,area:['70%','90%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updateUserDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			
    			getCountryByPid(form,'u_unitProvinceId',1,data.unitProvinceId);
    			getCountryByPid(form,'u_unitCityId',data.unitProvinceId,data.unitCityId);
    			getCountryByPid(form,'u_unitDistrictId',data.unitCityId,data.unitDistrictId);
    			form.val("updateUserFilter", {
    				  "userId": data.userId,
    				  "unitId": data.unitId,
    				  "unitName": data.unitName,
    				  "unitType": data.unitType+"",
    				  "unitAddress": data.unitAddress,
    				  "roleId": data.roleId+"",
    				  "loginName": data.loginName,
    				  "zhName": data.zhName,
    				  "phone": data.phone,
    				  "email": data.email
    			})
    			
    			
    		}
    		,end:function(){
    			$("#updateUserDiv").hide();
    		}
    	})
    }
  });
  
  var layer_curr_index = '';
  //新增单位
  $("#insertUnitBtn").click(function(){
		layer.open({
			type:1
			,title:'新增单位'
			,area:['70%','63%']
			,shade:0.8
			,btn:['关闭']
			,content:$("#insertUnitDiv")
			,yes:function(index){
				layer.close(layer_curr_index);
			}
			,success:function(layero, index){
				layer_curr_index = index;
				getCountryByPid(form,'unitProvinceId',1);
			}
			,end:function(){
				$("#insertUnitDiv").hide();
			}
		})
  })
  
  //监听提交
  form.on('submit(userInsert)', function(data){
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  var index = layer.load(2);
		  $.ajax({
			  url:'user/doctorInsert'
				  ,type:'post'
					  ,data:data.field
					  ,dataType:'json'
						  ,success:function(result){
							  if(10000 == result.code){
								  layer.closeAll();
								  tableIns.reload();
								  layer.msg("添加成功");
							  }else{
								  layer.msg(result.msg);
							  }
						  }
		  })
		  layer.close(index);
	  });
    return false;
  });
  form.on('submit(userUpdate)', function(data){
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  var index = layer.load(2);
		  $.ajax({
			  url:'user/doctorUpdate'
			  ,type:'post'
			  ,data:data.field
			  ,dataType:'json'
			  ,success:function(result){
				  if(10000 == result.code){
					  layer.closeAll();
					  tableIns.reload();
					  layer.msg("修改成功");
				  }else{
					  layer.msg(result.msg);
				  }
			  }
		  })  
		  layer.close(index);
	  });
	  return false;
  });
  form.on('submit(updatePassword)', function(data){
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'user/systemUpdate'
				  ,type:'post'
					  ,data:data.field
					  ,dataType:'json'
						  ,success:function(result){
							  if(10000 == result.code){
								  layer.closeAll();
								  tableIns.reload();
								  layer.msg("修改成功");
							  }else{
								  layer.msg(result.msg);
							  }
						  }
		  })  
		  layer.close(index);
	  });
	  return false;
  });
  form.on('submit(unitInsert)', function(data){
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'unit/insert'
			  ,type:'post'
			  ,data:data.field
			  ,dataType:'json'
			  ,success:function(result){
				  if(10000 == result.code){
					  layer.close(layer_curr_index);
					  unitList();
					  layer.msg("添加成功");
				  }else{
					  layer.msg(result.msg);
				  }
			  }
		  })  
		  layer.close(index);
	  });
	  return false;
  });

	//监听下拉框
	form.on('select(unitProvinceId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'unitCityId',obj.value);
		}
		
	}); 
	form.on('select(unitCityId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'unitDistrictId',obj.value);
		}
	}); 
	form.on('select(u_unitProvinceId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'u_unitCityId',obj.value);
		}
		
	}); 
	form.on('select(u_unitCityId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'u_unitDistrictId',obj.value);
		}
	}); 
	
});

function getCountryByPid(form,divId,pid,huixian){
	
	switch (divId) {
	case 'unitProvinceId':
		$("#unitCityId").empty();
		$("#unitDistrictId").empty();
		$("#unitProvinceId").empty();
		$("#unitProvinceId").append('<option value="">请选择省</option>');
		break;
	case 'unitCityId':
		$("#unitDistrictId").empty();
		$("#unitCityId").empty();
		$("#unitCityId").append('<option value="">请选择市</option>');
		break;
	case 'unitDistrictId':
		$("#unitDistrictId").empty();
		$("#unitDistrictId").append('<option value="">请选择区/县</option>');
		break;
	case 'u_unitProvinceId':
		$("#u_unitCityId").empty();
		$("#u_unitDistrictId").empty();
		$("#u_unitProvinceId").empty();
		$("#u_unitProvinceId").append('<option value="">请选择省</option>');
		break;
	case 'u_unitCityId':
		$("#u_unitDistrictId").empty();
		$("#u_unitCityId").empty();
		$("#u_unitCityId").append('<option value="">请选择市</option>');
		break;
	case 'u_unitDistrictId':
		$("#u_unitDistrictId").empty();
		$("#u_unitDistrictId").append('<option value="">请选择区/县</option>');
		break;

	}
	
	$.ajax({
		url: "baseCountry/getCountryByPid"
		,type:"get"
		,data:{pid:pid}
		,dataType:"json"
		,success:function(data){
			if(data!=null && data!='' && data!=undefined){
				for (var i = 0; i < data.length; i++) {
					if(huixian == data[i].id){
						$("#"+divId).append('<option value="'+data[i].id+'" selected>'+data[i].country+'</option>');
					}else{
						$("#"+divId).append('<option value="'+data[i].id+'">'+data[i].country+'</option>');						
					}
				}
				form.render();
			}
		}
	})
}