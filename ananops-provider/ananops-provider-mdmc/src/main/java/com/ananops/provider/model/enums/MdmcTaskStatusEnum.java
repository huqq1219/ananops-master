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

    JieDan2(4, "服务商业务员已接单，待维修工接单"),

    ZhiXing(5, "维修工已接单，进入维修中"),

    BeiJian(6,"维修工提交备件方案后，待用户负责人审核"),

    ZhiXing2(7,"用户负责人通过备件方案，二次维修"),

    ShenHeZhong2(8, "维修工提交维修结果，待服务商审核维修结果"),

    ShenHeZhong3(9, "服务商审核账单通过，待负责人审核账单"),

    DaiQueRen(10, "负责人审核账单通过，待值机员确认"),

    DaiZhiFu(11, "值机员确认服务，待负责人支付"),

    DaiPingJia(12, "负责人支付完成，待值机员评价"),

    WanCheng(13, "值机员评价完成，订单完成"),

    Reject1(14,"服务商业务员拒绝工单"),

    Reject2(15,"维修工拒绝工单"),

    Reject3(16,"服务商拒绝账单"),

    Reject4(17,"负责人拒绝账单");

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
