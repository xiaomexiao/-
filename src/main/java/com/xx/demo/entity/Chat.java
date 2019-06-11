package com.xx.demo.entity;

public class Chat {
          private String message;
          //发给别的sid
          private String sid;
          //自己的sid
          private String ownSid;

          public String getMessage() {
                    return message;
          }

          public void setMessage(String message) {
                    this.message = message;
          }

          public String getSid() {
                    return sid;
          }

          public void setSid(String sid) {
                    this.sid = sid;
          }

          public String getOwnSid() {
                    return ownSid;
          }

          public void setOwnSid(String ownSid) {
                    this.ownSid = ownSid;
          }

          @Override
          public String toString() {
                    return "Chat{" +
                              "message='" + message + '\'' +
                              ", sid='" + sid + '\'' +
                              '}';
          }
}
