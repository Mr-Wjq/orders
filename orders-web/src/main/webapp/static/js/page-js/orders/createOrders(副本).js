layui.use(['transfer','table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  var transfer = layui.transfer
  //自定义验证规则
  form.verify({
    loginName: function(value){
      if(!(/^[a-zA-Z][a-zA-Z0-9_-]{3,15}$/.test(value))){
        return '请输入字母开头,长度为4-16的英文、数字、下划线或减号';
      }
    }
	,zhName: function(value){
	  if(!(/^[a-zA-Z0-9\u4e00-\u9fa5]{2,16}$/.test(value))){
	    return '请输入2-16个中文、字母或数字';
	  }
	}
    ,password: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ]
    ,age: [
    	/^(?:[1-9][0-9]?|1[01][0-9]|120)$/ 
        ,'请输入正确的年龄'
      ]
    ,eq_password: function(value){
    	var password = $("#i_password").val();
    	if(value != password){
    		return '两次密码不一致';
    	}
    }
    ,eq_password2: function(value){
    	var password = $("#i_password2").val();
    	if(value != password){
    		return '两次密码不一致';
    	}
    }
    ,email:function(value){
    	if(value != '' && !(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(value))){
    		return '邮箱格式不正确';
    	}
    }
  });
  //监听下拉框
	form.on('select(threeDivBaseCureId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			selectProductTexture(obj.value,form)
		}else{
			$("#baseProductId").empty();
			$("#baseProductId").append("<option value=''>暂无产品材质</option>");
			form.render();
		}
		
	});
	form.on('select(threeDivBaseProductId)', function(obj){
		if(obj.value !=null && obj.value != '' && obj.value != undefined){
			$.ajax({
				url:'unit/selectFactoryByProductId'
				,type:'get'
				,data:{productId:obj.value}
				,dataType:'json'
				,async:false
				,success:function(data){
					if(data!=null && data!='' && data!=undefined){
						$("#receiveUnitId").empty();
						$("#receiveUnitId").append("<option value=''>请选择加工厂</option>");
						for (var i = 0; i < data.length; i++) {
							$("#receiveUnitId").append('<option value="'+data[i].id+'">'+data[i].unitName+'</option>');						
						}
					}else{
						$("#receiveUnitId").empty();
						$("#receiveUnitId").append("<option value=''>暂无加工厂</option>");
					}
				}
			})
		}else{
			$("#receiveUnitId").empty();
			$("#receiveUnitId").append("<option value=''>暂无加工厂</option>");
		}
		form.render();
	}); 
	form.on('checkbox(ordersAccessory)', function(data){
		  
		if(data.elem.checked == true && data.value == 1){
			  $("#accessoryTr").append("<td class='cols-font' >扫描文件</td><td colspan='3' >" +
			  		"<button class='layui-btn' type='button' onclick=\"$('#uploadFile').val('');$('#uploadFile').click();\">" +
			  	    "<i class='layui-icon'>&#xe62d;</i>上传扫描文件</button>"+
          	        "<input id='uploadFile' type='file'  style='display:none;position: fixed;bottom: 30px;right: 60px;z-index: 999999;'"+
					"onchange=\"$('#showFileName').html(this.value);\" multiple><span style='margin-left:20px;' id='showFileName'></span></td>");
			  $("#kousaoDiv").append('<a style="color:#0098ff;" href="runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=0">重新扫描</a>')
		 };
		 if(data.elem.checked == false && data.value == 1){
			  $("#accessoryTr").empty();
			  $("#kousaoDiv").empty();
		 };
	}); 
  form.on('submit(toNextTwo)', function(data){
	   $.ajax({
		  url:'orders/createOrder'
		  ,type:'post'
		  ,data:data.field
		  ,dataType:'json'
		  ,success:function(result){
			  if(10000 == result.code){
				    $("#ordersId").val(result.data);
				    $("#oneDiv").hide();
					$("#twoDiv").show();
				    soutside1ab.classList.remove("outside1ab");
			  }else{
				  layer.msg(result.msg);
			  }
		  }
	  })  
	  return false;
  });
  form.on('submit(createOrders)', function(data){
	  createOrders();
	  return false;
  });
  $("#useFactory").click(function(){
	  layer.open({
  		type:1
  		,title:'设置常用工厂'
  		,area:['50%','70%']
  		,shade:0.8
  		,btn:['关闭']
  		,content:$("#userFactoryDiv")
  		,yes:function(){
  			layer.closeAll();
  		}
  		,success:function(){
  			transferMethod(transfer);
  		}
  		,end:function(){
  			$("#userFactoryDiv").hide();
  		}
  	})
  })
  getBaseCure(form);
})

