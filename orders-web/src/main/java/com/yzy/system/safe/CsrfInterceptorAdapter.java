package com.yzy.system.safe;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.yzy.utils.ResponseCode;
import com.yzy.utils.Result;
import com.yzy.utils.StringUtil;

public class CsrfInterceptorAdapter extends HandlerInterceptorAdapter{
	/**
	 * 验证 _csrf 参数
	 * */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String session_csrf_token = (String)session.getAttribute("csrf_token");
		String header_csrf_token = request.getHeader("X-Csrf-Token");
		if(request.getHeader("Accept").contains("text/html")){
			return true;
        }else {
			if(StringUtils.isNotBlank(session_csrf_token) && StringUtils.isNotBlank(header_csrf_token) && session_csrf_token.equals(header_csrf_token)) {
				return true;
			}else{
				response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.forbidden_ip.getCode(), ResponseCode.forbidden_ip.getMsg());
                response.getWriter().append(new Gson().toJson(result));
                response.getWriter().flush();
                response.getWriter().close();
				return false;
			}
        }
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
