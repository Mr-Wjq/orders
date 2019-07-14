layui.use(['transfer','table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  var transfer = layui.transfer

  //监听下拉框
	form.on('select(threeDivBaseCureId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			selectProductTexture(obj.value,form)
		}else{
			$("#baseProductId").empty();
			$("#baseProductId").append("<option value=''>暂无产品材质</option>");
			form.render();
		}
		
	});
	form.on('select(threeDivBaseProductId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			selectFactoryByProductId(obj.value,form)
		}else{
			$("#receiveUnitId").empty();
			$("#receiveUnitId").append("<option value=''>暂无加工厂</option>");
		}
		form.render();
	}); 
	form.on('checkbox(ordersAccessory)', function(data){
		  
		if(data.elem.checked == true && data.value == 1){
			  $("#accessoryTr").append("<td class='cols-font' >扫描文件</td><td colspan='3' >" +
			  		"<button class='layui-btn' type='button' onclick=\"$('#uploadFile').val('');$('#uploadFile').click();\">" +
			  	    "<i class='layui-icon'>&#xe62d;</i>上传扫描文件</button>"+
        	        "<input id='uploadFile' type='file'  style='display:none;position: fixed;bottom: 30px;right: 60px;z-index: 999999;'"+
					"onchange=\"$('#showFileName').html(this.value);\" multiple><span style='margin-left:20px;' id='showFileName'></span></td>");
			  $("#kousaoDiv").append('<a class="layui-btn layui-btn-sm" href="runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=0">打开口扫仪</a>')
		 };
		 if(data.elem.checked == false && data.value == 1){
			  $("#accessoryTr").empty();
			  $("#kousaoDiv").empty();
		 };
	}); 
  $("#useFactory").click(function(){
	  layer.open({
  		type:1
  		,title:'设置常用工厂'
  		,area:['50%','70%']
  		,shade:0.8
  		,btn:['关闭']
  		,content:$("#userFactoryDiv")
  		,yes:function(){
  			layer.closeAll();
  		}
  		,success:function(){
  			transferMethod(transfer);
  		}
  		,end:function(){
  			$("#userFactoryDiv").hide();
  		}
  	})
  })
  form.on('submit(createOrders)', function(data){
	  createOrders();
	  return false;
  });
  form.on('submit(updateOrders)', function(data){
	  updateOrders();
	  return false;
  });
  getBaseCure(form);
  
  var ordersId = $("#ordersId").val();
  if(ordersId != '' && ordersId!=null && ordersId !=undefined ){
	  huixianUpdateOrders(form,ordersId);
  }
})

