<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<style type="text/css">
		.layui-layer-page .layui-layer-content{overflow:initial !important;}
	</style>
</head>
<body>
<form class="layui-form" action="" style="margin-top: 20px;">
<div class="layui-form-item">
    <label class="layui-form-label">查询</label>
    
    <div class="layui-input-inline">
       <input type="text" id="unitName" placeholder="单位名称" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <select id="unitType">
        <option value="">单位类型<option>
        <option value="1">医院</option>
        <option value="2">连锁门诊</option>
        <option value="3">个体门诊</option>
        <option value="4">工厂</option>
      </select>
    </div>
    <div class="layui-input-inline">
      <select id="s_unitProvinceId" lay-filter="s_unitProvinceId">
        <option value="">请选择省<option>
      </select>
    </div>
    <div class="layui-input-inline">
      <select id="s_unitCityId">
        <option value="">请选择市<option>
      </select>
    </div>
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>
<!-- 数据表格 -->
<table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>

<div id="insertDiv" style="display: none;padding: 10px;"" >
	<form class="layui-form" action="">
		<div class="layui-form-item">
	      <label class="layui-form-label">单位名称<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="unitName" lay-verify="required|unitName" placeholder="请输入单位名称" maxlength="50" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		
		<label class="layui-form-label">选择地区<span class="required-sign">☀</span></label>
	    <div class="layui-input-inline">
	      <select id="unitProvinceId" name="unitProvinceId" lay-verify="required" lay-filter="unitProvinceId">
	        <option value="">请选择省</option>
	        
	      </select>
	    </div>
	    <div class="layui-input-inline">
	      <select id="unitCityId" name="unitCityId" lay-verify="required" lay-filter="unitCityId">
	        <option value="">请选择市</option>
	        
	      </select>
	    </div>
	    <div class="layui-input-inline">
	      <select id="unitDistrictId" name="unitDistrictId" lay-verify="required">
	        <option value="">请选择县/区</option>
	        
	      </select>
	    </div>  
		
		<div class="layui-form-item">
		   <label class="layui-form-label">单位类型<span class="required-sign">☀</span></label>
		   <div class="layui-input-block">
		   		<input type="radio" name="unitType" value="3" title="个体门诊" checked>
		     	<input type="radio" name="unitType" value="1" title="医院" >
		   		<input type="radio" name="unitType" value="2" title="连锁门诊">
		   		<input type="radio" name="unitType" value="4" title="工厂">
		   </div>
		 </div>

	    <div class="layui-form-item">
	      <label class="layui-form-label">详细地址</label>
	      <div class="layui-input-block">
	        <textarea placeholder="请输出详细地址" name="unitAddress" class="layui-textarea" maxlength="100"></textarea>
	      </div>
	    </div>
	    
	    <div class="layui-form-item">
	      <label class="layui-form-label">单位来源</label>
	      <div class="layui-input-block">
		      <select id="dataUnitFromId" name="dataUnitFromId">
		        <option value="">请选择单位来源</option>
		      </select>
	      </div>
	    </div>
	    
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="insert">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	  </form>
</div>

