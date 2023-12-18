package com.amory.service.impl;

import com.amory.domain.ResponseResult;
import com.amory.domain.dto.LoginDto;
import com.amory.domain.entity.Admin;
import com.amory.domain.entity.Article;
import com.amory.domain.request.LoginRequest;
import com.amory.enums.AppHttpCodeEnum;
import com.amory.mapper.AdminMapper;
import com.amory.service.AdminService;
import com.amory.utils.BeanCopyUtils;
import com.amory.utils.TokenUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2023-12-11 11:13:39
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResponseResult login(LoginRequest request) {
        //返回LoginDto
        //根据用户名查询用户信息
//        dto = null;
//        try {
//            LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.eq(Admin::getUsername, request.getUsername());
//            queryWrapper.eq(Admin::getPassword, request.getPassword());
//            Admin admin = adminMapper.selectOne(queryWrapper);
//            dto= BeanCopyUtils.copyBean(admin, LoginDto.class);
//            return ResponseResult.okResult(dto);
//        } catch (Exception e) {
//            log.error("登陆错误");
//            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
//        }
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, request.getUsername());
        queryWrapper.eq(Admin::getPassword, request.getPassword());
        Admin admin = adminMapper.selectOne(queryWrapper);
        if(Objects.isNull(admin)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        LoginDto dto= BeanCopyUtils.copyBean(admin, LoginDto.class);

        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        dto.setToken(token);
        return ResponseResult.okResult(dto);




    }
}
