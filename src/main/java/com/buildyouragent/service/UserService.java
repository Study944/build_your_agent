package com.buildyouragent.service;

import com.buildyouragent.domain.dto.UserLoginDTO;
import com.buildyouragent.domain.dto.UserRegisterDTO;
import com.buildyouragent.domain.dto.UserUpdateDTO;
import com.buildyouragent.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buildyouragent.domain.vo.UserLoginVO;
import com.buildyouragent.domain.vo.UserRegisterVO;

/**
* 用户服务接口
*/
public interface UserService extends IService<User> {

    UserRegisterVO userRegister(UserRegisterDTO userRegisterDTO);

    UserLoginVO userLogin(UserLoginDTO userLoginDTO);

    String updateUserInfo(UserUpdateDTO userUpdateDTO);
}
