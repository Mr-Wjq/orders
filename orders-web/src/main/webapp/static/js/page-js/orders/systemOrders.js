var tableIns;
layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  //常规用法
  laydate.render({
    elem: '#createTime'
  });
  tableIns = table.render({
    elem: '#ordersTable'
    ,url:'orders/selectSystemOrders'
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
  	,limit:20
  	,limits:[20,30,50,100,200]
    ,title: '数据表'
    ,cols: [[

       {type: 'numbers', fixed: 'left'}
      ,{field:'ordersNum', title:'订单号' }
      ,{field:'patientName', title:'患者' }
      ,{field:'baseCureName', title:'治疗类型'}
      ,{field:'createUnitName', title:'诊所/医院'}
      ,{field:'createUserName', title:'医生'}
      ,{field:'receiveUnitName', title:'工厂'}
      ,{field:'receiveUserName', title:'接收人'}
      ,{field:'price', title:'价格'}
      ,{field:'status', title:'状态',templet:function(row){
    	  switch (row.status) {
			case 1:
				return '未发布';
				break;
			case 2:
				return '待接单';
				break;
			case 3:
				return "<span style='color:red;'>已拒接：</span>"+ row.refuseReason
				break;
			case 4:
				return "已接单"
				break;
			case 5:
				return "生产中"
				break;
			case 6:
				return '<a style="color:blue;" href="https://www.kuaidi100.com/chaxun?com='+row.expressName+'&nu='+row.expressNum+'" target="view_window">已发货:'+row.expressNum+'</a>'
				break;
			case 7:
				return "订单完成 "
				break;
			}
       }}
      ,{field:'createTime', title:'创建时间',templet:function(row){
    	  return common_tool.timeDate(row.createTime);
     }}
      ,{field:'xq', title:'操作',templet:function(row){
    	
    	  return '<button onclick="ordersDetails('+row.id+')" class="layui-btn layui-btn-xs">详情</button>';
     }}
      
    ]]
    ,page: true
  });

  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
		  ordersNum:$("#ordersNum").val(),
		  patientName:$("#patientName").val(),
		  receiveUnitName:$("#receiveUnitName").val(),
		  createUnitName:$("#createUnitName").val(),
		  status:$("#status").val(),
		  createTime:$("#createTime").val()
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
})


function ordersDetails(ordersId){
	
	$.ajax({
		url:'orders/ordersDetails'
		,type:'get'
		,data:{ordersId:ordersId}
		,dataType:'json'
		,async:false
		,success:function(result){
			if(result.code == 10000){
				var exists = result.data.exists; 
				var pdfPath = result.data.pdfPath;
				if(exists){
					layer.open({
						type:2
						,title:'详情'
						,area:['90%','90%']
						,shade:0.8
						,btn:['下载扫描文件','关闭']
						,content:"pdf/"+pdfPath
						,yes:function(){
							location.href="orders/downloadAccessory?ordersId="+ordersId;							
						}
						,bt2:function(){

							layer.closeAll();
						}
					})
				}else{
					layer.open({
						type:2
						,title:'详情'
						,area:['90%','90%']
						,shade:0.8
						,btn:['关闭']
						,content:"pdf/"+pdfPath
						,yes:function(){
							layer.closeAll();
						}
					})
				}
			}else{
				layer.msg(result.msg);
			}
		}
	})

}