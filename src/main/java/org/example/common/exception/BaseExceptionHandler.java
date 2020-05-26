package org.example.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.common.vo.BaseRequestVO;
import org.example.common.vo.BaseResponseVO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共异常处理
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponseVO serviceExceptionHandler(
            HttpServletRequest request, CommonServiceException e) {

        log.error("CommonServiceException, code:{}, message",
                e.getCode(), e.getMessage());

        return BaseResponseVO.serviceException(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponseVO methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {

        log.error("MethodArgumentNotValidException, code:{}, message",
                400, e.getMessage());

        return BaseResponseVO.notValidException(e);
    }

}
