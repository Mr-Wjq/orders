package com.yzy.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.dao.BaseProductMapper;
import com.yzy.dao.DataOrdersMapper;
import com.yzy.dao.DataProductPriceMapper;
import com.yzy.entity.BaseProduct;
import com.yzy.entity.DataOrders;
import com.yzy.entity.DataProductPrice;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.entity.vo.OrdersVO;
import com.yzy.entity.vo.UserUnitVO;
import com.yzy.service.OrdersService;
import com.yzy.service.SysUnitService;
import com.yzy.utils.DateUtils;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.PropertiesUtils;
import com.yzy.utils.Result;
import com.yzy.utils.StringUtil;
import com.yzy.utils.idworker.Sid;
import com.yzy.utils.pdf.Snippet;

import tk.mybatis.mapper.entity.Example;

@Service
public class OrdersServiceImpl implements OrdersService {

	private static Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	private static String baseFilePath = "";
	private static String templatePath = "";
	static {
		templatePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/template";
		try {
			Properties properties = PropertiesUtils.getProperties("env.properties");
		    baseFilePath = properties.getProperty("baseFilePath");
		} catch (IOException e) {
			logger.error("读取properties出错",e);
		}
	}

	@Autowired
	private DataOrdersMapper dataOrdersMapper;
	@Autowired
	private BaseProductMapper baseProductMapper;
	@Autowired
	private DataProductPriceMapper dataProductPriceMapper; 
	@Autowired
	private SysUnitService sysUnitService; 
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result createOrders(DataOrders dataOrders,MultipartFile accessoryFile) {
		
		if(accessoryFile!=null) {
			
			if(accessoryFile.getSize()>52428800) {
				return Result.error("请上传小于50M的文件");
			}
			
			String accessoryUrl = baseFilePath+"/accessory/"+DateUtils.getForDay()+"/";
			String accessoryName = StringUtil.getUUID()+",,"+accessoryFile.getOriginalFilename();
			File file = new File(accessoryUrl,accessoryName);
			//判断文件夹是否存在
			if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
				// 创建父文件夹
				file.getParentFile().mkdirs();
		    }
			try {
				accessoryFile.transferTo(file);
			} catch (Exception e) {
				logger.error("上传订单附件出错",e);
				return Result.error("上传扫描文件出错,请稍后重试!");
			}
			dataOrders.setAccessoryName(accessoryName);
			dataOrders.setAccessoryUrl(accessoryUrl);
		}
		
