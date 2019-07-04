layui.use(['table','laydate','element'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  var element = layui.element;  
  
  searchCureList(form);
  
  var addTableIns = table.render({
	    elem: '#productAddTableId'
	    ,url:'baseData/selectFactoryProductForDoctor'
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
	    ,toolbar: '#productAddTableToolbar'
	    ,title: '数据表'
	    ,cols: [[

	       {type: 'numbers', fixed: 'left'}
	      ,{field:'cureName', title:'治疗类型' }
	      ,{field:'textureName', title:'材质' }
	      ,{field:'brandName', title:'品牌' }
	      ,{field:'productName', title:'产品' }
	      ,{field:'factoryName', title:'工厂' }
	      ,{field:'price', title:'价格'}
	    ]]
	    ,page: true
	  });
	
  

  

  //搜索按钮
  $("#searchBtn").click(function(){
	  addTableIns.reload({where:{
		  cureId:$("#search_cureList").val(),
		  productName:$("#productName").val(),
		  textureName:$("#textureName").val(),
		  brandName:$("#brandName").val(),
		  factoryName:$("#factoryName").val(),
	  	},page: {
	  		curr: 1 //重新从第 1 页开始
	  	}
	  });
  })

});
function searchCureList(form){
	$.ajax({
		  url:'baseData/selectAllCureList'
		  ,type:'get'
		  ,dataType:'json'
		  ,async:false
		  ,success:function(result){
			if(result!=null && result!='' && result!=undefined){
				$("#search_cureList").empty();
				$("#search_cureList").append("<option value=''>治疗类型</option>");
				for (var i = 0; i < result.length; i++) {
					$("#search_cureList").append('<option value="'+result[i].id+'">'+result[i].cureName+'</option>');						
				}
				form.render();
			}
		  }
	  })
}
