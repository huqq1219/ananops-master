package com.ananops.provider.core.aspect;

import com.ananops.PubUtils;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.utils.RequestUtil;
import com.ananops.provider.core.annotation.AnanLogAnnotation;
import com.ananops.provider.model.domain.*;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.TaskStatusEnum;
import com.ananops.provider.service.*;
import com.ananops.wrapper.Wrapper;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Created by rongshuai on 2019/12/9 17:42
 */
@Slf4j
@Aspect
@Component
public class AnanLogAspect {

    @Resource
    ImcInspectionTaskLogService imcInspectionTaskLogService;
    @Resource
    ImcInspectionItemLogService imcInspectionItemLogService;
    @Resource
    ImcInspectionItemService imcInspectionItemService;
    @Resource
    ImcInspectionTaskService imcInspectionTaskService;
    @Resource
    SpcCompanyFeignApi spcCompanyFeignApi;
    @Resource
    SpcEngineerFeignApi spcEngineerFeignApi;

    private ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    private static final int MAX_SIZE = 2000;

    /**
     * Log annotation.
     */
    @Pointcut("@annotation(com.ananops.provider.core.annotation.AnanLogAnnotation)")//定义切点
    public void logAnnotation() {
    }

    /**
     * Do before.
     */
    @Before("logAnnotation()")//在切点方法之前执行
    public void doBefore() {
        this.threadLocal.set(new Date(System.currentTimeMillis()));
    }

    /**
     * Do after.
     *
     * @param joinPoint   the join point
     * @param returnValue the return value
     */
    @AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")//在切点方法返回后执行
    public void doAfter(final JoinPoint joinPoint, final Object returnValue) {
        if (returnValue instanceof Wrapper) {//如果返回的值是经过Wrapper包装的
            Wrapper result = (Wrapper) returnValue;

            if (!PubUtils.isNull(result) && result.getCode() == Wrapper.SUCCESS_CODE) {//如果操作结果非空，并且成功
                this.handleLog(joinPoint, result);//处理操作日志
            }

        }

    }

