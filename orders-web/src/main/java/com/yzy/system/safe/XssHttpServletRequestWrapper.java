package com.yzy.system.safe;

import java.util.Map;
import java.util.regex.Pattern;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper; 

/**
 * @author 王俊强
 * 时        间： 2018年6月27日 下午2:58:01
 * 功能描述 ： 这个类的目的是对用户发送的请求进行包装，把request中包含XSS代码进行过滤
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

    HttpServletRequest orgRequest = null;  

    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);
    }  
    /** 
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
     */  
    @Override  
    public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }
    @Override
    public String[] getParameterValues(String name) {
        String[] value = super.getParameterValues(name);
        if(value != null){
            for (int i = 0; i < value.length; i++) {
                value[i] = xssEncode(value[i]);
            }
        }
        return value;
    }
    @Override
    public Map getParameterMap() {
        // TODO Auto-generated method stub
        return super.getParameterMap();
    }

    /** 
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取 
     * getHeaderNames 也可能需要覆盖
     * 这一段代码在一开始没有注释掉导致出现406错误，原因是406错误是HTTP协议状态码的一种，
     * 表示无法使用请求的内容特性来响应请求的网页。一般是指客户端浏览器不接受所请求页面的 MIME 类型。 
     *  
    @Override  
    public String getHeader(String name) {  

        String value = super.getHeader(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
    **/


    /** 
     * 将容易引起xss漏洞的半角字符直接替换成全角字符 在保证不删除数据的情况下保存
     * @param s 
     * @return 过滤后的值
     */  
    public static String xssEncode(String value) {  
         if (value == null || value.isEmpty()) {  
            return value;  
        }  
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        value = value.replaceAll("","");  
        value = value.replaceAll("\'", "");
        // Avoid anything between script tags  
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",  
                Pattern.CASE_INSENSITIVE);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Avoid anything in a src='...' type of expression  
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");  
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Remove any lonesome </script> tag  
        scriptPattern = Pattern.compile("</script>",  
                Pattern.CASE_INSENSITIVE);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Remove any lonesome <script ...> tag  
        scriptPattern = Pattern.compile("<script(.*?)>",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Avoid eval(...) expressions  
        scriptPattern = Pattern.compile("eval\\((.*?)\\)",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Avoid expression(...) expressions  
        scriptPattern = Pattern.compile("expression\\((.*?)\\)",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Avoid javascript:... expressions  
        scriptPattern = Pattern.compile("javascript:",  
                Pattern.CASE_INSENSITIVE);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Avoid vbscript:... expressions  
        scriptPattern = Pattern.compile("vbscript:",  
                Pattern.CASE_INSENSITIVE);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Avoid onload= expressions  
        scriptPattern = Pattern.compile("onload(.*?)=",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");  

        scriptPattern = Pattern.compile("<iframe>(.*?)</iframe>",  
                Pattern.CASE_INSENSITIVE);  
        value = scriptPattern.matcher(value).replaceAll("");  

        scriptPattern = Pattern.compile("</iframe>",  
                Pattern.CASE_INSENSITIVE);  
        value = scriptPattern.matcher(value).replaceAll("");  
        // Remove any lonesome <script ...> tag  
        scriptPattern = Pattern.compile("<iframe(.*?)>",  
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE  
                        | Pattern.DOTALL);  
        value = scriptPattern.matcher(value).replaceAll("");
        return value;
    } 
}
