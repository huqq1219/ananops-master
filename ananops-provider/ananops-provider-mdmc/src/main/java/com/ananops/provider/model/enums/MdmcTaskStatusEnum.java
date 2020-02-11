package com.ananops.provider.model.enums;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;

/**
 * Created by rongshuai on 2019/11/26 13:33
 */

public enum MdmcTaskStatusEnum {

    QuXiao(1,"用户负责人审核工单未通过，工单已取消"),

    ShenHeZhong1(2, "维修申请提交后，进入审核"),

    JieDan1(3, "审核通过，待服务商接单"),

    JieDan2(4, "服务商业务员已接单，待分配工程师"),

    JieDan3(5,"已分配工程师，待工程师接单"),

    ZhiXing(6, "工程师已接单，进入维修中"),

    BeiJian(7,"工程师提交备件方案后，待备件库管理员审核"),

    BeiJian2(8,"备件库管理员已处理备件申请，待用户负责人确认"),

    ZhiXing2(9,"用户负责人通过备件方案，工程师开始二次维修"),

    ShenHeZhong2(10, "工程师提交维修结果，待值机员确认"),

    ShenHeZhong3(11, "值机员确认，待用户负责人审核账单"),

    DaiPingJia(12, "用户负责人支付完成，待值机员评价"),

    WanCheng(13, "值机员评价完成，订单完成"),

    Reject1(14,"服务商业务员拒绝工单，待平台服务员重新派单"),

    Reject2(15,"工程师拒绝工单，待服务商重新派单"),

    Reject3(16,"备件库管理员驳回备品备件方案，待工程师重新提交备品备件申请"),

    Reject4(17,"用户负责人驳回备品备件方案，待工程师重新提交备品备件申请");

    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    MdmcTaskStatusEnum(int statusNum,String statusMsg){
        this.statusNum = statusNum;
        this.statusMsg = statusMsg;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public static String getStatusMsg(int statusNum){
        for(MdmcTaskStatusEnum ele:MdmcTaskStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException(ErrorCodeEnum.GL9999094);
    }

}
