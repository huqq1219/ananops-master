package com.ananops.provider.service;

import com.ananops.provider.model.domain.Basebill;
import com.ananops.provider.model.dto.BillCreateDto;

import java.util.List;

public interface BaseService {
    public void insert(BillCreateDto billCreateDto, Float devicePrice, Float servicePrice, String transactionMethod);
    public List<Basebill> getAllBillByUserId(String userid);
    public Float getAmountByworkorderid(String workorderid);
    public Basebill getBillById(String id);
    public void modifyAmount(Basebill basebill, Float modifyAmount);
    public List<Basebill> getAllUBillBystate(String userid, String state);
    List<Basebill> getBillByWorkOrderId(String workOrderId);
}


