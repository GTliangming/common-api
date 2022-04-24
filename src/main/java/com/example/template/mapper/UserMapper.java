package com.example.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.template.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * userMapper 接口
 */
public interface UserMapper extends BaseMapper<User> {


    // 查询用户名
    @Select("select password from user where username=#{username}")
    User selectByName(String username);


}
