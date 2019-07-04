package com.yzy.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.DefaultParserFeedback;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import sun.misc.BASE64Encoder;

/**
 * 将js、css、图片合并到html 导出
 * 调用参考 main方法
 */

@SuppressWarnings("restriction")
public class JsCssImgInHtml {

    private URL strWeb = null;          //网址
    private String strEncoding = null;  //编码
    private String strText = null;      //网页文本
    private String strFileName = null;  //导出路径

    public JsCssImgInHtml(String strText, String strUrl, String strEncoding, String strFileName) {
        try {
            strWeb = new URL(strUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        this.strText = strText;
        this.strEncoding = strEncoding;
        this.strFileName = strFileName;
    }

    // 将InputStream转换成按字符编码的String  
    public static String InputStreamTOString(InputStream in, String encoding)  throws Exception {  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[1024 * 1000];  
        int count = -1;  
        while ((count = in.read(data, 0, 1024 * 1000)) != -1) {
            outStream.write(data, 0, count);  
        }
        data = null;  
        return new String(outStream.toByteArray(), encoding);  
    }  
    // 将InputStream转换成byte数组  
    public static byte[] InputStreamTOByte(InputStream in)  throws Exception {  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[1024 * 1000];  
        int count = -1;  
        while ((count = in.read(data, 0, 1024 * 1000)) != -1) {
            outStream.write(data, 0, count);  
        }
        data = null;  
        return outStream.toByteArray();  
    }  
    //根据链接获取html源代码
    public static String getHtmlText(String strUrl, String strEncoding) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            return InputStreamTOString(in, strEncoding);
        } catch (Exception e) {
            return "";
        }
    }

    //相对路径转绝对路径
    public static String makeAbsoluteURL(URL strWeb, String innerURL) {
    	if (StringUtils.isBlank(innerURL) || innerURL.indexOf("data:image/png;base64,")!=-1) {
			return innerURL;
		}
        // 去除后缀
        int pos = innerURL.indexOf("?");
        if (pos != -1) {
            innerURL = innerURL.substring(0, pos);
        }
        if (innerURL != null && innerURL.toLowerCase().indexOf("http") == 0) {
            return innerURL;
        }
        URL linkUri = null;
        try {
            linkUri = new URL(strWeb, innerURL);
        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;
        }
        String absURL = linkUri.toString();
        absURL = replace(absURL, "../", "");
        absURL = replace(absURL, "./", "");
        return absURL;
    }

    private Parser createParser(String inputHTML) {
        Lexer mLexer = new Lexer(new Page(inputHTML));
        return new Parser(mLexer, new DefaultParserFeedback(DefaultParserFeedback.QUIET));
    }

