package com.amory.controller;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Admin;
import com.amory.domain.request.LoginRequest;
import com.amory.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginRequest request){

        return adminService.login(request);
    }

}