package com.ananops.provider.exception;

import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created By ChengHao On 2020/1/6
 */
@Slf4j
public class AmcBizException  extends BusinessException {
    private static final long serialVersionUID = -7694347914487132558L;

    /**
     * Instantiates a new Amc rpc exception.
     */
    public AmcBizException() {
    }

    /**
     * Instantiates a new Amc rpc exception.
     * @param code
     * @param msgFormat
     * @param args
     */
    public AmcBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== AmcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * Instantiates a new Amc rpc exception.
     * @param code
     * @param message
     */
    public AmcBizException(int code, String message) {
        super(code, message);
        log.info("<== AmcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * Instantiates a new Amc rpc exception
     * @param codeEnum
     */
    public AmcBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum, codeEnum.msg());
        log.info("<== AmcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }

    /**
     * Instantiates a new Amc rpc exception
     * @param codeEnum
     * @param args
     */
    public AmcBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== AmcRpcException, code:{}, message:{}", this.code, super.getMessage());
    }
}
