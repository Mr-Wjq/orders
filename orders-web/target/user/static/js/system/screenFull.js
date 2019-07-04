
$(window, document).resize(function(){
    resize();
}).load(function(){
    $('.datavLayout').css('visibility', 'visible');
        resize();
    $('#datavLoading').fadeOut();
});

function resize() {
  if (window.screen.display == 2) {
    resizeCenter();
  } else if (window.screen.display == 3) {
    resizeFull();
  } else {
    resizeWidth();
  }
}
function resizeWidth() {
  var ratio = $(window).width()/(window.screen.width||$('#index_body').width());

  var windowWidth = $(window).width();
  var bodyWidth = 1920;
  var widthRatio = windowWidth/bodyWidth;
  $('#index_body').css({
    transform: "scale("+widthRatio+")",
    transformOrigin: "left top",
    backgroundSize: "100%"
  });
}
function resizeCenter() {
  if(!window.screen.height||!window.screen.width) return resizeCenterBak();
  var ratio = $(window).height()/window.screen.height;

var windowHeight = $(window).height();
  /*share.set('ratioX', ratio)
  share.set('ratioY', ratio)*/
  $('#index_body').css({
    transform: "scale("+ratio+")",
    transformOrigin: "left top",
    backgroundSize: 100*(window.screen.width/$(window).width() * ratio)+"%" + ' 100%',
    backgroundPosition: ($(window).width()-$('#index_body').width()*ratio)/2+"px top",
    marginLeft: ($(window).width()-$('#index_body').width()*ratio)/2
  });
}
function resizeFull() {
  if(!window.screen.height||!window.screen.width) return resizeFullBak();
  var ratioX = $(window).width()/window.screen.width;
  var ratioY = $(window).height()/window.screen.height;
  /*share.set('ratioX', ratioX)
  share.set('ratioY', ratioY)*/
  $('#index_body').css({
    transform: "scale("+ratioX+", "+ratioY+")",
    transformOrigin: "left top",
    backgroundSize: "100% 100%",
  });
}

function resizeCenterBak() {
  var ratioX = $(window).width()/$('#index_body').width();
  var ratioY = $(window).height()/$('#index_body').height();
  var ratio = Math.min(ratioX, ratioY);
  share.set('ratioX', ratio)
  share.set('ratioY', ratio)
  $('#index_body').css({
    transform: "scale("+ratio+")",
    transformOrigin: "left top",
    backgroundSize: (1/ratioX)*100*ratio+"%",
    backgroundPosition: ($(window).width()-$('#index_body').width()*ratio)/2+"px top",
    marginLeft: ($(window).width()-$('#index_body').width()*ratio)/2
  });
}
function resizeFullBak() {
  var ratioX = $(window).width()/$('#index_body').width();
  var ratioY = $(window).height()/$('#index_body').height();
  share.set('ratioX', ratioX)
  share.set('ratioY', ratioY)
  $('#index_body').css({
    transform: "scale("+ratioX+", "+ratioY+")",
    transformOrigin: "left top",
    backgroundSize: "100% "+ratioY*100+"%",
  });
}
function getComList(coms) {
  var result = [];
  var comList = {};
  coms.forEach(function(com, index) {
    if (com.parent) return;
    var idx = com.attr.zIndex || 1000;
    if (!comList[idx]) comList[idx] = [];
    comList[idx].push(com);
  });
  Object.keys(comList).sort(function(a,b){
    a = a*1;
    b = b*1;
    if(a>b) return 1;
    if(a<b)return -1;
    return 0
  }).forEach(function(idx){
    comList[idx].forEach(function(com){
      result.push(com.com_id);
    });
  });
  return result;
}