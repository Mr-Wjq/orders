<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<script type="text/javascript" src="static/js/layui/layui.all.js"></script>
<script type="text/javascript">
var form;
layui.use(['table','laydate'], function(){
	  var table = layui.table;
	  var laydate = layui.laydate;
	  form = layui.form;
	  
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
		,unitName: function(value){
			  if(!(/^[a-zA-Z0-9\u4e00-\u9fa5]{2,50}$/.test(value))){
			    return '请输入2-50个中文、字母或数字';
			  }
			}
		,age: [
	    	/^(?:[1-9][0-9]?|1[01][0-9]|120)$/ 
	        ,'请输入正确的年龄'
	     ]
	    ,password: [
	      /^[\S]{6,12}$/
	      ,'密码必须6到12位，且不能出现空格'
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
	    ,kongge:function(value){
	      	if(value != '' && value.indexOf(" ") >=0){
	      		return '不能有空格';
	      	}
	      }
	    ,money:[
	    	/^(([1-9]\d*)|\d)(\.\d{1,2})?$/
	    	,'请输入正确金额,保留小数点后两位'
	    ]
	  });
})
</script>