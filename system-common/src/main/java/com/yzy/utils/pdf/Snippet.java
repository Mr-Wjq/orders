package com.yzy.utils.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;  
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;  
import com.itextpdf.text.pdf.PdfImportedPage;  
import com.itextpdf.text.pdf.PdfReader;  
import com.itextpdf.text.pdf.PdfStamper;

public class Snippet {  
	private static Logger logger = LoggerFactory.getLogger(Snippet.class);
    // 利用模板生成pdf  
    public static boolean fillTemplate(String templatePath,String newPDFPath,Map<String,Object> params) {  
        
        PdfReader reader;  
        FileOutputStream out;  
        ByteArrayOutputStream bos;  
        PdfStamper stamper;  
        try {
        	File file = new File(newPDFPath);
        	//判断文件夹是否存在
			if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
				// 创建父文件夹
				file.getParentFile().mkdirs();
		    }
            out = new FileOutputStream(newPDFPath);// 输出流  
            reader = new PdfReader(templatePath);// 读取pdf模板  
            bos = new ByteArrayOutputStream();  
            stamper = new PdfStamper(reader, bos);  
            AcroFields form = stamper.getAcroFields();  
  
            /*BaseFont bf = BaseFont.createFont(Snippet.class.getResource("/")+"com/yzy/utils/font/SIMSUN.TTC,1",
            		BaseFont.IDENTITY_H, BaseFont.EMBEDDED);*/
            /*BaseFont bf = BaseFont.createFont(Snippet.class.getResource("/")+"com/yzy/utils/font/STKAITI.TTF",
            		BaseFont.IDENTITY_H, BaseFont.EMBEDDED);*/
			BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
     		Font FontChinese = new Font(bf, 12, Font.NORMAL);
            /*ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();   
            fontList.add(bf);
            form.setSubstitutionFonts(fontList);*/
            form.addSubstitutionFont(bf);
            Set<Entry<String, Object>> entrys = params.entrySet();
    		for(Entry<String, Object> entry:entrys){
    			form.setField(entry.getKey(),String.valueOf(entry.getValue()));
    		}
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true  
            stamper.close();  
            
            
            /*out.write(bos.toByteArray());  
            out.close(); */ 
            Document doc = new Document();  
            PdfCopy copy = new PdfCopy(doc, out);  
            doc.open();  
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);  
            copy.addPage(importPage);  
            doc.close();  
  
        } catch (IOException e) {  
        	logger.error("生产pdf出错",e);
        	return false;
        } catch (DocumentException e) {  
        	logger.error("生产pdf出错",e);
            return false;
        }  
        return true;
    }  
}  