package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Novel;
import com.novel.api.novel.mapper.NovelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NovelService {

    @Resource
    private NovelMapper novelMapper;

    public Novel getNovelById(String id) {
        return novelMapper.selectById(id);
    }

    public void saveNovel(Novel novel) {
        novelMapper.insert(novel);
    }

    public void updateNovel(Novel novel) {
        novelMapper.updateById(novel);
    }

    public void deleteNovelById(String id) {
        novelMapper.deleteById(id);
    }

    public IPage<Novel> searchNovels(String title, String author, String gender, String genre, int pageNum, int pageSize) {
        QueryWrapper<Novel> queryWrapper = new QueryWrapper<>();

        // 使用 lambda 表达式判断参数是否为空，并添加对应的查询条件
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title)
                .like(StringUtils.isNotBlank(author), "author", author)
                .eq(StringUtils.isNotBlank(gender), "gender", gender)
                .eq(StringUtils.isNotBlank(genre), "genre", genre);

        // 分页查询
        IPage<Novel> page = new Page<>(pageNum, pageSize);

        return novelMapper.selectPage(page, queryWrapper);
    }

    public Novel getOne(QueryWrapper<Novel> queryWrapper){
        return novelMapper.selectOne(queryWrapper);
    }

    public List<Novel> list(QueryWrapper<Novel> queryWrapper){
        return novelMapper.selectList(queryWrapper);
    }

    // 其他业务方法的实现
}

