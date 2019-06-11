package com.xx.demo.service.impl;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * webscoket 服务类
 */

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
          static Log log = LogFactory.getLog(WebSocketServer.class);
          //静态变量,用来记录当前在线连接数.应该把他设计成线程安全的.
          private static int onlineCount = 0;

          //是线程安全的set,用来存放每个客户端对应的
          private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

          //与服务端建立的会话,需要他来发送信息
          private Session session;

          //接受sid
          private String sid = "";

          /**
           * 建立连接时调用的方法
           */
          @OnOpen
          public void OnOpen(Session session, @PathParam("sid") String sid) {
                    this.session = session;
                    webSocketSet.add(this);
                    addOnlineCount();
                    log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
                    this.sid = sid;
                    try {
                              sendMessage("已成功与服务器建立websocket连接");
                    } catch (IOException e) {
                              log.error("websocket IO异常");
                    }
          }

          /**
           * 关闭连接时调用的方法
           */
          @OnClose
          public void OnClose() {
                    webSocketSet.remove(this);
                    subOnlineCount();
                    log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
          }

          /**
           * 接受到客户端消息后调用的方法
           */
          @OnMessage
          public void OnMessage(String message) {
                    log.info("收到来自窗口" + sid + "的信息:" + message);
                    //收到客户端发来的消息的时候,可以群发消息进行通知
                    for (WebSocketServer ws : webSocketSet) {
                              try {
                                        ws.sendMessage(message);
                              } catch (IOException e) {
                                        e.printStackTrace();
                              }
                    }
          }

          /**
           * 群发自定义消息
           */
          public static void sendInfo(String message, @PathParam("sid") String sid, @PathParam("ownSid") String ownSid) {
                    log.info("推送消息到窗口" + sid + "，推送内容:" + message);
                    for (WebSocketServer item : webSocketSet) {
                              try {
                                        //这里可以设定只推送给这个sid的，为null则全部推送
                                        if(sid==null) {
                                                  item.sendMessage(message);
                                        }else if(item.sid.equals(sid)){
                                                  item.sendMessage(ownSid+":"+message);
                                        }
                              } catch (IOException e) {
                                        continue;
                              }
                    }
          }

          /**
           * @param session
           * @param error
           */
          @OnError
          public void onError(Session session, Throwable error) {
                    log.error("发生错误");
                    error.printStackTrace();
          }

          /**
           * 实现服务器主动推送
           */
          public void sendMessage(String message) throws IOException {
                    this.session.getBasicRemote().sendText(message);
          }

          public static synchronized void addOnlineCount() {
                    WebSocketServer.onlineCount++;
          }

          public static synchronized void subOnlineCount() {
                    WebSocketServer.onlineCount--;
          }

          public static synchronized int getOnlineCount() {
                    return onlineCount;
          }
}
