package com.xx.demo.utils;

import java.util.Scanner;

public class Test {

          public static void main(String args[]){
                    System.out.println("请输入密码:");
                    Scanner scanner = new Scanner(System.in);
                    String username = scanner.nextLine();
                    if (username.length() <8){
                          System.out.println("密码的长度不得小于8位");
                    }else {
                              String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
                              if(username.matches(PW_PATTERN)){
                                        System.out.println("密码符合规范");
                              }else {
                                        System.out.println("密码必须包含数字，字母和特殊符号");
                              }
                    }


          }
}
