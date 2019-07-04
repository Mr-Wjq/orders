layui.use(['upload','laydate'], function(){
	var upload = layui.upload;
	var uploadInst = upload.render({
	    elem: '.userFaceBtn'
	    ,url: 'user/uploadUserPhoto'
	    ,field:'photoFile'
	    ,size:4096
    	,data: {
    		userId: function(){
    		    return $('#userId').val();
    		  }
    		}
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#userFace').attr('src', result); //图片链接（base64）
	      });
	    }
	    ,done: function(res){
	    	layer.msg(res.msg);
	        if(res.code != 10000){
	        	var demoText = $('#demoText');
	  	        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	  	        demoText.find('.demo-reload').on('click', function(){
	  	        uploadInst.upload();
	  	      });
	        }
	    }
	    ,error: function(){
	      //演示失败状态，并实现重传
	      var demoText = $('#demoText');
	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	      demoText.find('.demo-reload').on('click', function(){
	        uploadInst.upload();
	      });
	    }
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
