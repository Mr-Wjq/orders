
$(function(){
	secondMenu();
	//showCss();
	fitHeight();
});
//页面窗口改变大小时加载
$(window).resize(function () {
    fitHeight();
});
function fitHeight(){
	var width = document.documentElement.clientWidth; 
	$('.layui-header').css('width',width);
}

/* 警员模块 */
function toMoKuai(mes,val){
	switch (val) {
	case 1:
		// 本地项目
		location.href = getRootPath() + '/platform/getYdDynamic?configName='+mes;
		break;
	case 2:
		// 外接项目
		location.href = getRootPath() + '/platform/getQtDynamic?configName='+mes;
		break;
	default:
		// 用户管理
		location.href=getRootPath() + "/system/welcome";
		break;
	}
}

/*
 * 2018-4-10
 * 点击首页按钮出现二级菜单
 */
function secondMenu(){
	var ul = $(".secondMenu"),li=$(".secondMenu li"),i = li.length , n=i-1, r=120;
    ul.click(function(){
        $(this).toggleClass('active');
        if($(this).hasClass('active')){
            for(var a = 0;a < i; a++){
                li.eq(a).css({
                    'transition-delay':""+(100*a)+"ms",
                    '-webkit-transition-delay':""+(100*a)+"ms",
                    '-o-transition-delay':""+(100*a)+"ms",
                    'transform':"translate("+(r*Math.cos(180/n*a*(Math.PI/180)))+"px,"+(-r*Math.sin(180/n*a*(Math.PI/180)))+"px",
                    '-webkit-transform':"translate("+(r*Math.cos(180/n*a*(Math.PI/180)))+"px,"+(-r*Math.sin(180/n*a*(Math.PI/180)))+"px",
                    '-o-transform':"translate("+(r*Math.cos(180/n*a*(Math.PI/180)))+"px,"+(-r*Math.sin(180/n*a*(Math.PI/180)))+"px",
                    '-ms-transform':"translate("+(r*Math.cos(180/n*a*(Math.PI/180)))+"px,"+(-r*Math.sin(180/n*a*(Math.PI/180)))+"px"
                });
            }
        }else{
            li.removeAttr('style');
        }
    });
}

function showCss(){
	var length = $('.container .module').length;
    if(length == 11){
        $("link[title='eleven']").removeAttr("disabled");
        $("link[title='nine_ten']").attr("disabled", "disabled");
        $("link[title='six_eight']").attr("disabled", "disabled");
        $("link[title='five']").attr("disabled", "disabled");
    }else if(length == 10 || length == 9){
        $("link[title='nine_ten']").removeAttr("disabled");
        $("link[title='eleven']").attr("disabled", "disabled");
        $("link[title='six_eight']").attr("disabled", "disabled");
        $("link[title='five']").attr("disabled", "disabled");
    }else if(length == 8 ||length == 7 || length == 6){
        $("link[title='six_eight']").removeAttr("disabled");
        $("link[title='eleven']").attr("disabled", "disabled");
        $("link[title='nine_ten']").attr("disabled", "disabled");
        $("link[title='five']").attr("disabled", "disabled");
    }else if(length == 5 || length == 4 || length == 3 || length == 2){
        $("link[title='five']").removeAttr("disabled");
        $("link[title='eleven']").attr("disabled", "disabled");
        $("link[title='nine_ten']").attr("disabled", "disabled");
        $("link[title='six_eight']").attr("disabled", "disabled");
    }
    else {
        $("link[title='nine_ten']").removeAttr("disabled");
        $("link[title='six_eight']").attr("disabled", "disabled");
        $("link[title='five']").attr("disabled", "disabled");
        $("link[title='eleven']").attr("disabled", "true");
    }
}

