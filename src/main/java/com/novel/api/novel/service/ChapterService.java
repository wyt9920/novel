package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Chapter;
import com.novel.api.novel.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;
    private Object Page;

    public Chapter getChapterById(String id) {
        return chapterMapper.selectById(id);
    }

    public void saveChapter(Chapter chapter) {
        chapterMapper.insert(chapter);
    }

    public void updateChapter(Chapter chapter) {
        chapterMapper.updateById(chapter);
    }

    public void deleteChapterById(String id) {
        chapterMapper.deleteById(id);
    }

    public IPage<Chapter> getAllChapters(Integer pageNo, Integer pageSize, String novelTitle, String chapterTitle) {

        Page<Chapter> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(novelTitle), "novel_title", novelTitle)
                .like(StringUtils.isNotBlank(chapterTitle), "chapter_title", chapterTitle);

        return chapterMapper.selectPage(page, queryWrapper);

    }

    public List<Chapter> getChaptersByNovelId(String novelId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("novel_id", novelId);
        return chapterMapper.selectList(queryWrapper);
    }

    // 其他业务方法的实现
}

