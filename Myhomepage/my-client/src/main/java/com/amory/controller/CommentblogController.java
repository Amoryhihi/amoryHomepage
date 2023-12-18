package com.amory.controller;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Commentblog;
import com.amory.service.CommentblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentblogController {
    @Autowired
    private CommentblogService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Commentblog comment) {
        return commentService.addComment(comment);
    }

}
