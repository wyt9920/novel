package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@TableName("comment")
public class Comment {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String userId;
    private String novelId;
    private String commentContent;
    private LocalDate commentDate;
}
