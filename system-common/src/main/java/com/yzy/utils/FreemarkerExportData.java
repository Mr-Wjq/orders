package com.yzy.utils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerExportData {
	
	public void exportData(Map<String,Object> dataMap,String fileName,String ftlName,HttpServletRequest request,HttpServletResponse response) throws IOException, TemplateException {
		//Configuration用于读取ftl文件  
	      Configuration configuration = new Configuration();  
	      configuration.setDefaultEncoding("utf-8"); 
	      /*以下是两种指定ftl文件所在目录路径的方式, 注意这两种方式都是 
	       * 指定ftl文件所在目录的路径,而不是ftl文件的路径 
	       */  
	      //指定路径的第一种方式(根据某个类的相对路径指定)  
	      //configuration.setClassForTemplateLoading(this.getClass(),"");  
	        
	      //指定路径的第二种方式,我的路径是C:/a.ftl
	      String webrootPath = request.getSession(true).getServletContext().getRealPath("/freemarker");
	      configuration.setDirectoryForTemplateLoading(new File(webrootPath));  
	        
	        
	      // 输出文档路径及名称  
	     //File outFile = new File("D:/test5.doc");  
	    // OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outFile), "utf-8");
	     
	      //以utf-8的编码读取ftl文件  
	     Template t =  configuration.getTemplate(ftlName,"utf-8");  
	     
	     //浏览器弹框下载到本地
	     response.setContentType("application/x-msdownload");
     	 response.setCharacterEncoding("utf-8");  
         response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
         Writer writer = response.getWriter();
         /*Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);*/  
	     //Writer out = new BufferedWriter(outputStreamWriter,10240);  
	        t.process(dataMap, writer);  
	        writer.flush();
	        writer.close();
	}
	
/*	public static void main(String[] args) {
		try {
			// 要填充的数据, 注意map的key要和word中${xxx}的xxx一致  
		      Map<String,Object> dataMap = new HashMap<String,Object>();  
		      dataMap.put("time", "2015-05-05 12:24:26");  
		      dataMap.put("taskNum", "20");  
		      dataMap.put("cuangai", "50");
			  dataMap.put("anlian", "11");
			  dataMap.put("muma", "12");
			  dataMap.put("diaoyu", "13");
			  dataMap.put("webshell", "14");
			  dataMap.put("loudong", "15");
		      dataMap.put("keyongxing", "16");
		      dataMap.put("minganci", "17");
			  dataMap.put("ruokouling", "18");
			  dataMap.put("taskId", "18");
			  dataMap.put("taskName", "静态木马自动通报任务201709071502666");
			  ArrayList<BulletinTask> bulletionTaskList = new ArrayList<BulletinTask>();  
			  BulletinTask bulletinTask = new BulletinTask();
			  BulletinTask bulletinTask2 = new BulletinTask();
			  bulletinTask.setId("1");
			  bulletinTask.setTaskName("静态木马自动通报任务201709071502666");
			  bulletinTask.setBulletinObject("事件通报");
			  bulletinTask.setEventGrade(1);
			  bulletinTask.setBulletinType("突发事件");
			  bulletinTask.setBulletinWay("手动");
			  bulletinTask.setTaskTime("7");
			  bulletinTask.setReleaseTime("2017-09-07");
			  bulletinTask.setState(1);
			  bulletinTask.setLoginName("1111111");
			  bulletinTask.setDisposeTime("2017-05-06 23:23:20");
			  bulletinTask.setBulletinNum(2);
			  bulletinTask2.setId("2");
			  bulletinTask2.setTaskName("http自动通报任务201709071502666");
			  bulletinTask2.setBulletinObject("事件通报");
			  bulletinTask2.setEventGrade(1);
			  bulletinTask2.setBulletinType("突发事件");
			  bulletinTask2.setBulletinWay("手动");
			  bulletinTask2.setTaskTime("7");
			  bulletinTask2.setReleaseTime("2017-09-07");
			  bulletinTask2.setState(1);
			  bulletinTask2.setLoginName("1111111");
			  bulletinTask2.setDisposeTime("2017-05-06 23:23:20");
			  bulletinTask2.setBulletinNum(2);
			  bulletionTaskList.add(bulletinTask);
			  bulletionTaskList.add(bulletinTask2);
			  dataMap.put("bulletionTaskList", bulletionTaskList);
			  
			  ArrayList<TaskWebUnitVO> bulletionTaskDatails = new ArrayList<TaskWebUnitVO>();
			  TaskWebUnitVO bulletinTask3 = new TaskWebUnitVO();
			  bulletinTask3.setWebName("百度");
			  bulletinTask3.setWebUnitName("百度公司");
			  bulletinTask3.setWebUnitAddress("上地科技创业园");
			  bulletinTask3.setAreaCode("0452121");
			  bulletinTask3.setIndustryType("互联网");
			  bulletinTask3.setReceivedTime("2015-02-02");
			  bulletinTask3.setDisposeTime("2017-05-06 23:23:20");
			  bulletinTask3.setLoginName("1111111");
			  bulletinTask3.setDisposePrincipal("张三");
			  bulletinTask3.setDisposeState(2);
			  bulletinTask3.setBulletionEvents("篡改,暗链");
			  
		      TaskWebUnitVO bulletinTask4 =  bulletinTask3;
		      bulletionTaskDatails.add(bulletinTask3);
		      bulletionTaskDatails.add(bulletinTask4);
		      HashMap<String,Object> hashMap = new HashMap<String,Object>();
		      HashMap<String,Object> hashMap2 = new HashMap<String,Object>();
		      hashMap.put("taskId","1");
		      hashMap.put("taskName","静态木马自动通报任务201709071502666");
		      hashMap.put("bulletionTaskDatails", bulletionTaskDatails);
		      hashMap2.put("taskId","2");
		      hashMap2.put("taskName","静态木马自动通报任务201709071502667");
		      hashMap2.put("bulletionTaskDatails", bulletionTaskDatails);
		      ArrayList<Object> arrayList = new ArrayList<>();
		      arrayList.add(hashMap);
		      arrayList.add(hashMap2);
		      dataMap.put("bulletionTaskDatailsList", arrayList);
		      
		      
		      
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}*/
}
