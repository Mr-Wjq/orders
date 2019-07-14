layui.use(['table','laydate','transfer'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  laydate.render({
	    elem: '#endTime'
	    ,min: 0
  });
  laydate.render({
	  elem: '#endTime2'
		  ,min: 0
  });
  var form = layui.form;
  var transfer = layui.transfer
  var dscTableIns;
  var curr_layer_index = '';
  var tableIns = table.render({
    elem: '#tableId'
    ,url:'unit/select'
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
    ,toolbar: '#tableToolbar'
    ,title: '数据表'
    ,cols: [[

       {type: 'numbers', fixed: 'left'}
      ,{type:'checkbox'}
      ,{field:'unitName', title:'单位名称' }
      ,{field:'unitType', title:'单位类型' ,templet:function(row){
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
			case 4:
				return "工厂"
				break;
			}
     }}
      ,{field:'unitProvince', title:'省' }
      ,{field:'unitCity', title:'市' }
      ,{field:'unitDistrict', title:'区' }
      ,{field:'unitAddress', title:'详细地址' }
      ,{field:'fromName', title:'来源' }
      ,{field:'cz', title:'操作',width:250,templet:function(row){
    	  var btnArr = '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>';
    	  if(4 != row.unitType){
    		  btnArr += '<a class="layui-btn layui-btn-xs" lay-event="confFactory">设置工厂</a>'+
    		  '<a class="layui-btn layui-btn-xs" lay-event="discount">优惠券</a>'
    	  }
    	  
    	  return btnArr;
    	  
       }}
    ]]
    ,page: true
  });
  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
		  unitName:$("#unitName").val(),
		  unitType:$("#unitType").val(),
		  unitProvinceId:$("#s_unitProvinceId").val(),
		  unitCityId:$("#s_unitCityId").val()
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
  //头工具栏事件
  table.on('toolbar(tableFilter)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    //新增用户
    if('insert' == obj.event){
    	layer.open({
    		type:1
    		,title:'新增'
    		,area:['70%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#insertDiv")
    		,success:function(layero, index){
				getCountryByPid(form,'unitProvinceId',1);
				unitFrom('dataUnitFromId');
			}
    		,yes:function(){
    			layer.closeAll();
    		}
    		,end:function(){
    			$("#insertDiv").hide();
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
		    		if((i+1) == _length){
		    			ids += checkUsers[i].id
		    		}else{
		    			ids += checkUsers[i].id + ",";
		    		}
				}
		    	$.ajax({
		  		  url:'unit/delete'
		  		  ,type:'post'
		  		  ,data:{ids:ids}
		  		  ,dataType:'json'
		  		  ,success:function(result){
		  			  if(10000 == result.code){
		  				  layer.closeAll();
		  				  tableIns.reload();
		  			  }
		  			  layer.msg(result.msg);
		  		  }
		  	  })
    	});
    }
  });
  //行工具栏
  table.on('tool(tableFilter)', function(obj){
    var data = obj.data;
    if(obj.event == 'edit'){
    	layer.open({
    		type:1
    		,title:'修改'
    		,area:['70%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updateDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			unitFrom('u_dataUnitFromId',data.dataUnitFromId)
    			getCountryByPid(form,'u_unitProvinceId',1,data.unitProvinceId);
    			getCountryByPid(form,'u_unitCityId',data.unitProvinceId,data.unitCityId);
    			getCountryByPid(form,'u_unitDistrictId',data.unitCityId,data.unitDistrictId);
    			form.val("updateFilter", {
  				  "id": data.id,
  				  "unitName": data.unitName,
  				  "unitType": data.unitType+"",
  				  "unitAddress": data.unitAddress
  			  })
    		}
    		,end:function(){
    			$("#updateDiv").hide();
    		}
    	})
    }else if('confFactory' == obj.event){
    	layer.open({
      		type:1
      		,title:'设置常用工厂'
      		,area:['700px','570px']
      		,shade:0.8
      		,btn:['关闭']
      		,content:$("#userFactoryDiv")
      		,yes:function(){
      			layer.closeAll();
      		}
      		,success:function(){
      			transferMethod(transfer,data.id);
      		}
      		,end:function(){
      			$("#userFactoryDiv").hide();
      		}
      	})
    }else if('discount' == obj.event){
    	layer.open({
      		type:1
      		,title:'优惠券'
      		,area:['1000px','570px']
      		,shade:0.8
      		,btn:['关闭']
      		,content:$("#discountDiv")
      		,yes:function(){
      			layer.closeAll();
      		}
      		,success:function(){
/********************************************************分割线  end***************************************************************/
      			$(".sysUnitId").val(data.id);
      			
      			dscTableIns = table.render({
      			    elem: '#discountUnitTable'
      			    ,width:998
      			    ,url:'discount/selectUnitDiscount'
  			    	,where:{
  			    		sysUnitId:data.id
  					}
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
      			    ,toolbar: '#discountUnitTableToolbar'
      			    ,title: '数据表'
      			    ,cols: [[

      			       {type: 'numbers', fixed: 'left'}
      			      ,{type:'checkbox'}
      			      ,{field:'discountName', title:'优惠券' ,templet:function(row){
      			    	 var option = '';
	   					  if(row.factoryName!='' && row.factoryName != null && row.factoryName!=undefined){
	   						  option = row.factoryName + "专用"+row.discountPrice+"元优惠券";
	   					  }else{
	   						  if(row.textureName!='' && row.textureName != null && row.textureName!=undefined){
	   							  option += row.textureName; 
	   						  }
	   						  if(row.brandName!='' && row.brandName != null && row.brandName!=undefined){
	   							  option += row.brandName;
	   						  }
	   						  if(option!=''){
	   							  option += "专用"+row.discountPrice+"元优惠券";
	   						  }
	   					  }
	   					  if(option!='' && row.discountType == 3){
	   						  option = '平台发布'+row.discountPrice+"元优惠券";
	   					  }
	   					  return option;
      			       }}
      			      ,{field:'discountPrice', title:'金额',width:100}
      			      ,{field:'discountType', title:'类型',width:100,templet:function(row){
      			    	  if(1 == row.discountType){
      			    		  return '工厂';
      			    	  }else if(2 == row.discountType){
      			    		return '产品材质';
      			    	  }else if(3 == row.discountType){
      			    		return '平台'; 
      			    	  }
      			       } }
      			      ,{field:'num', title:'数量' ,width:80}
      			      ,{field:'endTime', title:'到期时间' ,width:150,templet:function(row){
      			    	  return common_tool.timeDay(row.endTime);
      			      }}
      			      ,{field:'cz2', title:'操作',width:80,fixed:'right',templet:function(row){
      			    	  var btnArr = '<a class="layui-btn layui-btn-xs" lay-event="dscEdit">修改</a>';
      			    	  return btnArr;
      			    	  
      			       }}
      			    ]]
      			    ,page: true
      			  });
      		  table.on('toolbar(discountUnitTableFilter)', function(obj){
      		    var checkStatus = table.checkStatus(obj.config.id);
      		    
      		    if('dscInsert' == obj.event){
      		    	layer.open({
      		    		type:1
      		    		,title:'新增'
      		    		,area:['600px','300px']
      		    		,shade:0.8
      		    		,btn:['关闭']
      		    		,content:$("#discountUnitInsertDiv")
      		    		,success:function(layero, index){
      		    			curr_layer_index = index;
      		    			selectDiscountAll(null,"dataDiscountId");
      					}
      		    		,yes:function(index){
      		    			layer.close(index);
      		    		}
      		    		,end:function(){
      		    			$("#discountUnitInsertDiv").hide();
      		    		}
      		    	})
      		    }else if('dscDelete' == obj.event){
      		    	var checkUsers = checkStatus.data;
      		    	if(checkUsers.length==0){
      		    		layer.msg("请选择要删除的数据");
      		    		return;
      		    	}
      		    	layer.confirm('确认删除？', {icon: 3, title:'提示'}, function(index){  
      				    	var ids = '';
      				    	var _length = checkUsers.length;
      				    	for (var i = 0; i < _length; i++) {
      				    		if((i+1) == _length){
      				    			ids += checkUsers[i].id
      				    		}else{
      				    			ids += checkUsers[i].id + ",";
      				    		}
      						}
      				    	$.ajax({
      				  		  url:'discount/deleteDscUnit'
      				  		  ,type:'post'
      				  		  ,data:{ids:ids}
      				  		  ,dataType:'json'
      				  		  ,success:function(result){
      				  			  if(10000 == result.code){
      				  				  layer.close(index);
      				  				dscTableIns.reload();
      				  			  }
      				  			  layer.msg(result.msg);
      				  		  }
      				  	  })
      		    	});
      		    }
      		  });
      		table.on('tool(discountUnitTableFilter)', function(obj){
      			var data = obj.data;
      			if(obj.event == 'dscEdit'){
          	    	layer.open({
          	    		type:1
          	    		,title:'修改'
          	    		,area:['400px','300px']
          	    		,shade:0.8
          	    		,btn:['关闭']
          	    		,content:$("#discountUnitUpdateDiv")
          	    		,yes:function(index){
      		    			layer.close(index);
      		    		}
          	    		,success:function(layero, index){
          	    			curr_layer_index = index;
          	    			selectDiscountAll(data.dataDiscountId,"u_dataDiscountId");
          	    			form.val("updateDscFilter", {
          	  				  "id": data.id,
          	  				  "num": data.num,
          	  				  "endTime": common_tool.timeDay(data.endTime)
          	  			  })
          	    		}
          	    		,end:function(){
          	    			$("#updateDiv").hide();
          	    		}
          	    	})
          	    }
      		})
      		  //监听提交
      		  form.on('submit(dscInsertBtn)', function(data){
      			var endTime = data.field.endTime;
      			var startTimestamp = '';
      			if(endTime!='' && endTime!=null && endTime!=undefined){
      				endTime += ' 23:59:59';
      				startTimestamp = new Date(endTime.replace(/-/g, "/"));
      			}
      			if(startTimestamp == ''){
      				layer.msg('时间转换错误');
      				return false;
      			}
      			data.field.endTime = startTimestamp;
      			  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
      				  $.ajax({
      					  url:'discount/insertDscUnit'
      					  ,type:'post'
      					  ,data:data.field
      					  ,dataType:'json'
      					  ,success:function(result){
      						  if(10000 == result.code){
      							  layer.close(curr_layer_index);
      							  dscTableIns.reload();
      						  }
      						  layer.msg(result.msg);
      					  }
      				  })
      			  });
      		    return false;
      		  });
      		  form.on('submit(dscUpdateBtn)', function(data){
      			var endTime = data.field.endTime;
      			var startTimestamp = '';
      			if(endTime!='' && endTime!=null && endTime!=undefined){
      				endTime += ' 23:59:59';
      				startTimestamp = new Date(endTime.replace(/-/g, "/"));
      			}
      			if(startTimestamp == ''){
      				layer.msg('时间转换错误');
      				return false;
      			}
      			data.field.endTime = startTimestamp;
      			  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
      				  $.ajax({
      					  url:'discount/updateDscUnit'
      					  ,type:'post'
      					  ,data:data.field
      					  ,dataType:'json'
      					  ,success:function(result){
      						  if(10000 == result.code){
      							  layer.close(curr_layer_index);
    							  dscTableIns.reload();
      						  }
      						  layer.msg(result.msg);
      					  }
      				  })  
      			  });
      			  return false;
      		  });
      		
/********************************************************分割线  end***************************************************************/      		  
      		  
      		}
      		,end:function(){
      			$("#discountDiv").hide();
      		}
      	})
    }
  });
  
  //监听提交
  form.on('submit(insert)', function(data){
	  
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'unit/insert'
			  ,type:'post'
			  ,data:data.field
			  ,dataType:'json'
			  ,success:function(result){
				  if(10000 == result.code){
					  layer.closeAll();
					  tableIns.reload();
				  }
				  layer.msg(result.msg);
			  }
		  })
	  });
    return false;
  });
  form.on('submit(update)', function(data){
	  
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'unit/update'
			  ,type:'post'
			  ,data:data.field
			  ,dataType:'json'
			  ,success:function(result){
				  if(10000 == result.code){
					  layer.closeAll();
					  tableIns.reload();
				  }
				  layer.msg(result.msg);
			  }
		  })  
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
	getCountryByPid(form,"s_unitProvinceId",1);
	form.on('select(s_unitProvinceId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'s_unitCityId',obj.value);
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
	case 's_unitProvinceId':
		$("#s_unitProvinceId").empty();
		$("#s_unitProvinceId").append('<option value="">请选择省</option>');
		break;
	case 's_unitCityId':
		$("#s_unitDistrictId").empty();
		$("#s_unitCityId").empty();
		$("#s_unitCityId").append('<option value="">请选择市</option>');
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

function unitFrom(divId,huixian){
	$.ajax({
		url: "unitFrom/select"
		,type:"get"
		,data:{page:1,limit:1000}
		,dataType:"json"
		,success:function(result){
			var data = result.rows;
			if(data!=null && data!='' && data!=undefined){
				$("#"+divId).empty();
				$("#"+divId).append('<option value="" selected>请选择单位来源</option>');
				for (var i = 0; i < data.length; i++) {
					if(huixian == data[i].id){
						$("#"+divId).append('<option value="'+data[i].id+'" selected>'+data[i].fromName+'</option>');
					}else{
						$("#"+divId).append('<option value="'+data[i].id+'">'+data[i].fromName+'</option>');						
					}
				}
				form.render();
			}
		}
	})
}

function transferMethod(transfer,unitId){
	$.ajax({
  		  url:'unit/selectUserFactory'
  		  ,type:'get'
  		  ,data:{unitId:unitId}
  		  ,dataType:'json'
  		  ,async:false
  		  ,success:function(data){
  			if(data!=null && data!='' && data!=undefined){
  				var allFactory = data.allFactory;
  				var useFactory = data.useFactory;
  				var arr=new Array();
  				for (var i = 0; i < useFactory.length; i++) {
  					arr[i] = useFactory[i].id
				}
  				transfer.render({
				    elem: '#factoryList'
				    ,id: 'factoryFansfer' //定义唯一索引
				    ,title: ['所有工厂', '常用工厂']
				    ,showSearch: true
				    ,data:allFactory
				    ,value:arr
				    ,parseData: function(res){
				        return {
				          "value": res.id //数据值
				          ,"title": res.unitName //数据标题
				          //,"disabled": res.disabled  //是否禁用
				          //,"checked": res.checked //是否选中
				        }
				     }
				    ,onchange: function(obj, index){
				        var type = index == 0 ? 'insert' : 'delete' ;
				        var factoryId ='';
				        for (var i = 0; i < obj.length; i++) {
							var row = obj[i];
							if((i+1)==obj.length){
								factoryId += row.value;
							}else{
								factoryId += row.value + ",";
							}
						}
				        $.ajax({
				  	  		  url:'unit/updateUserFactory'
				  	  		  ,type:'post'
				  	  		  ,data:{type:type,factoryId:factoryId,unitId:unitId}
				  	  		  ,dataType:'json'
				  	  		  ,async:false
				  	  		  ,success:function(result){
				  	  			 if(result.code != 10000){
				  	  				 layer.msg(result.msg);
				  	  			 }
				  	  			transferMethod(transfer,unitId)
				  	  		  }
				  	  	})
				    }
			    })
			}
  		  }
  	  })
}

function selectDiscountAll(huixian,divId){
	$.ajax({
  		  url:'discount/selectDiscountAll'
  		  ,type:'get'
  		  ,dataType:'json'
  		  ,async:false
  		  ,success:function(data){
  			if(data!=null && data!='' && data!=undefined){
  				$("#"+divId).empty();
  				$("#"+divId).append('<option value="">请选择优惠券</option>');
  				for (var i = 0; i < data.length; i++) {
  					  var option = '';
					  if(data[i].factoryName!='' && data[i].factoryName != null && data[i].factoryName!=undefined){
						  option = data[i].factoryName + "专用"+data[i].discountPrice+"元优惠券";
					  }else{
						  if(data[i].textureName!='' && data[i].textureName != null && data[i].textureName!=undefined){
							  option += data[i].textureName; 
						  }
						  if(data[i].brandName!='' && data[i].brandName != null && data[i].brandName!=undefined){
							  option += data[i].brandName;
						  }
						  if(option!=''){
							  option += "专用"+data[i].discountPrice+"元优惠券";
						  }
					  }
					  if(option!='' && data[i].discountType == 3){
						  option = '平台发布'+data[i].discountPrice+"元优惠券";
					  }
					  if(huixian!=null && huixian!='' && huixian!=undefined){
						  if(huixian == data[i].id){
							  $("#discountName").val(data[i].discountName);
						  }
					  }else{
						  $("#"+divId).append('<option value="'+data[i].id+'">'+option+'</option>');						
					  }
  				   }
				}
				form.render();
			}
  	})
}