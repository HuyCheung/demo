package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Log;
import java.util.List;

public interface LogMapper extends BaseMapper<Log> {
    int updateBatchSelective(List<Log> list);

    int insertOrUpdate(Log record);

    int insertOrUpdateSelective(Log record);
}