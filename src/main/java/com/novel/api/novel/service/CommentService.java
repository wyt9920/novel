package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Comment;
import com.novel.api.novel.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    public Comment getCommentById(String id) {
        return commentMapper.selectById(id);
    }

    public void saveComment(Comment comment) {
        commentMapper.insert(comment);
    }

    public void updateComment(Comment comment) {
        commentMapper.updateById(comment);
    }

    public void deleteCommentById(String id) {
        commentMapper.deleteById(id);
    }

    public Page<Comment> getAllComments(Integer pageNo, Integer pageSize) {
        Page<Comment> page = new Page<>(pageNo, pageSize);
        return commentMapper.selectPage(page, null);
    }

    public List<Comment> getList(String id){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("novel_id",id);
        return commentMapper.selectList(queryWrapper);
    }

    // 其他业务方法的实现
}

