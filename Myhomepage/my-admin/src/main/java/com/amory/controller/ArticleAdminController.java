package com.amory.controller;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Article;
import com.amory.domain.request.ArticlePageRequest;
import com.amory.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/articleChange")
public class ArticleAdminController {
    @Autowired
    private ArticleService articleService;


    @GetMapping("/pageByCondition")
    public ResponseResult pageByCondition(ArticlePageRequest request) {

        return articleService.pageByCondition(request);
    }

    @GetMapping("/{id}")
    public ResponseResult getWithId(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        return ResponseResult.okResult(article);
    }

    @PostMapping("/add")
    public ResponseResult addArticle(@RequestBody Article article) {
        articleService.save(article);
        return ResponseResult.okResult();
    }

    @PutMapping("/update")
    public ResponseResult update(@RequestBody Article article) {
        article.setUpdateTime(new Date());
        articleService.updateById(article);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        articleService.deleteArticleById(id);
        return ResponseResult.okResult();
    }
}
