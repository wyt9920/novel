package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.novel.api.novel.entity.Chapter;
import com.novel.api.novel.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @GetMapping("/{id}")
    public Chapter getChapterById(@PathVariable String id) {
        return chapterService.getChapterById(id);
    }

    @PostMapping("/save")
    public void saveChapter(@RequestBody Chapter chapter) {
        chapter.setPublicationDate(LocalDate.now());
        chapterService.saveChapter(chapter);
    }

    @PutMapping("/update")
    public void updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateChapter(chapter);
    }

    @DeleteMapping("/{id}")
    public void deleteChapterById(@PathVariable String id) {
        chapterService.deleteChapterById(id);
    }

    @GetMapping("/all")
    public IPage<Chapter> getAllChapters(@RequestParam(required = false) String novelTitle,
                                         @RequestParam(required = false) String chapterTitle,
                                         @RequestParam(defaultValue = "1") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize) {
        return chapterService.getAllChapters(pageNo, pageSize,novelTitle,chapterTitle);
    }

    @GetMapping("/chapters")
    public List<Chapter> getChaptersByNovelId(@RequestParam String novelId) {
        return chapterService.getChaptersByNovelId(novelId);
    }
    // 其他业务方法的映射
}

