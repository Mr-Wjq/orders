<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Long version = System.currentTimeMillis();
%>
<!DOCTYPE html>
<head>
	<base href="<%=basePath%>">
	<meta id="csrf_token" content="${csrf_token}"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="image/x-icon" rel="shortcut icon" href="static/images/favicon.png">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/js/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/orders/orders.css"/>
	<script type="text/javascript" src="<%=basePath%>static/js/system/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/system/common.js"></script>
	
	<style type="text/css">.required-sign{color: red;}
		.patientMesDiv{margin:20px 0px 0px 20px;}
		.ordersDiv{padding: 20px;width: 95%;}
		.tooth_choose{float: left;margin: 0 30px 30px 30px;}
		.tooth_mes{float: left;}
		.orders_mes{clear:both;margin-top:20px;padding: 20px;}
		.upper{margin-top:50px;}
	</style>

</head>
<body >
<input type="hidden" id="basePath" value="<%=basePath%>"/>
<input type="hidden" id="ordersId" value="${ordersId }"/>
<input type="hidden" id="patientId" value="${patientId }"/>

<div class="patientMesDiv" >
	<form class="layui-form">
		<div class="layui-form-item">
		    <div class="layui-input-inline"  style="width: 270px;">
		       <div class="layui-form-mid">日期:</div>
		       <div class="layui-form-mid layui-word-aux" id="clock"></div>
		    </div>
		    <div class="layui-input-inline">
		       <div class="layui-form-mid">医生:</div>
		       <div class="layui-form-mid layui-word-aux">${sessionScope.loginInfo.zhName }</div>
		    </div>
		    
		    <label class="layui-form-label">患者:</label>
		    <div class="layui-input-inline">
		       <select id="patientMes" lay-filter="patientMes" lay-search>
				     		<option value="">请选择患者</option>
			   </select>
		    </div>
		    <div class="layui-input-inline">
		       <div class="layui-form-mid">治疗类型:</div>
		       <input type="hidden" id="baseCureId" >
		       <div class="layui-form-mid layui-word-aux" id="baseCureName"></div>
		    </div>
		</div>	
	</form>
