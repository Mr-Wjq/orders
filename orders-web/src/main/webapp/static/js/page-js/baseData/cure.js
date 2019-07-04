layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  var tableIns = table.render({
    elem: '#cureTableId'
    ,url:'baseData/selectCure'
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
    ,toolbar: '#cureTableToolbar'
    ,title: '数据表'
    ,cols: [[

       {type: 'numbers', fixed: 'left'}
      ,{type:'checkbox'}
      ,{field:'cureName', title:'治疗类型' }
      ,{field:'cz', title:'操作',templet: '#lineToolbar'}
    ]]
    ,page: true
  });

  //头工具栏事件
  table.on('toolbar(cureTableFilter)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    //新增用户
    if('insert' == obj.event){
    	layer.open({
    		type:1
    		,title:'新增治疗类型'
    		,area:['30%','30%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#insertCureDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,end:function(){
    			$("#insertCureDiv").hide();
    		}
    	})
    //删除用户
    }else if('delete' == obj.event){
    	var checkUsers = checkStatus.data;
    	if(checkUsers.length==0){
    		layer.msg("请选择要删除的数据");
    		return;
    	}
    	layer.confirm('删除治疗类型则相关数据会一起被删除,是否继续？', {icon: 3, title:'提示'}, function(index){  
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
		  		  url:'baseData/deleteCure'
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
  table.on('tool(cureTableFilter)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
    	layer.open({
    		type:1
    		,title:'修改治疗类型'
    		,area:['30%','30%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updateCureDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			$("#cureId").val(data.id);
    			$("#updateCureDiv input[name='cureName']").val(data.cureName);
    		}
    		,end:function(){
    			$("#updateCureDiv").hide();
    		}
    	})
    }
  });
  
  var layer_curr_index = '';

  
  //监听提交
  form.on('submit(insert)', function(data){
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  var index = layer.load(2);
		  $.ajax({
			  url:'baseData/insertCure'
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
  form.on('submit(update)', function(data){
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  var index = layer.load(2);
		  $.ajax({
			  url:'baseData/updateCure'
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