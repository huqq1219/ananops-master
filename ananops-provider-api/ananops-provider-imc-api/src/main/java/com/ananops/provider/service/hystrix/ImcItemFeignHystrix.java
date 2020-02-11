package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.ImcItemChangeStatusDto;
import com.ananops.provider.model.dto.ItemChangeMaintainerDto;
import com.ananops.provider.model.dto.ConfirmImcItemDto;
import com.ananops.provider.service.ImcItemFeignApi;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Created by rongshuai on 2019/12/18 10:57
 */
@Component
public class ImcItemFeignHystrix implements ImcItemFeignApi {

//    @Override
//    public Wrapper<List<ItemDto>> getByProjectId(Long projectId){
//        return null;
//    }
    @Override
    public Wrapper<ItemChangeMaintainerDto> modifyMaintainerByItemId(@ApiParam(name = "modifyMaintainerByItemId",value = "修改巡检任务子项对应的工程师ID")@RequestBody ItemChangeMaintainerDto itemChangeMaintainerDto){
        return null;
    }

    @Override
    public Wrapper<ImcItemChangeStatusDto> refuseImcItemByItemId(@ApiParam(name = "refuseImcItemByItemId",value = "维修工拒单（巡检任务子项）")@RequestBody ConfirmImcItemDto confirmImcItemDto){
        return null;
    }

    @Override
    public Wrapper<ImcItemChangeStatusDto> modifyImcItemStatus(@ApiParam(name = "modifyImcItemStatus",value = "修改巡检任务子项的状态")@RequestBody ImcItemChangeStatusDto imcItemChangeStatusDto){
        return null;
    }
}
