package com.buildyouragent.domain.dto.user;

import lombok.Data;

/**
 * 用户更新请求
 */
@Data
public class UserUpdateDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 密码
     */
    private String newPassword;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

}