		try {
			Example example = new Example(DataProductPrice.class);
			example.createCriteria().andEqualTo("factoryId", dataOrders.getReceiveUnitId()).andEqualTo("baseProductId", dataOrders.getBaseProductId());
			DataProductPrice dataProductPrice = dataProductPriceMapper.selectOneByExample(example);
			dataOrders.setPrice(dataProductPrice.getPrice());
			
			dataOrdersMapper.insertSelective(dataOrders);
		} catch (Exception e) {
			logger.error("创建订单出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			String accessoryName = dataOrders.getAccessoryName();
			String accessoryUrl = dataOrders.getAccessoryUrl();
			File file = new File(accessoryUrl+accessoryName);
			if(file.exists()) {
				file.delete();
			}
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success(dataOrders.getId());
	}

	@Override
	public LayuiTable selectDoctorOrders(int page, int limit, String ordersNum, String patientName, String receiveUnitName,
			Integer status, String createTime) {
 		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		List<SysUnit> unitList = sysUnitService.getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }
        Long createUserId = null;
        if(loginInfo.getRoleId() != 2 && loginInfo.getRoleId() == 3) {
        	createUserId = loginInfo.getId();
        }
        PageHelper.startPage(page, limit);
        List<OrdersVO> list = dataOrdersMapper.selectDoctorOrders(unitIdList, ordersNum,  patientName,createUserId,  receiveUnitName, status,  createTime);
        PageInfo<OrdersVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	public LayuiTable selectFactoryOrders(int page, int limit, String ordersNum, String patientName,
			String createUnitName, Integer status, String createTime) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		List<SysUnit> unitList = sysUnitService.getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }
     /*   Long receiveUserId = null;
        if(loginInfo.getRoleId() != 4 && loginInfo.getRoleId() == 5) {
        	receiveUserId = loginInfo.getId();
        }*/
        PageHelper.startPage(page, limit);
        List<OrdersVO> list = dataOrdersMapper.selectFactoryOrders(unitIdList, ordersNum,  patientName,null,  createUnitName, status,  createTime);
        PageInfo<OrdersVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}
	
	@Override
	public OrdersVO selectOrdersById(Long ordersId) {
		
		OrdersVO ordersVO = dataOrdersMapper.selectOrdersById(ordersId);
		return ordersVO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateOrders(DataOrders dataOrders, MultipartFile accessoryFile) {
		
		DataOrders dataOrders2 = dataOrdersMapper.selectByPrimaryKey(dataOrders.getId());
		
		if(accessoryFile!=null) {
			if(accessoryFile.getSize()>52428800) {
				return Result.error("请上传小于50M的文件");
			}
			
			String accessoryUrl = baseFilePath+"/accessory/"+DateUtils.getForDay()+"/";
			String accessoryName = StringUtil.getUUID()+",,"+accessoryFile.getOriginalFilename();
			File file = new File(accessoryUrl,accessoryName);
			//判断文件夹是否存在
			if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
				// 创建父文件夹
				file.getParentFile().mkdirs();
		    }
			try {
				accessoryFile.transferTo(file);
			} catch (Exception e) {
				logger.error("上传订单附件出错",e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("上传扫描文件出错,请稍后重试!");
			}
			dataOrders.setAccessoryName(accessoryName);
			dataOrders.setAccessoryUrl(accessoryUrl);
		}else if(!dataOrders.getOrdersAccessory().startsWith("1") ){
			dataOrders.setAccessoryName("");
			dataOrders.setAccessoryUrl("");
		}
		
		try {
			Example example = new Example(DataProductPrice.class);
			example.createCriteria().andEqualTo("factoryId", dataOrders.getReceiveUnitId()).andEqualTo("baseProductId", dataOrders.getBaseProductId());
			DataProductPrice dataProductPrice = dataProductPriceMapper.selectOneByExample(example);
			dataOrders.setPrice(dataProductPrice.getPrice());
			
			dataOrdersMapper.updateByPrimaryKeySelective(dataOrders);
		} catch (Exception e) {
			logger.error("修改订单出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			if(accessoryFile!=null) {
				String accessoryName = dataOrders.getAccessoryName();
				String accessoryUrl = dataOrders.getAccessoryUrl();
				File file = new File(accessoryUrl+accessoryName);
				if(file.exists()) {
					file.delete();
				}
			}
			return Result.error("系统繁忙请稍后重试");
		}
		
		if(accessoryFile!=null || dataOrders.getOrdersAccessory().startsWith("1")) {
			if(StringUtils.isNotBlank(dataOrders2.getAccessoryName())) {
				String accessoryName = dataOrders2.getAccessoryName();
				String accessoryUrl = dataOrders2.getAccessoryUrl();
				File file = new File(accessoryUrl+accessoryName);
				if(file.exists()) {
					file.delete();
				}
			}
		}
		return Result.success(dataOrders.getId());
	}

	@Override
	public Result ordersDetails(Long ordersId) {
		OrdersVO ordersVO = dataOrdersMapper.selectOrdersById(ordersId);
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("createUnit", ordersVO.getCreateUnitName());
        params.put("createUser", ordersVO.getCreateUserName());
        params.put("patientName", ordersVO.getPatientName());
        params.put("patientAge", ordersVO.getPatientAge());
        params.put("patientSex", ordersVO.getPatientSex());
        params.put("patientType", ordersVO.getPatientType() == 1 ? "初诊" : "复诊");
        String accessoryName = ordersVO.getAccessoryName();
        if(StringUtils.isNotBlank(accessoryName)) {
        	int indexOf = accessoryName.indexOf(",,");
        	accessoryName = "  扫描文件: "+accessoryName.substring(indexOf+2);
        }else {
        	accessoryName = "";
        }
        String ordersAccessory = ordersVO.getOrdersAccessory();
        String accessoryType = "";
        if(StringUtils.isNotBlank(ordersAccessory)) {
        	String[] arry = ordersAccessory.split(",");
        	for (int i = 0; i < arry.length; i++) {
        		 switch (arry[i]) {
        			case "1":
        				accessoryType += "扫描数据";
        				break;
        			case "2":
        				accessoryType += ",颌架";
        				break;
        			case "3":
        				accessoryType += ",咬蜡";
        				break;
        			case "4":
        				accessoryType += ",托盘";
        				break;
        			case "5":
        				accessoryType += ",照片";
        				break;
        			case "6":
        				accessoryType += ",旧牙";
        				break;
        			case "7":
        				accessoryType += ",参考摸";
        				break;
        			case "8":
        				accessoryType += ",比色板";
        				break;
        			}
			}
        }
        /*params.put("ordersAccessory", accessoryType+" "+accessoryName);*/
        params.put("ordersAccessory", accessoryType);
        params.put("toothPosition1", StringUtils.isNotBlank(ordersVO.getToothPosition1()) ? ordersVO.getToothPosition1() : "");
        params.put("toothPosition2", StringUtils.isNotBlank(ordersVO.getToothPosition2()) ? ordersVO.getToothPosition2() : "");
        params.put("toothPosition3", StringUtils.isNotBlank(ordersVO.getToothPosition3()) ? ordersVO.getToothPosition3() : "");
        params.put("toothPosition4", StringUtils.isNotBlank(ordersVO.getToothPosition4()) ? ordersVO.getToothPosition4() : "");
        params.put("color", ordersVO.getColor());
        params.put("cureName", ordersVO.getBaseCureName());
        
        BaseProduct baseProduct = baseProductMapper.selectByPrimaryKey(ordersVO.getBaseProductId());
        StringBuffer buffer = new StringBuffer();
		if(StringUtils.isNotBlank(baseProduct.getTextureName())) {
			buffer.append("材质:"+baseProduct.getTextureName());
		}else {
			buffer.append("材质:无");
		}
		if(StringUtils.isNotBlank(baseProduct.getBrandName())) {
			buffer.append("--品牌:"+baseProduct.getBrandName());
		}else {
			buffer.append("--品牌:无");
		}
		if(StringUtils.isNotBlank(baseProduct.getProductName())) {
			buffer.append("--产品名称:"+baseProduct.getProductName());
		}else {
			buffer.append("--产品名称:无");
		}
        params.put("productName",String.valueOf(buffer));
        params.put("factoryName", ordersVO.getReceiveUnitName());
        params.put("remarks", ordersVO.getRemarks());
        
        String pdfTemplatePath = templatePath+"/orders.pdf";
        String newPDFPath = baseFilePath+"/pdf/"+ordersVO.getOrdersNum()+"_加工单.pdf";
        boolean b = Snippet.fillTemplate(pdfTemplatePath, newPDFPath, params);
        if(b) {
        	HashMap<String,Object> hashMap = new HashMap<String,Object>();
        	hashMap.put("exists", StringUtils.isNotBlank(accessoryName) ? true : false);
        	hashMap.put("pdfPath",ordersVO.getOrdersNum()+"_加工单.pdf");
        	return Result.success(hashMap);
        }
		return Result.error("系统繁忙请稍后重试!");
	}
	public static void main(String[] args) {
		Properties props = System.getProperties();
		System.out.println(props.getProperty("os.name").toLowerCase().startsWith("win")?"Windows":"linux");
	}

	@Override
	public void downloadAccessory(Long ordersId, HttpServletResponse response) {
		DataOrders dataOrders = dataOrdersMapper.selectByPrimaryKey(ordersId);
		try {
			String accessoryName = dataOrders.getAccessoryName();
			String downloadFile = dataOrders.getAccessoryUrl()+dataOrders.getAccessoryName();
			File file = new File(downloadFile);
			String fileName = "";
			if(file.exists()) {
	        	int indexOf = dataOrders.getAccessoryName().indexOf(",,");
	        	fileName = accessoryName.substring(indexOf+2);
			}
		    InputStream fis = new BufferedInputStream(new FileInputStream(downloadFile));
		    response.reset();
		    // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("utf-8");  
		    response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
		    ServletOutputStream outputStream = response.getOutputStream();
		    OutputStream toClient = new BufferedOutputStream(outputStream);
		    
		    byte[] buffer = new byte[1024];
		    int i =-1;
		    while ((i = fis.read(buffer)) != -1) {   //不能一次性读完，大文件会内存溢出（不能直接fis.read(buffer);）
		    	toClient.write(buffer, 0, i);  
                
            }
		    fis.close();
		    toClient.flush();
		    toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script>");   
				out.print("alert(\"not find the file\")");   
				out.print("</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}   
            logger.error("下载附件出错",e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateOrdersStatus(DataOrders dataOrders) {
		try {
			dataOrdersMapper.updateByPrimaryKeySelective(dataOrders);
		} catch (Exception e) {
			logger.error("修改订单状态出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试!"); 
		}		
		return Result.success();
	}

	@Override
	public LayuiTable selectSystemOrders(int page, int limit, String ordersNum, String patientName,
			String createUnitName, String receiveUnitName, Integer status, String createTime) {
		Subject subject = SecurityUtils.getSubject();
		LoginInfo loginInfo = (LoginInfo) subject.getSession().getAttribute("loginInfo");   //当前登录用户
		List<SysUnit> unitList = sysUnitService.getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }
    
        PageHelper.startPage(page, limit);
        List<OrdersVO> list = dataOrdersMapper.selectSystemOrders(unitIdList, ordersNum,  patientName,createUnitName,  receiveUnitName, status,  createTime);
        PageInfo<OrdersVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

}
