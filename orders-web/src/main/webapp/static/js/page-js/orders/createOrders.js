/*已选中的牙齿数组*/
var selectedToothArr = [];
/*layui表格*/
var tableIns;
$(function(){
    var li = $('.tooth_choose ul li');
    li.click(function(){
        var tooth = $(this).find('span.icon');
        var currToothNum = tooth.html();
		var lastToothNum = $("#toothNum").val();
		
		//选中牙齿时先判断上一个牙齿有没有被选中
		if(lastToothNum!=null && lastToothNum!='' && lastToothNum!=undefined){
			var flag = false;
			var flag2 = false;
			var updateTooth = '';
 			for (var i = 0; i < selectedToothArr.length; i++) {
 				var data = selectedToothArr[i];
 				if(data.toothNum == lastToothNum){
 					flag = true;
 				}
 				if(data.toothNum == currToothNum){
 					updateTooth = data;
 					flag2 = true;
 				}
 			}
			if(!flag){
				$(".tooth"+lastToothNum).removeClass('icon_active');
			}			
			if(flag2){
 				$("#toothNum").val(currToothNum);
				$("#toothNumShow").html(currToothNum);
				$("#toothColor").val(updateTooth.toothColor);
				form.render();
 			}else{
				if(lastToothNum == currToothNum){
 					$("#toothNum").val('');
 					$("#toothNumShow").html('');
 					remove_active(tooth);
 				}else{
 					add_active(tooth);
 					$("#toothNum").val(currToothNum);
 					$("#toothNumShow").html(currToothNum);
 				}
			}		
		}else{
			add_active(tooth);
			$("#toothNum").val(currToothNum);
			$("#toothNumShow").html(currToothNum);
		}
    })
    /*,function(){
    	$("#toothNum").val('');
		$("#toothNumShow").html('');
		remove_active(tooth);
    	var tooth = $(this).find('span.icon');
        var currToothNum = tooth.html();
 		var lastToothNum = $("#toothNum").val();
 		//选中牙齿时先判断上一个牙齿有没有被选中
 		if(lastToothNum!=null && lastToothNum!='' && lastToothNum!=undefined){
 			var flag = false;
 			var flag2 = false;
 			var updateTooth = '';
 			for (var i = 0; i < selectedToothArr.length; i++) {
 				var data = selectedToothArr[i];
 				if(data.toothNum == lastToothNum){
 					flag = true;
 				}
 				if(data.toothNum == currToothNum){
 					updateTooth = data;
 					flag2 = true;
 				}
 			}
 			if(!flag){
 				$(".tooth"+lastToothNum).removeClass('icon_active');
 			}			
 			if(flag2){
 				$("#toothNum").val(currToothNum);
				$("#toothNumShow").html(currToothNum);
				$("#toothColor").val(updateTooth.toothColor);
				form.render();
 			}else{
				if(lastToothNum == currToothNum){
 					$("#toothNum").val('');
 					$("#toothNumShow").html('');
 					remove_active(tooth);
 				}else{
 					add_active(tooth);
 					$("#toothNum").val(currToothNum);
 					$("#toothNumShow").html(currToothNum);
 				}
			}		
 		}else{
 			add_active(tooth);
 			$("#toothNum").val(currToothNum);
 			$("#toothNumShow").html(currToothNum);
 		}
	});*/

    $('#clearAll').click(function(){
    	layer.confirm('全部清除会清除掉保存的牙齿,是否继续?', {icon: 3, title:'提示'}, function(index){
    	   initPage();
		   layer.close(index);
		});
    	
    });
    
    $('#saveTooth').click(function(){
    	saveTooth();
    });
    $('#createOrders').click(function(){
    	createOrders();
    });
    patientMes();
    selectUnitFactory();
    clock();
})
/*创建订单*/
function createOrders(){
	var formData = new FormData();
	
	var patientId = $("#patientId").val();
	if(patientId=='' || patientId == undefined || patientId == null){
		layer.msg('未找到患者信息');
		return false;
	}
	formData.append("dataPatientId",patientId);
	formData.append("baseCureId",$("#baseCureId").val());
	if(selectedToothArr.length<1){
		layer.msg('请添加牙齿');
		return false;
	}
	var selectedTooths = JSON.stringify(selectedToothArr);
	formData.append("selectedTooths",selectedTooths);
	
	var ordersAccessory = '';
	$.each($('input[name="ordersAccessory"]:checkbox:checked'),function(){
		ordersAccessory += $(this).val() + ",";
    });
	if(ordersAccessory == ''){
		layer.msg('请选择订单附件') 
		return false;
	} 
    ordersAccessory = ordersAccessory.substring(0,ordersAccessory.length-1);
    formData.append("ordersAccessory",ordersAccessory);
    
    var fileName = $("#uploadFile").val();
	if(fileName!='' && fileName !=null && fileName!=undefined){
		var myfile = $("#uploadFile")[0].files[0];
		if(myfile.size>52428800){
	        layer.msg("请上传小于50M的文件");
	        return false;
	    }
		formData.append("accessoryFile",$("#uploadFile")[0].files[0]);
	}
	var factoryId = $("#factoryId").val();
	if(factoryId=='' || factoryId == undefined || factoryId == null){
		layer.msg('请选择工厂');
		return false;
	}
	formData.append("receiveUnitId",factoryId);
	formData.append("remarks",$("#remarks").val());
	$.ajax({
		url:'orders/createOrders'
		,type:'post'
		,cache: false//上传文件无需缓存
        ,processData: false//用于对data参数进行序列化处理 这里必须false
        ,contentType: false //必须
		,data:formData
	    ,dataType:"json"
	    ,success:function(result){
	    	if(10000 == result.code){
	    		
			}else{
				layer.msg(result.msg);
			}
	    }
	})
}
/*保存单个牙齿*/
function saveTooth(){
	var patientMes = $("#patientMes").val();
	if(patientMes=='' || patientMes==undefined || patientMes==null){
		layer.msg('请选择患者');
		return false;
	}

	var toothNum = $("#toothNum").val();
	if(toothNum=='' || toothNum==undefined || toothNum==null){
		layer.msg('牙齿编号不能为空');
		return false;
	}
	var toothColor = $("#toothColor").val();
	if(toothColor=='' || toothColor==undefined || toothColor==null){
		layer.msg('色号不能为空');
		return false;
	}
	var rows = table.checkStatus('baseProductTable');
	if(rows.data.length < 1){
		layer.msg('请选择产品材质');
		return false;
	}
	var rowData = rows.data[0]
	var baseProductId = rowData.baseProductId;
	
	var baseProductName = '';
	if(rowData.textureName!='' && rowData.textureName != null && rowData.textureName!=undefined){
		baseProductName = rowData.textureName;
	}
	if(rowData.brandName!='' && rowData.brandName != null && rowData.brandName!=undefined){
		if(baseProductName!=''){
			baseProductName +='-' + rowData.brandName;  
		}else{
			baseProductName = rowData.brandName;
		}
	}
	
	if(selectedToothArr.length==0){
		var row = {};
		row.toothNum = toothNum;
		row.toothColor = toothColor;
		row.baseProductId = baseProductId;
		row.toothPrice = rowData.productPrice;
		row.baseProductName = baseProductName;
		selectedToothArr.push(row)
	}
	var flag = false;
	for (var i = 0; i < selectedToothArr.length; i++) {
        if(selectedToothArr[i].toothNum == toothNum){
        	selectedToothArr[i].toothNum = toothNum;
        	selectedToothArr[i].toothColor = toothColor;
        	selectedToothArr[i].baseProductId = baseProductId;
        	selectedToothArr[i].toothPrice = rowData.productPrice;
        	selectedToothArr[i].baseProductName = baseProductName;
        	flag = true;
        }
    }
	if(!flag){
		var row = {};
		row.toothNum = toothNum;
		row.toothColor = toothColor;
		row.baseProductId = baseProductId;
		row.toothPrice = rowData.productPrice;
		row.baseProductName = baseProductName;
		selectedToothArr.push(row)
	}
	$("#selectedToothTable").empty();
	for (var i = 0; i < selectedToothArr.length; i++) {
		var data = selectedToothArr[i];
        
		$("#selectedToothTable").append('<tr>'+
        		'<th>'+data.toothNum+'</th>'+
        		'<th>'+data.toothColor+'</th>'+
        		'<th>'+data.baseProductName+'</th>'+
        		'<th>'+data.toothPrice+'</th>'+
        		'<th><button type="button" onclick="deleteSelectedTooth('+data.toothNum+')" class="layui-btn layui-btn-xs">删除</button></th></tr>');
    }
}
/*删除已经保存的牙齿*/
function deleteSelectedTooth(toothNum){
	var index;
	for (var i = 0; i < selectedToothArr.length; i++) {
        if(selectedToothArr[i].toothNum == toothNum){
        	index = i;
        }
    }
	if(index != undefined){
		selectedToothArr.splice(index, 1);
		$(".tooth"+toothNum).removeClass('icon_active');
		var curr = $("#toothNum").val();
		if(curr == toothNum){
			$("#toothNum").val('');
			$("#toothNumShow").html('');
	     }
	}
	$("#selectedToothTable").empty();
	for (var i = 0; i < selectedToothArr.length; i++) {
		var data = selectedToothArr[i];
        $("#selectedToothTable").append('<tr>'+
        		'<th>'+data.toothNum+'</th>'+
        		'<th>'+data.toothColor+'</th>'+
        		'<th>'+data.baseProductName+'</th>'+
        		'<th><button type="button" onclick="deleteSelectedTooth('+data.toothNum+')" class="layui-btn layui-btn-xs">删除</button></th></tr>');
    }
}
/*获取单位自己的加工厂*/
function selectUnitFactory(){
	$.ajax({
		url:'unit/selectMyFactory'
		,type:'get'
		,dataType:'json'
		,async:false
		,success:function(data){
			$("#factoryId").empty();
			if(data!=null && data!='' && data!=undefined){
				for (var i = 0; i < data.length; i++) {
					$("#factoryId").append('<option value="'+data[i].id+'">'+data[i].unitName+'</option>');						
				}
			}else{
				$("#factoryId").append("<option value=''>未找到跟您合作的加工厂,请联系管理员</option>");
			}
			form.render();
		}
	})
}
/*获取产品材质*/
var tableIns;
function selectProductTexture(baseCureId){
	if(baseCureId==null || baseCureId == '' || baseCureId == undefined){
		return false;
	}
  tableIns = table.render({
	    elem: '#baseProductTable'
	    ,url:'baseData/selectOrdersProductVO'
	    ,headers:{
	    	"X-Csrf-Token":csrf_token
	    }
  		,where:{
  			baseCureId:baseCureId
  		}
  		,height:400
		,response: {
		    statusName: 'code'
		    ,statusCode: 200
		    ,msgName: 'msg'
		    ,countName: 'total'
		    ,dataName: 'rows'
		 } 
	    ,title: '数据表'
	    ,cols: [[
	    	{type:'radio'}
	      ,{field:'textureName', title:'材质' }
	      ,{field:'brandName', title:'品牌' }
	      ,{field:'productPrice', title:'价格' }
	      ,{field:'yh', title:'优惠' ,templet:function(row){
	     	 if(row.num != '' && row.num != undefined && row.num != null){
	    		 return '<span style="color:red">您有'+row.discountPrice+"元优惠券待使用";
	    	 }else{
	    		 return '';
	    	 }
	      }}
	    ]]
	  });
}
/*获取患者信息*/
function patientMes(){
	$.ajax({
		url:'patient/selectPatientByCurrUnitId'
		,type:'get'
		,dataType:'json'
		,async:false
		,success:function(data){
			var patientId = $("#patientId").val();
			if(data!=null && data!='' && data!=undefined){
				$("#patientMes").empty();
				$("#patientMes").append('<option value="">请选择患者</option>');
				for (var i = 0; i < data.length; i++) {
					if(patientId!=null && patientId!='' && patientId!=undefined && patientId == data[i].id){
						$("#patientMes").append('<option selected value="'+data[i].id+','+data[i].baseCureId+'">'+data[i].patientName+'</option>');
						 if(2 == data[i].baseCureId){
				    		  $("#baseCureName").html('修复');
				    		  $("#kousaoyi").attr("href",'runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=0');
				    	  }else if(3 == data[i].baseCureId){
				    		  $("#baseCureName").html('正畸');
				    		  $("#kousaoyi").attr("href",'runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=1');
				    	  }else if(4 == data[i].baseCureId){
				    		  $("#baseCureName").html('种植');
				    		  $("#kousaoyi").attr("href",'runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=2');
				    	  }
						  $("#patientId").val(data[i].id);
			    		  $("#baseCureId").val(data[i].baseCureId);
						 selectProductTexture(data[i].baseCureId);
					}else{
						$("#patientMes").append('<option value="'+data[i].id+','+data[i].baseCureId+'">'+data[i].patientName+'</option>');
					}
				}
				form.render();
			}
		}
	})
}
// 点击某颗牙齿变成红色
function add_active(tooth){
    tooth.addClass('icon_active');
}

