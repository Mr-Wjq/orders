package com.yzy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUser;
import com.yzy.service.SysUserService;
import com.yzy.service.SystemService;
import com.yzy.utils.ResponseCode;
import com.yzy.utils.Result;
import com.yzy.utils.SystemConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 系统功能模块
 */
@Api(value = "系统功能模块")
@Controller
@RequestMapping("system")
public class SystemController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(SystemController.class);
   
    @Autowired
    private SystemService systemService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestParam String loginName,
                        @RequestParam String password,
                        @RequestParam String code,
                        HttpServletRequest request,HttpServletResponse response)throws Exception{

    	//验证码
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
    	if(StringUtils.isEmpty(verificationCode)) {
    		return Result.error("验证码失效请刷新后重试!");
    	}
        if (!code.toLowerCase().equals(verificationCode)) {
            return Result.instance(ResponseCode.verify_captcha_error.getCode(), ResponseCode.verify_captcha_error.getMsg());
        }
        SysUser user = sysUserService.selectByLoginName(loginName);
        if (user == null) {
            return Result.instance(ResponseCode.unknown_account.getCode(), ResponseCode.unknown_account.getMsg());
        }
        if (user.getStatus() == 2) {
            return Result.instance(ResponseCode.forbidden_account.getCode(), ResponseCode.forbidden_account.getMsg());
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(user.getLoginName(), password));
        LoginInfo loginInfo = sysUserService.login(request,user, subject.getSession().getId());
        subject.getSession().setAttribute("loginInfo", loginInfo);
        return Result.success();
    }
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response) throws IOException {
    	Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
    	if(loginInfo!=null) {
    		redisTemplate.opsForValue().getOperations().delete(SystemConstant.SHIRO_CACHE_PREFIX + loginInfo.getLoginName());
    	}
    	redisTemplate.opsForValue().getOperations().delete(session.getId());
        subject.logout();
    	response.sendRedirect("/");
    }
    @RequestMapping(value = "getPhoneCode", method = RequestMethod.GET)
    @ResponseBody
    public Result getPhoneCode(String phone) {
    	if(StringUtils.isBlank(phone)) {
    		return Result.error("手机号不能为空");
    	}
    	Result result = sysUserService.findUserByPhone(phone);
		if(result.getCode() !=10000 ) {
			return result;
		}
    	return systemService.getPhoneCode(phone);
    }
    @RequestMapping(value = "getPhoneCodeForRp", method = RequestMethod.GET)
    @ResponseBody
    public Result getPhoneCodeForRp(String phone) {
    	if(StringUtils.isBlank(phone)) {
    		return Result.error("手机号不能为空");
    	}
    	return systemService.getPhoneCodeForRp(phone);
    }
    @RequestMapping(value = "checkPhoneCode", method = RequestMethod.GET)
    @ResponseBody
    public Result checkPhoneCode(String phoneCode) {
    	if(StringUtils.isBlank(phoneCode)) {
    		return Result.error("手机验证码不能为空");
    	}
    	Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	String sess_phoneCode = (String) session.getAttribute("phoneCode");
		if(StringUtils.isBlank(sess_phoneCode)) {
			return Result.error("手机验证码过期,请重新获取");
		}
		if(!sess_phoneCode.equals(phoneCode)) {
			return Result.error("手机验证码错误");
		}
    	return Result.success();
    }
}
