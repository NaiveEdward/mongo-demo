package org.example.service;

import org.example.common.exception.CommonServiceException;
import org.example.entity.User;
import org.example.web.vo.*;

public interface IUserService {
    User register(UserRegisterVO userRegisterVO) throws CommonServiceException;

    boolean saveInfo(UserInfoVO userInfoVO);

    long saveTag(UserTagVO userTagVO);

    boolean remove(String id);

    User login(UserLoginVO userLoginVO) throws CommonServiceException;

    long saveContact(UserContactVO contactVO);

    boolean removeContact(String userId, String id);
}
