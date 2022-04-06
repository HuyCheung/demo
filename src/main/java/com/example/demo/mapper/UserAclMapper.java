package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserAcl;
import java.util.List;

public interface UserAclMapper extends BaseMapper<UserAcl> {
    int updateBatchSelective(List<UserAcl> list);

    int insertOrUpdate(UserAcl record);

    int insertOrUpdateSelective(UserAcl record);
}