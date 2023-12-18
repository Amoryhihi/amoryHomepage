package com.amory.service.impl;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Article;
import com.amory.domain.request.ArticlePageRequest;
import com.amory.domain.vo.ArticleDetailVo;
import com.amory.domain.vo.ArticleVo;
import com.amory.domain.vo.PageVo;
import com.amory.enums.SystemConstants;
import com.amory.mapper.ArticleMapper;
import com.amory.service.ArticleService;
import com.amory.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2023-11-27 15:18:18
 */
@Service("articleService")
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult articleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, 0);
        queryWrapper.orderByDesc(Article::getId);
        Page<Article> page = new Page<>(1,5);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        List<ArticleVo> vs = BeanCopyUtils.copyBeanList(articles, ArticleVo.class);
        return ResponseResult.okResult(vs);
    }

//    @Override
//    public Object selectPage(ArticlePageRequest request) {
//        PageHelper.startPage(request.getPageNum(),request.getPageSize());
//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Article::getStatus, 0);
//        List<Article> list = list(queryWrapper);
//        List<ArticleVo> vs = BeanCopyUtils.copyBeanList(list, ArticleVo.class);
//        return new PageInfo<>(vs);
//    }

    @Override
    public ResponseResult selectPage(Integer pageNum,Integer pageSize) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

//        List<Article> articles = page.getRecords();
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleVo.class);
        PageVo pagevo = new PageVo(articleVos,page.getTotal());
        return ResponseResult.okResult(pagevo);
    }

    @Override
    public ResponseResult pageByCondition(ArticlePageRequest request) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        lambdaQueryWrapper.eq(Objects.nonNull(request.getCategoryId())&&request.getCategoryId()>0,
                Article::getCategoryId, request.getCategoryId());
        lambdaQueryWrapper.like(StringUtils.hasText(request.getTitle()),
                Article::getTitle,request.getTitle());
        lambdaQueryWrapper.orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(request.getPageNum(),request.getPageSize());
        page(page,lambdaQueryWrapper);
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleVo.class);
        PageVo pagevo = new PageVo(articleVos,page.getTotal());
        return ResponseResult.okResult(pagevo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转成vo
        ArticleDetailVo articleDetail = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        return ResponseResult.okResult(articleDetail);

    }

    @Override
    public void deleteArticleById(Integer id) {
        articleMapper.deleteById(id);
    }


}
