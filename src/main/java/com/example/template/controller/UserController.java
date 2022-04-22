package com.example.template.controller;


import com.example.template.mapper.UserMapper;
import com.example.template.model.User;
import com.example.template.types.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    // 查询所有
    @GetMapping
    public List<User> getUser(){
        return userMapper.findAll();
    }

    // 新增
    @PostMapping
    public String addUser(@RequestBody User user){
        userMapper.save(user);
        return "success";
    }
    // 更新
    @PutMapping
    public String updateUser(@RequestBody User user){
        userMapper.updateById(user);
        return "success";
    }
    // 根据Id删除
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userMapper.deleteById(id);
        return "success";
    }
    // 根据ID查询
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") long id){
        return userMapper.findById(id);
    }
    // 分页查询
    @GetMapping("/page")
    public Page<User> findByPage(
            @RequestParam(defaultValue ="0") Integer limit,
            @RequestParam(defaultValue ="10") Integer offset){
        List<User> userdata = userMapper.findByPage(limit,offset);
        Page<User> page = new Page<>();
        page.setData(userdata);

        Integer total = userMapper.getCount();
        page.setTotal(total);
        page.setLimit(limit);
        page.setOffser(offset);
        return page;
    }
}
