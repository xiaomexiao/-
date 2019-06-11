package com.xx.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class HistoryApplication implements Serializable {
          private static final long serialVersionUID = 1L;

          /**
           * 任务流程id
           */
          @ApiModelProperty(value = "任务流程id")
          protected String processInstanceId;
          /**
           * 运行时任务id
           */
          @ApiModelProperty(value = "运行时任务id")
          protected String taskId;
          /**
           * 任务名称
           */
          @ApiModelProperty(value = "任务名称")
          protected String taskName;
          /**
           * 执行人
           */
          @ApiModelProperty(value = "执行人")
          protected String assigne;
          /**
           * 开始日期
           */
          @ApiModelProperty(value = "开始日期 ")
          protected Date startTime;
          /**
           * 结束日期
           */
          @ApiModelProperty(value = "结束日期")
          protected Date endTime;


          public HistoryApplication() {
          }

          public String getProcessInstanceId() {
                    return processInstanceId;
          }

          public void setProcessInstanceId(String processInstanceId) {
                    this.processInstanceId = processInstanceId;
          }

          public String getTaskId() {
                    return taskId;
          }

          public void setTaskId(String taskId) {
                    this.taskId = taskId;
          }

          public String getTaskName() {
                    return taskName;
          }

          public void setTaskName(String taskName) {
                    this.taskName = taskName;
          }

          public String getAssigne() {
                    return assigne;
          }

          public void setAssigne(String assigne) {
                    this.assigne = assigne;
          }

          public Date getStartTime() {
                    return startTime;
          }

          public void setStartTime(Date startTime) {
                    this.startTime = startTime;
          }

          public Date getEndTime() {
                    return endTime;
          }

          public void setEndTime(Date endTime) {
                    this.endTime = endTime;
          }

          @Override
          public String toString() {
                    return "HistorySubTask{" +
                              "processInstanceId=" + processInstanceId +
                              ", taskId=" + taskId +
                              ", taskName=" + taskName +
                              ", assigne=" + assigne +
                              ", startTime=" + startTime +
                              ", endTime=" + endTime +
                              "}";
          }
}
