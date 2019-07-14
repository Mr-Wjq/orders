<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<link rel="stylesheet" type="text/css" href="static/css/linker.css"/>
	<style type="text/css">
		.mesDiv{padding: 30px;}
		.layui-form-label {width: 100px;}
		.bottomDiv{margin:0 auto;}
		.btnZu {text-align: center;}
		.layui-table img {max-width: 100%;}
		.layui-form-item {
		     margin-bottom: 0px;
		    /* clear: both; */
		    *zoom: 1;
		}
		.cols-font {font-size: 15px;
		    font-weight: 550;
		    color: black;
		}
		.layui-input, .layui-select, .layui-textarea {
		    background-color: #f7f0f0;
		}
	</style>
</head>
<body>
<input type="hidden" id="ordersId" value="${ordersId }"/>
<div class="mesDiv">

	单位名称:&nbsp;&nbsp;${unitName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;医生姓名:&nbsp;&nbsp;${sessionScope.loginInfo.zhName }
		<form class="layui-form" lay-filter="ordersTable">
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
				      <td><input type="text" name="patientName" id="patientName" required lay-verify="required|zhName" maxlength="10" placeholder="" autocomplete="off" class="layui-input"></td>
				      <td class="cols-font">年龄<span class="required-sign">☀</span></td>
				      <td><input type="text" name="patientAge" id="patientAge" required lay-verify="required|age" maxlength="3" placeholder="" autocomplete="off" class="layui-input"></td>
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
				    	<td class="cols-font">订单附件<span class="required-sign">☀</span></td>
				        <td colspan="3">
				      	    <div class="layui-form-item">
							     <div style="float: left;"> 
							      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="扫描数据" value="1" lay-filter="ordersAccessory">
							      </div>
							    <div id="kousaoDiv" style="float: left;"></div>
							</div>
				      		<div >
				      		  <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="扫描数据" value="1" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="颌架" value="2" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="咬蜡" value="3" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="托盘" value="4" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="照片" value="5" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="旧牙" value="6" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="参考模" value="7" lay-filter="ordersAccessory">
						      <input type="checkbox" name="ordersAccessory" lay-skin="primary" title="比色板" value="8" lay-filter="ordersAccessory">
						    </div>
					      </td>
				    </tr>
				    <tr id="accessoryTr">
				    
				    </tr>  
				    <tr>
				      <td class="cols-font">牙位</td>
				      <td colspan="3">
				      		<table class="layui-table" lay-skin="nob">
							  <tbody>
							  	<colgroup>
								    <col width="100">
								    <col width="100">
								</colgroup>
							    <tr>
							      <td><input type="text" id="toothPosition1"  placeholder="左上" autocomplete="off" class="layui-input"></td>
							      <td><input type="text" id="toothPosition2"  placeholder="右上" autocomplete="off" class="layui-input"></td>
							    </tr>
							    <tr>
							      <td><input type="text" id="toothPosition3"  placeholder="左下" autocomplete="off" class="layui-input"></td>
							      <td><input type="text" id="toothPosition4"  placeholder="右下" autocomplete="off" class="layui-input"></td>
							    </tr>
							  </tbody>
							</table>
				      </td>
				    </tr>
				    <tr>
				    	<td class="cols-font">色号</td>
				      	<td colspan="3">
				      		<input type="text" name="color" id="color" placeholder="色号" autocomplete="off" class="layui-input">
				      	</td>
				    </tr>
				    <tr>
				      <td class="cols-font">治疗类型<span class="required-sign">☀</span></td>
				      <td colspan="3"><select name="baseCureId" id="baseCureId" lay-verify="required" lay-filter="threeDivBaseCureId"><option value="">请选择治疗类型</option></select></td>
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
				      <td colspan="3"><textarea name="remarks" id="remarks" placeholder="请输入内容" class="layui-textarea" maxlength="255"></textarea></td>
				    </tr>
				    
				  </tbody>
				</table>
			</div>
			<div class="btnZu">
			<c:choose>	
				<c:when test="${ordersId != null}">
				    <button class="layui-btn" type="button" style="width: 200px;" lay-submit="" lay-filter="updateOrders">修改订单</button>
				</c:when>
				<c:otherwise>
					<button class="layui-btn" type="button" style="width: 200px;" lay-submit="" lay-filter="createOrders">创建订单</button>
				</c:otherwise>
			</c:choose>
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