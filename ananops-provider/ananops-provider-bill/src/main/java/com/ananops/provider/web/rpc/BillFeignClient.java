package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.mapper.BasebillMapper;
import com.ananops.provider.model.domain.Basebill;
import com.ananops.provider.service.BillFeignApi;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value = "API - BillFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BillFeignClient extends BaseController implements BillFeignApi {
    @Autowired
    BasebillMapper basebillMapper;

    @Override
    @ApiOperation(httpMethod = "GET", value = "根据工单ID查询金额")

    public Wrapper<Float> getAmountByWorkOrderId(Long workOrderId) {
        List<Basebill> list=new ArrayList<>();
        Example example = new Example(Basebill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(String.valueOf(workOrderId));
        list=basebillMapper.selectByExample(example);
        Basebill basebill1=new Basebill();
        if(list!=null&&list.size()>0){
            for (Basebill basebill:list){
                basebill1=basebill;
            }
        }
        return WrapMapper.ok(basebill1.getAmount());
    }
}
