package com.xx.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.Event;
import com.xx.demo.entity.Eventdetail;
import com.xx.demo.mapper.EventdetailMapper;
import com.xx.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-26
 */
@Controller
@RequestMapping("/event")
public class EventController {

          @Autowired
          private EventService eventService;

          @Autowired
          private EventdetailMapper eventdetailMapper;

          @RequestMapping(value = "listEvent")
          @ResponseBody
          public List<Event> listEvent(String page,String limit,String title){
                 EntityWrapper<Event> eventEntityWrapper = new EntityWrapper<>();
                 eventEntityWrapper.like("title",title);
                 List<Event> events = eventService.selectList(eventEntityWrapper);
                 return events;
          }

          @RequestMapping(value = "listEventDetail")
          @ResponseBody
          public List<Eventdetail> listEventDetail(@RequestBody Eventdetail eventdetail){
                    EntityWrapper<Eventdetail> eventdetailEntityWrapper = new EntityWrapper<>();
                    eventdetailEntityWrapper.eq("eventid",eventdetail.getId());
                    return eventdetailMapper.selectList(eventdetailEntityWrapper);
          }
}

