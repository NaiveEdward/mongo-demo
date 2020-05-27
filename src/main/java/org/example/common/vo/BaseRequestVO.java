package org.example.common.vo;


import org.example.common.exception.CommonServiceException;

public abstract class BaseRequestVO {

    /**
     * 参数校验
     */
    public abstract void checkParam() throws CommonServiceException;

}
