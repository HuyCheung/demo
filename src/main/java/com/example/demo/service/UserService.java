package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.entity.query.PageQuery;
import com.example.demo.result.ResultResponse;

import java.util.List;

/**
 * <p>
 * 管理员用户表 服务类
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-03-08
 */
public interface UserService extends IService<User> {
    /**
     * 添加
     *
     * @param user 用户信息
     * @return {@link Integer}
     */
    Long add(User user);

    /**
     * 更新
     *
     * @param user 用户信息
     * @return {@link Boolean}
     */
    Boolean update(User user);

    /**
     * 批量删除
     *
     * @param ids id
     * @return {@link Boolean}
     */
    Boolean batchDelete(List<Long> ids);

    /**
     * 启用
     *
     * @param enable 是否启用
     * @param ids    id
     * @return {@link Boolean}
     */
    Boolean batchEnable(List<Long> ids, Boolean enable);

    /**
     * 列表
     *
     * @param pageQuery 页面查询
     * @return {@link List}<{@link User}>
     */
    Page<User> list(PageQuery pageQuery);

    /**
     * 得到
     *
     * @param id id
     * @return {@link User}
     */
    User get(Long id);

    /**
     * 登录
     *
     * @param account    账户
     * @param ciphertext 密文
     * @return {@link ResultResponse}<{@link Void}>
     */
    ResultResponse<Void> doLogin(String account, String ciphertext);
}