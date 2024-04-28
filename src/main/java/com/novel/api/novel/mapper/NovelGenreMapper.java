package com.novel.api.novel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.novel.api.novel.entity.NovelGenre;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NovelGenreMapper extends BaseMapper<NovelGenre> {
    // 这里可以定义额外的方法
}
