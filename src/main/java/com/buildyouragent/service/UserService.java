package com.buildyouragent.service;

import com.buildyouragent.domain.dto.user.UserLoginDTO;
import com.buildyouragent.domain.dto.user.UserRegisterDTO;
import com.buildyouragent.domain.dto.user.UserUpdateDTO;
import com.buildyouragent.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buildyouragent.domain.vo.user.UserLoginVO;
import com.buildyouragent.domain.vo.user.UserRegisterVO;

/**
* 用户服务接口
*/
public interface UserService extends IService<User> {

    UserRegisterVO userRegister(UserRegisterDTO userRegisterDTO);

    UserLoginVO userLogin(UserLoginDTO userLoginDTO);

    String updateUserInfo(UserUpdateDTO userUpdateDTO);
}
