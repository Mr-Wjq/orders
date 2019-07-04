layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  searchCureList(form);
  
  var tableIns = table.render({
    elem: '#productTableId'
    ,url:'baseData/selectProduct'
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
    ,toolbar: '#productTableToolbar'
    ,title: '数据表'
    ,cols: [[

       {type: 'numbers', fixed: 'left'}
      ,{type:'checkbox'}
      ,{field:'cureName', title:'治疗类型' }
      ,{field:'textureName', title:'材质' }
      ,{field:'brandName', title:'品牌' }
      ,{field:'productName', title:'产品' }
      ,{field:'cz', title:'操作',templet: '#lineToolbar'}
    ]]
    ,page: true
  });
  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
		  cureId:$("#search_cureList").val(),
		  productName:$("#productName").val(),
		  textureName:$("#textureName").val(),
		  brandName:$("#brandName").val(),
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
  //头工具栏事件
  table.on('toolbar(productTableFilter)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    //新增用户
    if('insert' == obj.event){
    	layer.open({
    		type:1
    		,title:'新增产品'
    		,area:['50%','50%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#insertProductDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			$.ajax({
  		  		  url:'baseData/selectAllCureList'
  		  		  ,type:'get'
  		  		  ,dataType:'json'
  		  		  ,success:function(data){
  		  			if(data!=null && data!='' && data!=undefined){
  		  				$("#cureList").empty();
  		  				$("#cureList").append("<option value=''>请选择治疗类型</option>");
  						for (var i = 0; i < data.length; i++) {
  							$("#cureList").append('<option value="'+data[i].id+'">'+data[i].cureName+'</option>');						
  						}
  						form.render();
  					}
  		  		  }
  		  	  })
    		}
    		,end:function(){
    			$("#insertProductDiv").hide();
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
		  		  url:'baseData/deleteProduct'
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
    	});
    }
  });
  
  //行工具栏
  table.on('tool(productTableFilter)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
    	layer.open({
    		type:1
    		,title:'修改产品名称'
    		,area:['50%','50%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updateProductDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			$.ajax({
		  		  url:'baseData/selectAllCureList'
		  		  ,type:'get'
		  		  ,dataType:'json'
		  		  ,async:false
		  		  ,success:function(result){
		  			if(result!=null && result!='' && result!=undefined){
		  				$("#u_cureList").empty();
		  				$("#u_cureList").append("<option value=''>请选择治疗类型</option>");
						for (var i = 0; i < result.length; i++) {
							if(data.baseCureId == result[i].id){
								$("#u_cureList").append('<option value="'+result[i].id+'" selected>'+result[i].cureName+'</option>');
							}else{
								$("#u_cureList").append('<option value="'+result[i].id+'">'+result[i].cureName+'</option>');						
							}
						}
						form.render();
					}
		  		  }
		  	  })
		  	  form.val("updateProductFilter", {
				  "id": data.id,
				  "textureName": data.textureName,
				  "brandName": data.brandName,
				  "productName": data.productName
			  })
    		}
    		,end:function(){
    			$("#updateProductDiv").hide();
    		}
    	})
    }
  });
  
  var layer_curr_index = '';
  
  //监听提交
  form.on('submit(insert)', function(data){
	  
	 if((data.field.textureName == '' || data.field.textureName == null || data.field.textureName == undefined) && 
			 (data.field.brandName == '' || data.field.brandName == null || data.field.brandName == undefined) ){
		 layer.msg("材质和品牌不能都为空!");
		 return false;
	 };
	  
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'baseData/insertProduct'
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
	  });
    return false;
  });
  form.on('submit(update)', function(data){
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'baseData/updateProduct'
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
	  });
	  return false;
  });
  
});
function searchCureList(form){
	$.ajax({
		  url:'baseData/selectAllCureList'
		  ,type:'get'
		  ,dataType:'json'
		  ,async:false
		  ,success:function(result){
			if(result!=null && result!='' && result!=undefined){
				$("#search_cureList").empty();
				$("#search_cureList").append("<option value=''>治疗类型</option>");
				for (var i = 0; i < result.length; i++) {
					$("#search_cureList").append('<option value="'+result[i].id+'">'+result[i].cureName+'</option>');						
				}
				form.render();
			}
		  }
	  })
}
