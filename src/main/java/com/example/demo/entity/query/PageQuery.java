package com.example.demo.entity.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 页面查询参数封装
 *
 * @author Huy Cheung
 * @date 2022/04/07
 */
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