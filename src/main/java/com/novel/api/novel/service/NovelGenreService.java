package com.novel.api.novel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Genre;
import com.novel.api.novel.entity.NovelGenre;
import com.novel.api.novel.mapper.NovelGenreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NovelGenreService {

    @Resource
    private NovelGenreMapper novelGenreMapper;

    public NovelGenre getNovelGenreById(String id) {
        return novelGenreMapper.selectById(id);
    }

    public void saveNovelGenre(NovelGenre novelGenre) {
        novelGenreMapper.insert(novelGenre);
    }

    public void updateNovelGenre(NovelGenre novelGenre) {
        novelGenreMapper.updateById(novelGenre);
    }

    public void deleteNovelGenreById(String id) {
        novelGenreMapper.deleteById(id);
    }
    public Page<NovelGenre> getAllNovelGenres(Integer pageNo, Integer pageSize) {
        Page<NovelGenre> page = new Page<>(pageNo, pageSize);
        return novelGenreMapper.selectPage(page, null);
    }
    // 其他业务方法的实现
}

