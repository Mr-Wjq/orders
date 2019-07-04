$(function(){
	var configData = $('#config_data').val();
	initUrl(configData);
	
});
/*
 * 获取url
 */
function initUrl(configData){
	if (configData !== null && configData !== "" && undefined !== configData) {
		$("#frameId").prop("src",configData);
		$(window).resize(function () {
			fixHeight();
		}).resize();
	}
}

function fixHeight() {
    $("#frameId").attr("height", $(window).height()-60+ "px");
}
/*
 * 返回首页
 */
function toMain(){
	location.href=getRootPath() + "/system/toMain";
}
