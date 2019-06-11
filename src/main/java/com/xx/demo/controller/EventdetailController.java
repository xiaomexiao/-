package com.xx.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.Event;
import com.xx.demo.entity.Eventdetail;
import com.xx.demo.entity.User;
import com.xx.demo.service.EventService;
import com.xx.demo.service.EventdetailService;
import com.xx.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-27
 */
@Controller
@RequestMapping("/eventdetail")
@Api(value = "eventdetail", tags = "支教详情补充")
public class EventdetailController {

          @Autowired
          private EventdetailService eventdetailService;

          @Autowired
          private UserService userService;

          @Autowired
          private EventService eventService;

          @ApiOperation(value = "查询支教详情补充")
          @RequestMapping(value = "listEventDetail", method = RequestMethod.GET)
          public ModelAndView listEventDetail(String id) {
                    ModelAndView mav = new ModelAndView();
                    EntityWrapper<Event> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", id);
                    List<Event> eventList = eventService.selectList(wrapper);
                    EntityWrapper<Eventdetail> ew = new EntityWrapper<>();
                    ew.eq("eventid", id);
                    List<Eventdetail> eventdetailList = eventdetailService.selectList(ew);
                    mav.setViewName("details");
                    mav.addObject("event",eventList);
                    mav.addObject("eventdetail", eventdetailList);
                    mav.addObject("user", userService.getSessionName());
                    return mav;
          }

}

