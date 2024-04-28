package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("userpreference")
public class UserPreference {
    @TableId(type = IdType.ASSIGN_UUID)
    private String preferenceId;
    private String userId;
    private String genreId;
    private Integer preferenceWeight;
}
