package com.buildyouragent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buildyouragent.common.JwtUtil;
import com.buildyouragent.common.ThrowUtil;
import com.buildyouragent.domain.dto.user.UserLoginDTO;
import com.buildyouragent.domain.dto.user.UserRegisterDTO;
import com.buildyouragent.domain.dto.user.UserUpdateDTO;
import com.buildyouragent.domain.entity.User;
import com.buildyouragent.domain.vo.user.UserLoginVO;
import com.buildyouragent.domain.vo.user.UserRegisterVO;
import com.buildyouragent.exception.ErrorCode;
import com.buildyouragent.service.UserService;
import com.buildyouragent.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务接口实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    /**
     * 用户注册
     * @param userRegisterDTO 注册参数
     * @return 注册结果
     */
    @Override
    public UserRegisterVO userRegister(UserRegisterDTO userRegisterDTO) {
        // 1.注册参数校验
        String account = userRegisterDTO.getAccount();
        String password = userRegisterDTO.getPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        ThrowUtil.throwIf(account.length() < 5, ErrorCode.PARAMS_ERROR, "账号过短");
        ThrowUtil.throwIf(password.length() < 5, ErrorCode.PARAMS_ERROR, "密码过短");
        ThrowUtil.throwIf(!password.equals(checkPassword), ErrorCode.PARAMS_ERROR, "密码不一致");
        // 2.账户不能重复
        boolean exists = this.lambdaQuery()
                .eq(User::getUserAccount, account)
                .exists();
        ThrowUtil.throwIf(exists, ErrorCode.OPERATION_ERROR,"账号重复");
        // 3.密码加密
        String encryptPassword = getEncryptPassword(password);
        // 4.插入数据
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(encryptPassword);
        boolean save = this.save(user);
        // 5.返回脱敏结果
        ThrowUtil.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return BeanUtil.copyProperties(user, UserRegisterVO.class);
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public UserLoginVO userLogin(UserLoginDTO userLoginDTO) {
        // 1.参数校验
        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();
        ThrowUtil.throwIf(account.length() < 5, ErrorCode.PARAMS_ERROR, "账号过短");
        ThrowUtil.throwIf(password.length() < 5, ErrorCode.PARAMS_ERROR, "密码过短");
        // 2.查询数据库
        User user = this.lambdaQuery()
                .eq(User::getUserAccount, account)
                .one();
        ThrowUtil.throwIf(user == null, ErrorCode.OPERATION_ERROR, "用户不存在");
        // 3.密码匹配
        String encryptPassword = getEncryptPassword(password);
        ThrowUtil.throwIf(!user.getUserPassword().equals(encryptPassword),
                ErrorCode.OPERATION_ERROR, "密码错误");
        // 4.返回令牌
        UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);
        String token = JwtUtil.generateToken(user.getId());
        userLoginVO.setToken(token);
        return userLoginVO;
    }

    @Override
    public String updateUserInfo(UserUpdateDTO userUpdateDTO) {
        // 参数判空校验
        Long id = userUpdateDTO.getId();
        String oldPassword = userUpdateDTO.getOldPassword();
        String newPassword = userUpdateDTO.getNewPassword();
        String userName = userUpdateDTO.getUserName();
        User user = new User();
        user.setId(id);
        // 旧密码校验
        if (StrUtil.isNotBlank(oldPassword) && StrUtil.isNotBlank(oldPassword) ){
            User byId = getById(id);
            String encryptPassword = getEncryptPassword(oldPassword);
            ThrowUtil.throwIf(!byId.getUserPassword().equals(encryptPassword),ErrorCode.PARAMS_ERROR,"密码错误");
            ThrowUtil.throwIf(newPassword.length() < 5, ErrorCode.PARAMS_ERROR, "新密码过短");
            user.setUserPassword(getEncryptPassword(newPassword));
        }
        if (userName != null) user.setUserName(userName);
        boolean update = updateById(user);
        ThrowUtil.throwIf(!update, ErrorCode.OPERATION_ERROR);
        return "更新成功";
    }

    /**
     * 加密
     * @param password 原始密码
     */
    private String getEncryptPassword(String password) {
        String salt = "zxc";
        return DigestUtils.md5DigestAsHex((salt + password).getBytes());
    }
}




