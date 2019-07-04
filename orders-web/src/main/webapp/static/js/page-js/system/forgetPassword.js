layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;

  form.on('submit(resetPassword)', function(data){
	  layer.confirm('确认重置密码？', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			  url:'user/resetPassword'
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

})
var soutside1ab=document.getElementById("outside1abs");
var soutside2as=document.getElementById("outside2as");
function nextTwo(){
	var phone = $("input[name='phone']").val();
	if(phone =='' || phone == null || phone == undefined){
		layer.msg("请输入手机号");
		return false;
	}
	if(!(/^1[23456789]\d{9}$/.test(phone))){ 
		layer.msg("手机号码有误，请重新输入");  
        return false; 
    } 
	var phoneCode = $("input[name='phoneCode']").val();
	if(phoneCode =='' || phoneCode == null || phoneCode == undefined){
		layer.msg("请输入手机验证码");
		return false;
	}
	var flag = true;
	$.ajax({
		url: "system/checkPhoneCode"
		,type:"get"
		,data:{phoneCode:phoneCode}
		,dataType:"json"
		,success:function(result){
			if(result.code != 10000){
				layer.msg(result.msg);
				flag = false;
			}
		}
	})
	if(!flag){
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
	
	var flag = false;
	$.ajax({
		url: "system/getPhoneCodeForRp"
		,type:"get"
		,data:{phone:phone}
		,dataType:"json"
		,async:false
		,success:function(result){
			if(result.code != 10000){
				layer.msg(result.msg);
			}else{
				flag = true;
			}
		}
	})
	if(!flag){
		return flag;
	}
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
