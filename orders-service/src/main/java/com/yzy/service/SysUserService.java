package com.yzy.service;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysUnit;
import com.yzy.entity.SysUser;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface SysUserService {

	LoginInfo login(HttpServletRequest request, SysUser user, Serializable id);

	SysUser selectByLoginName(String loginName);

	LayuiTable selectUserAndUnit(int page,int limit, String zhName, String unitName, String loginName, String phone,ArrayList<Integer> roleIdList,Integer status);

	Result systemInsert(SysUser sysUser, long unitId, long roleId);

	Result systemUpdate(SysUser sysUser);

	Result systemDelete(String ids);

	Result updateUnitAndUser(SysUser sysUser, SysUnit sysUnit, Long roleId);

	Result findUserByPhone(String phone);

	Result register(SysUser sysUser, SysUnit sysUnit, Long roleId);

	Result resetPassword(String phone, String password);

	Result personalUpdate(SysUser sysUser, SysUnit sysUnit);

	Result uploadUserPhoto(Long userId,MultipartFile photoFile);

}
