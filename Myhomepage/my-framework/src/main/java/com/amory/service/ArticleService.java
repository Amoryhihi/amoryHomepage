package com.amory.service;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Article;
import com.amory.domain.request.ArticlePageRequest;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2023-11-27 15:18:17
 */
public interface ArticleService extends IService<Article> {

    ResponseResult articleList();

    ResponseResult selectPage(Integer pageNum,Integer pageSize);

    ResponseResult pageByCondition(ArticlePageRequest request);

    ResponseResult getArticleDetail(Long id);

    void deleteArticleById(Integer id);
//    Object selectPage(ArticlePageRequest request);
}
