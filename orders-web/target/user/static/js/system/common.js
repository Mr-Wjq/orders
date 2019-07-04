//右下角显示消息
common_tool = {
    messager_show: function (msg) {
       /* $.messager.show({
            title: '系统提示',
            msg: msg,
            timeout: 2000,
            showType: 'slide',
            style:{
            }
        });*/
        layer.msg(msg);
    },
    timestampToDateTime: function (value) {
        date = new Date(value);
        timeStr = date.getFullYear() + "-" + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + "-" + date.getDate() + " " + (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ":" + (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ":" + (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
        return timeStr;
    },
    timeDate:function(value){
        if ("" != value && null != value &&undefined != value) {
            var datetime = new Date();
            datetime.setTime(value);
            var year = datetime.getFullYear();
            var month = datetime.getMonth() + 1;
            var date = datetime.getDate();
            var hour = datetime.getHours();
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
}
function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPatht = curWwwPath.substring(0, pos);
    /*var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1)*/
    var projectName = "";
    return (localhostPatht + projectName);
}
