package com.amory.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Admin)表实体类
 *
 * @author makejava
 * @since 2023-12-11 11:13:38
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin")
public class Admin  {
    @TableId
    private Integer id;

    
    private String username;
    
    private String password;
    //1是管理员，0是游客
    private Integer status;



}

