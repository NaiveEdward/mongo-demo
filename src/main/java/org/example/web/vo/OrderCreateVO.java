package org.example.web.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseRequestVO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderCreateVO extends BaseRequestVO {

    @NotBlank
    private String userId;

    @NotBlank
    private String itemId;

    @NotNull
    private Integer count;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
