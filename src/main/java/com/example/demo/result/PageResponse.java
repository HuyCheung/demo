package com.example.demo.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分页响应类 <br><br><br>
 *
 * <p>
 * 这个类主要是存放分页的一些属性，包含如下字段：
 * </p>
 *
 * <ul>
 *     <li>{@code currentPage}      ：当前页</li>
 *     <li>{@code pageSize}         ：每页显示条数</li>
 *     <li>{@code totalRows}        ：总条数</li>
 *     <li>{@code totalPages}       ：总页数</li>
 *     <li>{@code content}          ：数据</li>
 * </ul>
 *
 * <p>
 *     改动说明：属性与之前保持不变，只是改了类名。另外，增加构建者模式
 * </p>
 *
 * @author Huy Cheung
 * @date 2022/04/06
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "分页响应封装", description = "分页响应模板")
public class PageResponse<T> implements Serializable {

    /**
     * 当前页
     * <p>
     * 将Integer改为Long的原因：假如你的总页数远远大于整数的最大数，
     * 那么这样就很难处理了。但修改之后，并不会影响我们现有的业务
     * </p>
     */
    private Long currentPage;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Long totalRows;

    /**
     * 总页数
     */
    private Long totalPages;

    /**
     * 响应数据
     */
    private List<T> result;

    /**
     * 转换 Mybatis-Plus 分页对象为系统对象
     *
     * @param page 页面对象
     * @return {@link PageResponse}<{@link T}>
     */
    public static <T> PageResponse<T> convert(Page<T> page) {
        return new PageResponse<T>()
                .setCurrentPage(page.getCurrent())
                .setPageSize((int) page.getSize())
                .setTotalRows(page.getTotal())
                .setTotalPages(page.getPages())
                .setResult(page.getRecords());
    }
}