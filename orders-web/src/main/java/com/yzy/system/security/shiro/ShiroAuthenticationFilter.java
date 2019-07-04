package com.yzy.system.security.shiro;

import com.google.gson.Gson;
import com.yzy.utils.PropertiesUtils;
import com.yzy.utils.ResponseCode;
import com.yzy.utils.Result;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 王俊强
 * @Description 登录过滤器
 */
public class ShiroAuthenticationFilter extends PassThruAuthenticationFilter {
    private static Logger log = LoggerFactory.getLogger(ShiroAuthenticationFilter.class);
    static String basePath = "";
    
    static {
    	try {
			Properties properties = PropertiesUtils.getProperties("env.properties");
			basePath = properties.getProperty("basePath");
		} catch (IOException e) {
			log.error("读取env.properties异常",e);
		}
    }
    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse res) throws Exception {
		
        if (isLoginRequest(req, res)) {
            return true;
        } else {
        	HttpServletResponse response = (HttpServletResponse)res;
        	HttpServletRequest request = (HttpServletRequest)req;
            saveRequest(req);
            saveRequest(request);
            if (StringUtils.isNotBlank(request.getHeader("Accept")) && request.getHeader("Accept").contains("application/json")) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.setHeader("sessionstatus", "timeout");
                Result result = new Result(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                response.getWriter().append(new Gson().toJson(result));
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                response.sendRedirect(basePath+"?timeOut=1");
            }
            return false;
        }
    }
}
