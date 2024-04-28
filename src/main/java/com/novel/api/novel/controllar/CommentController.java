package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Comment;
import com.novel.api.novel.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable String id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/save")
    public void saveComment(@RequestBody Comment comment) {
        comment.setCommentDate(LocalDate.now());
        commentService.saveComment(comment);
    }

    @PutMapping("/update")
    public void updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable String id) {
        commentService.deleteCommentById(id);
    }

    @GetMapping("/all")
    public Page<Comment> getAllComments(@RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return commentService.getAllComments(pageNo, pageSize);
    }

    @GetMapping("/novel")
    public List<Comment> getNovelList(@RequestParam String novelId){
        return commentService.getList(novelId);
    }
    // 其他业务方法的映射
}

