package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int updateBatchSelective(List<User> list);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);
}