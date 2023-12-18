package com.amory.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Long id;

    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //回复目标评论id
    private Long toCommentId;
    //回复目标评论用户名
    private String toCommentUserName;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private List<CommentVo> children;

}
