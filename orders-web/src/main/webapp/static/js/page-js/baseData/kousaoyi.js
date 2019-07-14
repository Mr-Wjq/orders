layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  var tableIns = table.render({
    elem: '#kousaoyiTableId'
    ,url:'kousaoyi/select'
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
    ,toolbar: '#kousaoyiTableToolbar'
    ,title: '数据表'
    ,cols: [[

       {type: 'numbers', fixed: 'left'}
      ,{type:'checkbox'}
      ,{field:'kousaoyiName', title:'品牌' }
      ,{field:'openMethod', title:'启动链接' }
      ,{field:'cz', title:'操作',templet:function(row){
    	  return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>'+
    	  	'<a href="'+row.openMethod+'" class="layui-btn layui-btn-xs" lay-event="test">测试：打开口扫仪</a>';
    	  
       }}
    ]]
    ,page: true
  });
  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
		  kousaoyiName:$("#kousaoyiName").val(),
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
  //头工具栏事件
  table.on('toolbar(kousaoyiTableFilter)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    //新增用户
    if('insert' == obj.event){
    	layer.open({
    		type:1
    		,title:'新增口扫仪'
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
		  		  url:'kousaoyi/delete'
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
  table.on('tool(kousaoyiTableFilter)', function(obj){
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
  				  "kousaoyiName": data.kousaoyiName,
  				  "openMethod": data.openMethod,
  			  })
    		}
    		,end:function(){
    			$("#updateDiv").hide();
    		}
    	})
    }else if(obj.event === 'test'){
    	
    }
  });
  
  //监听提交
  form.on('submit(insert)', function(data){
	  
	 var length1 = data.field.openMethod.split("\'").length;
	 var length2 = data.field.openMethod.split("\"").length;
	 if(length1>1 || length2>1){
		  layer.msg("启动链接中不能包含单引号或双引号!");
			 return false;
	  }
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'kousaoyi/insert'
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
	  
	  var length1 = data.field.openMethod.split("\'").length;
	  var length2 = data.field.openMethod.split("\"").length;
	  if(length1>1 || length2>1){
		  layer.msg("启动链接中不能包含单引号或双引号!");
			 return false;
	  }
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'kousaoyi/update'
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