function createOrders(){
	var formData = new FormData();
	formData.append("patientName",$("#patientName").val());
	formData.append("patientAge",$("#patientAge").val());
	formData.append("patientSex",$("input[name='patientSex']:checked").val());
	formData.append("patientType",$("input[name='patientType']:checked").val());
	var ordersAccessory = '';
	$.each($('input[name="ordersAccessory"]:checkbox:checked'),function(){
		ordersAccessory += $(this).val() + ",";
    });
    ordersAccessory = ordersAccessory.substring(0,ordersAccessory.length-1);
    
	/*var ordersAccessory = $("input[name='ordersAccessory']:checked").val();*/
	if(ordersAccessory == ''){
		layer.msg('请选择订单附件') 
		return false;
	} 
	formData.append("ordersAccessory",ordersAccessory);
	
	var fileName = $("#uploadFile").val();
	if(fileName!='' && fileName !=null && fileName!=undefined){
		var myfile = $("#uploadFile")[0].files[0];
		if(myfile.size>52428800){
	        layer.msg("请上传小于50M的文件");
	        return false;
	    }
		formData.append("accessoryFile",$("#uploadFile")[0].files[0]);
	}
	formData.append("toothPosition1",$("#toothPosition1").val());
	formData.append("toothPosition2",$("#toothPosition2").val());
	formData.append("toothPosition3",$("#toothPosition3").val());
	formData.append("toothPosition4",$("#toothPosition4").val());
	formData.append("color",$("#color").val());
	formData.append("baseCureId",$("#baseCureId").val());
	formData.append("baseProductId",$("#baseProductId").val());
	formData.append("receiveUnitId",$("#receiveUnitId").val());
	formData.append("remarks",$("#remarks").val());
	$.ajax({
		url:'orders/createOrders'
		,type:'post'
		,cache: false//上传文件无需缓存
        ,processData: false//用于对data参数进行序列化处理 这里必须false
        ,contentType: false //必须
		,data:formData
	    ,dataType:"json"
	    ,success:function(result){
	    	if(10000 == result.code){
	    		layer.msg("订单创建成功,并已下发到指定工厂", {icon: 1,time:2000},function(){
	    			location.href =basePath + "page/toDoctorOrders";
				});
			}else{
				layer.msg(result.msg);
			}
	    }
	})
}
function updateOrders(){
	var formData = new FormData();
	formData.append("ordersId",$("#ordersId").val());
	formData.append("patientName",$("#patientName").val());
	formData.append("patientAge",$("#patientAge").val());
	formData.append("patientSex",$("input[name='patientSex']:checked").val());
	formData.append("patientType",$("input[name='patientType']:checked").val());
	var ordersAccessory = '';
	$.each($('input[name="ordersAccessory"]:checkbox:checked'),function(){
		ordersAccessory += $(this).val() + ",";
    });
    ordersAccessory = ordersAccessory.substring(0,ordersAccessory.length-1);
    
	/*var ordersAccessory = $("input[name='ordersAccessory']:checked").val();*/
	if(ordersAccessory == ''){
		layer.msg('请选择订单附件') 
		return false;
	} 
	formData.append("ordersAccessory",ordersAccessory);
	
	var fileName = $("#uploadFile").val();
	if(fileName!='' && fileName !=null && fileName!=undefined){
		var myfile = $("#uploadFile")[0].files[0];
		if(myfile.size>52428800){
	        layer.msg("请上传小于50M的文件");
	        return false;
	    }
		formData.append("accessoryFile",$("#uploadFile")[0].files[0]);
	}
	formData.append("toothPosition1",$("#toothPosition1").val());
	formData.append("toothPosition2",$("#toothPosition2").val());
	formData.append("toothPosition3",$("#toothPosition3").val());
	formData.append("toothPosition4",$("#toothPosition4").val());
	formData.append("color",$("#color").val());
	formData.append("baseCureId",$("#baseCureId").val());
	formData.append("baseProductId",$("#baseProductId").val());
	formData.append("receiveUnitId",$("#receiveUnitId").val());
	formData.append("remarks",$("#remarks").val());
	$.ajax({
		url:'orders/updateOrders'
		,type:'post'
		,cache: false//上传文件无需缓存
        ,processData: false//用于对data参数进行序列化处理 这里必须false
        ,contentType: false //必须
		,data:formData
	    ,dataType:"json"
	    ,success:function(result){
	    	if(10000 == result.code){
	    		layer.msg("操作成功",{time:1000},function(){
	    			parent.layer.closeAll();
	    		});
			}else{
				layer.msg(result.msg);
			}
	    }
	})
}
function transferMethod(transfer){
	$.ajax({
  		  url:'unit/selectUserFactory'
  		  ,type:'get'
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
				  	  		  ,data:{type:type,factoryId:factoryId}
				  	  		  ,dataType:'json'
				  	  		  ,async:false
				  	  		  ,success:function(result){
				  	  			 if(result.code != 10000){
				  	  				 layer.msg(result.msg);
				  	  			 }
				  	  			transferMethod(transfer)
				  	  		  }
				  	  	})
				    }
			    })
			}
  		  }
  	  })
}
function getBaseCure(form){
	$.ajax({
  		  url:'baseData/selectAllCureList'
  		  ,type:'get'
  		  ,dataType:'json'
  		  ,success:function(data){
  			if(data!=null && data!='' && data!=undefined){
  				$("#baseCureId").empty();
  				$("#baseCureId").append("<option value=''>请选择治疗类型</option>");
				for (var i = 0; i < data.length; i++) {
					$("#baseCureId").append('<option value="'+data[i].id+'">'+data[i].cureName+'</option>');						
				}
				form.render();
			}
  		  }
  	  })
}
function selectProductTexture(baseCureId,form,productId){
	if(baseCureId==null || baseCureId == '' || baseCureId == undefined){
		return false;
	}
	$.ajax({
		url:'baseData/selectProductTexture'
		,type:'get'
		,data:{baseCureId:baseCureId}
		,dataType:'json'
		,success:function(data){
			if(data!=null && data!='' && data!=undefined){
				$("#baseProductId").empty();
				$("#baseProductId").append("<option value=''>请选择产品材质</option>");
				for (var i = 0; i < data.length; i++) {
					if(productId!=null && productId!='' && productId!=undefined && productId == data[i].id){
						$("#baseProductId").append('<option value="'+data[i].id+'" selected>'+data[i].name+'</option>');						
					}else{
						$("#baseProductId").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');						

					}
				}
				form.render();
			}
		}
	})
}

