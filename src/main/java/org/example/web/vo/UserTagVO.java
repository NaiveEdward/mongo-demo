package org.example.web.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseRequestVO;

@Data
public class UserTagVO extends BaseRequestVO {
    private String id;
    private String tag;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