<div id="updateDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="" lay-filter="updateFilter">
		<input type="hidden" name="id">
		<div class="layui-form-item">
	      <label class="layui-form-label">单位名称<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="unitName" lay-verify="required|unitName" placeholder="请输入单位名称" maxlength="50" autocomplete="off" class="layui-input">
	      </div>
	    </div>
		
		<label class="layui-form-label">选择地区<span class="required-sign">☀</span></label>
	    <div class="layui-input-inline">
	      <select id="u_unitProvinceId" name="unitProvinceId" lay-verify="required" lay-filter="u_unitProvinceId">
	        <option value="">请选择省</option>
	        
	      </select>
	    </div>
	    <div class="layui-input-inline">
	      <select id="u_unitCityId" name="unitCityId" lay-verify="required" lay-filter="u_unitCityId">
	        <option value="">请选择市</option>
	        
	      </select>
	    </div>
	    <div class="layui-input-inline">
	      <select id="u_unitDistrictId" name="unitDistrictId" lay-verify="required">
	        <option value="">请选择县/区</option>
	        
	      </select>
	    </div>  
		
		<div class="layui-form-item">
		   <label class="layui-form-label">单位类型<span class="required-sign">☀</span></label>
		   <div class="layui-input-block">
		   		<input type="radio" name="unitType" value="3" title="个体门诊" checked>
		     	<input type="radio" name="unitType" value="1" title="医院" >
		   		<input type="radio" name="unitType" value="2" title="连锁门诊">
		   		<input type="radio" name="unitType" value="4" title="工厂">
		   </div>
		 </div>

	    <div class="layui-form-item">
	      <label class="layui-form-label">详细地址</label>
	      <div class="layui-input-block">
	        <textarea placeholder="请输出详细地址" name="unitAddress" class="layui-textarea" maxlength="100"></textarea>
	      </div>
	    </div>
	    
	    <div class="layui-form-item">
	      <label class="layui-form-label">单位来源</label>
	      <div class="layui-input-block">
		      <select id="u_dataUnitFromId" name="dataUnitFromId">
		        <option value="">请选择单位来源</option>
		      </select>
	      </div>
	    </div>
	    
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="update">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	  </form>
</div>
<!-- 设置工厂 -->
<div id="userFactoryDiv" style="display: none;margin: 0 auto;text-align:center;padding: 50px;">
	<div id="factoryList"></div>
</div>
<!-- 优惠券 -->
<div id="discountDiv" style="display: none;">
	<!-- 数据表格 -->
	<table class="layui-hide" id="discountUnitTable" lay-filter="discountUnitTableFilter"></table>

	<script type="text/html" id="discountUnitTableToolbar">
  		<div class="layui-btn-container">
			<button class="layui-btn layui-btn-sm" lay-event="dscInsert">新增</button>
    		<button class="layui-btn layui-btn-sm" lay-event="dscDelete">删除</button>
  		</div>
	</script>
	
</div>
<div id="discountUnitInsertDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="">
		<input type="hidden" name="sysUnitId" class="sysUnitId">
		<div class="layui-form-item">
			<label class="layui-form-label">优惠券<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <select id="dataDiscountId" name="dataDiscountId" lay-verify="required" lay-search>
		        <option value="">请选择优惠券</option>
		        
		      </select>
		    </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">数量<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="num" lay-verify="required"  placeholder="请输入5位非0正整数" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"  maxlength="5" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">到期时间<span class="required-sign">☀</span></label>
	      <div class="layui-input-block">
	        <input type="text" name="endTime" id="endTime" lay-verify="required" autocomplete="off" placeholder="优惠券到期时间" class="layui-input">
	      </div>
	    </div>
	    
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="dscInsertBtn">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	  </form>
</div>
	
<div id="discountUnitUpdateDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="" lay-filter="updateDscFilter">
		<input type="hidden" name="id">
		<input type="hidden" name="sysUnitId" class="sysUnitId">
		<input type="hidden" name="dataDiscountId">
		<div class="layui-form-item">
		<label class="layui-form-label">优惠券<span class="required-sign">☀</span></label>
	    <div class="layui-input-inline">
	      <input type="text" id="discountName"  autocomplete="off" class="layui-input layui-disabled" disabled>
	    </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">数量<span class="required-sign">☀</span></label>
	      <div class="layui-input-inline">
	        <input type="text" name="num" lay-verify="required" placeholder="请输入5位非0正整数" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"  maxlength="5" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    <div class="layui-form-item">
	      <label class="layui-form-label">到期时间<span class="required-sign">☀</span></label>
	      <div class="layui-input-inline">
	        <input type="text" name="endTime" id="endTime2" lay-verify="required" autocomplete="off" placeholder="优惠券到期时间" class="layui-input">
	      </div>
	    </div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="dscUpdateBtn">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	  </form>
	</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/user/sysUnit.js?v=<%=version %>"></script>
</body>