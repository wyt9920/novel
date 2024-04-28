package com.novel.api.novel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.novel.api.novel.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 这里可以定义额外的方法
}
