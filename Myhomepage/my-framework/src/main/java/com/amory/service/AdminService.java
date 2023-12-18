package com.amory.service;

import com.amory.domain.ResponseResult;
import com.amory.domain.dto.LoginDto;
import com.amory.domain.entity.Admin;
import com.amory.domain.request.LoginRequest;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2023-12-11 11:13:39
 */
public interface AdminService extends IService<Admin> {

    ResponseResult login(LoginRequest request);
}