</div>
<div class="ordersDiv">
	<div class="tooth_choose">
	    <div style="margin-bottom: 20px;">
	    	<a id="kousaoyi" type="button" style="width: 100%;height: 80px;padding: 23px;" class="layui-btn layui-btn-normal">打开口扫仪</a>
	    </div>
		<div class="upper">
		    <ul class="upper_left">
		        <li><span class="icon tooth11">11</span></li>
		        <li><span class="icon tooth12">12</span></li>
		        <li><span class="icon tooth13">13</span></li>
		        <li><span class="icon tooth14">14</span></li>
		        <li><span class="icon tooth15">15</span></li>
		        <li><span class="icon tooth16">16</span></li>
		        <li><span class="icon tooth17">17</span></li>
		        <li><span class="icon tooth18">18</span></li>
		    </ul>
		    <ul class="upper_right">
		        <li><span class="icon tooth21">21</span></li>
		        <li><span class="icon tooth22">22</span></li>
		        <li><span class="icon tooth23">23</span></li>
		        <li><span class="icon tooth24">24</span></li>
		        <li><span class="icon tooth25">25</span></li>
		        <li><span class="icon tooth26">26</span></li>
		        <li><span class="icon tooth27">27</span></li>
		        <li><span class="icon tooth28">28</span></li>
		    </ul>
		</div>
		<div class="clean_btn">
			<button type="button" id="clearAll">清除所有</button>
		</div>
		<div class="lower">
		    <ul class="lower_left">
		        <li><span class="icon tooth48">48</span></li>
		        <li><span class="icon tooth47">47</span></li>
		        <li><span class="icon tooth46">46</span></li>
		        <li><span class="icon tooth45">45</span></li>
		        <li><span class="icon tooth44">44</span></li>
		        <li><span class="icon tooth43">43</span></li>
		        <li><span class="icon tooth42">42</span></li>
		        <li><span class="icon tooth41">41</span></li>
		    </ul>
		    <ul class="lower_right">
		        <li><span class="icon tooth31">31</span></li>
		        <li><span class="icon tooth32">32</span></li>
		        <li><span class="icon tooth33">33</span></li>
		        <li><span class="icon tooth34">34</span></li>
		        <li><span class="icon tooth35">35</span></li>
		        <li><span class="icon tooth36">36</span></li>
		        <li><span class="icon tooth37">37</span></li>
		        <li><span class="icon tooth38">38</span></li>
		    </ul>
		</div>
	</div>
	
	<div class="tooth_mes">
		<form class="layui-form tooth_mes_form" action="">
		  	<div class="layui-form-item">
			    <label class="layui-form-label">牙齿编号</label>
			    <div class="layui-input-block">
			      <label class="layui-form-label" id="toothNumShow" style="text-align: left;"></label>
			      <input type="hidden" id="toothNum" class="layui-input">
			    </div>
		    </div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">色号<span class="required-sign">☀</span></label>
			    <div class="layui-input-block">
			      <input type="text" id="toothColor" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
			    </div>
		    </div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">产品材质<span class="required-sign">☀</span></label>
			    <div class="layui-input-block">
				    <table class="layui-hide" id="baseProductTable"></table>
			    </div>
		    </div>
		    <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit="" id="saveTooth" type="button">保存该牙齿</button>
			    </div>
			</div> 
		</form>
	</div>
	
	<div class="orders_mes">
		<form class="layui-form" action="">
		  	<div class="layui-form-item">
			    <label class="layui-form-label">已选牙齿</label>
			    <div class="layui-input-block">
			      <table class="layui-table" lay-even="" lay-skin="nob">
					  <colgroup>
					    <col width="50">
					    <col width="50">
					    <col width="200">
					    <col width="50">
					    <col width="50">
					  </colgroup>
					  <thead>
					    <tr>
					      <th>牙齿编号</th>
					      <th>色号</th>
					      <th>产品材质</th>
					      <th>价格</th>
					      <th>操作</th>
					    </tr> 
					  </thead>
					  <tbody id="selectedToothTable">
					
					  </tbody>
					</table> 
			    </div>
		    </div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">订单附件<span class="required-sign">☀</span></label>
			    <div class="layui-input-block">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="扫描数据" value="1" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="颌架" value="2" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="咬蜡" value="3" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="托盘" value="4" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="照片" value="5" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="旧牙" value="6" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="参考模" value="7" lay-filter="ordersAccessory">
			      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="比色板" value="8" lay-filter="ordersAccessory">
			    </div>
		    </div>
		    <div class="layui-form-item">
			    <label class="layui-form-label">上传附件</label>
			    <div class="layui-input-block">
			        <button type="button" class="layui-btn" onclick="$('#uploadFile').val('');$('#uploadFile').click();">
			        <i class="layui-icon layui-icon-upload"></i>上传文件</button>
			        <input id='uploadFile' type='file'  style='display:none;position: fixed;bottom: 30px;right: 60px;z-index: 999999;' 
			        onchange="$('#showFileName').html(this.value);" multiple>
			        <span id="showFileName" style="width: 100%;">如果要上传多个扫描文件请压缩后在上传!</span>
			    </div>
		    </div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">加工厂<span class="required-sign">☀</span></label>
			    <div class="layui-input-block">
			        <select id="factoryId" lay-verify="required">
				        <option value="">请选择加工厂</option>
				    </select>
			    </div>
		    </div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">设计需求</label>
			    <div class="layui-input-block">
			        <textarea id="remarks" placeholder="请输入内容" class="layui-textarea"></textarea>
			    </div>
		    </div>
		    <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn layui-btn-fluid" lay-submit="" type="button" id="createOrders">创建订单</button>
			    </div>
			</div> 
		</form>  
	</div>
</div>

<!-- style="display: none;" -->
<div id="paymentDiv"  >
	<form class="layui-form" action="/order/buy/">
		<div class="layui-form-item">
			<label class="layui-form-label">订单：</label>
			<div class="layui-form-mid" style="float: none;">
				<a href="javascript:void(0)" id="ordersNum"></a>
			</div>
		</div>                  

		<div class="layui-form-item">
			<label class="layui-form-label">支付方式：</label>
			<div class="layui-input-block">
				<input type="radio" name="payStatus" value="" title="在线支付" checked>
      			<input type="radio" name="payStatus" value="女" title="线下支付">
			</div>
			<div class="layui-form-mid" style="color: red;">☀离线支付不享受任何优惠</div>  
		</div> 
		
		<div class="layui-form-item" >
			<label class="layui-form-label">工厂优惠：</label>
			<div class="layui-input-block" id="factoryDiscount">
				
			</div>
		</div> 
		<div class="layui-form-item">
			<label class="layui-form-label">产品优惠：</label>
			<div class="layui-input-block" id="productDiscount">
				<input type="radio" name="payStatus" value="" title="在线支付" checked>
      			<input type="radio" name="payStatus" value="女" title="线下支付">
			</div>
		</div> 
		<div class="layui-form-item">
			<label class="layui-form-label">平台优惠：</label>
			<div class="layui-input-block" id="productDiscount">
				<input type="radio" name="payStatus" value="" title="在线支付" checked>
      			<input type="radio" name="payStatus" value="女" title="线下支付">
			</div>
		</div> 
	</form>
</div>
<script type="text/javascript" src="<%=basePath%>static/js/layui/layui.all.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/page-js/orders/createOrders.js?v=<%=version %>"></script>
</body>