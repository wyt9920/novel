package com.novel.api.novel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("subscription")
public class Subscription {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String userId;
    private String novelId;
    private Date subscriptionDate;
}

