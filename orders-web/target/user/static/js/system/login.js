login_tool = {
	changeImg:function(){
		var imgSrc = $("#imgObj");  
	    var src = imgSrc.attr("src");
	    imgSrc.attr("src",src+"?"+Math.random());  
	},
	initData:function(){
		var configName = new Array();
		configName[0] = 'logTitle';
		configName[1] = 'logTitle2';
		configName[2] = 'logTitle3';
		for (var i = 0; i < configName.length; i++) {
			var configNameId = configName[i]
			$.ajax({
				url:getRootPath() + '/system/getProjectConfigByConfigName'
				,type:'post'
				,data:{configName:configName[i]}
				,dataType:'json'
				,success:function(data){
					$("#"+data.configName).html(data.configData);
				}
			})
		}
	},
	login:function(){
		var loginName = $("#userNameId").val();
		var password = $("#userPwdId").val();
		var code = $("#code").val();
		loginName = login_tool.trim(loginName,'g'); 
		password = login_tool.trim(password,'g');
		code = login_tool.trim(code,'g');
		if(loginName=="" ||  loginName == null){
			common_tool.messager_show("请输入登录账号");
		}else
		if(password=="" ||  password == null){
			common_tool.messager_show("请输入密码");
		}else
		if(code=="" ||  code == null){
			common_tool.messager_show("请输入验证码");
		}else{
			$.ajax({
				url:getRootPath() + '/system/login'
				,type:'post'
				,data:{
					loginName:loginName,
					password:password,
					code:code,
					platform:1,//登录终端类型
				}
				,dataType:'json'
				,success:function(result){
					if (result.code == 10000) {
	                    location.href=getRootPath() + '/system/toMain';
	                    return false;
	                }
	                else {
	                	login_tool.changeImg();
	                    common_tool.messager_show(result.msg);
	                }
				}
			})
		}
	},
	trim:function(str,is_global){ //去除空格    加上 g 参数则是去除所有空格
	   var result;
	   result = str.replace(/(^\s+)|(\s+$)/g,"");
	   if(is_global.toLowerCase()=="g")
	   {
	    result = result.replace(/\s/g,"");
	    }
	   return result;
	},
	fetRequest:function() {   
		   var url = location.search; //获取url中"?"符后的字串   
		   var theRequest = new Object();   
		   if (url.indexOf("?") != -1) {   
		      var str = url.substr(1);   
		      strs = str.split("&");   
		      for(var i = 0; i < strs.length; i ++) {   
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
		      }   
		   }
		   if(theRequest.kickout != undefined && theRequest.kickout != "" && theRequest.kickout != null){
			   layer.alert('您的账号已在其他地方登录,IP为'+theRequest.ip+'</br>请联系管理员修改密码或者重新登录!',{icon:2,title:'警告'});  
		   }
		   if(theRequest.timeOut != undefined && theRequest.timeOut != "" && theRequest.timeOut != null){
			   layer.alert('会话超时请重新登录!',{icon:2,title:'警告'});  
		   }
	},
	verifyAuthorizationMes:function(){
		$.ajax({
			url: getRootPath() + "/authorization/verifyAuthorizationMes",
			type: "GET",
			dataType: "json",
			async: true,//异步请求
			success: function(data) {
				if("failed"== data.verificationMes){
					$(".layer").show();
				}
			}
		});
	},
	getMachineCode:function(){
		$.ajax({
			url:  getRootPath() + "/authorization/getMachineCode",
			type: "POST",
			dataType: "json",
			async: true,//异步请求
			success: function(result) {
				if("10000"==result.code){
					layer.open({
						  title: '授权申请码'
						  ,content: result.data
						  ,btn:['复制授权码']
					  	  ,yes: function(index, layero){
					  		var oInput = document.createElement('input');
					        oInput.value = result.data;//要复制的数据
					        document.body.appendChild(oInput);
					        oInput.select(); // 选择对象
					        document.execCommand("Copy"); // 执行浏览器复制命令
					        oInput.className = 'oInput';
					        oInput.style.display='none';
					        layer.msg('复制成功',{time:1500});
					  	  }
					});
	            }else{
	            	common_tool.messager_show(result.msg);
	            }
			}
		});
	}
}
$(document).ready(function () {
	
	login_tool.verifyAuthorizationMes();
	login_tool.fetRequest();
	login_tool.initData();
	$("#imgObj").click(function(){
		login_tool.changeImg();
	})
	$("#changeImg").click(function(){
		login_tool.changeImg();
	})
	$("#loginButton").click(function(){
		login_tool.login();
	})
	$(".btnfile").click(function(){
		login_tool.getMachineCode();
	})
	$(".a-upload").change(function(){
		var filePath=$("#selectUploadFile").val();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $(".showFileName").html("<span>"+fileName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='uploadFile()' class='layui-btn layui-btn-xs'>上传</button>");
	})
});

function uploadFile(){
	//$('#uploadPic').serialize() 无法序列化二进制文件，这里采用formData上传
    //需要浏览器支持：Chrome 7+、Firefox 4+、IE 10+、Opera 12+、Safari 5+。
	var formData = new FormData();
	formData.append('uploadFile',$("#selectUploadFile")[0].files[0]);
	$.ajax({
		url:  getRootPath() + "/authorization/uploadAuthorizationFile",
        data: formData,
        type: "Post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (result) {
            if("10000"==result.code){
            	location.reload()
            }else{
            	common_tool.messager_show(result.msg);
            }
        }
	});
}