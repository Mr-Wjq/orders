package com.yzy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author 王俊强
 * @Description
 */
@Controller
@RequestMapping("error")
public class ErrorController extends BaseController {

    /**
     * 未授权
     *
     * @return
     */
    @ApiOperation(value = "未授权", httpMethod = "GET", produces = "text/html")
    @RequestMapping(value = "unAuthorization", method = RequestMethod.GET)
    public String unAuthorization() {
    	return "error/unAuthorization";
    }

    /**
     * 404
     *
     * @return
     */
    @ApiOperation(value = "未找到页面", httpMethod = "GET", produces = "text/html")
    @RequestMapping(value = "notFound", method = RequestMethod.GET)
    public String notFound() {
        return "error/404";
    }

    /**
     * 内部错误
     *
     * @return
     */
    @ApiOperation(value = "内部错误", httpMethod = "GET", produces = "text/html")
    @RequestMapping(value = "internalError", method = RequestMethod.GET)
    public String internalError() {
        return "error/500";
    }


}
