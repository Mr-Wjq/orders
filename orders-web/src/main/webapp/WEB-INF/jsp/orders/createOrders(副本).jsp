<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<link rel="stylesheet" type="text/css" href="static/css/linker.css"/>
	<style type="text/css">
		.mesDiv{clear:both;margin:0 auto;width: 100%;padding: 30px;}
		.layui-form-label {width: 100px;}
		.bottomDiv{margin:0 auto;}
		.btnZu {text-align: center;}
		.layui-table img {max-width: 100%;}
		.layui-form-item {
		     margin-bottom: 0px;
		    /* clear: both; */
		    *zoom: 1;
		}
		.cols-font {
		    font-size: 15px;
		    font-weight: 550;
		    color: black;
		}
	</style>
</head>
<body>
	<!-- 导航 -->
<div class="rebinding-box">
	<div class="box-timeline">
		<ul class="text-center" style="width: 800px;" >
			<li>
				新建病例
				<div class="box-num1">
					1
				</div>
			</li>
			<li class="ml45">
				开始诊断
				<div class="box-outside1 outside1ab" id="outside1abs">
					<div class="box-num2 num2ab">
					  2
				    </div>
				</div>
			</li>
			<li class="ml45">
				创建订单
				<div class="box-outside2 outside2a" id="outside2as">
					<div class="box-num3 num3a" >
					 3
				   </div>
				</div>
			</li>
			<div class="clear">
				
			</div>
		</ul>
	</div>
