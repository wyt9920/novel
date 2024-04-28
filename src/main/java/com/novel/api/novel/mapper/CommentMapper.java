package com.novel.api.novel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.novel.api.novel.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    // 这里可以定义额外的方法
}