    //导出文件
    private void outPutHtmlFile() {
        try {
            File file = new File(strFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file),strEncoding);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(strText);
            bw.newLine();//换行
            bw.flush(); 
            osw.close();
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取页面的js链接
    private void extractAllScriptNodes(NodeList nodes, HashMap<String, String> urlMap) {
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("script"), true);
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String src = tag.getAttribute("src");
            if (src != null && src.length() > 0) {
                String innerURL = src;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                String jsHtml = filtered.elementAt(i).toHtml();
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, jsHtml);
                }
            }
        }
    }
    //js代码写入html
    @SuppressWarnings("rawtypes")
	private void jsInHtml(HashMap<String, String> urlMap) {
        String key = null;
        String val = null;
        for (Iterator iter = urlMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            key = (String) entry.getKey(); //绝对路径
            val = (String) entry.getValue(); //html页面中引入的js标签
            StringBuilder sb = new StringBuilder();
            sb.append("<script>\r\n").append(getHtmlText(key, strEncoding)).append("\r\n</script>");//代码头尾添加标签
            strText = replace(strText, val, sb.toString());
        }
    }

    //获取页面的css链接
    private void extractAllCssNodes(NodeList nodes, HashMap<String, String> urlMap) {
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("link"), true);
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String type = (tag.getAttribute("type"));
            String rel = (tag.getAttribute("rel"));
            String href = tag.getAttribute("href");
            boolean isCssFile = false;
            if (rel != null) {
                isCssFile = rel.indexOf("stylesheet") != -1;
            } else if (type != null) {
                isCssFile |= type.indexOf("text/css") != -1;
            }
            if (isCssFile && href != null && href.length() > 0) {
                String innerURL = href;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                String cssHtml = filtered.elementAt(i).toHtml();
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, cssHtml);
                }
            }
        }
    }
    //css代码写入html
    @SuppressWarnings("rawtypes")
	private void cssInHtml(HashMap<String, String> urlMap) {
        String key = null;
        String val = null;
        for (Iterator iter = urlMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            key = (String) entry.getKey(); //绝对路径
            val = (String) entry.getValue(); //html页面中引入的css标签
            StringBuilder sb = new StringBuilder();
            sb.append("<style type=\"text/css\">\r\n").append(getHtmlText(key, strEncoding)).append("\r\n</style>");//代码头尾添加标签
            strText = replace(strText, val, sb.toString());
        }
    }
    //获取网页包含的图像链接,并将图片的base64编码写入html
    private void extractAllImageNodes(NodeList nodes) {
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("IMG"), true);
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String src = tag.getAttribute("src");
            if (src != null && src.length() > 0) {
                String oldImgHtml = tag.toHtml();
                String innerURL = src;
                if (StringUtils.isBlank(innerURL) || innerURL.indexOf("data:image/png;base64,")!=-1) {
                	tag.setAttribute("src", innerURL);
                } else {
                	String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                	String imgBase64 = getImg(absoluteURL);
                	StringBuilder sb = new StringBuilder();
                	sb.append("data:image/jpeg;base64,");
                	sb.append(imgBase64);
                	tag.setAttribute("src", sb.toString());
                }
                String imgHtml = tag.toHtml();
                strText = replace(strText, oldImgHtml, imgHtml);
            }
        }
    }
    //根据链接获得图片的base64编码
    public static String getImg(String imgUrl) {
    	if (StringUtils.isBlank(imgUrl) || imgUrl.indexOf("data:image/png;base64,")!=-1) {
			return imgUrl;
		}
        URL url = null;  
        InputStream in = null; 
        HttpURLConnection httpUrl = null;
        try{  
            url = new URL(imgUrl);  
            httpUrl = (HttpURLConnection) url.openConnection();  
            in = httpUrl.getInputStream();            
            byte[] data = InputStreamTOByte(in);  // 读取图片字节数组  
            /* *这种方法不能用，会返回空字符串
                data = new byte[in.available()];  
                in.read(data);  
                in.close();
            */  
            in.close();
            // 对字节数组Base64编码  
            BASE64Encoder encoder = new BASE64Encoder();  
            return encoder.encode(data);// 返回Base64编码过的字节数组字符串  
        }catch (Exception e) {  
            e.printStackTrace(); 
            return "get image error";
        }
    }

    //主应用
    public boolean compile() {
        if (strWeb == null || strText == null || strFileName == null || strEncoding == null) {
            return false;
        }
        HashMap<String, String> urlMap = new HashMap<String, String>();
        NodeList nodes = new NodeList();
        try {
            Parser parser = createParser(strText);
            parser.setEncoding(strEncoding);
            nodes = parser.parse(null);
        } catch (ParserException e) {
            e.printStackTrace();
        }

        extractAllScriptNodes(nodes, urlMap); //1、获得所有js引用的相对路径和绝对路径，用HashMap存储（key-绝对路径，value-要替换的js标签 如（<script src="../common/server.js"></script>））
        jsInHtml(urlMap);                     //引入js代码到html中
        urlMap.clear();                       //用完后将HashMap清空

        extractAllCssNodes(nodes, urlMap);    //2、将所有css的引用改成服务器地址
        cssInHtml(urlMap);                    //引入css代码到html中
        urlMap.clear();                       //用完后将HashMap清空

        extractAllImageNodes(nodes);          //3、将所有img的引用改成服务器地址,引入图片的base64编码写到html中

        outPutHtmlFile();                     //导出html文件
        return true;
    }

    public static String replace(String s, String s1, String s2) {
        return s.replace(s1, s2);
    }

    public static void main(String[] args) {
        long startMili=System.currentTimeMillis();// 开始时间

        //System.out.print(getImg("http://localhost:8080/chartsshow_engine/service/charting/resource/image?path=/book/icon/3f8c7212848dfb87993de1d31bce57d9.png"));
        String strUrl = "http://localhost:63342/chartsshow_web/p.html?s=438ee9f32adda180&web_mode";
        String strEncoding = "utf-8";
        String strText1 = getHtmlText(strUrl, strEncoding);
        JsCssImgInHtml jciih = new JsCssImgInHtml(strText1, strUrl, strEncoding, "d:\\index.html");
        if(jciih.compile()) {
            System.out.println("导出成功");
        };

        long endMili=System.currentTimeMillis(); // 结束时间
        System.out.println("总耗时为："+(endMili-startMili)/1000+"秒");
    }

}

