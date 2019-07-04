package com.yzy.controller;

import com.google.gson.Gson;
import com.yzy.utils.PropertiesUtils;
import com.yzy.utils.ResponseCode;
import com.yzy.utils.Result;
import com.yzy.utils.StringUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * 基础controller,方便统一异常处理
 *
 * @Author: 王俊强
 */
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    
    static String basePath = "";
    
    static {
    	try {
			Properties properties = PropertiesUtils.getProperties("env.properties");
			basePath = properties.getProperty("basePath");
		} catch (IOException e) {
			log.error("读取env.properties异常",e);
		}
    }

    //根据请求类型,响应不同类型
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
        log.error("exception occur : \n {}", StringUtil.exceptionDetail(exception));
        if (request.getHeader("Accept").contains("application/json")) {
            Result result = Result.error();
            if (exception instanceof IncorrectCredentialsException) {
            	//账户或密码错误
                result = Result.instance(ResponseCode.password_incorrect.getCode(), ResponseCode.password_incorrect.getMsg());
            }/*else if (exception instanceof AuthenticationException) {
            	//账户或密码错误
                result = Result.instance(ResponseCode.password_incorrect.getCode(), ResponseCode.password_incorrect.getMsg());
            }*/
            else if (exception instanceof UnknownAccountException) {
            	//账户不存在
                result = Result.instance(ResponseCode.unknown_account.getCode(), ResponseCode.unknown_account.getMsg());
            } else if (exception instanceof UnauthorizedException) {
            	//无操作权限
                result = Result.instance(ResponseCode.unauthorized.getCode(), ResponseCode.unauthorized.getMsg());
            } else if (exception instanceof UnauthenticatedException) {
            	//未登录
                result = Result.instance(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
            } else if (exception instanceof MissingServletRequestParameterException) {
            	//缺少参数
                result = Result.instance(ResponseCode.missing_parameter.getCode(), ResponseCode.missing_parameter.getMsg());
            } else if ((exception instanceof MethodArgumentTypeMismatchException)) {
            	//参数格式错误
                result = Result.instance(ResponseCode.param_format_error.getCode(), ResponseCode.param_format_error.getMsg());
            } else {
            	//其他错误
            	result = Result.error();
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
            response.getWriter().append(new Gson().toJson(result));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            String url = "/error/internalError";
            if (exception instanceof UnauthorizedException) {
                url = "/error/unAuthorization";
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(basePath + url);
        }
    }
}
