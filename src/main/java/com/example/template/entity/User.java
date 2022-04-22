package com.example.template.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * IdType 类型含义
     * AUTO 自动增长
     * ID_WORKER mybatis-plus 自带策略 生成19位值
     * ID_WORKER_STR  与上面类似,只单独针对string
     * INPUT 没有策略 需要自己设定ID
     * None 没有策略
     * UUID 自动生成随机数
     */
    @TableId(type = IdType.AUTO)
    private  Long id;
    private  String name;
    private  Integer age;
    private  String email;

    /**
     *添加自动填充的注解
     */
    @TableField(fill = FieldFill.INSERT)
    private  Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private  Date updateTime;
}
