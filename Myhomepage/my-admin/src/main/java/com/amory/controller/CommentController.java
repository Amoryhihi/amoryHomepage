package com.amory.controller;

import com.amory.domain.ResponseResult;
import com.amory.domain.request.ArticlePageRequest;
import com.amory.domain.request.BaseRequest;
import com.amory.service.AdminService;
import com.amory.service.CommentblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/commentDelete")
public class CommentController {
    @Autowired
    private CommentblogService commentblogService;

    @GetMapping("/page")
    public ResponseResult pageByCondition(BaseRequest request) {

        return commentblogService.pageByCondition(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        commentblogService.deleteCommentById(id);
        return ResponseResult.okResult();
    }
}
