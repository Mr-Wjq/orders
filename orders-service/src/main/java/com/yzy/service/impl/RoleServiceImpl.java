package com.yzy.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzy.dao.SysRoleMapper;
import com.yzy.entity.SysRole;
import com.yzy.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public HashMap<String, Object> roleList() {
		List<SysRole> list = sysRoleMapper.selectAll();
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("code",0);
		map.put("data",list);
		map.put("count",list.size());
		map.put("is",true);
		map.put("tip","操作成功！");
		return map;
	}

	
	
}
