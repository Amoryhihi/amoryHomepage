package com.amory.controller;


import com.amory.domain.ResponseResult;
import com.amory.domain.request.ArticlePageRequest;
import com.amory.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public ResponseResult listArticle() {
        ResponseResult res = articleService.articleList();
        return res;
    }
    //找到所有可用的并分页
    @GetMapping("/page")
    public ResponseResult page(Integer pageNum,Integer pageSize) {

        return articleService.selectPage(pageNum,pageSize);
    }

    //按条件查询，条件:String title,Long categoryId
    @GetMapping("/pageByCondition")
    public ResponseResult pageByCondition(ArticlePageRequest request) {

        return articleService.pageByCondition(request);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
}
