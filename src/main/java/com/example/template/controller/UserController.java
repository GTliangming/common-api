package com.example.template.controller;


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
}
