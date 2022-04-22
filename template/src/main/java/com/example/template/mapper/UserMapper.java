package com.example.template.mapper;

import com.example.template.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    @Transactional
    List<User> findAll();


    @Update("INSERT INTO `user` (`name`,`age`,`email`) VALUES (#{name} , #{age} , #{email});" )
    @Transactional
    void save(User user);

    @Update("update user set name=#{name},age=#{age},email=#{email} where id=#{id}")
    @Transactional
    void updateById(User user);

    @Delete("delete from  user where id=#{id}")
    @Transactional
    void deleteById(long id);

    @Select("select * from user where id=#{id}")
    @Transactional
    User findById(long id);
}
