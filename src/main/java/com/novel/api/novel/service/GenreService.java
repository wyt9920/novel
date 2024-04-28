package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Comment;
import com.novel.api.novel.entity.Genre;
import com.novel.api.novel.entity.Novel;
import com.novel.api.novel.mapper.GenreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GenreService {

    @Resource
    private GenreMapper genreMapper;

    public Genre getGenreById(String id) {
        return genreMapper.selectById(id);
    }

    public void saveGenre(Genre genre) {
        genreMapper.insert(genre);
    }

    public void updateGenre(Genre genre) {
        genreMapper.updateById(genre);
    }

    public void deleteGenreById(String id) {
        genreMapper.deleteById(id);
    }


    public Page<Genre> getAllGenres(Integer pageNo, Integer pageSize,String genreName) {
        QueryWrapper<Genre> queryWrapper = new QueryWrapper<>();

        // 使用 lambda 表达式判断参数是否为空，并添加对应的查询条件
        queryWrapper.like(StringUtils.isNotBlank(genreName), "genre_name", genreName);

        Page<Genre> page = new Page<>(pageNo, pageSize);
        return genreMapper.selectPage(page, queryWrapper);
    }

    // 其他业务方法的实现
}

