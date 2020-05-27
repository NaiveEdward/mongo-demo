package org.example.web.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseRequestVO;

import javax.validation.constraints.NotBlank;

@Data
public class UserContactVO extends BaseRequestVO {
    @NotBlank
    private String userId;
    private String id;
    private String type;
    private String account;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
