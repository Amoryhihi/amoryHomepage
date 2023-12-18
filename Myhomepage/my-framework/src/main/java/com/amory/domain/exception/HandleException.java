package com.amory.domain.exception;

import cn.hutool.core.util.StrUtil;
import com.amory.domain.ResponseResult;
import com.amory.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class HandleException {
    @ExceptionHandler(value = ServiceException.class)
    public ResponseResult exceptionError(ServiceException e) {
        log.error("系统错误",e);
        Integer code = e.getCode();
        if (code != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseResult serviceExceptionError(Exception e) {
        log.error("系统错误",e);
        return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
    }
}
