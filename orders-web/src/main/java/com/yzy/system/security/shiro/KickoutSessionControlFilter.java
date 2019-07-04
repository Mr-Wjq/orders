package com.yzy.system.security.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yzy.utils.PropertiesUtils;
import com.yzy.utils.SystemConstant;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Properties;


public class KickoutSessionControlFilter extends AccessControlFilter {
	private static final Logger log = LoggerFactory.getLogger(KickoutSessionControlFilter.class);
	
	static String basePath = "";
    
    static {
    	try {
			Properties properties = PropertiesUtils.getProperties("env.properties");
			basePath = properties.getProperty("basePath");
		} catch (IOException e) {
			log.error("读取env.properties异常",e);
		}
    }
	
    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //false 踢出之前登录的/ true 之后登录的  默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1
    
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(SystemConstant.KICKOUT_CACHE);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response); 
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        //TODO 同步控制
        Deque<Serializable> deque = cache.get(SystemConstant.KICKOUT+username);
        if(deque == null) {
            deque = new LinkedList<Serializable>(); 
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
            cache.put(SystemConstant.KICKOUT+username, deque);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { //踢出后者
                kickoutSessionId = deque.removeFirst();
            } else { //踢出前者
                kickoutSessionId = deque.removeLast();
            }
            cache.put(SystemConstant.KICKOUT+username, deque);
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
            }
        }
//        cache.put(SystemConstant.KICKOUT+username, deque);
        //登录踢出 如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            	log.error("KickoutSessionControlFilter报错====>"+e.toString());
            }
            saveRequest(request);
            /*SysLoginStatus loginStatus = new SysLoginStatus();
            loginStatus.setSessionId(deque.getFirst().toString());
            SysLoginStatus sysLoginStatus = sysLoginStatusMapper.select(loginStatus);
            String url = "";
            if(sysLoginStatus!=null) {
                url = "&ip="+sysLoginStatus.getLoginIp();
            }
            String basePath = systemService.selectRedisCacheByConfigName("basePath");*/
            //WebUtils.issueRedirect(request, response,basePath+kickoutUrl+url);
            ((HttpServletResponse) response).sendRedirect(basePath+kickoutUrl);
            return false;
        }
        //管理员踢出用户
        if (session.getAttribute("forceLogout") != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            	log.error("KickoutSessionControlFilter报错====>"+e.toString());
            }
            saveRequest(request);
            ((HttpServletResponse) response).sendRedirect(basePath+"?forceLogout=1");
            return false;
        }
        return true;
    }
}