function transferMethod(transfer){
	$.ajax({
  		  url:'unit/selectUserFactory'
  		  ,type:'get'
  		  ,dataType:'json'
  		  ,async:false
  		  ,success:function(data){
  			if(data!=null && data!='' && data!=undefined){
  				var allFactory = data.allFactory;
  				var useFactory = data.useFactory;
  				var arr=new Array();
  				for (var i = 0; i < useFactory.length; i++) {
  					arr[i] = useFactory[i].id
				}
  				transfer.render({
				    elem: '#factoryList'
				    ,id: 'factoryFansfer' //定义唯一索引
				    ,title: ['所有工厂', '常用工厂']
				    ,showSearch: true
				    ,data:allFactory
				    ,value:arr
				    ,parseData: function(res){
				        return {
				          "value": res.id //数据值
				          ,"title": res.unitName //数据标题
				          //,"disabled": res.disabled  //是否禁用
				          //,"checked": res.checked //是否选中
				        }
				     }
				    ,onchange: function(obj, index){
				        var type = index == 0 ? 'insert' : 'delete' ;
				        var factoryId ='';
				        for (var i = 0; i < obj.length; i++) {
							var row = obj[i];
							if((i+1)==obj.length){
								factoryId += row.value;
							}else{
								factoryId += row.value + ",";
							}
						}
				        $.ajax({
				  	  		  url:'unit/updateUserFactory'
				  	  		  ,type:'post'
				  	  		  ,data:{type:type,factoryId:factoryId}
				  	  		  ,dataType:'json'
				  	  		  ,async:false
				  	  		  ,success:function(result){
				  	  			 if(result.code != 10000){
				  	  				 layer.msg(result.msg);
				  	  			 }
				  	  			transferMethod(transfer)
				  	  		  }
				  	  	})
				    }
			    })
			}
  		  }
  	  })
}

function createOrders(){
	alert($('#uploadFile').val());
}

function startScan(){
	location.href="runyes3ds1.1://hostApp=Clear&patientId=p001&caseId=111&isNewCase=1&workflow=0";
	toThreeDiv();
}

function toThreeDiv(){
	$("#twoDiv").hide();
	$("#threeDiv").show();
	soutside2as.classList.remove("outside2a");
}

function canal(){
	$("#twoDiv").show();
	$("#threeDiv").hide();
	soutside2as.classList.add("outside2a");
}

var soutside1ab=document.getElementById("outside1abs");
var soutside2as=document.getElementById("outside2as");

function nextThree(){
	soutside2as.classList.remove("outside2a");
}
function getBaseCure(form){
	$.ajax({
  		  url:'baseData/selectAllCureList'
  		  ,type:'get'
  		  ,dataType:'json'
  		  ,success:function(data){
  			if(data!=null && data!='' && data!=undefined){
  				$("#baseCureId").empty();
  				$("#baseCureId").append("<option value=''>请选择治疗类型</option>");
				for (var i = 0; i < data.length; i++) {
					$("#baseCureId").append('<option value="'+data[i].id+'">'+data[i].cureName+'</option>');						
				}
				form.render();
			}
  		  }
  	  })
}
function getBaseCure2(form){
	$.ajax({
  		  url:'baseData/selectAllCureList'
  		  ,type:'get'
  		  ,dataType:'json'
  		  ,success:function(data){
  			if(data!=null && data!='' && data!=undefined){
  				$("#threeDivBaseCureId").empty();
  				$("#threeDivBaseCureId").append("<option value=''>请选择治疗类型</option>");
				for (var i = 0; i < data.length; i++) {
					$("#threeDivBaseCureId").append('<option value="'+data[i].id+'">'+data[i].cureName+'</option>');						
				}
				form.render();
			}
  		  }
  	  })
}
function selectProductTexture(baseCureId,form){
	if(baseCureId==null || baseCureId == '' || baseCureId == undefined){
		return false;
	}
	$.ajax({
		url:'baseData/selectProductTexture'
		,type:'get'
		,data:{baseCureId:baseCureId}
		,dataType:'json'
		,success:function(data){
			if(data!=null && data!='' && data!=undefined){
				$("#baseProductId").empty();
				$("#baseProductId").append("<option value=''>请选择产品材质</option>");
				for (var i = 0; i < data.length; i++) {
					$("#baseProductId").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');						
				}
				form.render();
			}
		}
	})
}