    private void handleLog(final JoinPoint joinPoint, final Object result) {//日志处理
        final Date startTime = this.threadLocal.get();
        final Date endTime = new Date(System.currentTimeMillis());
        HttpServletRequest request = RequestUtil.getRequest();
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        try{
            final Object[] args = joinPoint.getArgs();
            final String targetName = joinPoint.getTarget().getClass().getName();//获取被代理对象的类名
            final Class targetClass = Class.forName(targetName);//获取被代理的对象
            final Method[] methods = targetClass.getMethods();//获取被代理对象的全部方法
            final String targetMethod = joinPoint.getSignature().getName();//获取连接点的方法名
            String movement="";//当前操作的描述
            final Long taskId;//当前任务的ID
            final Long itemId;//当前任务子项的ID（如果有的话）
            final Integer status;//当前日志对应的巡检状态
            AnanLogAnnotation relog = giveController(joinPoint);
            if(relog == null){
                return ;
            }
            //获取客户端操作系统
            final String os = userAgent.getOperatingSystem().getName();
            //获取客户端浏览器
            final String browser = userAgent.getBrowser().getName();
            final String ipAddress = RequestUtil.getRemoteAddr(request);

            //根据被代理的接口传入参数的不同，进行不同的日志记录
            //获取当前操作的描述
            for(int i=0;i<methods.length;i++){
                Method method = methods[i];
                //判断是否是这个方法
                if(method.getName().equals(targetMethod)){
                    Class[] clazzs = method.getParameterTypes();
                    //判断参数是否一样
                    if(clazzs.length == args.length){
                        movement = method.getAnnotation(ApiOperation.class).value();
                        System.out.println(movement);
                    }
                }
            }
            //获取当前操作对应的任务ID
            Wrapper wrapper = (Wrapper) result;
            if(wrapper.getResult().getClass().getName().equals(ImcAddInspectionTaskDto.class.getName())){
                //如果当前的日志是编辑巡检任务的
                ImcInspectionTaskLog imcInspectionTaskLog = new ImcInspectionTaskLog();
                ImcAddInspectionTaskDto imcAddInspectionTaskDto = (ImcAddInspectionTaskDto) wrapper.getResult();
                taskId = imcAddInspectionTaskDto.getId();
                status = imcAddInspectionTaskDto.getStatus();
                LoginAuthDto loginUser = RequestUtil.getLoginUser();
                if(imcInspectionTaskLogService.createInspectionTaskLog(createTaskLog(taskId,status,startTime,endTime,movement,os,browser,ipAddress),loginUser) == 1){
                    System.out.println("巡检任务日志创建成功" + imcInspectionTaskLog);
                }
                List<ImcAddInspectionItemDto> imcAddInspectionItemDtoList = imcAddInspectionTaskDto.getImcAddInspectionItemDtoList();
                if(imcAddInspectionItemDtoList.size()>0){//如果在创建巡检任务的同时还创建了巡检任务子项
                    imcAddInspectionItemDtoList.forEach(item->{
                        ImcInspectionItemLog imcInspectionItemLog = createItemLog(item.getId(),taskId,item.getStatus(),startTime,endTime,"编辑巡检任务子项记录",os,browser,ipAddress);
                        if(imcInspectionItemLogService.createInspectionItemLog(imcInspectionItemLog,loginUser) == 1){
                            System.out.println("巡检任务子项日志创建成功" + imcInspectionItemLog);
                        }
                    });
                }
            }else if(wrapper.getResult().getClass().getName().equals(ImcAddInspectionItemDto.class.getName())){
                //如果当前的日志是巡检任务子项的
                ImcAddInspectionItemDto imcAddInspectionItemDto = (ImcAddInspectionItemDto) wrapper.getResult();
                taskId = imcAddInspectionItemDto.getInspectionTaskId();
                itemId = imcAddInspectionItemDto.getId();
                status = imcAddInspectionItemDto.getStatus();
                ImcInspectionItemLog imcInspectionItemLog = createItemLog(itemId,taskId,status,startTime,endTime,movement,os,browser,ipAddress);
                LoginAuthDto loginUser = RequestUtil.getLoginUser();
                if(imcInspectionItemLogService.createInspectionItemLog(imcInspectionItemLog,loginUser) == 1){
                    System.out.println("巡检任务子项日志创建成功" + imcInspectionItemLog);
                }
            }else if(wrapper.getResult().getClass().getName().equals(ImcDeviceOrder.class.getName())){
                //如果当前日志是备品备件的
                ImcDeviceOrder imcDeviceOrder = (ImcDeviceOrder) wrapper.getResult();
                taskId = imcDeviceOrder.getInspectionTaskId();
                itemId = imcDeviceOrder.getInspectionItemId();
                ImcInspectionItemLog imcInspectionItemLog = createItemLog(itemId,taskId,getItem(itemId).getStatus(),startTime,endTime,movement,os,browser,ipAddress);
                LoginAuthDto loginUser = RequestUtil.getLoginUser();
                if(imcInspectionItemLogService.createInspectionItemLog(imcInspectionItemLog,loginUser) == 1){
                    System.out.println("巡检任务子项日志创建成功" + imcInspectionItemLog);
                }
            }else if(wrapper.getResult().getClass().getName().equals(ImcInspectionReview.class.getName())){
                //如果当前日志是评论的
                ImcInspectionReview imcInspectionReview = (ImcInspectionReview) wrapper.getResult();
                taskId = imcInspectionReview.getInspectionTaskId();
                status = getTask(taskId).getStatus();
                ImcInspectionTaskLog imcInspectionTaskLog = createTaskLog(taskId,status,startTime,endTime,movement,os,browser,ipAddress);
                LoginAuthDto loginUser = RequestUtil.getLoginUser();
                if(imcInspectionTaskLogService.createInspectionTaskLog(imcInspectionTaskLog,loginUser) == 1){
                    System.out.println("巡检任务日志创建成功" + imcInspectionTaskLog);
                }
            }else if(wrapper.getResult().getClass().getName().equals(ImcTaskChangeStatusDto.class.getName())){
                //如果当前日志是修改任务状态的
                ImcTaskChangeStatusDto imcTaskChangeStatusDto = (ImcTaskChangeStatusDto) wrapper.getResult();
                taskId = imcTaskChangeStatusDto.getTaskId();
                status = imcTaskChangeStatusDto.getStatus();
                String statusMsg = imcTaskChangeStatusDto.getStatusMsg();
                ImcInspectionTaskLog imcInspectionTaskLog;
                if("服务商拒单".equals(movement)||"服务商接单".equals(movement)||"同意执行巡检任务".equals(movement)||"否决执行巡检任务".equals(movement)){
                     imcInspectionTaskLog = createTaskLog(taskId,status,startTime,endTime,movement ,os,browser,ipAddress);
                } else{
                    imcInspectionTaskLog = createTaskLog(taskId,status,startTime,endTime,statusMsg,os,browser,ipAddress);
                }
                LoginAuthDto loginUser;
                try{//如果是模块内部调用产生的日志打印，则通过此方法获取
                    loginUser = RequestUtil.getLoginUser();
                }catch (Exception e){//如果是模块间调用产生的日志打印，则通过此方法获取
                    loginUser = imcTaskChangeStatusDto.getLoginAuthDto();
                }
                if(imcInspectionTaskLogService.createInspectionTaskLog(imcInspectionTaskLog,loginUser) == 1){
                    System.out.println("巡检任务日志创建成功" + imcInspectionTaskLog);
                }
            }else if(wrapper.getResult().getClass().getName().equals(ImcItemChangeStatusDto.class.getName())){
                //如果当前日志是修改任务子项状态的
                ImcItemChangeStatusDto imcItemChangeStatusDto = (ImcItemChangeStatusDto) wrapper.getResult();
                itemId = imcItemChangeStatusDto.getItemId();
                status = imcItemChangeStatusDto.getStatus();
                Example example = new Example(ImcInspectionItem.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id",itemId);
                taskId = imcInspectionItemService.selectByExample(example).get(0).getInspectionTaskId();
                String statusMsg = imcItemChangeStatusDto.getStatusMsg();
                ImcInspectionItemLog imcInspectionItemLog;
                if("工程师拒单".equals(movement)||"工程师接单".equals(movement)){
                    imcInspectionItemLog = createItemLog(itemId,taskId,status,startTime,endTime,movement,os,browser,ipAddress);
                }else{
                    imcInspectionItemLog = createItemLog(itemId,taskId,status,startTime,endTime,statusMsg,os,browser,ipAddress);
                }
                LoginAuthDto loginUser;
                try{//如果是模块内部调用产生的日志打印，则通过此方法获取
                    loginUser = RequestUtil.getLoginUser();
                }catch (Exception e){//如果是模块间调用产生的日志打印，则通过此方法获取
                    loginUser = imcItemChangeStatusDto.getLoginAuthDto();
                }
                if(imcInspectionItemLogService.createInspectionItemLog(imcInspectionItemLog,loginUser) == 1){
                    System.out.println("巡检任务子项日志创建成功" + imcInspectionItemLog);
                }
                if(imcInspectionTaskService.getTaskByTaskId(taskId).getStatus()==TaskStatusEnum.WAITING_FOR_CONFIRM.getStatusNum()){
                    //如果在巡检任务子项状态改变的同时，巡检任务的状态也改变

                    ImcInspectionTaskLog imcInspectionTaskLog = createTaskLog(taskId,4,startTime,endTime,"修改巡检任务状态为" + TaskStatusEnum.WAITING_FOR_CONFIRM.getStatusMsg(),os,browser,ipAddress);
                    if(imcInspectionTaskLogService.createInspectionTaskLog(imcInspectionTaskLog,loginUser)==1){
                        System.out.println("巡检任务日志创建成功" + imcInspectionTaskLog);
                    }
                }
            }else if(wrapper.getResult().getClass().getName().equals(TaskNameChangeDto.class.getName())){
                //如果当前日志是修改任务名字的
                TaskNameChangeDto taskNameChangeDto = (TaskNameChangeDto) wrapper.getResult();
                taskId = taskNameChangeDto.getTaskId();
                String taskName = taskNameChangeDto.getTaskName();
                ImcInspectionTask imcInspectionTask = getTask(taskId);
                status = imcInspectionTask.getStatus();
                String details = "为" + taskName;
                ImcInspectionTaskLog imcInspectionTaskLog = createTaskLog(taskId,status,startTime,endTime,movement + details,os,browser,ipAddress);
                LoginAuthDto loginUser;
                try{//如果是模块内部调用产生的日志打印，则通过此方法获取
                    loginUser = RequestUtil.getLoginUser();
                }catch (Exception e){//如果是模块间调用产生的日志打印，则通过此方法获取
                    loginUser = taskNameChangeDto.getLoginAuthDto();
                }
                if(imcInspectionTaskLogService.createInspectionTaskLog(imcInspectionTaskLog,loginUser) == 1){
                    System.out.println("巡检任务日志创建成功" + imcInspectionTaskLog);
                }
            }else if(wrapper.getResult().getClass().getName().equals(TaskChangeFacilitatorDto.class.getName())){
                //如果当前日志是修改任务对应的服务商的
                TaskChangeFacilitatorDto taskChangeFacilitatorDto = (TaskChangeFacilitatorDto) wrapper.getResult();
                taskId = taskChangeFacilitatorDto.getTaskId();
                ImcInspectionTask imcInspectionTask = imcInspectionTaskService.getTaskByTaskId(taskId);
                status = imcInspectionTask.getStatus();
                Long facilitatorId = imcInspectionTask.getFacilitatorId();
                String facilitatorName = spcCompanyFeignApi.getCompanyDetailsById(facilitatorId).getResult().getGroupName();
                String details = "为" + facilitatorName;
                ImcInspectionTaskLog imcInspectionTaskLog = createTaskLog(taskId,status,startTime,endTime,movement + details,os,browser,ipAddress);
                LoginAuthDto loginUser;
                try{//如果是模块内部调用产生的日志打印，则通过此方法获取
                    loginUser = RequestUtil.getLoginUser();
                }catch (Exception e){//如果是模块间调用产生的日志打印，则通过此方法获取
                    loginUser = taskChangeFacilitatorDto.getLoginAuthDto();
                }
                if(imcInspectionTaskLogService.createInspectionTaskLog(imcInspectionTaskLog,loginUser) == 1){
                    System.out.println("巡检任务日志创建成功" + imcInspectionTaskLog);
                }
            }else if(wrapper.getResult().getClass().getName().equals(ItemChangeMaintainerDto.class.getName())){
                //如果当前日志是修改任务子项对应的工程师的
                ItemChangeMaintainerDto itemChangeMaintainerDto = (ItemChangeMaintainerDto) wrapper.getResult();
                itemId = itemChangeMaintainerDto.getItemId();
                ImcInspectionItem imcInspectionItem = imcInspectionItemService.getItemByItemId(itemId);
                status = imcInspectionItem.getStatus();
                taskId = imcInspectionItem.getInspectionTaskId();
                Long maintainerId = imcInspectionItem.getMaintainerId();
                String maintainerName = spcEngineerFeignApi.getEngineerById(maintainerId).getResult().getUserName();
                String details = "为" + maintainerName;
                ImcInspectionItemLog imcInspectionItemLog = createItemLog(itemId,taskId,status,startTime,endTime,movement + details,os,browser,ipAddress);
                LoginAuthDto loginUser;
                try{//如果是模块内部调用产生的日志打印，则通过此方法获取
                    loginUser = RequestUtil.getLoginUser();
                }catch (Exception e){//如果是模块间调用产生的日志打印，则通过此方法获取
                    loginUser = itemChangeMaintainerDto.getLoginAuthDto();
                }
                if(imcInspectionItemLogService.createInspectionItemLog(imcInspectionItemLog,loginUser) == 1){
                    System.out.println("巡检任务子项日志创建成功" + imcInspectionItemLog);
                }
            }

        }catch(Exception ex){
            log.error("获取注解类出现异常={}", ex.getMessage(), ex);
        }
    }

    /**
     * 根据任务Id获取对应的任务
     * @param taskId
     * @return
     */
    public ImcInspectionTask getTask(Long taskId){
        Example example = new Example(ImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        return imcInspectionTaskService.selectByExample(example).get(0);
    }

    /**
     * 根据任务子项Id获取对应的任务子项
     * @param itemId
     * @return
     */
    public ImcInspectionItem getItem(Long itemId){
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        return imcInspectionItemService.selectByExample(example).get(0);
    }

    /**
     * 创建巡检任务日志
     * @param taskId
     * @param status
     * @param startTime
     * @param endTime
     * @param movement
     * @param os
     * @param browser
     * @param ipAddress
     * @return
     */
    public ImcInspectionTaskLog createTaskLog(Long taskId,Integer status,Date startTime,Date endTime,String movement,String os,String browser,String ipAddress){//创建一条任务的日志
        ImcInspectionTaskLog imcInspectionTaskLog = new ImcInspectionTaskLog();
        imcInspectionTaskLog.setTaskId(taskId);
        imcInspectionTaskLog.setStatus(status);
        imcInspectionTaskLog.setCreatedTime(startTime);
        imcInspectionTaskLog.setUpdateTime(endTime);
        imcInspectionTaskLog.setMovement(movement);
        imcInspectionTaskLog.setStatusTimestamp(endTime);
        imcInspectionTaskLog.setOs(os);
        imcInspectionTaskLog.setBrowser(browser);
        imcInspectionTaskLog.setIpAddress(ipAddress);
        return imcInspectionTaskLog;
    }

    /**
     * 创建巡检任务子项日志
     * @param itemId
     * @param taskId
     * @param status
     * @param startTime
     * @param endTime
     * @param movement
     * @param os
     * @param browser
     * @param ipAddress
     * @return
     */
    public ImcInspectionItemLog createItemLog(Long itemId,Long taskId,Integer status,Date startTime,Date endTime,String movement,String os,String browser,String ipAddress){
        ImcInspectionItemLog imcInspectionItemLog = new ImcInspectionItemLog();
        imcInspectionItemLog.setItemId(itemId);
        imcInspectionItemLog.setTaskId(taskId);
        imcInspectionItemLog.setCreatedTime(startTime);
        imcInspectionItemLog.setUpdateTime(endTime);
        imcInspectionItemLog.setMovement(movement);
        imcInspectionItemLog.setStatusTimestamp(endTime);
        imcInspectionItemLog.setStatus(status);
        imcInspectionItemLog.setOs(os);
        imcInspectionItemLog.setBrowser(browser);
        imcInspectionItemLog.setIpAddress(ipAddress);
        return imcInspectionItemLog;
    }
    /**
     * 是否存在注解, 如果存在就记录日志
     */
    private static AnanLogAnnotation giveController(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        String methodName = joinPoint.getSignature().getName();
        if (null != methods && 0 < methods.length) {
            for (Method met : methods) {
                AnanLogAnnotation relog = met.getAnnotation(AnanLogAnnotation.class);
                if (null != relog && methodName.equals(met.getName())) {
                    return relog;
                }
            }
        }

        return null;
    }
}
