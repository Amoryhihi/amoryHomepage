package com.amory.domain.request;

import lombok.Data;

@Data
public class ArticlePageRequest extends BaseRequest{
    private String title;
    private int categoryId;
}
