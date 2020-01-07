package com.ananops.provider.exception;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * SPC Biz Exception(加盟服务商业务逻辑异常)
 *
 * Created by bingyueduan on 2019/12/28.
 */
@Slf4j
public class SpcBizException extends BusinessException {

    private static final long serialVersionUID = -354525648023909584L;

    /**
     * 实例化一个新的SPC RPC异常
     */
    public SpcBizException() {
    }

    /**
     * 实例化一个新的带参数的SPC RPC异常
     *
     * @param code       异常码
     *
     * @param msgFormat  消息格式
     *
     * @param args       传入参数
     */
    public SpcBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== SpcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * 实例化一个新的带参数的SPC RPC异常
     *
     * @param code     异常码
     *
     * @param message  异常消息
     */
    public SpcBizException(int code, String message) {
        super(code, message);
        log.info("<== SpcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * 实例化一个新的带参数的SPC RPC异常
     *
     * @param codeEnum 异常码
     */
    public SpcBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum);
        log.info("<== SpcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * 实例化一个新的带参数的SPC RPC异常
     *
     * @param codeEnum 异常码
     *
     * @param args     传入参数
     */
    public SpcBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== SpcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }
}
