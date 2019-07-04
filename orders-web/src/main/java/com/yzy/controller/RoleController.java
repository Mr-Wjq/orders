package com.yzy.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value = "roleList", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> roleList(){
		return roleService.roleList();
	}
	
}
