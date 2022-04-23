package com.example.template.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    // 查询所有
    @GetMapping
    public List<User> getUser(){
        return userMapper.selectList(null);
    }

    // 新增
    @PostMapping
    public String addUser(@RequestBody User user){
        int insert = userMapper.insert(user);
        return "success insert "+insert +" data!";
    }
    // 更新
    @PutMapping
    public String updateUser(@RequestBody User user){
        int update =  userMapper.updateById(user);
        return "success update "+update +" data!";
    }
    // 根据Id删除
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id){
        int delete = userMapper.deleteById(id);
        return "success delete "+delete +" data!";
    }
    // 根据ID查询
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") long id){
        return userMapper.selectById(id);
    }
    // 分页查询
    @GetMapping("/page")
    public Page<User> findByPage(
            @RequestParam(defaultValue ="0") Integer limit,
            @RequestParam(defaultValue ="10") Integer offset){
        // 分页参数
        Page<User> pageParams = new Page<>(limit,offset);
        // 执行分页
        userMapper.selectPage(pageParams,null);
        return pageParams;
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
