﻿$.extend($.fn.validatebox.defaults.rules, {
   CHS: {
            validator: function (value, param) {
              return /^[\u0391-\uFFE5]+$/.test(value);
            },
            message: '请输入汉字'
          },
   english : {// 验证英语
                validator : function(value) {
                    return /^[A-Za-z]+$/i.test(value);
                },
                message : '请输入英文'
            },
   ip : {// 验证IP地址
                validator : function(value) {
                    return /\d+\.\d+\.\d+\.\d+/.test(value);
                },
                message : 'IP地址格式不正确'
            },
   ZIP: {
            validator: function (value, param) {
              return /^[0-9]\d{5}$/.test(value);
            },
            message: '邮政编码不存在'
          },
   QQ: {
            validator: function (value, param) {
              return /^[1-9]\d{4,10}$/.test(value);
            },
            message: 'QQ号码不正确'
          },
   phone: {
            validator: function (value, param) {
              return /^1[3578]\\d{9}$/.test(value);
            },
            message: '请正确输入电话号码'
          },
   money:{
            validator: function (value, param) {
             	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
             },
             message:'请输入正确的金额'

          },
   xiaoshu:{ 
              validator : function(value){ 
              return /^(([1-9][0-9]\d*)|([0-9]+\.[0-9]{1,2}))$/.test(value);
              }, 
              message : '最多保留两位小数！'    
          	},       
   integ:{
            validator:function(value,param){
              return /^[0-9]\d*$/.test(value);
            },
            message: '请输入整数'
          },   
   multiple:{
        	  validator:function(value,param){
        		  if(/^[1-9]\d*$/.test(value)){
        			  return value%100==0;
        		  }else{
        			  return false;
        		  };
        	  },
        	  message: '请输入100的倍数'
          },   
   range:{
            validator:function(value,param){
              if(/^[1-9]\d*$/.test(value)){
                return value >= param[0] && value <= param[1]
              }else{
                return false;
              }
            },
                message:'输入的数字在{0}到{1}之间'
          },
   minLength:{
            validator:function(value,param){
              return value.length >=param[0]
            },
            message:'至少输入{0}个字'
          },
   maxLength:{
            validator:function(value,param){
              return value.length<=param[0]
            },
            message:'最多{0}个字'
          },
     //select即选择框的验证
    selectValid:{
           validator:function(value,param){
              if(value == param[0]){
                return false;
              }else{
                return true ;
              }
            },
            message:'请选择'
          },
    idCode:{
            validator:function(value,param){
              return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
            },
            message: '请输入正确的身份证号'
          },
    loginName: {
            validator: function (value, param) {
              return /^\w{6,}$/.test(value);  
            },
            message: '登录名称最少为6位数的英文、数字及下划线。'
          },
    pwd: {  
        	  validator: function (value, param) {  
        		  if (value == null || value.length <6) {
        		        return false;
        		  }
        		  return /^(?![^a-zA-Z]+$)(?!\D+$)/.test(value);  
        	  },  
        	  message: '请输入6位以上且必须字母数字混合密码！', 
          },
    equalToPwd:{
              validator: function (value, param) {
              return value == $(param[0]).val();
            },
            message: '两次输入的密码不一致'
          },	
    englishOrNum : {// 只能输入英文和数字
                validator : function(value) {
                    return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
                },
                message : '请输入英文、数字、下划线或者空格'
            },
     age:{
          validator:function(value,param){
            if(/^[1-9][0-9]{2}$/.test(value)){
              return value >= param[0] && value <= param[1];
            }else{
              return false;
            }
          },
          message:'请输入1到150之间正整数'
        },
    sex:{
          	validator:function(value,param){
          		if(/^[\u0391-\uFFE5]+$/.test(value)){
          				return value=="男" || value == "女";
          			}else{
          				return false;
          			}
          		},
          		message:'性别只能为男和女'
          	},
    date:{
        	validator:function(value,param){
        		if(/^\1|2\d{3}-\d{1,2}-[0-3]\d{1,2} [0-2]\d:[0-5]\d:[0-5]\d$/.test(value)){
        			return true;
        		}else{
        			return false;
        		}
        	},
        	message:'请输入正确的日期格式'
        },
        policeCode:{
        	validator:function(value,param){
        		if(/^[0-9]{6,7}$/.test(value)){
        			return true;
        		}else{
        			return false;
        		}
        	},
        	message:'警员编号为6-7位数字！'
        }
	});  
