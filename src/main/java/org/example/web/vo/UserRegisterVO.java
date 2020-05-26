package org.example.web.vo;

import lombok.Data;
import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseRequestVO;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegisterVO extends BaseRequestVO {

    // 以字母开头，长度在6~18之间，只能包含字符、数字和下划线
    @NotBlank
    @Pattern(regexp = "^\\w{6,18}$")
    private String account;

    // 以字母开头，长度在6~32之间，只能包含字符、数字和下划线
    @NotBlank
    @Pattern(regexp = "^\\w{6,32}$")
    private String password;

    @Size(max = 32)
    private String name;

    @Max(200)
    private int age;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
