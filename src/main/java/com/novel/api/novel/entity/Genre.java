package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("genre")
public class Genre {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String genreName;
}
