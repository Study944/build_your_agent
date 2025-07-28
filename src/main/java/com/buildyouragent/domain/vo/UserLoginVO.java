package com.buildyouragent.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册响应
 */
@Data
public class UserLoginVO implements Serializable {

    /**
     * 账号
     */
    String userAccount;

    /**
     * 用户昵称
     */
    String userName;

    /**
     * 用户角色：user/admin/ban
     */
    String userRole;

    /**
     * 用户简介
     */
    String userProfile;

    /**
     * 登录认证令牌
     */
    String token;

}