</div>
<div class="mesDiv">
		<!--第一步-->
	<div id="oneDiv" >
	<form class="layui-form">
	<input type="hidden" name="createUnitId" value="${sessionScope.loginInfo.unitId }">
	<input type="hidden" name="createUserId" value="${sessionScope.loginInfo.id }">
		<div>
			<table class="layui-table" >
			  
			  <tbody>
			    <tr>
			      <td class="cols-font">单位名称</td>
			      <td>${unitName }</td>
			      <td class="cols-font">医生姓名</td>
			      <td>${sessionScope.loginInfo.zhName }</td>
			    </tr>
			    <tr>
			      <td class="cols-font">类别</td>
			      <td>
			      	  <input type="radio" name="patientType" value="1" title="初诊" checked>
      				  <input type="radio" name="patientType" value="2" title="复诊" >
			      </td>
			      <td class="cols-font">治疗类型</td>
			      <td>
			      	  <select name="baseCureId" id="baseCureId" lay-verify="required">
				        <option value="">请选择治疗类型</option>
				      </select>
			      </td>
			    </tr>
			    <tr>
			      <td class="cols-font">患者</td>
			      <td><input type="text" name="patientName" required lay-verify="required|zhName" maxlength="20" placeholder="" autocomplete="off" class="layui-input"></td>
			      <td class="cols-font">年龄</td>
			      <td><input type="text" name="patientAge" required lay-verify="required|age"  maxlength="3" placeholder="" autocomplete="off" class="layui-input"></td>
			    </tr>
			    <tr>
			      <td class="cols-font">性别</td>
			      <td><input type="radio" name="patientSex" value="1" title="男" checked>
      				  <input type="radio" name="patientSex" value="2" title="女" ></td>
			    </tr>
			  </tbody>
			</table>
		</div>
	    <div class="btnZu">
			<button class="layui-btn" type="button" style="width: 200px;" lay-submit="" lay-filter="toNextTwo">下一步</button>
	    </div>
	</form>
	</div>
	<!--第二步-->
	<div id="twoDiv" style="display: none;">
		<div  class="btnZu">
		  <button class="layui-btn" onclick="startScan()" >启动口扫仪</button> 
		  <button class="layui-btn" onclick="toThreeDiv()">传统印模</button> 
		</div>
	</div>
	<!--第三步-->
	<div id="threeDiv" style="display: none;">
	<input type="hidden" id="ordersId" > 
	单位名称:&nbsp;&nbsp;${unitName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;医生姓名:&nbsp;&nbsp;${sessionScope.loginInfo.zhName }
		<form class="layui-form">
			<div>
				<table class="layui-table" lay-size="sm">
				<colgroup>
				    <col width="30">
				    <col width="200">
				    <col width="30">
				    <col width="200">
				    <col width="100">
				</colgroup>
				  <tbody>
				    
				    <tr>
				      <td class="cols-font">患者<span class="required-sign">☀</span></td>
				      <td><input type="text" name="patientName" required lay-verify="required" maxlength="10" placeholder="" autocomplete="off" class="layui-input"></td>
				      <td class="cols-font">年龄<span class="required-sign">☀</span></td>
				      <td><input type="text" name="patientAge"  required lay-verify="required" maxlength="3" placeholder="" autocomplete="off" class="layui-input"></td>
				      <td rowspan="5" ><img alt="牙齿排列图" width="100%" height="100%" src="static/images/touch.jpg"></td>
				    </tr>
				    <tr>
				      <td class="cols-font">性别</td>
				      <td><input type="radio" name="patientSex" value="1" title="男" checked>
	      				  <input type="radio" name="patientSex" value="2" title="女" ></td>
	      			  <td class="cols-font">类别</td>
				      <td >
				      	  <input type="radio" name="patientType" value="1" title="初诊" checked>
	      				  <input type="radio" name="patientType" value="2" title="复诊" >
				      </td>
				    </tr>  
				    <tr>
				      <td class="cols-font">牙位<span class="required-sign">☀</span></td>
				      <td>
				      		<table class="layui-table" lay-skin="nob">
							  <tbody>
							  	<colgroup>
								    <col width="100">
								    <col width="100">
								</colgroup>
							    <tr>
							      <td><input type="text" id="patientAge1"  placeholder="左上" autocomplete="off" class="layui-input"></td>
							      <td><input type="text" id="patientAge2"  placeholder="右上" autocomplete="off" class="layui-input"></td>
							    </tr>
							    <tr>
							      <td><input type="text" id="patientAge3"  placeholder="左下" autocomplete="off" class="layui-input"></td>
							      <td><input type="text" id="patientAge4"  placeholder="右下" autocomplete="off" class="layui-input"></td>
							    </tr>
							  </tbody>
							</table>
				      </td>
				      <td class="cols-font">色号</td>
				      <td><textarea name="color" placeholder="请输入内容" class="layui-textarea"></textarea></td>
				      <!-- <td rowspan="3"><img alt="牙齿排列图" width="100%" height="100%" src="static/images/touch.jpg"></td> -->
				    </tr>
				    <tr>
				      <td class="cols-font">治疗类型<span class="required-sign">☀</span></td>
				      <td colspan="3"><select name="baseCureId" id="threeDivBaseCureId" lay-verify="required" lay-filter="threeDivBaseCureId"><option value="">请选择治疗类型</option></select></td>
				    </tr>
				    <tr>
				      <td class="cols-font">产品材质<span class="required-sign">☀</span></td>
				      <td colspan="3"><select name="baseProductId" id="baseProductId" lay-verify="required" lay-search lay-filter="threeDivBaseProductId"><option value="">请选择产品材质</option></select></td>
				    </tr> 
				    <tr>
				      <td class="cols-font">加工厂<span class="required-sign">☀</span></td>
				      <td colspan="3">
					      <div class="layui-form-item">
					      	  <div class="layui-input-inline"><select name="receiveUnitId" id="receiveUnitId" lay-verify="required"><option value="">请选择加工厂</option></select></div>
					      	  <div class="layui-form-mid layui-word-aux"><a href="javascript:void(0);" id="useFactory" style="color: #0098ff" >设置常用工厂</a></div>
					      </div>
				      </td>
				    </tr>
				    <tr>
				      <td class="cols-font">设计要求</td>
				      <td colspan="3"><textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea></td>
				    </tr>
				    <tr>
				    	<td class="cols-font">订单附件<span class="required-sign">☀</span></td>
				        <td colspan="3">
				      		<div >
				      		  <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="扫描数据" value="1" lay-filter="ordersAccessory">
				      		  <span id="kousaoDiv" ></span>
				      		</div>
				      		<div >
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="颌架" value="2" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="咬蜡" value="3" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="托盘" value="4" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="照片" value="5" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="旧牙" value="6" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="参考模" value="7" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="托比色板" value="8" lay-filter="ordersAccessory">
						    </div>
					      </td>
				    </tr>
				    <tr id="accessoryTr">
				    
				    </tr>
				  </tbody>
				</table>
			</div>
			<div class="btnZu">
				<button class="layui-btn" type="button" style="width: 200px;" lay-submit="" lay-filter="createOrders">创建订单</button>
		    </div>
		</form>
	</div>
</div>
<div id="userFactoryDiv" style="display: none;margin: 0 auto;text-align:center;margin-top: 50px;">
	<div id="factoryList"></div>
</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/orders/createOrders.js?v=<%=version %>"></script>
</body>