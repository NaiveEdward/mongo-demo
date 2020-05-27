package org.example.web.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseRequestVO;

@Data
public class UserInfoVO extends BaseRequestVO {
    private String id;
    private String name;
    private int age;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
