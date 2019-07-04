package com.yzy.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yzy.dao.SysUserMapper;
import com.yzy.entity.SysUser;
import com.yzy.service.SysUserService;
import com.yzy.service.SystemService;
import com.yzy.utils.Dxyzm;
import com.yzy.utils.Result;

@Service
public class SystemServiceImpl implements SystemService {
	private Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);
	
	@Autowired
	private SysUserMapper sysUserMapper; 
	
	@Override
	public Result getPhoneCode(String phone) {
		SysUser sysUser = sysUserMapper.selectUserByPhone(phone);
		if(sysUser==null) {
			return Result.error("该手机号未注册");
		}
		DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIh84lgCReJQJh", "iC4MgjTeoPIUZ2IpZvf0P5axpfLHxI");
        IAcsClient client = new DefaultAcsClient(profile);
        String phoneCode = Dxyzm.getNonce_str();
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "牙智云");
        request.putQueryParameter("TemplateCode", "SMS_168415076");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+phoneCode+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(phone+"获取手机验证码。返回信息为:"+response.getData());
            JsonParser parse =new JsonParser();
            JsonObject json=(JsonObject) parse.parse(response.getData());  //创建jsonObject对象
            String code = json.get("Code").getAsString();
            switch (code) {
			case "OK":
				Subject subject = SecurityUtils.getSubject();
		    	Session session = subject.getSession();
		    	session.setAttribute("phoneCode", phoneCode);
		    	session.setAttribute("phoneNum", phone);
				return Result.success();
			case "isv.MOBILE_NUMBER_ILLEGAL":
				return Result.error("非法手机号");
			case "isv.BUSINESS_LIMIT_CONTROL":
				return Result.error("获取验证码过于频繁");
			default:
				return Result.error("获取手机验证码失败");
			}
        } catch (Exception e) {
        	logger.error("获取手机验证码异常",e);
        	return Result.error("系统繁忙请稍后重试");
        }
	}

	
	
	@Override
	public Result getPhoneCodeForRp(String phone) {
		return getPhoneCode( phone);
	}
}
