package com.xx.demo.controller;

import com.xx.demo.entity.Chat;
import com.xx.demo.service.impl.WebSocketServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "chat")
public class ChatController {

          @RequestMapping(value = "toChat")
          public String tochat(@RequestBody Chat chat) {
               System.out.println("消息:" +chat.getMessage()+"端口号:" +chat.getSid());
               WebSocketServer.sendInfo(chat.getMessage(),chat.getSid(),chat.getOwnSid());
               return "成功返回参数";
          }
}
