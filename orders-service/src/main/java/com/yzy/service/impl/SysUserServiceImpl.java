package com.yzy.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzy.dao.SysFirstPageMapper;
import com.yzy.dao.SysUnitMapper;
import com.yzy.dao.SysUnitUserMapper;
import com.yzy.dao.SysUserMapper;
import com.yzy.dao.SysUserRoleMapper;
import com.yzy.entity.DataProductPrice;
import com.yzy.entity.LoginInfo;
import com.yzy.entity.SysFirstPage;
import com.yzy.entity.SysUnit;
import com.yzy.entity.SysUnitUser;
import com.yzy.entity.SysUser;
import com.yzy.entity.SysUserRole;
import com.yzy.entity.vo.UserUnitVO;
import com.yzy.service.SysUnitService;
import com.yzy.service.SysUserService;
import com.yzy.utils.DateUtils;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.PropertiesUtils;
import com.yzy.utils.RegexUtil;
import com.yzy.utils.Result;
import com.yzy.utils.StringUtil;
import com.yzy.utils.SystemConstant;

import tk.mybatis.mapper.entity.Example;


@Service
public class SysUserServiceImpl implements SysUserService {

	private static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	private static String baseFilePath = "";
	static {
		try {
			Properties properties = PropertiesUtils.getProperties("env.properties");
		    baseFilePath = properties.getProperty("baseFilePath");
		} catch (IOException e) {
			logger.error("读取properties出错",e);
		}
	}
	@Autowired
	private SysFirstPageMapper sysFirstPageMapper; 
	@Autowired
	private SysUserMapper sysUserMapper; 
	@Autowired
	private SysUnitMapper sysUnitMapper; 
	@Autowired
	private SysUnitService sysUnitService; 
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper; 
	@Autowired
	private SysUnitUserMapper sysUnitUserMapper; 
	
	@Override
	public LoginInfo login(HttpServletRequest request, SysUser user, Serializable id) {
		LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(user, loginInfo);
        SysUserRole sysUserRole = sysUserRoleMapper.selectByUserId(user.getId());
        loginInfo.setRoleId(sysUserRole.getSysRoleId());
        SysUnitUser unitUserByUser = sysUnitUserMapper.selectUnitUserByUserId(user.getId());
        SysUnit currentUnit = sysUnitMapper.selectByPrimaryKey(unitUserByUser.getSysUnitId());
        loginInfo.setUnitId(currentUnit.getId());
        loginInfo.setUnitName(currentUnit.getUnitName());
        SysFirstPage sysFirstPage  = sysFirstPageMapper.selectByRoleId(sysUserRole.getSysRoleId());
        loginInfo.setFirstPage(sysFirstPage.getFirstPagePath());
		return loginInfo;
	}
	public void refreshLoginInfo(Long userId){
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
    	
    	SysUser user = sysUserMapper.selectByPrimaryKey(userId);
    	BeanUtils.copyProperties(user, loginInfo);
    	SysUserRole sysUserRole = sysUserRoleMapper.selectByUserId(userId);
    	loginInfo.setRoleId(sysUserRole.getSysRoleId());
        SysUnitUser unitUserByUser = sysUnitUserMapper.selectUnitUserByUserId(userId);
        SysUnit currentUnit = sysUnitMapper.selectByPrimaryKey(unitUserByUser.getSysUnitId());
        loginInfo.setUnitId(currentUnit.getId());
        loginInfo.setUnitName(currentUnit.getUnitName());
        SysFirstPage sysFirstPage  = sysFirstPageMapper.selectByRoleId(sysUserRole.getSysRoleId());
        loginInfo.setFirstPage(sysFirstPage.getFirstPagePath());
        session.setAttribute("loginInfo", loginInfo);
	}
	@Override
	public SysUser selectByLoginName(String loginName) {
		SysUser sysUser = null;
		boolean b = RegexUtil.phone(loginName);
		if(b) {
			sysUser = sysUserMapper.selectUserByPhone(loginName);
		}else {
			sysUser = sysUserMapper.selectUserByLoginName(loginName);
		}
		return sysUser;
	}

