var login = {
	changeImg:function(){
		var imgSrc = $("#imgObj");  
	    var src = imgSrc.attr("src");
	    imgSrc.attr("src",src+"?"+Math.random());  
	},
	login:function(){
		var loginName = $("#loginName").val();
		var password = $("#password").val();
		var code = $("#code").val();
		loginName = login.trim(loginName,'g'); 
		password = login.trim(password,'g');
		code = login.trim(code,'g');
		if(loginName=="" ||  loginName == null){
			layer.msg("请输入登录账号");
		}else
		if(password=="" ||  password == null){
			layer.msg("请输入密码");
		}else
		if(code=="" ||  code == null){
			layer.msg("请输入验证码");
		}else{
			$.ajax({
				url:'system/login'
				,type:'post'
				,beforeSend: function (XMLHttpRequest) {
		         		XMLHttpRequest.setRequestHeader("X-Csrf-Token", $("#csrf_token").attr("content"));
		         }
				,data:{
					loginName:loginName,
					password:password,
					code:code
				}
				,dataType:'json'
				,success:function(result){
					if (result.code == 10000) {
						login.changeImg();
	                    location.href=basePath + 'page/toMain';
	                    return false;
	                }
					else{
						login.changeImg();
						layer.msg(result.msg);
					}
				}
			})
		}
	},
	trim:function(str,is_global){ //去除空格    加上 g 参数则是去除所有空格
		   var result = str.replace(/(^\s+)|(\s+$)/g,"");
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
			   layer.alert('您的账号已在其他地方登录',{icon:2,title:'警告'});  
		   }else
		   if(theRequest.timeOut != undefined && theRequest.timeOut != "" && theRequest.timeOut != null){
			   layer.alert('会话超时请重新登录!',{icon:2,title:'警告'});  
		   }else
        if(theRequest.forceLogout != undefined && theRequest.forceLogout != "" && theRequest.forceLogout != null){
            layer.alert('您已被管理员强制下线!',{icon:2,title:'警告'});
        }
	}
}

$(function(){
	login.fetRequest();
	$("#imgObj").click(function(){
		login.changeImg();
	})
	$("#submit-btn").click(function(){
		login.login();
	})
})