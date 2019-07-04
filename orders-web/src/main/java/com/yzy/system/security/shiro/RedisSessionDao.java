package com.yzy.system.security.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.yzy.entity.LoginInfo;
import com.yzy.utils.SystemConstant;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author 王俊强
 * @Description session redis实现    cookie生成  已经redis  key存活时间变更
 */
public class RedisSessionDao extends AbstractSessionDAO {
    private static long timeout;
    private transient static Logger log = LoggerFactory.getLogger(RedisSessionDao.class);
    static{
    	Properties prop = new Properties();   
        InputStream in = RedisSessionDao.class.getResourceAsStream("/env.properties");   
        try {   
            prop.load(in);   
            timeout = Integer.parseInt(prop.getProperty("shiro.sessionTimeout"))/1000;   
        } catch (Exception e) {
             // TODO Auto-generated catch block
        	 log.error("RedisSessionDao获取properties文件内容出错"+e);
         }    
    }
    @Autowired
    private transient RedisTemplate<Serializable, Session> redisTemplate;
 
    //在用户首次访问时创建一个shiro session 
    protected Serializable doCreate(Session session) {
        Serializable sessionId = SystemConstant.SHIRO_CACHE_PREFIX + UUID.randomUUID().toString();
        //往session中指派ID
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(sessionId, session, timeout, TimeUnit.SECONDS);
        log.info("create shiro session ,sessionId is :{}", sessionId.toString());
        return sessionId;
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        return redisTemplate.opsForValue().get(sessionId);
    }


    @Override
    public void update(Session session) throws UnknownSessionException {
    	LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
    	if(loginInfo!=null) {
    		String kickoutName = SystemConstant.SHIRO_CACHE_PREFIX+SystemConstant.KICKOUT+loginInfo.getLoginName();
    		redisTemplate.expire(kickoutName,timeout, TimeUnit.SECONDS);
    		String catchName = SystemConstant.SHIRO_CACHE_PREFIX+loginInfo.getLoginName();
    		redisTemplate.expire(catchName,timeout, TimeUnit.SECONDS);
    	}
        redisTemplate.opsForValue().set(session.getId(), session, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
    	LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
    	//删除登录唯一验证的缓存
//    	String kickoutName = SystemConstant.SHIRO_CACHE_PREFIX+SystemConstant.KICKOUT+loginInfo.getLoginName();
//    	redisTemplate.opsForValue().getOperations().delete(kickoutName);
    	if(loginInfo!=null) {
    		//删除用户缓存
    		String catchName = SystemConstant.SHIRO_CACHE_PREFIX+loginInfo.getLoginName();
    		redisTemplate.opsForValue().getOperations().delete(catchName);
    	}
    	//删除用户session
    	redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        //先获取所有的用户的登录信息的缓存
    	Set<Serializable> keys = redisTemplate.keys(SystemConstant.SHIRO_CACHE_PREFIX_KEYS);
        if (keys.size() == 0) {
            return Collections.emptySet();
        }
        ArrayList<Session> sessions = new ArrayList<Session>();
        ArrayList<Long> containsUser = new ArrayList<Long>();
        for (Serializable key : keys) {
        	Session session = null;
			try {
				//筛选用户session（登录信息的缓存中存在非session缓存，在线用户不需要，而且会报错）
				session = redisTemplate.opsForValue().get(key);
			} catch (Exception e) {
				continue;
			}
        	if(session!=null && session.getAttribute("forceLogout") == null) {  //当包含forceLogout标签时  证明该session已经被踢出
        		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        		if(loginInfo!=null) {
        			if(!containsUser.contains(loginInfo.getId())) {
        				sessions.add(session);
        				containsUser.add(loginInfo.getId());
        			};
        		}
        	}
        }
        //List<Session> sessions = redisTemplate.opsForValue().multiGet(keys);
        
        return Collections.unmodifiableCollection(sessions);
    }
}
