layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  var tableIns = table.render({
    elem: '#tableId'
    ,url:'discount/select'
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
      ,{field:'discountName', title:'优惠券' }
      ,{field:'discountPrice', title:'金额' }
      ,{field:'discountType', title:'类型' ,templet:function(row){
    	 if(1 == row.discountType){
    		 return '工厂优惠券';
    	 }else if(2 == row.discountType){
    		 return '产品材质优惠券';
    	 }else if(3 == row.discountType){
    		 return '平台优惠券';
    	 }
      }}
      ,{field:'factoryName', title:'工厂' }
      ,{field:'productName', title:'产品',templet:function(row){
    	  var productName = '';
		  if(row.textureName!='' && row.textureName != null && row.textureName!=undefined){
			  productName = row.textureName;
		  }
		  if(row.brandName!='' && row.brandName != null && row.brandName!=undefined){
			  if(productName!=''){
				  productName +='-' + row.brandName;  
			  }else{
				  productName = row.brandName;
			  }
		  }
		  return productName;
       }}
      ,{field:'cz', title:'操作',templet:function(row){
    	  return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>';
       }}
    ]]
    ,page: true
  });
  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
		  discountName:$("#discountName").val(),
		  discountType:$("#discountType").val()
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
    		,area:['50%','50%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#insertDiv")
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
		  		  url:'discount/delete'
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
    if(obj.event === 'edit'){
    	layer.open({
    		type:1
    		,title:'修改'
    		,area:['50%','50%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updateDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			form.val("updateFilter", {
  				  "id": data.id,
  				  "discountName": data.discountName,
  				  "discountPrice": data.discountPrice,
  				  "discountType": data.discountType
  			  })
  			  if(data.factoryId!=null && data.factoryId!='' && data.factoryId!=undefined ){
  				  getFactoryOrProduct('factory','u_factoryId','u_baseProductId',data.factoryId);
  			  }else if(data.baseProductId!=null && data.baseProductId!='' && data.baseProductId!=undefined ){
  				  getFactoryOrProduct('product','u_baseProductId','u_factoryId',data.baseProductId);
  			  }else if(data.discountType == 3){
  				clearFactoryOrProduct();
  			  }
    		}
    		,end:function(){
    			$("#updateDiv").hide();
    		}
    	})
    }
  });
  
  //监听提交
  form.on('submit(insert)', function(data){
	  
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'discount/insert'
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
			  url:'discount/update'
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
  form.on('select(i_discountType)', function(obj){
	if(obj.value !=null && obj.value != '' && obj.value != undefined){
		 if(1 == obj.value){
			  	getFactoryOrProduct('factory','i_factoryId','i_baseProductId');
			}else if(2 == obj.value){
				getFactoryOrProduct('product','i_baseProductId','i_factoryId');
			}else if(3 == obj.value){
				clearFactoryOrProduct();
		}
	}else{
		clearFactoryOrProduct();
	}
	form.render();
  });
  form.on('select(u_discountType)', function(obj){
	  if(obj.value !=null && obj.value != '' && obj.value != undefined){
		    if(1 == obj.value){
			  	getFactoryOrProduct('factory','u_factoryId','u_baseProductId');
			}else if(2 == obj.value){
				getFactoryOrProduct('product','u_baseProductId','u_factoryId');
			}else if(3 == obj.value){
				clearFactoryOrProduct();
			}
	  }else{
		  clearFactoryOrProduct();
	  }
	  form.render();
	  
  });
});

function getFactoryOrProduct(type,id,hideId,huixian){
	if('factory' == type){
		$("#"+id+"Div").show();
		$("#"+id).empty();
		$.ajax({
		  url:'unit/selectAllFactory'
		  ,type:'get'
		  ,dataType:'json'
		  ,async:false
		  ,success:function(data){
			  if(data!='' && data != null && data!=undefined){
				  $("#"+id).append('<option value="">请选择工厂</option>');
				  for (var i = 0; i < data.length; i++) {
					  if(huixian == data[i].id){
						  $("#"+id).append('<option selected value="'+data[i].id+'">'+data[i].unitName+'</option>');
					  }else{
						  $("#"+id).append('<option value="'+data[i].id+'">'+data[i].unitName+'</option>');
					  }
				}
			  }
		  }
		  })
		$("#"+hideId+"Div").hide();
		$("#"+hideId).empty();
	}else if('product' == type){
		$("#"+id+"Div").show();
		$("#"+id).empty();
		$.ajax({
		  url:'baseData/selectAllProduct'
		  ,type:'get'
		  ,dataType:'json'
		  ,async:false
		  ,success:function(data){
			  if(data!='' && data != null && data!=undefined){
				  $("#"+id).append('<option value="">请选择产品材质</option>');
				  for (var i = 0; i < data.length; i++) {
					  var option = '';
					  if(data[i].textureName!='' && data[i].textureName != null && data[i].textureName!=undefined){
						  option = data[i].textureName;
					  }
					  if(data[i].brandName!='' && data[i].brandName != null && data[i].brandName!=undefined){
						  if(option!=''){
							  option +='--' + data[i].brandName;  
						  }else{
							  option = data[i].brandName;
						  }
					  }
					  if(huixian == data[i].id){
						  $("#"+id).append('<option selected value="'+data[i].id+'">'+option+'</option>');
					  }else{
						  $("#"+id).append('<option value="'+data[i].id+'">'+option+'</option>');
					  }
				}
			  }
		  }
		})
		$("#"+hideId+"Div").hide();
		$("#"+hideId).empty();
	}
	form.render();
}
function clearFactoryOrProduct(){
	$("#i_factoryIdDiv").hide();
	$("#i_factoryId").empty();
	$("#i_baseProductIdDiv").hide();
	$("#i_baseProductId").empty();
	  $("#u_factoryIdDiv").hide();
	  $("#u_factoryId").empty();
	  $("#u_baseProductIdDiv").hide();
	  $("#u_baseProductId").empty();	
}