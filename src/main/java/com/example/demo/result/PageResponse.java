package com.example.demo.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serial;
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
public class PageResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7772247147295029134L;

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
     * 无参数构造方法
     */
    public PageResponse() {
    }

    /**
     * 构造方法
     *
     * @param currentPage 当前页
     * @param pageSize    分页大小
     * @param totalRows   总行数
     * @param totalPages  总页数
     * @param result      内容
     */
    public PageResponse(Long currentPage, Integer pageSize, Long totalRows, Long totalPages, List<T> result) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        this.totalPages = totalPages;
        this.result = result;
    }

    /**
     * 转换 Mybatis-Plus 分页对象为系统对象
     *
     * @param page 页面对象
     * @return {@link PageResponse}<{@link T}>
     */
    public static <T> PageResponse<T> convert(Page<T> page) {
        return new PageResponse.Builder<T>()
                .currentPage(page.getCurrent())
                .pageSize((int) page.getSize())
                .totalRows(page.getTotal())
                .totalPages(page.getPages())
                .result(page.getRecords())
                .build();
    }

    /**
     * 获取当前页
     *
     * @return 返回当前页
     */
    public Long getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页
     *
     * @param currentPage 当前页
     * @return PageResponseVo
     */
    public PageResponse<T> setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    /**
     * 获取分页大小
     *
     * @return 分页大小
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置分页大小
     *
     * @param pageSize 分页大小
     * @return PageResponseVo
     */
    public PageResponse<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 获取总条数
     *
     * @return 总条数
     */
    public Long getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总条数
     *
     * @param totalRows 总条数
     * @return PageResponseVo
     */
    public PageResponse<T> setTotalRows(long totalRows) {
        this.totalRows = totalRows;
        return this;
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public Long getTotalPages() {
        return totalPages;
    }

    /**
     * 设置总页数
     *
     * @param totalPages 总页数
     * @return PageResponseVo
     */
    public PageResponse<T> setTotalPages(long totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * 设置内容
     *
     * @param result 内容
     * @return PageResponseVo
     */
    public PageResponse<T> setResult(List<T> result) {
        this.result = result;
        return this;
    }


    /**
     * PageResponse构造者，可通过该类构造出PageResponseVo
     *
     * @param <T> 范型
     */
    public static class Builder<T> implements IBuilder<PageResponse<T>> {

        // PageResponse 对象
        private final PageResponse<T> pageResponse;

        /**
         * 无参数构造方法，new一个 PageResponseVo 对象
         */
        public Builder() {
            pageResponse = new PageResponse<>();
        }

        /**
         * 设置当前页
         *
         * @param currentPage 当前页
         * @return Builder
         */
        public Builder<T> currentPage(long currentPage) {
            this.pageResponse.currentPage = currentPage;
            return this;
        }

        /**
         * 设置分页大小
         *
         * @param pageSize 分页大小
         * @return Builder
         */
        public Builder<T> pageSize(int pageSize) {
            this.pageResponse.pageSize = pageSize;
            return this;
        }

        /**
         * 设置总条数
         *
         * @param totalRows 总条数
         * @return Builder
         */
        public Builder<T> totalRows(long totalRows) {
            this.pageResponse.totalRows = totalRows;
            return this;
        }

        /**
         * 设置总页数
         *
         * @param totalPages 总页数
         * @return Builder
         */
        public Builder<T> totalPages(long totalPages) {
            this.pageResponse.totalPages = totalPages;
            return this;
        }

        /**
         * 设置内容
         *
         * @param result 结果
         * @return Builder
         */
        public Builder<T> result(List<T> result) {
            this.pageResponse.result = result;
            return this;
        }

        /**
         * 给属性赋值完成，调该方法可构造出PageResponseVo的对象
         *
         * @return PageResponseVo
         */
        public PageResponse<T> build() {
            return this.pageResponse;
        }
    }

    /**
     * toString方法
     *
     * @return 将属性及对应值拼接成字符串
     */
    @Override
    public String toString() {
        return "PageResponseVo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                ", result=" + result +
                '}';
    }
}