package com.example.demo.entity.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageQuery {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 关键字
     */
    private String keyword;
}