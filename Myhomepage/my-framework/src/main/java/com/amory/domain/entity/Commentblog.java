package com.amory.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 评论表(Commentblog)表实体类
 *
 * @author makejava
 * @since 2023-12-04 10:20:44
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("commentblog")
public class Commentblog  {
    @TableId
    private Long id;

    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //回复目标评论id
    private Long toCommentId;
    //缩略图
    private String portrait;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;



}