	@Override
	public LayuiTable selectUserAndUnit(int page,int limit,String zhName,String unitName,String loginName,String phone,ArrayList<Integer> roleIdList,Integer status) {
		List<SysUnit> unitList = sysUnitService.getSubjectionUnit();
		//单位ID集合
        List<Long> unitIdList = new ArrayList<>();
        for (SysUnit sysUnit:unitList) {
            unitIdList.add(sysUnit.getId());
        }
        PageHelper.startPage(page, limit);
		List<UserUnitVO> list = sysUnitUserMapper.selectUserAndUnit(unitIdList, zhName, unitName, loginName, phone,  roleIdList,status);
		PageInfo<UserUnitVO> pageInfo = new PageInfo<>(list);   
 		long sum = pageInfo.getTotal();
		return LayuiTable.success(sum, list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result systemInsert(SysUser sysUser, long unitId, long roleId) {
		
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("loginName", sysUser.getLoginName()).andNotEqualTo("status",0);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return Result.error("账号已被使用!");
		}
		Example example2 = new Example(SysUser.class);
		example2.createCriteria().andEqualTo("phone", sysUser.getPhone()).andNotEqualTo("status",0);
		List<SysUser> list2 = sysUserMapper.selectByExample(example2);
		if(!list2.isEmpty()) {
			return Result.error("手机号已被使用!");
		}
		if(StringUtils.isNotBlank(sysUser.getEmail())) {
			Example example3 = new Example(SysUser.class);
			example3.createCriteria().andEqualTo("email", sysUser.getEmail()).andNotEqualTo("status",0);
			List<SysUser> list3 = sysUserMapper.selectByExample(example3);
			if(!list3.isEmpty()) {
				return Result.error("邮箱已被使用!");
			}
		}
		try {
			sysUser.setCreateTime(new Date());
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			sysUser.setPasswordSalt(salt);
			sysUser.setPassword(StringUtil.createPassword(sysUser.getPassword(), salt, 2));
			sysUserMapper.insertSelective(sysUser);
			
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSysUserId(sysUser.getId());
			sysUserRole.setSysRoleId(roleId);
			sysUserRole.setCreateTime(new Date());
			sysUserRoleMapper.insert(sysUserRole);
			
			SysUnitUser sysUnitUser = new SysUnitUser();
			sysUnitUser.setSysUserId(sysUser.getId());
			sysUnitUser.setSysUnitId(unitId);
			sysUnitUserMapper.insertSelective(sysUnitUser);
		} catch (Exception e) {
			logger.error("添加用户出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result systemUpdate(SysUser sysUser) {
		
		if(StringUtils.isNotBlank(sysUser.getLoginName())) {
			Example example = new Example(SysUser.class);
			example.createCriteria().andEqualTo("loginName", sysUser.getLoginName()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list = sysUserMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("账号已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getPhone())) {
			Example example2 = new Example(SysUser.class);
			example2.createCriteria().andEqualTo("phone", sysUser.getPhone()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list2 = sysUserMapper.selectByExample(example2);
			if(!list2.isEmpty()) {
				return Result.error("手机号已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getEmail())) {
			Example example3 = new Example(SysUser.class);
			example3.createCriteria().andEqualTo("email", sysUser.getEmail()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list3 = sysUserMapper.selectByExample(example3);
			if(!list3.isEmpty()) {
				return Result.error("邮箱已被使用!");
			}
		}
		
		if(StringUtils.isNotBlank(sysUser.getPassword())) {
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			sysUser.setPasswordSalt(salt);
			sysUser.setPassword(StringUtil.createPassword(sysUser.getPassword(), salt, 2));
		}
		sysUser.setUpdateTime(new Date());
		try {
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		} catch (Exception e) {
			logger.error("修改用户出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		if(sysUser.getId().equals(loginInfo.getId())) {
			refreshLoginInfo(loginInfo.getId());
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result systemDelete(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] split = ids.split(",");
			int length = split.length;
			for (int i = 0; i < length; i++) {
				if("1".equals(split[i])) {
					return Result.error("不可删除"); 
				}
				SysUser sysUser2 = sysUserMapper.selectByPrimaryKey(split[i]);
				if(sysUser2.getIsFinal() == 2) {
					return Result.error(sysUser2.getLoginName()+"-不可已删除");
				}
			}
			
			try {
				for (int i = 0; i < length; i++) {
					
					SysUser sysUser = new SysUser();
					sysUser.setId(Long.parseLong(split[i]));
					sysUser.setStatus(0);
					sysUserMapper.updateByPrimaryKeySelective(sysUser);
	
					Example example = new Example(SysUnitUser.class);
					example.createCriteria().andEqualTo("sysUserId", split[i]);
					SysUnitUser sysUnitUser = new SysUnitUser();
					sysUnitUser.setStatus(0);
					sysUnitUserMapper.updateByExampleSelective(sysUnitUser, example);
					
				}
			} catch (Exception e) {
				logger.error("删除用户出错",e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("系统繁忙请稍后重试");
			}
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result updateUnitAndUser(SysUser sysUser, SysUnit sysUnit, Long roleId) {
		if(StringUtils.isNotBlank(sysUnit.getUnitName())) {
			Example example = new Example(SysUnit.class);
			example.createCriteria().andEqualTo("unitName", sysUnit.getUnitName()).andNotEqualTo("id", sysUnit.getId()).andNotEqualTo("status",0);
			List<SysUnit> list = sysUnitMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("单位名称已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getLoginName())) {
			Example example = new Example(SysUser.class);
			example.createCriteria().andEqualTo("loginName", sysUser.getLoginName()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list = sysUserMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("账号已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getPhone())) {
			Example example2 = new Example(SysUser.class);
			example2.createCriteria().andEqualTo("phone", sysUser.getPhone()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list2 = sysUserMapper.selectByExample(example2);
			if(!list2.isEmpty()) {
				return Result.error("手机号已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getEmail())) {
			Example example3 = new Example(SysUser.class);
			example3.createCriteria().andEqualTo("email", sysUser.getEmail()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list3 = sysUserMapper.selectByExample(example3);
			if(!list3.isEmpty()) {
				return Result.error("邮箱已被使用!");
			}
		}
		
		if(StringUtils.isNotBlank(sysUser.getPassword())) {
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			sysUser.setPasswordSalt(salt);
			sysUser.setPassword(StringUtil.createPassword(sysUser.getPassword(), salt, 2));
		}
		sysUser.setUpdateTime(new Date());
		sysUnit.setUpdateTime(new Date());
		
		try {
			sysUserRoleMapper.deleteByUserId(sysUser.getId());
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSysRoleId(roleId);
			sysUserRole.setSysUserId(sysUser.getId());
			sysUserRole.setCreateTime(new Date());
			sysUserRoleMapper.insert(sysUserRole);
			
			sysUnitMapper.updateByPrimaryKeySelective(sysUnit);
			
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		} catch (Exception e) {
			logger.error("修改医院/工厂用户出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		if(sysUser.getId().equals(loginInfo.getId())) {
			refreshLoginInfo(loginInfo.getId());
		}
		return Result.success();
	}

	@Override
	public Result findUserByPhone(String phone) {
		Example example2 = new Example(SysUser.class);
		example2.createCriteria().andEqualTo("phone", phone).andNotEqualTo("status",0);
		List<SysUser> list2 = sysUserMapper.selectByExample(example2);
		if(!list2.isEmpty()) {
			return Result.error("手机号已被使用!");
		}
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result register(SysUser sysUser, SysUnit sysUnit, Long roleId) {
		
		if(StringUtils.isNotBlank(sysUnit.getUnitName())) {
			Example example = new Example(SysUnit.class);
			example.createCriteria().andEqualTo("unitName", sysUnit.getUnitName()).andNotEqualTo("status",0);
			List<SysUnit> list = sysUnitMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("单位名称已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getLoginName())) {
			Example example = new Example(SysUser.class);
			example.createCriteria().andEqualTo("loginName", sysUser.getLoginName()).andNotEqualTo("status",0);
			List<SysUser> list = sysUserMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("账号已被使用!");
			}
		}
		
		if(StringUtils.isNotBlank(sysUser.getEmail())) {
			Example example3 = new Example(SysUser.class);
			example3.createCriteria().andEqualTo("email", sysUser.getEmail()).andNotEqualTo("status",0);
			List<SysUser> list3 = sysUserMapper.selectByExample(example3);
			if(!list3.isEmpty()) {
				return Result.error("邮箱已被使用!");
			}
		}
		
		try {
			sysUser.setCreateTime(new Date());
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			sysUser.setPasswordSalt(salt);
			sysUser.setPassword(StringUtil.createPassword(sysUser.getPassword(), salt, 2));
			sysUserMapper.insertSelective(sysUser);
			
			sysUnit.setPid((long)1);
			sysUnit.setCreateTime(new Date());
			sysUnitMapper.insertSelective(sysUnit);
			
			
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSysUserId(sysUser.getId());
			sysUserRole.setSysRoleId(roleId);
			sysUserRole.setCreateTime(new Date());
			sysUserRoleMapper.insert(sysUserRole);
			
			SysUnitUser sysUnitUser = new SysUnitUser();
			sysUnitUser.setSysUserId(sysUser.getId());
			sysUnitUser.setSysUnitId(sysUnit.getId());
			sysUnitUser.setCreateTime(new Date());
			sysUnitUserMapper.insertSelective(sysUnitUser);
		} catch (Exception e) {
			logger.error("注册出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试!");
		}
		
		return Result.success();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result resetPassword(String phone, String password) {
		try {
			Example example2 = new Example(SysUser.class);
			example2.createCriteria().andEqualTo("phone", phone).andNotEqualTo("status",0);
			SysUser sysUser = sysUserMapper.selectOneByExample(example2);
			if(sysUser == null) {
				return Result.error("该手机号未注册");
			}
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			sysUser.setPasswordSalt(salt);
			sysUser.setPassword(StringUtil.createPassword(password, salt, 2));
		
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		} catch (Exception e) {
			logger.error("重置密码出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试!");
		}
		return Result.success();
	}

	@Override
	public Result personalUpdate(SysUser sysUser, SysUnit sysUnit) {
		if(StringUtils.isNotBlank(sysUnit.getUnitName())) {
			Example example = new Example(SysUnit.class);
			example.createCriteria().andEqualTo("unitName", sysUnit.getUnitName()).andNotEqualTo("id", sysUnit.getId()).andNotEqualTo("status",0);
			List<SysUnit> list = sysUnitMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("单位名称已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getLoginName())) {
			Example example = new Example(SysUser.class);
			example.createCriteria().andEqualTo("loginName", sysUser.getLoginName()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list = sysUserMapper.selectByExample(example);
			if(!list.isEmpty()) {
				return Result.error("账号已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getPhone())) {
			Example example2 = new Example(SysUser.class);
			example2.createCriteria().andEqualTo("phone", sysUser.getPhone()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list2 = sysUserMapper.selectByExample(example2);
			if(!list2.isEmpty()) {
				return Result.error("手机号已被使用!");
			}
		}
		if(StringUtils.isNotBlank(sysUser.getEmail())) {
			Example example3 = new Example(SysUser.class);
			example3.createCriteria().andEqualTo("email", sysUser.getEmail()).andNotEqualTo("id", sysUser.getId()).andNotEqualTo("status",0);
			List<SysUser> list3 = sysUserMapper.selectByExample(example3);
			if(!list3.isEmpty()) {
				return Result.error("邮箱已被使用!");
			}
		}
		
		if(StringUtils.isNotBlank(sysUser.getPassword())) {
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			sysUser.setPasswordSalt(salt);
			sysUser.setPassword(StringUtil.createPassword(sysUser.getPassword(), salt, 2));
		}
		sysUser.setUpdateTime(new Date());
		sysUnit.setUpdateTime(new Date());
		
		try {
			
			sysUnitMapper.updateByPrimaryKeySelective(sysUnit);
			
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		} catch (Exception e) {
			logger.error("修改医院/工厂用户出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("系统繁忙请稍后重试");
		}
		Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		if(sysUser.getId().equals(loginInfo.getId())) {
			refreshLoginInfo(loginInfo.getId());
		}
		return Result.success();
	}

	@Override
	public Result uploadUserPhoto(Long userId,MultipartFile photoFile) {
		if(photoFile.getSize()>52428800) {
			return Result.error("请上传小于50M的文件");
		}
		
		String userPhotoUrl = baseFilePath+"/userPhoto/";
		String accessoryName = StringUtil.getUUID()+"."+StringUtil.getExtensionName(photoFile.getOriginalFilename());
		File file = new File(userPhotoUrl,accessoryName);
		//判断文件夹是否存在
		if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
			// 创建父文件夹
			file.getParentFile().mkdirs();
	    }
		try {
			photoFile.transferTo(file);
		} catch (Exception e) {
			logger.error("上传用户头像出错",e);
			return Result.error(SystemConstant.SYSTEM_ERROR);
		}
		
		
		String oldUserPhoto = "";
		try {
			SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
			if(sysUser!=null && StringUtils.isNotBlank(sysUser.getUserPhoto())) {
				oldUserPhoto = sysUser.getUserPhoto();
				sysUser.setUserPhoto("userPhoto/"+accessoryName);
				sysUserMapper.updateByPrimaryKeySelective(sysUser);
				
				Subject subject = SecurityUtils.getSubject();
		    	Session session = subject.getSession();
		    	LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
				if(sysUser.getId().equals(loginInfo.getId())) {
					loginInfo.setUserPhoto(sysUser.getUserPhoto());
					session.setAttribute("loginInfo", loginInfo);
				}
			}
		} catch (Exception e) {
			logger.error("修改用户头像出错",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			File file2 = new File(userPhotoUrl+accessoryName);
			if(file2.exists()) {
				file2.delete();
			}
			Result.error(SystemConstant.SYSTEM_ERROR);
		}
		File file3 = new File(baseFilePath+"/"+oldUserPhoto);
		if(file3.exists()) {
			file3.delete();
		}
		return Result.success();
	}

}
