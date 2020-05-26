package org.example.common.exception;

import lombok.Data;

@Data
public class CommonServiceException extends Exception{

    private Integer code;
    private String message;

    public CommonServiceException(int code,String message){
        this.code = code;
        this.message = message;
    }

}
