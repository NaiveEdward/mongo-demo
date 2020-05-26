package org.example.web.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseRequestVO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginVO extends BaseRequestVO {
    // 长度在6~18之间，只能包含字符、数字和下划线
    @NotBlank
    @Pattern(regexp = "^\\w{6,18}$")
    private String account;

    // 长度在6~32之间，只能包含字符、数字和下划线
    @NotBlank
    // @Pattern(regexp = "^[a-zA-Z]\\w{5,31}$")
    @Pattern(regexp = "^\\w{6,32}$")
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
