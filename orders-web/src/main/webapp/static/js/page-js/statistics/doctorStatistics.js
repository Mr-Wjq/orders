var startTime = '';
var endTime = '';
layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element;
  var table = layui.table;
  var laydate = layui.laydate;
  //常规用法
  laydate.render({
    elem: '#createTime'
    ,range: true
  });
  var tableIns = table.render({
	    elem: '#doctorStatisticsTable'
	    ,url:'tj/doctorStatisticsData'
	    ,headers:{
	    	"X-Csrf-Token":csrf_token
	    }
		,response: {
		    statusName: 'code'
		    ,statusCode: 200
		    ,msgName: 'msg'
		    ,countName: 'total'
		    ,dataName: 'rows'
		 } 
		,where:{
			statisticsType:'doctor'
		}
	    ,title: '数据表'
	    ,cols: [[
	       {type: 'numbers', fixed: 'left'}
	      ,{field:'unitName', title:'订单发布统计' }
	      ,{field:'zhengji', title:'正畸'}
	      ,{field:'zhongzhi', title:'种植'}
	      ,{field:'xiufu', title:'修复' }
	    ]]
  });

  
  $("#searchBtn").click(function(){
	  
	  var createTime = $("#createTime").val();
	  if(createTime!='' && createTime!=null && createTime!=undefined){
		  startTime = createTime.split(' - ')[0];
		  endTime = createTime.split(' - ')[1];
	  }else{
		  startTime = '';
		  endTime = '';
	  };
	  tableIns.reload({
		  where:{
			  startTime:startTime,
			  endTime:endTime
		  }
	  });
  })
})

