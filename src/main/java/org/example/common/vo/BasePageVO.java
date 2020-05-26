package org.example.common.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;

/**
 * 分页请求类
 **/
@Data
public class BasePageVO extends BaseRequestVO {

    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {

        // TODO nowpage和pageSize不能为空 balaba

    }
}
