package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("chapter")
public class Chapter {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String novelId;
    private String chapterTitle;
    private String content;
    private LocalDate publicationDate;
    private String chapterNum;
    private String novelTitle;
}

