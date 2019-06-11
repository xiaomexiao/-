package com.xx.demo;


import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ServletComponentScan
@MapperScan("com.xx.demo.mapper")
public class AidEducationApplication {

         /*extends SpringBootServletInitializer
         @Override
          protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
                    return application.sources(AidEducationApplication.class);
          }*/

          public static void main(String[] args) {
                    SpringApplication.run(AidEducationApplication.class, args);
          }

}