function selectFactoryByProductId(productId,form,receiveFactoryId){
	$.ajax({
		url:'unit/selectFactoryByProductId'
		,type:'get'
		,data:{productId:productId}
		,dataType:'json'
		,async:false
		,success:function(data){
			if(data!=null && data!='' && data!=undefined){
				$("#receiveUnitId").empty();
				$("#receiveUnitId").append("<option value=''>请选择加工厂</option>");
				for (var i = 0; i < data.length; i++) {
					if(receiveFactoryId!=null && receiveFactoryId!='' && receiveFactoryId!=undefined && receiveFactoryId == data[i].id){
						$("#receiveUnitId").append('<option value="'+data[i].id+'" selected>'+data[i].unitName+'</option>');						
					}else{
						$("#receiveUnitId").append('<option value="'+data[i].id+'">'+data[i].unitName+'</option>');						
					}
				}
			}else{
				$("#receiveUnitId").empty();
				$("#receiveUnitId").append("<option value=''>暂无加工厂</option>");
			}
			form.render();
		}
	})
}
function huixianUpdateOrders(form,ordersId){
	$.ajax({
		url:'orders/selectOrdersById'
		,type:'get'
		,data:{ordersId:ordersId}
		,dataType:'json'
		,success:function(data){
			if(data!=null && data!='' && data!=undefined){
				form.val("ordersTable", {
					"patientName": data.patientName,
					"patientAge": data.patientAge,
					"patientSex": data.patientSex,
					"patientType": data.patientType,
					"color": data.color,
					"baseCureId": data.baseCureId,
					"remarks": data.remarks
				})
				
				var ordersAccessory = data.ordersAccessory;
				console.log(ordersAccessory);
				var array = ordersAccessory.split(",");
				for (var i = 0; i < array.length; i++) {
					$.each($('input[name="ordersAccessory"]'),function(){
						if(array[i] == $(this).val()){
							$(this).prop('checked',true);
						}
					});
				}
				
				
				$("#toothPosition1").val(data.toothPosition1);
				$("#toothPosition2").val(data.toothPosition2);
				$("#toothPosition3").val(data.toothPosition3);
				$("#toothPosition4").val(data.toothPosition4);
				
				selectProductTexture(data.baseCureId,form,data.baseProductId);
				selectFactoryByProductId(data.baseProductId,form,data.receiveUnitId);
				
				if(data.accessoryName != '' && data.accessoryName != null && data.accessoryName != undefined){
					  $("#accessoryTr").empty();
					  $("#kousaoDiv").empty();
					  $("#accessoryTr").append("<td class='cols-font' >扫描文件</td><td colspan='3' >" +
					  		"<button class='layui-btn' type='button' onclick=\"$('#uploadFile').val('');$('#uploadFile').click();\">" +
					  	    "<i class='layui-icon'>&#xe62d;</i>上传扫描文件</button>"+
		          	        "<input id='uploadFile' type='file'  style='display:none;position: fixed;bottom: 30px;right: 60px;z-index: 999999;'"+
							"onchange=\"$('#showFileName').html(this.value);\" multiple><span style='margin-left:20px;' id='showFileName'></span></td>");
					  $("#kousaoDiv").append('<a class="layui-btn layui-btn-sm" href="runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=0">打开口扫仪</a>')
					  var fileName = data.accessoryName.split(",,");
					  $('#showFileName').html(fileName[1]);
				 }else{
					  $("#accessoryTr").empty();
					  $("#kousaoDiv").empty();
				 };
			}
		}
	})
}