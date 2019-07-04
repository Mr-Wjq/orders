$(function(){
        skinChange();
});
/*
*皮肤切换函数
* 实现思路：分不同的css，通过添加自定义属性切换调用不同的css文件
* 1.点击标签处 添加属性:data-skin="default" ，如 <a href="javascript:;" data-skin="default">默认</a>
* 2.引入css文件时，link标签中添加  id="skit" kit-skin 俩个属性，如：
* <link href="css/themes/default.css" media="all" id="skit" kit-skin/>
* 3.引入文件以下 js
*/
function skinChange(){
    $('dl.skin > dd').on('click', function() {
        var $that = $(this);
        var skin = $that.children('a').data('skin');
        switchSkin(skin);
        //注：点击刷新页面，暂时没有和后台联通
        //因为是通过js继承父级样式，需要刷新加载js,后台做好配置以后，这段代码就不要用了；
        window.location.reload();
    });
    var setSkin = function(value) {
        layui.data('kit_skin', {
            key: 'skin',
            value: value
        });
    },
    getSkinName = function() {
        return layui.data('kit_skin').skin;
    },
    switchSkin = function(value) {
        var _target = $('link[kit-skin]')[0];
        _target.href = _target.href.substring(0, _target.href.lastIndexOf('/') + 1) + value + _target.href.substring(_target.href.lastIndexOf('.'));
        setSkin(value);
    },
    initSkin = function() {
        var skin = getSkinName();
        switchSkin(skin === undefined ? 'default' : skin);
    }();
}