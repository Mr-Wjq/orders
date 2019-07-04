package com.yzy.system.security.shiro;

import com.google.gson.Gson;
import com.yzy.utils.PropertiesUtils;
import com.yzy.utils.ResponseCode;
import com.yzy.utils.Result;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author 王俊强
 * @Description 权限过滤器 未启用
 */
public class ShiroAuthorizationFilter extends AuthorizationFilter {
    private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationFilter.class);
    static String basePath = "";
    
    static {
    	try {
			Properties properties = PropertiesUtils.getProperties("env.properties");
			basePath = properties.getProperty("basePath");
		} catch (IOException e) {
			log.error("读取env.properties异常",e);
		}
    }
    /*@Autowired
    private SystemService systemService;*/
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            //未登录跳转至登陆页面
            saveRequest(request);
            if (((HttpServletRequest) request).getHeader("Accept").contains("application/json")) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                response.getWriter().append(new Gson().toJson(result));
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                ((HttpServletResponse) response).sendRedirect(basePath);
            }
        } else {
            //已登录未授权
            if (((HttpServletRequest) request).getHeader("Accept").contains("application/json")) {
                log.debug("授权认证:未通过:json"+((HttpServletRequest) request).getRequestURL());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.unauthorized.getCode(), ResponseCode.unauthorized.getMsg());
                response.getWriter().append(new Gson().toJson(result));
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                log.debug("授权认证:未通过:web"+((HttpServletRequest) request).getRequestURL());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                ((HttpServletResponse) response).sendRedirect("/error/unAuthorization");
            }
        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    log.debug("授权认证:未通过");
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    log.debug("授权认证:未通过");
                    isPermitted = false;
                }
            }
        }
        return isPermitted;
    }
}
