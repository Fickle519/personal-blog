package com.exh.myblog.service;

import com.exh.myblog.model.AdminUser;

public interface AdminUserService {
    AdminUser login(String userName,String password);
    AdminUser getAdminUserDetailById(Integer adminUserId);
    boolean updatePassword(Integer adminUserId,String originalPassword,String newPassword);
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
