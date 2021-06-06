package com.exh.myblog.mapper;

import com.exh.myblog.model.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

}