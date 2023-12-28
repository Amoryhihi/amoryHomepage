package com.amory.service.impl;

import com.amory.domain.ResponseResult;
import com.amory.domain.entity.Commentblog;
import com.amory.domain.request.BaseRequest;
import com.amory.domain.vo.ArticleVo;
import com.amory.domain.vo.CommentVo;
import com.amory.domain.vo.DelCommentVo;
import com.amory.domain.vo.PageVo;
import com.amory.mapper.CommentblogMapper;
import com.amory.service.CommentblogService;
import com.amory.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Commentblog)表服务实现类
 *
 * @author makejava
 * @since 2023-12-04 10:20:44
 */
@Service("commentblogService")
public class CommentblogServiceImpl extends ServiceImpl<CommentblogMapper, Commentblog> implements CommentblogService {
    @Autowired
    private CommentblogMapper commentblogMapper;

    @Override
    public ResponseResult commentList(Integer pageNum, Integer pageSize) {
        //分页查询所有根评论
        LambdaQueryWrapper<Commentblog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Commentblog::getRootId,-1);
        queryWrapper.orderByDesc(Commentblog::getCreateTime);
        Page<Commentblog> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> list = toCommentVoList(page.getRecords());
        for (CommentVo commentVo : list) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(list,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Commentblog comment) {
        save(comment);
        return ResponseResult.okResult();
    }
//仅用于后台系统的平论列表
    @Override
    public ResponseResult pageByCondition(BaseRequest request) {
        LambdaQueryWrapper<Commentblog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Commentblog::getCreateTime);
        Page<Commentblog> page = new Page(request.getPageNum(),request.getPageSize());
        page(page,queryWrapper);
        List<DelCommentVo> commentVos = BeanCopyUtils.copyBeanList(page.getRecords(), DelCommentVo.class);
        PageVo pagevo = new PageVo(commentVos,page.getTotal());
        return ResponseResult.okResult(pagevo);
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentblogMapper.deleteById(id);
    }

    //id是根评论id，根据其查找所对应的所有子评论
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Commentblog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Commentblog::getRootId,id);
        queryWrapper.orderByDesc(Commentblog::getCreateTime);
        List<Commentblog> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Commentblog> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            if (commentVo.getToCommentId()!=-1) {
                Commentblog father = getById(commentVo.getToCommentId());
                commentVo.setToCommentUserName(father.getUsername());
            }
        }
        return commentVos;
    }
}
