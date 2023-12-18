package com.amory.domain.dto;

import lombok.Data;

@Data
public class LoginDto {
    private Integer id;
    private String username;
    //1是管理员，0是游客
    private Integer status;
    private String token;
}
