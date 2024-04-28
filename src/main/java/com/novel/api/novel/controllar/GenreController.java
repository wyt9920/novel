package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Genre;
import com.novel.api.novel.service.GenreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Resource
    private GenreService genreService;

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable String id) {
        return genreService.getGenreById(id);
    }

    @PostMapping("/save")
    public void saveGenre(@RequestBody Genre genre) {
        genreService.saveGenre(genre);
    }

    @PutMapping("/update")
    public void updateGenre(@RequestBody Genre genre) {
        genreService.updateGenre(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenreById(@PathVariable String id) {
        genreService.deleteGenreById(id);
    }

    @GetMapping("/all")
    public Page<Genre> getAllGenres(@RequestParam(required = false) String genreName,
                                    @RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        return genreService.getAllGenres(pageNo, pageSize,genreName);
    }
    // 其他业务方法的映射
}

