package com.example.template.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.template.common.Result;
import com.example.template.mapper.UserMapper;
import com.example.template.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * User实体类
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    UserMapper userMapper;

    // 分页 模糊 查询
    @GetMapping("/search")
    public Result<?> findPage(@RequestParam(defaultValue = "0") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
       LambdaQueryWrapper<User> wrapper =  Wrappers.<User>lambdaQuery();
        // 非空判断
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickname,search);
        }
        Page<User> user=userMapper.selectPage(
                new Page<>(pageNum,pageSize), wrapper);

        return  Result.success(user);
    }

    @PostMapping("/delete/{id}")
    public Result<?> deleteUer(@PathVariable long id){
        userMapper.deleteById(id);
        return Result.success();
    }





    // 管理员登录
    @PostMapping("/login")
    public Result userLogin(@RequestBody User userParam){
        // 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userParam.getUsername());
        queryWrapper.eq("password",userParam.getPassword());

        User user =  userMapper.selectOne(queryWrapper);
        if (user == null){
            return  Result.error("-1","用户名或密码错误");
        }
        return  Result.success();
    }


    // 用户注册
    @PostMapping("/register")
    public Result userRegister(@RequestBody User userParam){
        // 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 先判断用户名的唯一性
        queryWrapper.eq("username",userParam.getUsername());
        User res =  userMapper.selectOne(queryWrapper);
        if(res != null){
            return  Result.error("-1","该用户已注册");
        }
        if(userParam.getPassword() == null){
            userParam.setPassword("123456");
        }
        userMapper.insert(userParam);
        return Result.success();
    }
}
