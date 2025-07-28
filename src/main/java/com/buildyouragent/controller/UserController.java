package com.buildyouragent.controller;

import cn.hutool.core.bean.BeanUtil;
import com.buildyouragent.common.BaseResponse;
import com.buildyouragent.common.ResultUtil;
import com.buildyouragent.common.ThrowUtil;
import com.buildyouragent.common.UserContext;
import com.buildyouragent.domain.dto.UserLoginDTO;
import com.buildyouragent.domain.dto.UserRegisterDTO;
import com.buildyouragent.domain.dto.UserUpdateDTO;
import com.buildyouragent.domain.entity.User;
import com.buildyouragent.domain.vo.UserLoginVO;
import com.buildyouragent.domain.vo.UserRegisterVO;
import com.buildyouragent.exception.ErrorCode;
import com.buildyouragent.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterDTO 用户注册信息
     */
    @PostMapping("/register")
    public BaseResponse<UserRegisterVO> userRegister(@RequestBody UserRegisterDTO userRegisterDTO) {
        ThrowUtil.throwIf(userRegisterDTO == null, ErrorCode.PARAMS_ERROR, "参数为空");
        UserRegisterVO res = userService.userRegister(userRegisterDTO);
        return ResultUtil.success(res);
    }

    /**
     * 用户登录
     * @param userLoginDTO
     */
    @PostMapping("/login")
    public BaseResponse<UserLoginVO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        ThrowUtil.throwIf(userLoginDTO == null, ErrorCode.PARAMS_ERROR, "参数为空");
        UserLoginVO res = userService.userLogin(userLoginDTO);
        return ResultUtil.success(res);
    }

    /**
     * 用户注销
     */
    @PostMapping("/logout")
    public BaseResponse<String> userLogout() {
        UserContext.removeUserId();
        return ResultUtil.success("注销成功");
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    public BaseResponse<UserLoginVO> getUserInfo() {
        Long userId = UserContext.getUserId();
        User user = userService.getById(userId);
        UserLoginVO res = new UserLoginVO();
        BeanUtil.copyProperties(user, res);
        return ResultUtil.success(res);
    }

    /**
     * 修改用户信息
     * @param userUpdateDTO
     */
    @PostMapping("/update")
    public BaseResponse<String> updateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO) {
        Long userId = UserContext.getUserId();
        Long id = userUpdateDTO.getId();
        ThrowUtil.throwIf(!userId.equals(id), ErrorCode.NO_AUTH_ERROR);
        String res = userService.updateUserInfo(userUpdateDTO);
        return ResultUtil.success(res);
    }

}
