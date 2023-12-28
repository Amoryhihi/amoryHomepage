package com.amory.service;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Commentblog;
import com.amory.domain.request.BaseRequest;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Commentblog)表服务接口
 *
 * @author makejava
 * @since 2023-12-04 10:20:44
 */
public interface CommentblogService extends IService<Commentblog> {

    ResponseResult commentList(Integer pageNum, Integer pageSize);

    ResponseResult addComment(Commentblog comment);

    ResponseResult pageByCondition(BaseRequest request);

    void deleteCommentById(Integer id);
}
