package com.yzy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.entity.BaseCountry;
import com.yzy.service.BaseCountryService;

import java.util.List;

@Controller
@RequestMapping("baseCountry")
public class BaseCountryController extends BaseController{

    @Autowired
    private BaseCountryService baseCountryService;

    @ResponseBody
    @RequestMapping(value = "getCountryByPid")
    public List<BaseCountry> getCountryByPid(Integer pid) {
        return baseCountryService.getCountryByPid(pid);
    }


}