// 点击某颗牙齿变成白色
function remove_active(tooth){
    tooth.removeClass('icon_active');
}	

//清除所有选中牙齿
function clean_active(all_tooth){
    all_tooth.removeClass('icon_active');
}
var form;
var table;
layui.use(['form','table'], function(){
	form = layui.form;
	table = layui.table;
	/*监听选择患者*/
	form.on('select(patientMes)', function(obj){
		initPage();
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			var split = obj.value.split(",");
			if(2 == split[1]){
	    		  $("#baseCureName").html('修复');
	    		  $("#kousaoyi").attr("href",'runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=0');
	    	  }else if(3 == split[1]){
	    		  $("#baseCureName").html('正畸');
	    		  $("#kousaoyi").attr("href",'runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=1');
	    	  }else if(4 == split[1]){
	    		  $("#baseCureName").html('种植');
	    		  $("#kousaoyi").attr("href",'runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=2');
	    	  }
			  $("#patientId").val(split[0]);
			  $("#baseCureId").val(split[1]);
			  selectProductTexture(split[1]);
		}
		form.render();
		
	});
})
 function clock(){
	 var time = new Date();//获取系统当前时间
	 var year = time.getFullYear();
	 var month = time.getMonth()+1;
	 var date= time.getDate();//系统时间月份中的日
	 var day = time.getDay();//系统时间中的星期值
	 var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
	 var week = weeks[day];//显示为星期几
	 var hour = time.getHours();
	 var minutes = time.getMinutes();
	 var seconds = time.getSeconds();
	 if(month<10){
	 month = "0"+month; 
	 }
	 if(date<10){
	 date = "0"+date; 
	 }
	 if(hour<10){
	 hour = "0"+hour; 
	 }
	 if(minutes<10){
	 minutes = "0"+minutes; 
	 }
	 if(seconds<10){
	 seconds = "0"+seconds; 
	 }
	 //var newDate = year+"年"+month+"月"+date+"日"+week+hour+":"+minutes+":"+seconds;
	 document.getElementById("clock").innerHTML = year+"年"+month+"月"+date+"日&nbsp;&nbsp;"+week+"&nbsp;&nbsp;"+hour+":"+minutes+":"+seconds;
	 setTimeout('clock()',1000);
 }
function initPage(){
	$("#patientId").val('');
	$("#baseCureId").val('');
	$("#baseCureName").html('');
	$("#kousaoyi").attr("href",'javascript:void(0)');
	var li = $('.tooth_choose ul li');
	var all_span = li.find('span.icon_active');
    clean_active(all_span);
    $("#toothNumShow").empty();
    $("#toothNum").val('');
    $("#toothColor").val('');
	$("#selectedToothTable").empty();
	$("#uploadFile").val('');
	$("#showFileName").html('');
	$("#remarks").val('');
	selectedToothArr = [];
	$.each($('input[name="ordersAccessory"]:checkbox:checked'),function(){
		$(this).prop("checked",false);
    });
	form.render();
}