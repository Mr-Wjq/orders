layui.use(['upload','laydate','table'], function(){
	var table = layui.table;
	var dscTableIns = table.render({
		    elem: '#myDiscount'
		    ,width:798
		    ,url:'discount/selectUnitDiscount'
	    	,where:{
	    		sysUnitId:$("#unitId").val()
			}
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
		    ,title: '数据表'
		    ,cols: [[

		       {type: 'numbers', fixed: 'left'}
		      ,{field:'discountName', title:'优惠券' ,templet:function(row){
			    	 var option = '';
  					  if(row.factoryName!='' && row.factoryName != null && row.factoryName!=undefined){
  						  option = row.factoryName + "专用"+row.discountPrice+"元优惠券";
  					  }else{
  						  if(row.textureName!='' && row.textureName != null && row.textureName!=undefined){
  							  option += row.textureName; 
  						  }
  						  if(row.brandName!='' && row.brandName != null && row.brandName!=undefined){
  							  option += row.brandName;
  						  }
  						  if(option!=''){
  							  option += "专用"+row.discountPrice+"元优惠券";
  						  }
  					  }
  					  if(option!='' && row.discountType == 3){
  						  option = '平台发布'+row.discountPrice+"元优惠券";
  					  }
  					  return option;
 			   }}
		      ,{field:'discountPrice', title:'金额' ,width:80}
		      ,{field:'discountType', title:'类型',width:100,templet:function(row){
		    	  if(1 == row.discountType){
		    		  return '工厂';
		    	  }else if(2 == row.discountType){
		    		return '产品材质';
		    	  }else if(3 == row.discountType){
		    		return '平台'; 
		    	  }
		       } }
		      ,{field:'num', title:'数量' ,width:80}
		      ,{field:'endTime', title:'到期时间' ,width:150,templet:function(row){
			    	  return common_tool.timeDay(row.endTime);
			      }}
		    ]]
      });
	
	
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
	
	form.on('select(u_unitProvinceId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'u_unitCityId',obj.value);
		}
		
	}); 
	form.on('select(u_unitCityId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'u_unitDistrictId',obj.value);
		}
	});
	
  form.on('submit(userUpdate)', function(data){
	  layer.confirm('确定修改？', {icon: 3, title:'提示'}, function(index){
		  var index = layer.load(2);
		  $.ajax({
			  url:'user/personalUpdate'
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

$(function(){
	getMyFactory();
	selectCurrUnit();
})

function getMyFactory(){
	$.ajax({
		url: "unit/selectMyFactory"
		,type:"get"
		,dataType:"json"
		,async:false
		,success:function(data){
			if(data!='' && data != null && data != undefined){
				$("#myFactory").empty();
				for (var i = 0; i < data.length; i++) {
					$("#myFactory").append('<span class="layui-badge">'+data[i].unitName+'</span>');
				}
			}
		}
	})
}
function selectCurrUnit(){
	$.ajax({
		url: "unit/selectCurrUnit"
		,type:"get"
		,dataType:"json"
		,async:false
		,success:function(result){
			if(result.code == 10000){
				var data = result.data;
				getCountryByPid(form,'u_unitProvinceId',1,data.unitProvinceId);
				getCountryByPid(form,'u_unitCityId',data.unitProvinceId,data.unitCityId);
				getCountryByPid(form,'u_unitDistrictId',data.unitCityId,data.unitDistrictId);
				form.val("updateUserFilter", {
    				  "unitName": data.unitName,
    				  "unitType": data.unitType,
    				  "unitAddress": data.unitAddress,
    			})
			}
		}
	})
}
function getCountryByPid(form,divId,pid,huixian){
	
	switch (divId) {
	case 'u_unitProvinceId':
		$("#u_unitCityId").empty();
		$("#u_unitDistrictId").empty();
		$("#u_unitProvinceId").empty();
		$("#u_unitProvinceId").append('<option value="">请选择省</option>');
		break;
	case 'u_unitCityId':
		$("#u_unitDistrictId").empty();
		$("#u_unitCityId").empty();
		$("#u_unitCityId").append('<option value="">请选择市</option>');
		break;
	case 'u_unitDistrictId':
		$("#u_unitDistrictId").empty();
		$("#u_unitDistrictId").append('<option value="">请选择区/县</option>');
		break;
	}
	
	$.ajax({
		url: "baseCountry/getCountryByPid"
		,type:"get"
		,data:{pid:pid}
		,dataType:"json"
		,success:function(data){
			if(data!=null && data!='' && data!=undefined){
				for (var i = 0; i < data.length; i++) {
					if(huixian == data[i].id){
						$("#"+divId).append('<option value="'+data[i].id+'" selected>'+data[i].country+'</option>');
					}else{
						$("#"+divId).append('<option value="'+data[i].id+'">'+data[i].country+'</option>');						
					}
				}
				form.render();
			}
		}
	})
}
