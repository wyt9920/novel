package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("novelgenre")
public class NovelGenre {
    @TableId
    private String novelId;
    private String genreId;
}

