package com.exh.myblog.service.impl;

import com.exh.myblog.mapper.AdminUserMapper;
import com.exh.myblog.model.AdminUser;
import com.exh.myblog.service.AdminUserService;
import com.exh.myblog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String md5String = MD5Util.MD5Encode(password,"UTF-8");
        AdminUser adminUser = adminUserMapper.login(userName,md5String);
        return adminUser;
    }

    @Override
    public AdminUser getAdminUserDetailById(Integer adminUserId) {
        return adminUserMapper.selectByPrimaryKey(adminUserId);
    }

    @Override
    public boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //修改信息
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }
}
