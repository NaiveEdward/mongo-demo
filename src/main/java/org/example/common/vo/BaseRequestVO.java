package org.example.common.vo;


import org.example.common.exception.CommonServiceException;

public abstract class BaseRequestVO {

    public abstract void checkParam() throws CommonServiceException;

}
