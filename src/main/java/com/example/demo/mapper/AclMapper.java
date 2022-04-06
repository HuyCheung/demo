package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Acl;
import java.util.List;

public interface AclMapper extends BaseMapper<Acl> {
    int updateBatchSelective(List<Acl> list);

    int insertOrUpdate(Acl record);

    int insertOrUpdateSelective(Acl record);
}