package com.ananops.provider.model.dto;

import lombok.Getter;

@Getter
public class DeviceOrderItemInfoDto {
    
    private Long id;
    
    private String name;
    
    private String type;
    
    private String manufacture;
    
    private String model;
    
    private Integer count;
}
