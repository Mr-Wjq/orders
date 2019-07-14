var form;
layui.use(['table','laydate'], function(){
	
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

var sm = {
	pageSkip:function(url){
		if (url != null && url != "" && undefined != url) {
			$("#systemIframe").attr("src",url);
		}
	}
}
$(function(){
	$("#logout").click(function(){
		layer.confirm('是否退出?', {icon: 3, title:'提示'}, function(index){
			location.href=basePath + "system/logout";
		})
	})

	$("#updatePwd").click(function(){
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
    		,end:function(){
    			$("#updatePasswordDiv").hide();
    		}
    	})
	})
})

