package com.xx.demo.controller;


import com.xx.demo.entity.Huser;
import com.xx.demo.service.HuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-28
 */
@Controller
@RequestMapping("/huser")
@Api(value = "huser", tags = "后台登录验证")
public class HuserController {

          @Autowired
          private HuserService huserService;

          public static HttpSession session;

          @ApiOperation(value = "后台登陆验证")
          @RequestMapping(value = "toLogin" ,method = RequestMethod.POST)
          @ResponseBody
          public String getLogin(@RequestBody Huser huser, HttpServletRequest request){
                    if(huserService.getLogin(huser).equals("yes")){
                              session = request.getSession();
                              session.setAttribute("huser",huser);
                              return "{\"msg\":\"yes\"}";
                    }
                     return "{\"msg\":\"no\"}";
          }

}

