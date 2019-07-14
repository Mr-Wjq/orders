var common_tool = {
	timeDate:function(value){
		if ("" != value && null != value &&undefined != value) {
			var datetime = new Date();
			datetime.setTime(value);
			var year    = datetime.getFullYear() <= 9 ?  "0" + datetime.getFullYear() : datetime.getFullYear();
			var month   = datetime.getMonth()<=9 ? "0" + (datetime.getMonth()+1):(datetime.getMonth()+1);
			var date    = datetime.getDate() <=9 ? "0" + datetime.getDate():datetime.getDate();
			var hour    = datetime.getHours()<=9 ? "0" + datetime.getHours():datetime.getHours();
			if(hour<=9){
				hour="0"+hour;
			}
			var minute = datetime.getMinutes();
			if(minute<=9){
				minute="0"+minute;
			}
			var second = datetime.getSeconds();
			if(second<=9){
				second="0"+second;
			}
			return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
		}
	}
	,timeDay:function(value){
		if ("" != value && null != value &&undefined != value) {
			var datetime = new Date();
			datetime.setTime(value);
			var year    = datetime.getFullYear() <= 9 ?  "0" + datetime.getFullYear() : datetime.getFullYear();
			var month   = datetime.getMonth()<=9 ? "0" + (datetime.getMonth()+1):(datetime.getMonth()+1);
			var date    = datetime.getDate() <=9 ? "0" + datetime.getDate():datetime.getDate();
			var hour    = datetime.getHours()<=9 ? "0" + datetime.getHours():datetime.getHours();
			if(hour<=9){
				hour="0"+hour;
			}
			var minute = datetime.getMinutes();
			if(minute<=9){
				minute="0"+minute;
			}
			var second = datetime.getSeconds();
			if(second<=9){
				second="0"+second;
			}
			return year + "-" + month + "-" + date;
		}
	}
}

var basePath = '';
$(function(){
	basePath =  $("#basePath").val();
})

//全局的ajax访问，处理ajax清求时sesion超时 
$.ajaxSetup({
	
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    complete : function(XMLHttpRequest, textStatus) {
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
        if (sessionstatus == "timeout") {
        	window.location.reload();
        }
        if(layui_load_index!=''){
        	layer.close(layui_load_index);
        }
    }
});
var csrf_token = $("#csrf_token").attr("content");
var layui_load_index = '';
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader("X-Csrf-Token", csrf_token);
	layui_load_index = layer.load(2);
});
