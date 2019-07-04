$(function(){
	changePassword();
	getHeadTile();
	verifyAuthorizationMes();
});
function getHeadTile(){
		$.ajax({
			url:getRootPath() + '/system/getProjectConfigByConfigName'
			,type:'post'
			,data:{configName:'internalTitle'}
			,dataType:'json'
			,success:function(data){
				$("#internalTitle").html(data.configData);
			}
		})
}
/*
 * 返回首页
 */
function toMain(){
	location.href=getRootPath() +"/system/toMain";
}
/* 退出方法 */
function dropOut() {
	layer.confirm("您确定要退出吗?", {icon: 3, title:"退出确认"}, function(index){
		toLogout();
	});
}
function toLogout(){
	$.ajax({
        method: 'get',
        url: getRootPath() + '/system/logout',
        async: false,
        dataType: 'json',
        success: function (result) {
            location = getRootPath();
        },
    });
}
//鼠标移到用户信息上面，显示修改密码，移出隐藏
function changePassword() {
	$('.header_info').on('mouseenter',function(){
		$('.list').slideToggle(200);
	}).on('mouseleave',function(){
		$('.list').css('display','none');
	});
}
/* 修改密码 */
function change() {
	layer.open({
		area: ['350px', '320px']
		,shade: 0.3 //不显示遮罩
		,skin: 'yourclass'
		,icon:3
		,title:'修改密码'
		,type: 1
		,closeBtn:1
		,content:$("#changeId")
		,btn: ['确认', '取消']
		,yes: function(){
			checkout();
		}
		,end: function () {
			$('#changeId').hide();
        }
	});
}
/* 校验密码 */
function checkout(){
	$("#oldInfo").html("");
	$("#newPasswordInfo").html("");
	$("#rePassword").html("");
	var loginName = $('#login_name').val();//账号
	var oldPassword = $('#oldPassword').val();//密码
	var newPassword = $('#newPassword').val();//新密码
	var secondPassword = $('#secondPassword').val();//确认密码
	if (oldPassword !=undefined && oldPassword !=null&&oldPassword !=""
			&&newPassword !=undefined && newPassword !=null&&newPassword !=""
			&&secondPassword !=undefined && secondPassword !=null&&secondPassword !="") {
		var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;//6到12位数字与字母组合
		if(oldPassword == newPassword){
			$("#newPasswordInfo").html("新密码不能当前密码相同！");
			return false;
		}else if(!pwdReg.test(newPassword)){
			$("#newPasswordInfo").html("密码6-12位,字母、数字混合！");
			return false;
		}else if(newPassword != secondPassword){
			$("#rePassword").html("两次输入密码不一致!");
			return false;
		}else{
			$.ajax({
				data: {
					"loginName":loginName,
					"oldPassword":oldPassword,
					"newPassword":newPassword,
				},
				method: 'post',
				url: getRootPath() + '/system/changePassword',
				async: false,
				dataType: 'json',
				success: function (result) {
					if (result.code == "10000") {
						$("#oldPassword").val("");
						$("#newPassword").val("");
						$("#secondPassword").val("");
						$("#oldInfo").html("");
						$("#newPasswordInfo").html("");
						$("#rePassword").html("");
						layer.msg("修改成功!",{time: 2000},function(){
							toLogout();
                        });
					}else{
						$("#oldInfo").html("旧密码错误!");
					}
				},
			});
		}
	}else{
		common_tool.messager_show("请完善表单");
	}
}
/*校验授权时间*/
function verifyAuthorizationMes(){
	$.ajax({
		url: getRootPath() + "/authorization/verifyAuthorizationMes",
		type: "GET",
		dataType: "json",
		async: true,//异步请求
		success: function(data) {
			if("terminable" == data.verificationMes){
				$("#dayNum").html("试用期剩余：<span style='font-size: 20px;'>"+data.terminableNum+"</span>天");
			}else
			if("failed"== data.verificationMes){
				toLogout();
			}
		}
	});
}

function personalDate(){
	layer.closeAll();
    layer.open({
        type: 2,
        title: '个人中心',
        shadeClose: true,
        shade: 0.8,
        area: ['46%', '40%'],
        content: getRootPath() +'/user/personal' //iframe的url
    });
}

