package com.ananops.provider.model.enums;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;

public enum MdmcItemStatusEnum {

    Default(1,"不需要备件"),

    ShenHe(2, "维修工填了备件方案，备件审核"),

    Approval(3, "用户负责人通过备件方案"),

    Reject(4,"驳回备件方案");

    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    MdmcItemStatusEnum(int statusNum,String statusMsg){
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
        for(MdmcItemStatusEnum ele:MdmcItemStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException(ErrorCodeEnum.GL9999094);
    }



}
