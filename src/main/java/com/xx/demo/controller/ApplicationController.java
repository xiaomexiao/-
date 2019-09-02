package com.xx.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.Application;
import com.xx.demo.entity.Approveapplication;
import com.xx.demo.entity.HistoryApplication;
import com.xx.demo.entity.User;
import com.xx.demo.mapper.ApplicationMapper;
import com.xx.demo.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-27
 */
@Controller
@RequestMapping("/application")
@Api(value = "UserApplication",  tags = "用户申请,工作流模块")
public class ApplicationController {

         @Autowired
         private ApplicationService applicationService;

         @Autowired
         private ApplicationMapper applicationMapper;

         public static String processInstanceId;

          @ApiOperation(value = "提交申请 && 绑定session中的提交人")
          @RequestMapping(value = "addApplication" , method = RequestMethod.POST)
          public ModelAndView addApplication(Application application, @RequestParam("file") MultipartFile file){
                    ModelAndView mav = new ModelAndView();
                    if (file.isEmpty()){
                       application.setResumeurl("无简历上传");
                       processInstanceId = applicationService.getApplicationProcess(application);

                       //提交给报名表初审
                       applicationService.getBaoMingBiao(processInstanceId,"admin");
                       mav.addObject("processInstanceId",processInstanceId);
                       mav.setViewName("index");
                       return mav;
             }else {
                       String fileName = file.getOriginalFilename();
                       String path = "C://upload";
                       File dest = new File(path+"/"+fileName);
                       try {
                                 file.transferTo(dest);
                       } catch (IOException e) {
                                 e.printStackTrace();
                       }
                       application.setResumeurl(applicationService.getImageUrl(dest));
                       processInstanceId = applicationService.getApplicationProcess(application);
                       //提交给报名表初审
                       applicationService.getBaoMingBiao(processInstanceId,"admin");
                       mav.addObject("processInstanceId",processInstanceId);
                       mav.setViewName("index");
                       return mav;
             }
          }

          @ApiOperation(value = "查看申请流程状态图")
          @RequestMapping(value = "/getImage/{processInstanceId}", method = RequestMethod.POST)
          @ResponseBody
          public Object getImage(@PathVariable String processInstanceId) {
                    String fileName = applicationService.getDiagram(processInstanceId);
                    return "生成的图片路径在:"+fileName;
          }



          @ApiOperation(value = "当前的登录人待签收,待办理的任务(报名表初审)")
          @RequestMapping(value = "/getTask", method = RequestMethod.GET)
          @ResponseBody
          public  Map<String, List<Application>> getPersonTask(User user) {
                    List<Application> applicationList = applicationService.getApplicationTask(user.getName());
                    Map<String, List<Application>> maps = applicationList.stream().collect(Collectors.groupingBy(Application
                    ::getState));
                    return maps;
          }

          @RequestMapping(value = "/getSuccessApplication", method = RequestMethod.GET)
          @ResponseBody
          public  Map<String, List<Application>> getSuccessApplication(String limit,String page) {
                    System.out.println("limit="+limit+","+"page="+page);
                    List<Application> applicationList = applicationMapper.selectList(null);
                    Map<String, List<Application>> maps = applicationList.stream().collect(Collectors.groupingBy(Application
                              ::getState));
                    return maps;
          }



          @ApiOperation(value = "查看子任务的历史流程记录")
          @RequestMapping(value = "/getHistoryApplication/{processInstanceId}", method = RequestMethod.GET)
          @ResponseBody
          public List<HistoryApplication> getHistorySubTask(@PathVariable String processInstanceId) {
                    List<HistoryApplication> historyApplications = applicationService.getHistorySubTask(processInstanceId);
                    return historyApplications;

          }


          @RequestMapping(value = "getByprocessInstanceId")
          @ResponseBody
          public Application getByprocessInstanceId(@RequestBody Application application){
                    EntityWrapper<Application> ew = new EntityWrapper<>();
                    ew.eq("processInstanceId",application.getProcessInstanceId());
                    return applicationMapper.selectList(ew).get(0);
          }

          @RequestMapping(value = "toSucessBaoMing")
          public ModelAndView toSucessBaoMing(@RequestBody Application application){
                    ModelAndView mav = new ModelAndView();
                    applicationService.handleApplication(application.getProcessInstanceId());
                    applicationService.getMianShi(application.getProcessInstanceId(),"admin");
                    mav.setViewName("admin/main");
                    return mav;
          }

          @RequestMapping(value = "toSucessMianShi")
          public ModelAndView toSucessMianShi(@RequestBody Application application){
                    ModelAndView mav = new ModelAndView();
                    applicationService.handleApplication(application.getProcessInstanceId());
                    applicationService.getPeiXun(application.getProcessInstanceId(),"admin");
                    mav.setViewName("admin/main");
                    return mav;
          }

          @RequestMapping(value = "toErrorBaoMing")
          public ModelAndView toErrorBaoMing(@RequestBody Approveapplication approveapplication){
                    ModelAndView mav = new ModelAndView();
                    applicationService.handleAppplicationError(approveapplication);
                    mav.setViewName("admin/main");
                    return mav;
          }

          //全部审核成功
          @RequestMapping(value = "toSucessPeiXun")
          public ModelAndView toSucessPeiXun(@RequestBody Application application){
                    ModelAndView mav = new ModelAndView();
                    applicationService.handleApplication(application.getProcessInstanceId());
                    applicationService.successShenhe(application.getProcessInstanceId());
                    mav.setViewName("admin/main");
                    return mav;
          }


          @RequestMapping(value = "toErrorPeiXun")
          public ModelAndView toErrorPeiXun(@RequestBody Approveapplication approveapplication){
                    ModelAndView mav = new ModelAndView();
                    applicationService.handleAppplicationError(approveapplication);
                    mav.setViewName("admin/main");
                    return mav;
          }

          @RequestMapping(value = "/image", method = RequestMethod.GET)
          public void image(HttpServletResponse response) {
                    EntityWrapper<Application> ew = new EntityWrapper<>();
                    ew.eq("phone",UserController.phone);
                    Application application = applicationMapper.selectList(ew).get(0);
                    System.out.println("生成的图片的流程id是:"+application.getProcessInstanceId());
                    try {
                              InputStream is = applicationService.getImage(application.getProcessInstanceId());
                              if (is == null)
                                        return;

                              response.setContentType("image/png");

                              BufferedImage image = ImageIO.read(is);
                              OutputStream out = response.getOutputStream();
                              ImageIO.write(image, "png", out);

                              is.close();
                              out.close();
                    } catch (Exception ex) {
                              System.out.println("查看流程图失败!");
                    }
          }
}

