package com.ananops.provider.model.enums;


import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;

/**
 * Created by rongshuai on 2019/12/5 10:53
 */
public enum ItemStatusEnum {

    NO_SUCH_STATUS(-1,"没有这种状态"),

    WAITING_FOR_MAINTAINER(1,"等待分配工程师"),

    WAITING_FOR_ACCEPT(2,"等待工程师接单"),

    IN_THE_INSPECTION(3,"巡检工巡检中"),

    INSPECTION_OVER(4,"该子项巡检结束，等待甲方负责人审核"),

    VERIFIED(5,"甲方负责人已经审核结束");
    /**
     * The statusNum.
     */
    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    ItemStatusEnum(int statusNum,String statusMsg){
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
        for(ItemStatusEnum ele:ItemStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException(ErrorCodeEnum.GL9999094);
    }

    public static ItemStatusEnum getEnum(int statusNum){
        for(ItemStatusEnum ele:ItemStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele;
            }
        }
        return ItemStatusEnum.NO_SUCH_STATUS;
    }

}
