package com.xx.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-04-03
 */
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 流程实例id
     */
    @TableField("processInstanceId")
    private String processInstanceId;
    /**
     * 申请状态
     */
    private String state;
    /**
     * 用户id
     */
    @TableField("userId")
    private String userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 简历路径
     */
    private String resumeurl;
    /**
     * 申请原因
     */
    private String reason;

    public Application() {
    }

    public Application(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResumeurl() {
        return resumeurl;
    }

    public void setResumeurl(String resumeurl) {
        this.resumeurl = resumeurl;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Application{" +
        "id=" + id +
        ", processInstanceId=" + processInstanceId +
        ", state=" + state +
        ", userId=" + userId +
        ", name=" + name +
        ", mail=" + mail +
        ", phone=" + phone +
        ", resumeurl=" + resumeurl +
        ", reason=" + reason +
        "}";
    }
}
