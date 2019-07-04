package com.yzy.system.security.shiro;

import com.yzy.dao.SysPermissionMapper;
import com.yzy.dao.SysRoleMapper;
import com.yzy.dao.SysUserMapper;
import com.yzy.entity.SysPermission;
import com.yzy.entity.SysRole;
import com.yzy.entity.SysUser;
import com.yzy.utils.SystemConstant;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 自定义realm实现
 */
public class ShiroRealm extends AuthorizingRealm {
    private static Logger log = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 鉴权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("开始查询授权信息");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String loginStr = (String) principalCollection.getPrimaryPrincipal();
        SysUser user = sysUserMapper.selectUserByLoginName(loginStr);
        //获取用户对应的角色
        SysRole sysRole = sysRoleMapper.selectByUserId(user.getId());
        Set<String> roles = new HashSet<>();
        roles.add(sysRole.getName());
        info.addRoles(roles);
        
        //获取用户对应的权限
        Set<String> permissions = new HashSet<>();
    	//由管理员创建的用户对应的权限
    	List<SysPermission> sysPermissionList = sysPermissionMapper.selectByRoleId(sysRole.getId());
    	for (SysPermission sysPermission : sysPermissionList) {
    		permissions.add(sysPermission.getCode());
    	}
        info.addStringPermissions(permissions);
        log.debug("角色信息: \n {}", roles.toString());
        log.debug("权限信息: \n{}", permissions.toString());
        return info;
    }

    /**
     * 登录验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("登录验证");
        String loginName = (String) authenticationToken.getPrincipal();
        SysUser sysUser = sysUserMapper.selectUserByLoginName(loginName);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getPasswordSalt()), getName());
        return authenticationInfo;
    }

    @Override
    protected void doClearCache(PrincipalCollection principals) {
        redisTemplate.delete(SystemConstant.SHIRO_CACHE_PREFIX + principals.getPrimaryPrincipal().toString());
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        log.debug("clearCachedAuthorizationInfo");
    }

}
