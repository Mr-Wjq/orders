var tableIns;
var form;
layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  form = layui.form;
  //常规用法
  laydate.render({
    elem: '#createTime'
  });
  tableIns = table.render({
    elem: '#ordersTable'
    ,url:'orders/selectFactoryOrders'
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
      ,{field:'createUnitName', title:'诊所/医院'}
      ,{field:'createUserName', title:'医生'}
      ,{field:'patientName', title:'患者' }
      ,{field:'baseCureName', title:'治疗类型'}
      ,{field:'price', title:'价格'}
      ,{field:'status', title:'状态',templet:function(row){
    	  switch (row.status) {
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
      ,{field:'cz', title:'操作',templet:function(row){
    	  var buttonArr = '';
    	  switch (row.status) {
			case 2:
				buttonArr += '<button class="layui-btn layui-btn-xs" onclick="receive('+row.id+')">接单</button>'+
				       '<button class="layui-btn layui-btn-xs" onclick="refuse('+row.id+')">拒接</button>';
				break;
			case 4:
				buttonArr +=  '<button class="layui-btn layui-btn-xs" onclick="beginMade('+row.id+')">开始生产</button>'
				break;
			case 5:
				buttonArr +=  '<button class="layui-btn layui-btn-xs" onclick="send('+row.id+')">发货</button>'
				break;
			default:
				buttonArr +=  ''
				break;
		 }
    	  
    	  return buttonArr += '<button onclick="ordersDetails('+row.id+')" class="layui-btn layui-btn-xs">详情</button>';
     }}
    ]]
    ,page: true
  });

  //搜索按钮
  $("#searchBtn").click(function(){
	  tableIns.reload({where:{
		  ordersNum:$("#ordersNum").val(),
		  patientName:$("#patientName").val(),
		  createUnitName:$("#createUnitName").val(),
		  status:$("#status").val(),
		  createTime:$("#createTime").val()
	  	},page: {
	  	    curr: 1 //重新从第 1 页开始
	    }
	  });
  })
})
function receive(ordersId){
	layer.confirm('确认接单？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			url:'orders/receiveOrders'
			,type:'post'
			,data:{ordersId:ordersId}
			,dataType:'json'
			,async:false
			,success:function(result){
				if(result.code == 10000){
					tableIns.reload();
				}else{
					layer.msg(result.msg);
				}
			}
		})
		layer.close(index);
	});
}
function beginMade(ordersId){
	layer.confirm('确认生产？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			url:'orders/beginMade'
			,type:'post'
			,data:{ordersId:ordersId}
			,dataType:'json'
			,async:false
			,success:function(result){
				if(result.code == 10000){
					tableIns.reload();
				}else{
					layer.msg(result.msg);
				}
			}
		})
		layer.close(index);
	});
}
function refuse(ordersId){
	
	layer.open({
		type:1
		,title:'拒接理由'
		,area:['30%','30%']
		,shade:0.8
		,btn:['拒接','关闭']
		,content:$("#refuseDiv")
		,yes:function(){
			layer.confirm('确认拒接？', {icon: 3, title:'提示'}, function(index){
				var refuseReason = $("#refuseReason").val();
				if(refuseReason == '' || refuseReason == null || refuseReason == undefined){
					layer.msg('请填写拒接理由');
					return false;
				}
				$.ajax({
					url:'orders/refuseOrders'
					,type:'post'
					,data:{ordersId:ordersId,refuseReason:refuseReason}
					,dataType:'json'
					,async:false
					,success:function(result){
						if(result.code == 10000){
							tableIns.reload();
							layer.closeAll();
						}else{
							layer.msg(result.msg);
						}
					}
				})
				layer.close(index);
			});
		}
		,bt2:function(){
			layer.closeAll();
		}
		,end:function(){
			$("#refuseDiv").hide();
		}
	})
}

function send(ordersId){
	
	layer.open({
		type:1
		,title:'发货'
		,shade:0.8
		,btn:['发货','关闭']
		,content:$("#expressDiv")
		,yes:function(){
			layer.confirm('确认发货？', {icon: 3, title:'提示'}, function(index){
				var expressName = $("#expressName").val();
				if(expressName == '' || expressName == null || expressName == undefined){
					layer.msg('请选择快递公司');
					return false;
				}
				var expressNum = $("#expressNum").val();
				if(expressNum == '' || expressNum == null || expressNum == undefined){
					layer.msg('请填写快递编号');
					return false;
				}
				$.ajax({
					url:'orders/send'
					,type:'post'
					,data:{ordersId:ordersId,expressName:expressName,expressNum:expressNum}
					,dataType:'json'
					,async:false
					,success:function(result){
						if(result.code == 10000){
							tableIns.reload();
							layer.closeAll();
						}else{
							layer.msg(result.msg);
						}
					}
				})
				layer.close(index);
			});
		}
		,bt2:function(){
			layer.closeAll();
		}
		,success:function(){
			$.ajax({
				url:'baseData/selectBaseExpress'
				,type:'get'
				,dataType:'json'
				,async:false
				,success:function(data){
					if(data != '' && data != null && data !=undefined){
						$("#expressName").empty();
						$("#expressName").append('<option value="">选择快公司（可搜索）</option>');
						for (var i = 0; i < data.length; i++) {
							$("#expressName").append('<option value="'+data[i].pinyin+'">'+data[i].hanzi+'</option>');
						}
						form.render();
					}
				}
			})
		}
		,end:function(){
			$("#expressDiv").hide();
		}
	})

}
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
							location.href=basePath + "orders/downloadAccessory?ordersId="+ordersId;							
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