package service.hystrix;

import com.ananops.wrapper.Wrapper;
import model.dto.CreateNewOrderFeignDto;
import model.dto.ProcessOrderFeignDto;
import service.RdcDeviceOrderFeignApi;

import java.math.BigDecimal;
import java.util.List;

public class RdcDeviceOrderFeignApiFeignHystrix implements RdcDeviceOrderFeignApi {
    @Override
    public Wrapper<Object> createOrder(CreateNewOrderFeignDto createNewOrderFeignDto) {
        return null;
    }

    @Override
    public Wrapper<Object> operation(ProcessOrderFeignDto processOrderDto) {
        return null;
    }

    @Override
    public Wrapper<Object> getTodoDeviceOrderByUserId(Long userId) {
        return null;
    }

    @Override
    public Wrapper<Object> getDoneDeviceOrderByUserId(Long userId) {
        return null;
    }

    @Override
    public Wrapper<Object> getDeviceOrderByUserId(Long userId) {
        return null;
    }

    @Override
    public Wrapper<Object> getDeviceOrderByObject(Long objectId, Integer objectType) {
        return null;
    }

    @Override
    public Wrapper<Object> getAllDevice() {
        return null;
    }

    @Override
    public Wrapper<Object> getTotalCost(Long orderId) {
        return null;
    }

    @Override
    public Wrapper<Object> getPriceOfDevices(List<Long> deviceIdArray) {
        return null;
    }
}
