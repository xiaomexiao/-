package com.xx.demo.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.Application;
import com.xx.demo.mapper.ApplicationMapper;
import com.xx.demo.service.ApplicationService;
import com.xx.demo.service.EventService;
import com.xx.demo.service.UserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 页面跳转控制类
 */
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationMapper applicationMapper;

    //去登录页面
    @RequestMapping(value = "toLogin")
    public ModelAndView toLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("user",UserController.session);
        return mav;
    }

    //去注册页面
    @RequestMapping(value = "toRegister")
    public ModelAndView toRegister(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        mav.addObject("user",UserController.session);
        return mav;
    }

    //去主页面
    @RequestMapping(value = "toIndex")
    public ModelAndView toIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("events",eventService.selectList(null));
        mav.addObject("user",UserController.session);
        return mav;
    }

    //去关于我们的页面
    @RequestMapping(value = "toAboutUs")
    public ModelAndView toAboutUS(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("aboutus");
        mav.addObject("user",userService.getSessionName());
        return mav;
    }

    //支教页面
    @RequestMapping(value = "toZhiJiao")
    public ModelAndView toZhiJiao(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("zhijiao");
        mav.addObject("user",userService.getSessionName());
        return mav;
    }

    //去支教index页面
    @RequestMapping(value = "toZhiJiaoIndex")
    public ModelAndView toZhiJiaoIndex(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("events",eventService.selectList(null));
        mav.addObject("user",userService.getSessionName());
        mav.setViewName("zhijiaoindex");
        return mav;
    }

    //去支教申请页面
    @RequestMapping(value = "toApplication")
    public ModelAndView toApplication(){
        ModelAndView mav = new ModelAndView();
        if(UserController.session == null){
            mav.setViewName("login");
            return mav;
        }else {
            mav.addObject("user",userService.getSessionName());
            mav.setViewName("application");
            return mav;
        }
    }

    //去支教管理流程图页面
    @RequestMapping(value = "toProcessInstance")
    public ModelAndView toProcessInstance(){
        //查出当前用户的流程实例id,根据流程实例id生成图片
        //看当前登陆用户的信息只能从session去查找,或者获取其登录成功的信息
      /*  EntityWrapper<Application> ew = new EntityWrapper<>();
        ew.eq("phone",UserController.phone);*/
        //List<Application> applicationList = applicationMapper.selectList(ew);
        //选择最新显示的申请进度
        /*Application application = applicationMapper.selectList(ew).get(0);
        if (application!=null && application.getProcessInstanceId() !=null){
            String result = applicationService.getDiagram(application.getProcessInstanceId());
            System.out.println("生成的流程id是:"+application.getProcessInstanceId()+"生成的图片路径在:"+result);
            ModelAndView mav = new ModelAndView();
            mav.addObject("user",userService.getSessionName());
            mav.setViewName("zhijiaoprocess");
            return mav;
        }else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("user",userService.getSessionName());
            mav.setViewName("zhijiaoprocess");
            return mav;
        }*/
        ModelAndView mav = new ModelAndView();
        mav.addObject("user",userService.getSessionName());
      //  mav.addObject("phone",userService)
        mav.setViewName("zhijiaoprocess");
        return mav;
    }

    //去后台管理登录页面
    @RequestMapping(value = "toHIndex")
    public String toHIndex(){
        return "hindex";
    }

    //去后台管理页面
    @RequestMapping(value = "admin/main")
    public String toHDetail(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/main";
        }
    }

    @RequestMapping(value = "admin/user_list")
    public String toAdminUser(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/user_list";
        }
    }

    @RequestMapping(value = "admin/info")
    public String toAdminInfo(){

        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/info";
        }
    }

    @RequestMapping(value = "admin/instance_list")
    public String toAdminInstance(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/instance_list1";
        }
    }

    @RequestMapping(value = "admin/instance_list2")
    public String toAdminInstance2(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/instance_list2";
        }
    }

    @RequestMapping(value = "admin/instance_list3")
    public String toAdminInstance3(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/instance_list3";
        }
    }

    @RequestMapping(value = "admin/application_list")
    public String toApplicationList(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/application_list";
        }
    }

    @RequestMapping(value = "toIndexChat")
    public String toindex(){
        return "chat";
    }

    //去后台信息管理
    @RequestMapping(value = "admin/zhijiao_list")
    public String toZhijiao_list(){
        if (HuserController.session == null){
            return "hindex";
        }else {
            return "admin/zhijiao_list";
        }
    }
}
