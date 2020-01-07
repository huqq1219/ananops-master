package com.ananops.provider.model.enums;

import lombok.Getter;

@Getter
public enum DeviceOrderStatusEnum {
    
    ShenHeZhong(1, "审核中"),
    
    ShenHeWanCheng(2, "审核完成");
    
    
    private Integer code;
    
    private String msg;
    
    DeviceOrderStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    
   
}
