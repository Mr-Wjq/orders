layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;

  form.on('radio(unitTypeFilter)', function(data){
	  var roleId = $("input[name='roleId']:checked").val();
	  if(roleId == 2) unitList(data.value);
  }); 
  
  form.on('radio(roleIdFilter)', function(data){
	  if(data.value == 1){
		  $("#showUnitDiv").show();
		  unitList($("input[name='unitType']:checked").val());
	  }else if(data.value == 2){
		  $("#showUnitDiv").hide();
		  $("#unitList").empty();
	  }
  }); 
  form.on('submit(userInsert)', function(data){
	  layer.confirm('确定提交？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'user/register'
			  ,type:'post'
			  ,data:data.field
			  ,dataType:'json'
			  ,success:function(result){
				  if(10000 == result.code){
					  $("#twoDiv").hide();
					  $("#threeDiv").show();
					  nextThree();
				  }else{
					  layer.msg(result.msg);
				  }
			  }
		  })  
		  layer.close(index);
	  });
	  return false;
  });
  //获取单位
  var unitList = function(unitType){
	//获取单位
	  $.ajax({
		  url:'unit/getUnitList'
		  ,type:'get'
		  ,data:{unitType:unitType}
	  	  ,dataType:'json'
		  ,async:false
		  ,success:function(result){
			  if(10000 == result.code){
				  $("#unitList").empty();
				  $("#unitList").append('<option value="">请选择单位名称</option>');
				  var data = result.data;
				  for (var i = 0; i < data.length; i++) {
					  $("#unitList").append('<option value="'+data[i].id+'">'+data[i].unitName+'</option>');
				  }
				  form.render('select');
			  }else{
				  layer.msg(result.msg);
			  }
		  }
	  })
  };
  	//	监听下拉框
	form.on('select(unitProvinceId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'unitCityId',obj.value);
		}
		
	}); 
	form.on('select(unitCityId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			getCountryByPid(form,'unitDistrictId',obj.value);
		}
	}); 
  getCountryByPid(form,'unitProvinceId',1);
})
var soutside1ab=document.getElementById("outside1abs");
var soutside2as=document.getElementById("outside2as");
function nextTwo(){
	var unitName = $("input[name='unitName']").val();
	if(unitName=='' || unitName == null || unitName == undefined){
		layer.msg('请输入单位名称！');
		return false;
	}
	if(!(/^[a-zA-Z0-9\u4e00-\u9fa5]{2,50}$/.test(unitName))){
		layer.alert('单位名称为2-50个中文、字母或数字');
		return false;
	  }
	var flag = true;
	$.ajax({
		url: "unit/selectByUnitName"
		,type:"get"
		,data:{unitName:unitName}
		,dataType:"json"
		,async:false
		,success:function(result){
			if(result.code != 10000){
				layer.alert(result.msg);
				flag = false;
			}
		}
	})
	if(!flag){
		return false;
	}
	var unitType = $("input[name='unitType']:checked").val();
	if(unitType=='' || unitType == null || unitType == undefined){
		layer.msg('请选择单位类型！');
		return false;
	}
	var unitProvinceId = $("#unitProvinceId").val();
	var unitCityId = $("#unitCityId").val();
	if(unitProvinceId=='' || unitProvinceId == null || unitProvinceId == undefined){
		layer.msg('请选择省！');
		return false;
	}
	if(unitCityId=='' || unitCityId == null || unitCityId == undefined){
		layer.msg('请选择市！');
		return false;
	}
	$("#oneDiv").hide();
	$("#twoDiv").show();
    soutside1ab.classList.remove("outside1ab");
    
}
function canalOne(){
	 soutside1ab.classList.add("outside1ab");
	$("#oneDiv").show();
	$("#twoDiv").hide();
}
function nextThree(){
	soutside2as.classList.remove("outside2a");

}
function getPhoneCode(){
	var phone = $("input[name='phone']").val();
	if(phone =='' || phone == null || phone == undefined){
		layer.msg("请输入手机号");
		return false;
	}
	if(!(/^1[23456789]\d{9}$/.test(phone))){ 
		layer.msg("手机号码有误，请重新输入");  
        return false; 
    }
	
	$.ajax({
		url: "system/getPhoneCode"
		,type:"get"
		,data:{phone:phone}
		,dataType:"json"
		,success:function(result){
			if(result.code != 10000){
				layer.msg(result.msg);
			}
		}
	})
	
	$("#getPhoneCodeDiv").empty();
	$("#getPhoneCodeDiv").append('<a href="javascript:void(0);" style="color:grey" >重新获取(60)</a>');
	var time = 60;
	var timer = setInterval(function() {
		time--;
		if (time == 0) {
		    clearInterval(timer);
		    $("#getPhoneCodeDiv").empty();
			$("#getPhoneCodeDiv").append('<a href="javascript:void(0);" onclick="getPhoneCode()" style="color:#0098ff" >重新获取</a>');
		} else {
			$("#getPhoneCodeDiv").empty();
			$("#getPhoneCodeDiv").append('<a href="javascript:void(0);" style="color:grey" >重新获取('+time+')</a>');
		    
		}
	}, 1000);
}
function getCountryByPid(form,divId,pid,huixian){
	
	switch (divId) {
	case 'unitProvinceId':
		$("#unitCityId").empty();
		$("#unitDistrictId").empty();
		$("#unitProvinceId").empty();
		$("#unitProvinceId").append('<option value="">请选择省</option>');
		break;
	case 'unitCityId':
		$("#unitDistrictId").empty();
		$("#unitCityId").empty();
		$("#unitCityId").append('<option value="">请选择市</option>');
		break;
	case 'unitDistrictId':
		$("#unitDistrictId").empty();
		$("#unitDistrictId").append('<option value="">请选择区/县</option>');
		break;
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