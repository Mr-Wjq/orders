layui.use(['table','laydate','element'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  var element = layui.element;  
  
  searchCureList(form);
  
  var addTableIns = table.render({
	    elem: '#productAddTableId'
	    ,url:'baseData/selectFactoryProduct'
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
	    ,toolbar: '#productAddTableToolbar'
	    ,title: '数据表'
	    ,cols: [[
	       {type: 'numbers', fixed: 'left'}
	      ,{type:'checkbox'}
	      ,{field:'cureName', title:'治疗类型' }
	      ,{field:'textureName', title:'材质' }
	      ,{field:'brandName', title:'品牌' }
	      ,{field:'productName', title:'产品' }
	      ,{field:'price', title:'价格', edit: 'text'}
	    ]]
	    ,page: true
	  });
	  
  	  var notAddTableIns;
  	  var tabIndex = 0;
  	  element.on('tab(productTabBrief)', function(data){
  		  tabIndex = data.index;
  		  if(data.index == 0){
			  addTableIns.reload();
		  }else if(data.index == 1){
			  notAddTableIns = table.render({
				    elem: '#productNotAddTableId'
				    ,url:'baseData/selectNotAddProductVO'
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
				    ,toolbar: '#productNotAddTableToolbar'
				    ,title: '数据表'
				    ,cols: [[

				       {type: 'numbers', fixed: 'left'}
				      ,{type:'checkbox'}
				      ,{field:'cureName', title:'治疗类型' }
				      ,{field:'textureName', title:'材质' }
				      ,{field:'brandName', title:'品牌' }
				      ,{field:'productName', title:'产品' }
				    ]]
				    ,page: true
				  });
		  };
	  });
  

  
  //监听单元格编辑
  table.on('edit(productAddTableFilter)', function(obj){
    var value = obj.value //得到修改后的值
    ,data = obj.data //得到所在行所有键值
    ,field = obj.field; //得到字段
    
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if(reg.test(value)){
    	$.ajax({
	  		  url:'baseData/factoryUpdateProductPrice'
	  		  ,type:'post'
	  		  ,data:{id:data.id,price:value}
	  		  ,dataType:'json'
	  		  ,success:function(result){
	  			  if(10000 == result.code){
	  				  layer.closeAll();
	  				  addTableIns.reload();
	  				  layer.msg("修改成功");
	  			  }else{
	  				  layer.msg(result.msg);
	  			  }
	  		  }
	  	 })
    }else{
    	layer.msg('请输入正确的金额');
    	addTableIns.reload();
    	return false;
    }
  });
  
  //搜索按钮
  $("#searchBtn").click(function(){
	  if(tabIndex == 0){
		  addTableIns.reload({where:{
			  cureId:$("#search_cureList").val(),
			  productName:$("#productName").val(),
			  textureName:$("#textureName").val(),
			  brandName:$("#brandName").val(),
		  	},page: {
		  		curr: 1 //重新从第 1 页开始
		  	}
		  });
	  }else{
		  notAddTableIns.reload({where:{
			  cureId:$("#search_cureList").val(),
			  productName:$("#productName").val(),
			  textureName:$("#textureName").val(),
			  brandName:$("#brandName").val(),
		  	},page: {
		  		curr: 1 //重新从第 1 页开始
		  	}
		  });
	  }
	  
  })
  //头工具栏事件
  table.on('toolbar(productAddTableFilter)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    if('delete' == obj.event){
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
		  		  url:'baseData/factoryDeleteProduct'
		  		  ,type:'post'
		  		  ,data:{ids:ids}
		  		  ,dataType:'json'
		  		  ,success:function(result){
		  			  if(10000 == result.code){
		  				  layer.closeAll();
		  				  addTableIns.reload();
		  				  layer.msg("删除成功");
		  			  }else{
		  				  layer.msg(result.msg);
		  			  }
		  		  }
		  	  })
    	});
    }
  });
  
  table.on('toolbar(productNotAddTableFilter)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    if('factoryAddProduct' == obj.event){
	    	var checkUsers = checkStatus.data;
	    	if(checkUsers.length==0){
	    		layer.msg("请选择要添加的产品");
	    		return;
	    	}
	    	layer.confirm('确认添加这些产品吗？', {icon: 3, title:'提示'}, function(index){  
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
			  		  url:'baseData/factoryAddProduct'
			  		  ,type:'post'
			  		  ,data:{ids:ids}
			  		  ,dataType:'json'
			  		  ,success:function(result){
			  			  if(10000 == result.code){
			  				  layer.closeAll();
			  				  notAddTableIns.reload();
			  				  layer.alert("您选择的产品已添加成功，请到“已添加产品”中查看");
			  			  }else{
			  				  layer.msg(result.msg);
			  			  }
			  		  }
			  	  })
	    	});
	    }
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
