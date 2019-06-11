package com.xx.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-04-09
 */
public class Approveapplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 流程实例id
     */
    @TableField("processInstanceId")
    private String processInstanceId;
    /**
     * activiti任务id
     */
    @TableField("taskId")
    private String taskId;
    /**
     * 用户id
     */
    @TableField("userId")
    private String userId;
    /**
     * 0不通过,1通过
     */
    @TableField("adminResult")
    private Integer adminResult;
    /**
     * 审批意见
     */
    private String comment;
    /**
     * 审批日期
     */
    @TableField("adminTime")
    private String adminTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAdminResult() {
        return adminResult;
    }

    public void setAdminResult(Integer adminResult) {
        this.adminResult = adminResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(String adminTime) {
        this.adminTime = adminTime;
    }

    @Override
    public String toString() {
        return "Approveapplication{" +
        "id=" + id +
        ", processInstanceId=" + processInstanceId +
        ", taskId=" + taskId +
        ", userId=" + userId +
        ", adminResult=" + adminResult +
        ", comment=" + comment +
        ", adminTime=" + adminTime +
        "}";
    }
}
