package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.NovelGenre;
import com.novel.api.novel.service.NovelGenreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/novelGenre")
public class NovelGenreController {

    @Resource
    private NovelGenreService novelGenreService;

    @GetMapping("/{id}")
    public NovelGenre getNovelGenreById(@PathVariable String id) {
        return novelGenreService.getNovelGenreById(id);
    }

    @PostMapping("/save")
    public void saveNovelGenre(@RequestBody NovelGenre novelGenre) {
        novelGenreService.saveNovelGenre(novelGenre);
    }

    @PutMapping("/update")
    public void updateNovelGenre(@RequestBody NovelGenre novelGenre) {
        novelGenreService.updateNovelGenre(novelGenre);
    }

    @DeleteMapping("/{id}")
    public void deleteNovelGenreById(@PathVariable String id) {
        novelGenreService.deleteNovelGenreById(id);
    }

    @GetMapping("/all")
    public Page<NovelGenre> getAllNovelGenres(@RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return novelGenreService.getAllNovelGenres(pageNo, pageSize);
    }
    // 其他业务方法的映射
}

