package com.yzy.system.safe;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yzy.utils.StringUtil;

/**
 * @author 王俊强
 * 时        间： 2018年6月27日 下午3:17:03
 * 功能描述 ： 处理一些安全漏洞
 */
public class SafeFilter implements Filter{

    public void destroy() {
    }
    /**
     * 过滤器用来过滤的方法
     */
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
    	/**
    	 * 防止外部iframe嵌套
    	 * 
         * X-Frame-Options 响应头有三个可选的值：
         *DENY：页面不能被嵌入到任何iframe或frame中；
         *SAMEORIGIN：页面只能被本站页面嵌入到iframe或者frame中；
         *ALLOW-FROM：页面允许frame或frame加载。
         * */
    	HttpServletResponse res = (HttpServletResponse)response;
    	res.setHeader("X-Frame-Options", "SAMEORIGIN");
    	
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpSession session = req.getSession();
		String session_csrf_token = (String)session.getAttribute("csrf_token");
		if(StringUtils.isBlank(session_csrf_token)) {
			String uuid = StringUtil.getUUID();
			session.setAttribute("csrf_token", uuid);
		}
    	//包装request  防止xss sql注入
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}