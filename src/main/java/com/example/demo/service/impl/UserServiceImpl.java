package com.example.demo.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.StatusConsts;
import com.example.demo.entity.User;
import com.example.demo.entity.query.PageQuery;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.result.ResultResponse;
import com.example.demo.result.ReturnCode;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 管理员用户表 服务实现类
 * </p>
 *
 * @author Huy Cheung
 * @since 2022-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${jasypt.encryptor.password}")
    private String salt;

    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(User user) {
        User one = userMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getAccount, user.getAccount()));
        if (Objects.nonNull(one)) {
            throw new CustomException("该账号已存在!");
        }
        user.setId(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        // 默认密码为account+123456
        String cypher = SaSecureUtil.md5BySalt(user.getAccount() + "123456", salt);
        user.setPassword(cypher);
        // 提取出方法是为了精准控制事务
        insert(user);
        return user.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public Boolean update(User user) {
        user.setCreateTime(null);
        user.setUpdateTime(null);
        // 默认密码为account+123456
        String password = SaSecureUtil.md5BySalt(user.getPassword(), salt);
        user.setPassword(password);
        userMapper.updateById(user);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchDelete(List<Long> ids) {
        userMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchEnable(List<Long> ids, Boolean enable) {
        User user = new User();
        user.setStatus(StatusConsts.NORMAL);
        userMapper.update(user, new UpdateWrapper<User>().lambda().in(User::getId, ids));
        return true;
    }

    @Override
    public Page<User> list(PageQuery pageQuery) {
        String keyword = pageQuery.getKeyword();
        int pageNum = Objects.isNull(pageQuery.getPageNum()) ? 1 : pageQuery.getPageNum();
        int pageSize = Objects.isNull(pageQuery.getPageSize()) ? 10 : pageQuery.getPageSize();
        LambdaQueryWrapper<User> queryLambda = new QueryWrapper<User>().lambda().ne(User::getStatus, StatusConsts.DELETE);
        if (StringUtils.hasText(keyword)) {
            queryLambda.and(wrapper -> wrapper.eq(User::getAccount, "%" + keyword + "%").or()
                    .eq(User::getUsername, "%" + keyword + "%").or()
                    .eq(User::getRealName, "%" + keyword + "%").or()
                    .eq(User::getEmail, "%" + keyword + "%").or()
                    .eq(User::getPhone, "%" + keyword + "%"));
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), queryLambda);
        for (User record : userPage.getRecords()) {
            record.setPassword(null);
        }
        return userPage;
    }

    @Override
    public User get(Long id) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getId, id).ne(User::getStatus, StatusConsts.DELETE));
        user.setPassword(null);
        return user;
    }

    @Override
    public ResultResponse<Void> doLogin(String account, String ciphertext) {
        User user = this.lambdaQuery()
                .eq(User::getPassword, ciphertext)
                .and(query ->
                        query.eq(User::getAccount, account)
                                .or().eq(User::getUsername, account)
                                .or().eq(User::getEmail, account)
                                .or().eq(User::getPhone, account)).one();
        if (Objects.isNull(user)) {
            return ResultResponse.fail(ReturnCode.Default.USER_PASSWORD_INCORRECT);
        }
        Byte status = user.getStatus();
        switch (status) {
            case StatusConsts.DELETE:
                return ResultResponse.fail(ReturnCode.Default.USER_EXPIRE);
            case StatusConsts.FREEZE:
                return ResultResponse.fail(ReturnCode.Default.USER_LOCKED);
            default:
                StpUtil.login(account);
                return ResultResponse.success();
        }
    }
}