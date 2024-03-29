layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  var tableIns = table.render({
    elem: '#tableId'
    ,url:'unitFrom/select'
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
      ,{field:'fromName', title:'来源' }
      ,{field:'fuwufei', title:'服务费' }
      ,{field:'cz', title:'操作',templet:function(row){
    	  return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>';
       }}
    ]]
    ,page: true
  });

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
		  		  url:'unitFrom/delete'
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
  				  "fromName": data.fromName,
  				  "fuwufei": data.fuwufei,
  			  })
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
			  url:'unitFrom/insert'
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
			  url:'unitFrom/update'
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
});
