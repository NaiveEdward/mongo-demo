package org.example.common.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
public class BaseResponseVO<M> {

    // 业务编号
    private Integer code;

    // 异常信息
    private String message;

    // 业务数据返回
    private M data;

    private BaseResponseVO() {
    }

    /**
     * 成功但是无参数
     */
    public static BaseResponseVO success() {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        return response;
    }

    /**
     * 成功有参数
     */
    public static <M> BaseResponseVO success(M data) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    /**
     * 未登录异常
     */
    public static <M> BaseResponseVO noLogin() {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(401);
        response.setMessage("请登录");
        return response;
    }

    /**
     * 出现参数异常
     */
    public static <M> BaseResponseVO notValidException(MethodArgumentNotValidException e) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(400);
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * 出现业务异常
     */
    public static <M> BaseResponseVO serviceException(CommonServiceException e) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

}
