function getBasePath(){
	var location = (window.location+'').split('/');  
	var basePath = location[0]+'//'+location[2]+'/'+location[3];
	return basePath;
} 

var basePath = getBasePath();
//href = basePath+"/user/toLogin";


window.onload = function(){
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.src = basePath+'/static/js/easyui/jquery.min.js';
	document.getElementsByTagName('head')[0].appendChild(script);
	
	var linkList=window.parent.document.getElementsByTagName("link");//获取父窗口link标签对象列表
	var scriptList=window.parent.document.getElementsByTagName("script");//获取父窗口link标签对象列表
	var head=document.getElementsByTagName("head").item(0);
	var first = head.firstChild;
	console.log(first);
	//外联样式
	for(var i=0;i<linkList.length;i++){
		var l = document.createElement("link");
		var s = document.createElement("script");
		/*l.rel = 'stylesheet'
		l.type = 'text/css';*/ 
		l.href=linkList[i].href;
		s.type = 'text/javascript';
		s.src=scriptList[i].src;
		head.appendChild(l);
		head.insertBefore(s,first);
	}
}