package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("novel")
public class Novel {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String title;
    private String author;
    private String description;
    private String coverImage;
    private LocalDate publicationDate;
    private String gender;
    private String genre;
    private String status;
    private String textTotal;
}

