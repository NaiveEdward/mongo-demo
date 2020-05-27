package org.example.web.controller;

import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseResponseVO;
import org.example.entity.User;
import org.example.service.IUserService;
import org.example.web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public BaseResponseVO register(@RequestBody @Validated UserRegisterVO userRegisterVO) throws CommonServiceException {
        // 检查参数
        userRegisterVO.checkParam();

        User user = userService.register(userRegisterVO);
        return BaseResponseVO.success(user);
    }

    @PostMapping("/login")
    public BaseResponseVO login(@RequestBody @Validated UserLoginVO userLoginVO) throws CommonServiceException {
        // 检查参数
        userLoginVO.checkParam();

        User user = userService.login(userLoginVO);
        return BaseResponseVO.success(user);
    }

    @PostMapping("/save-info")
    public BaseResponseVO saveInfo(@RequestBody @Validated UserInfoVO userInfoVO) throws CommonServiceException {
        userInfoVO.checkParam();

        boolean flag = userService.saveInfo(userInfoVO);
        return BaseResponseVO.success(flag);
    }

    @PostMapping("/remove")
    public BaseResponseVO remove(@NotBlank String id) {
        boolean flag = userService.remove(id);
        return BaseResponseVO.success(flag);
    }

    @PostMapping("/save-tag")
    public BaseResponseVO saveTag(@RequestBody @Validated UserTagVO userTagVO) throws CommonServiceException {
        userTagVO.checkParam();

        long count = userService.saveTag(userTagVO);
        return BaseResponseVO.success(count);
    }

    @PostMapping("/save-contact")
    public BaseResponseVO saveContact(@RequestBody @Validated UserContactVO userContactVO) throws CommonServiceException {
        userContactVO.checkParam();

        long count = userService.saveContact(userContactVO);
        return BaseResponseVO.success(count);
    }

    @PostMapping("/remove-contact")
    public BaseResponseVO removeContact(@NotBlank String userId, @NotBlank String id) {
        boolean flag = userService.removeContact(userId, id);
        return BaseResponseVO.success(flag);
    }

}
