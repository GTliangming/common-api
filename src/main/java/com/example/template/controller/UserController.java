package com.example.template.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.template.common.Result;
import com.example.template.common.TokenUtils;
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
        System.out.print(user);
        return  Result.success(user);
    }

    @PostMapping("/delete/{id}")
    public Result<?> deleteUer(@PathVariable long id){
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public String getUserList(){
        return  "1";
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
        String token = TokenUtils.getToken(user.getId().toString(),user.getPassword());
        return  Result.successByToken(user,token);
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
        User newUser = userMapper.selectOne(queryWrapper);
        String token = TokenUtils.getToken(newUser.getId().toString(),newUser.getPassword());
        return Result.successByToken(newUser,token);
    }
    // 复杂条件查询
    @GetMapping("/select")
    public List<User> testSelectQuery(){
        // 创建查询对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        /*
            添加查询条件
            ge 大于等于  gt 大于
            le 小于等于  lt 小于
            eq 等于     ne 不等于
            between  范围查询
            like     模糊查询
            orderByDesc 降序排序  orderByAsc 升序排序
            last 拼接sql
            select 查询指定的列
         */
        // 查询age>=21记录
//        wrapper.ge("age",21);

        // 查询age>21记录
//        wrapper.gt("age",21);

        // 查询name=小明 记录
//        wrapper.eq("name","小明");

        // 查询name!=小明 记录
//        wrapper.ne("name","小明");

        // 查询 年龄在20-30内的记录
//        wrapper.between("age",20,30);

        // 查询 name中包含 n的记录
//        wrapper.like("name","n");

        // 根据 id 进行降序排列
//        wrapper.orderByDesc("id");

        // 拼接到sql语句后
//        wrapper.last("limit 1");

        // 指定查询列 name和id
//        wrapper.select("id","name");

        List<User> user = userMapper.selectList(wrapper);
        return user;
    }
}
