package com.xx.demo.service;

import com.xx.demo.entity.Application;
import com.baomidou.mybatisplus.service.IService;
import com.xx.demo.entity.Approveapplication;
import com.xx.demo.entity.HistoryApplication;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-27
 */
public interface ApplicationService extends IService<Application> {

          /**
           * 用户ftp用户的图片上传,返还图片的上传路径
           */
          String getImageUrl(File file);

          /**
           * 提交申请开启流程
           * @param application
           * @return
           */
          String getApplicationProcess(Application application);

          /**
           * 报名表初审
           * @param processInstanceId
           * @param name
           * @return
           */
          void getBaoMingBiao(String processInstanceId,String name);

          /**
           * 面试审查
           * @param processInstanceId
           * @param name
           */
         void getMianShi(String processInstanceId,String name);

          /**
           * 集中培训
           * @param processInstanceId
           * @param name
           */
         void getPeiXun(String processInstanceId,String name);

         void successShenhe(String processInstanceId);

          /**
           * 查看当前流程的流程图功能
           */
          String getDiagram(String processInstanceId);

          /**
           * 查看当前登录用户的待审批任务<br>
           * 比如提交给admin
           */
         List<Application> getApplicationTask(String name);

          /**
           * 审批人处理信息 --> 通过
           */
          void handleApplication(String processInstanceId);

          /**
           * 审批人处理信息 --> 驳回
           */
          void handleAppplicationError(Approveapplication approveapplication);

          /**
           * 根据流程id来查询申请任务
           * @param processInstanced
           * @return
           */
         Application getApplications(String processInstanced);

          /**
           * 查询任务的历史流程记录
           */
          List<HistoryApplication> getHistorySubTask(String processInstanceId);


          InputStream getImage(String processInstanceId);

}
