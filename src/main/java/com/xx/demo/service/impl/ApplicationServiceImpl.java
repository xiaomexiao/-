package com.xx.demo.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xx.demo.entity.Approveapplication;
import com.xx.demo.entity.HistoryApplication;
import com.xx.demo.mapper.ApproveapplicationMapper;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.NativeTaskQueryImpl;
import org.apache.commons.io.FileUtils;
import com.xx.demo.entity.Application;
import com.xx.demo.mapper.ApplicationMapper;
import com.xx.demo.service.ApplicationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xx.demo.service.UserService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-27
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
          @Autowired
          private UserService userService;

          @Autowired
          private RuntimeService runtimeService;

          @Autowired
          private TaskService taskService;

          @Autowired
          private ApplicationMapper applicationMapper;

          @Autowired
          private HistoryService historyService;

          @Autowired
          private RepositoryService repositoryService;

          @Autowired
          private ProcessEngine processEngine;

          @Autowired
          private ApproveapplicationMapper approveapplicationMapper;

          @Override
          public String getImageUrl(File file) {
                    String localFile4Upload = file.getAbsolutePath();
                    String remoteFolder = "/";
                    String ftpServer = "47.100.216.7";
                    String name = "Administrator";
                    String password = "my--XX3.14159";
                    Ftp ftp = new Ftp(ftpServer,21,name,password);
                    ftp.upload(remoteFolder, FileUtil.file(localFile4Upload));
                    return file.getName();
          }

          /**
           *  流程第一步，提交表单
           * @param application
           * @return
           */
          @Override
          public String getApplicationProcess(Application application) {
                    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("zhijiaoapplication");
                    String processInstanceId = processInstance.getId();
                    Task taskAllot = taskService.createTaskQuery().processInstanceId(processInstanceId)
                              .singleResult();
                    taskService.setAssignee(taskAllot.getId(),application.getUserId());
                    taskService.complete(taskAllot.getId());
                    //申请人的姓名是当前登录人的姓名
                    String id = RandomStringUtils.random(8,true,true);
                    application.setId(id);
                    application.setProcessInstanceId(processInstanceId);
                    applicationMapper.insert(application);
                    return processInstanceId;
          }

          /**
           * 报名表审查
           * @param processInstanceId
           * @param name
           * @return
           */
          @Override
          public void getBaoMingBiao(String processInstanceId, String name) {
                    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                              .singleResult();
                    taskService.setAssignee(task.getId(), name);
                    Application application = getApplications(processInstanceId);
                    application.setState("报名表初审");
                    applicationMapper.updateById(application);
          }

          @Override
          public void getMianShi(String processInstanceId, String name) {
                    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                              .singleResult();
                    taskService.setAssignee(task.getId(), name);
                    Application application = getApplications(processInstanceId);
                    application.setState("面试审查");
                    applicationMapper.updateById(application);
          }

          @Override
          public void getPeiXun(String processInstanceId, String name) {
                    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                              .singleResult();
                    taskService.setAssignee(task.getId(), name);
                    Application application = getApplications(processInstanceId);
                    application.setState("集中培训");
                    applicationMapper.updateById(application);
          }

          @Override
          public void successShenhe(String processInstanceId) {
                    Application application = getApplications(processInstanceId);
                    application.setState("审核成功");
                    applicationMapper.updateById(application);
          }

          @Override
          public String getDiagram(String processInstanceId) {
                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                              .processInstanceId(processInstanceId).singleResult();
                    String processDefinitionId = StringUtils.EMPTY;
                    if (processInstance == null) {
                              HistoricProcessInstance processInstanceHistory =
                                        historyService.createHistoricProcessInstanceQuery()
                                                  .processInstanceId(processInstanceId).singleResult();
                              if (processInstanceHistory == null) {
                                        return null;
                              } else {
                                        processDefinitionId = processInstanceHistory.getProcessDefinitionId();
                              }
                    } else {
                              processDefinitionId = processInstance.getProcessDefinitionId();
                    }

                    String forName = "宋体";
                    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
                    List<String> currentActs = Collections.EMPTY_LIST;
                    if (processInstance != null)
                              currentActs = runtimeService.getActiveActivityIds(processInstance.getId());

                    InputStream is = processEngine.getProcessEngineConfiguration()
                              .getProcessDiagramGenerator()
                              .generateDiagram(bpmnModel, "png", currentActs, new ArrayList<String>(),
                                        forName, forName, forName, null, 1.0);

                    try {
                              String result = "G:\\IDEArepository\\AidEducation\\src\\main\\resources\\static\\images\\applicationprocess.png";
                              FileUtils.copyInputStreamToFile(is, new File(result));
                              return result;
                    } catch (IOException e) {
                              e.printStackTrace();
                    }
                    return null;
          }

          @Override
          public List<Application> getApplicationTask(String name) {
                    String sql = "SELECT * FROM act_ru_task WHERE ASSIGNEE_= '" + name + "'";
                    Object getTaskListBySql = taskService.createNativeTaskQuery().sql(sql);
                    List<Task> tasks = ((NativeTaskQueryImpl) getTaskListBySql).list();
                    List<Application> applicationList = new ArrayList<>();
                    for(Task task : tasks){
                           Application application = new Application();
                           application.setId(RandomStringUtils.random(8,true,true));
                           String phone = getApplications(task.getProcessInstanceId()).getPhone();
                           application.setProcessInstanceId(task.getProcessInstanceId());
                           application.setState(getApplications(task.getProcessInstanceId()).getState());
                           if(userService.getUserByPhone(phone).getId()!=null){
                               application.setUserId(String.valueOf(userService.getUserByPhone(phone).getId()));
                           }
                           else {
                               application.setUserId("根据手机号查不到用户名,缺失的脏数据");
                           }

                           application.setName(userService.getUserByPhone(phone).getName());
                           application.setMail(getApplications(task.getProcessInstanceId()).getMail());
                           application.setPhone(phone);
                           application.setResumeurl(getApplications(task.getProcessInstanceId()).getResumeurl());
                           application.setReason(getApplications(task.getProcessInstanceId()).getReason());
                           applicationList.add(application);
                    }
                    return applicationList;
          }

          @Override
          public void handleApplication(String processInstanceId){
                    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
                    taskService.claim(task.getId(), "admin");
                    taskService.complete(task.getId());
          }

          @Override
          public void handleAppplicationError(Approveapplication approveapplication) {
                    //增加不通过的信息到application approve的表中去
                    approveapplication.setAdminResult(1);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    approveapplication.setAdminTime(df.format(new DateTime()));
                    approveapplicationMapper.insert(approveapplication);
                    Task task = taskService.createTaskQuery().processInstanceId(approveapplication.getProcessInstanceId()).singleResult();
                    //删除流程下的此任务节点
                    runtimeService.deleteProcessInstance(approveapplication.getProcessInstanceId(),task.getId());
          }

          @Override
          public Application getApplications(String processInstanced) {
                    EntityWrapper<Application> ew = new EntityWrapper<>();
                    ew.eq("processInstanceId",processInstanced);
                    return selectList(ew).get(0);
          }

          @Override
          public List<HistoryApplication> getHistorySubTask(String processInstanceId) {
                    List<HistoricTaskInstance> historicTaskInstances = ProcessEngines.getDefaultProcessEngine().getHistoryService().createHistoricTaskInstanceQuery().
                              processInstanceId(processInstanceId).list();

                    List<HistoryApplication> historySubTasks = new ArrayList<>();
                    for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
                              HistoryApplication historyApplication = new HistoryApplication();
                              historyApplication.setProcessInstanceId(historicTaskInstance.getProcessInstanceId());
                              historyApplication.setTaskId(historicTaskInstance.getId());
                              historyApplication.setTaskName(historicTaskInstance.getName());
                              historyApplication.setAssigne(historicTaskInstance.getAssignee());
                              historyApplication.setStartTime(historicTaskInstance.getStartTime());
                              historyApplication.setEndTime(historicTaskInstance.getEndTime());
                              historySubTasks.add(historyApplication);
                    }
                    return historySubTasks;
          }

          @Override
          public InputStream getImage(String processInstanceId) {

                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                              .processInstanceId(processInstanceId).singleResult();
                    String processDefinitionId = StringUtils.EMPTY;
                    if (processInstance == null) {
                              HistoricProcessInstance processInstanceHistory =
                                        historyService.createHistoricProcessInstanceQuery()
                                                  .processInstanceId(processInstanceId).singleResult();
                              if (processInstanceHistory == null) {
                                        return null;
                              } else {
                                        processDefinitionId = processInstanceHistory.getProcessDefinitionId();
                              }
                    } else {
                              processDefinitionId = processInstance.getProcessDefinitionId();
                    }
                    String forName = "宋体";
                    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
                    List<String> currentActs = Collections.EMPTY_LIST;
                    if (processInstance != null)
                              currentActs = runtimeService.getActiveActivityIds(processInstance.getId());

                    InputStream is = processEngine.getProcessEngineConfiguration()
                              .getProcessDiagramGenerator()
                              .generateDiagram(bpmnModel, "png", currentActs, new ArrayList<String>(),
                                        forName, forName, forName, null, 1.0);
                    return is;
          }
}
