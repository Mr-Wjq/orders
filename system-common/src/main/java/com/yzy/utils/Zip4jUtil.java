package com.yzy.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.log4j.Logger;

public class Zip4jUtil {

	private static Logger logger = Logger.getLogger(Zip4jUtil.class);
	
	// 初始化
	private static Zip4jUtil instance = new Zip4jUtil();
	
	// 异步处理
	public static synchronized Zip4jUtil getInstance() {
		return instance;
	}

	public static void main(String[] args) throws ZipException{
		
//		String srcFolderPath = "C:\\Users\\admo\\Desktop\\kkkkkk\\jiami\\unit_id_info54545475458asdgta2s4g01";
		
		List<String> srcFilePathList = new ArrayList<String>();
		srcFilePathList.add("C:\\Users\\admo\\Desktop\\kkkkkk\\jiami\\unit_id_info54545475458asdgta2s4g01\\data_self_check.txt");
		srcFilePathList.add("C:\\Users\\admo\\Desktop\\kkkkkk\\jiami\\unit_id_info54545475458asdgta2s4g01\\data_task_unit.txt");
		
		String compressPath = "C:\\Users\\admo\\Desktop\\kkkkkk\\jiami\\unit_id_info54545475458asdgta2s4g01\\unit_id_info54545475458asdgta2s4g01.zip";
		String decompressPath = "C:\\Users\\admo\\Desktop\\kkkkkk\\jiami\\jieya\\unit_id_info54545475458asdgta2s4g01";
		compressFileForZip(srcFilePathList, compressPath);
//		decompressForZip(compressPath, decompressPath);
		
	}
	
	/**
	 * 解压文件
	 * @param zipFilePath 解压目标文件的全路径
	 * @param decompressPath 将目标文件解压后所在的路径文件夹
	 * @throws ZipException 
	 * */
	@SuppressWarnings("unchecked")
	public static void decompressForZip(String zipFilePath, String decompressPath) throws ZipException {    
		long startTime = System.currentTimeMillis();
		ZipFile zipFile2 = new ZipFile(zipFilePath);
		
		zipFile2.setFileNameCharset("UTF8");//设置编码格式
		if (!zipFile2.isValidZipFile()) {
			throw new ZipException("文件不合法或不存在");
		}
		
		//检查是否需要密码
		if (zipFile2.isEncrypted()) {
			zipFile2.setPassword("&$L.(,nf_T/%IU-@#g=K@!%*$+A~`?");//输入密码
		}
		
		List<FileHeader> fileHeaderList = zipFile2.getFileHeaders();
		for (int i = 0; i < fileHeaderList.size(); i++) {
			FileHeader fileHeader = fileHeaderList.get(i);
			zipFile2.extractFile(fileHeader, decompressPath);
		}
		long endTime = System.currentTimeMillis();
		logger.info("******解压成功," + "耗时：" + (endTime - startTime) + "ms******");
    }
	
	
	/** 
     * 加密压缩多个文件
     * @param srcFilePathList 要压缩的多个文件的全路径
     * @param destFilePath 压缩文件存放全路径
     */  
    public static boolean compressFileForZip(List<String> srcFilePathList, String destFilePath) {
    	
    	long startTime = System.currentTimeMillis();
    	
    	boolean flg = false;
    	ZipFile zip;
		try {
			
			zip = new ZipFile(destFilePath);
			//要紧跟设置编码
			zip.setFileNameCharset("UTF8");
			ArrayList<File> list=new ArrayList<>();

			for(int i=0; i<srcFilePathList.size(); i++){
				list.add(new File(srcFilePathList.get(i)));
			}

			ZipParameters para = new ZipParameters();
			para.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			//设置密码：
			para.setEncryptFiles(true);
			//设置AES加密方式
			para.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			//必须设置长度
			para.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);  
			para.setPassword("&$L.(,nf_T/%IU-@#g=K@!%*$+A~`?");
			zip.addFiles(list, para);
			long endTime = System.currentTimeMillis();
			logger.info("******压缩成功," + "耗时：" + (endTime - startTime) + "ms******");
			flg = true;
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flg;
    }
    
    /** 
     * 不加密 压缩多个文件
     * @param srcFilePathList 要压缩的多个文件的全路径
     * @param destFilePath 压缩文件存放全路径
     */  
    public static boolean compressFileForOrdinaryZip(List<String> srcFilePathList, String destFilePath) {
    	
    	long startTime = System.currentTimeMillis();
    	
    	boolean flg = false;
    	ZipFile zip;
    	try {
    		
    		zip = new ZipFile(destFilePath);
    		//要紧跟设置编码
    		zip.setFileNameCharset("UTF8");
    		ArrayList<File> list=new ArrayList<>();
    		
    		for(int i=0; i<srcFilePathList.size(); i++){
    			list.add(new File(srcFilePathList.get(i)));
    		}
    		
    		ZipParameters para = new ZipParameters();
    		para.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
    		para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
    		//设置密码：
    		para.setEncryptFiles(false);
    		//设置AES加密方式
    		para.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
    		//必须设置长度
    		para.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);  
    		
    		zip.addFiles(list, para);
    		long endTime = System.currentTimeMillis();
    		logger.info("******压缩成功," + "耗时：" + (endTime - startTime) + "ms******");
    		flg = true;
    	} catch (ZipException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return flg;
    }

}
