layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  
  var tableIns = table.render({
    elem: '#userTableId'
    ,url:'user/selectUserAndUnit'
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
      ,{field:'zhName', title:'姓名' }
      ,{field:'phone', title:'手机号' }
      ,{field:'email', title:'邮箱' }
      ,{field:'status', title:'状态' ,templet: '#statusBtn'}
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
      ,{field:'cz', title:'操作',templet: '#lineToolbar'}
    ]]
    ,page: true
  });
  
  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
	  		zhName:$("#zhName").val(),
	  		loginName:$("#loginName").val(),
	  		phone:$("#phone").val(),
	  		_status:$("#status").val()
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
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
    		,area:['50%','60%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#insertUserDiv")
    		,yes:function(){
    			layer.closeAll();
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
    		,area:['50%','50%']
    		,shade:0.8
    		,btn:['关闭']
    		,content:$("#updateUserDiv")
    		,yes:function(){
    			layer.closeAll();
    		}
    		,success:function(){
    			$("#userId").val(data.userId);
    			$("#updateUserDiv input[name='loginName']").val(data.loginName);
    			$("#updateUserDiv input[name='zhName']").val(data.zhName);
    			$("#updateUserDiv input[name='phone']").val(data.phone);
    			$("#updateUserDiv input[name='email']").val(data.email);
    		}
    		,end:function(){
    			$("#updateUserDiv").hide();
    		}
    	})
    }
  });
  
  //监听提交
  form.on('submit(userInsert)', function(data){
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  var index = layer.load(2);
		  $.ajax({
			  url:'user/systemInsert'
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

});
