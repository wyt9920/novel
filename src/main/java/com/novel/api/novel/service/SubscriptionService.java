package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Genre;
import com.novel.api.novel.entity.Subscription;
import com.novel.api.novel.mapper.SubscriptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SubscriptionService {

    @Resource
    private SubscriptionMapper subscriptionMapper;

    public Subscription getSubscriptionById(String id) {
        return subscriptionMapper.selectById(id);
    }

    public void saveSubscription(Subscription subscription) {
        subscriptionMapper.insert(subscription);
    }

    public void updateSubscription(Subscription subscription) {
        subscriptionMapper.updateById(subscription);
    }

    public void deleteSubscriptionById(String id) {
        subscriptionMapper.deleteById(id);
    }
    public Page<Subscription> getAllSubscriptions(Integer pageNo, Integer pageSize,String nid,String uid) {
        QueryWrapper<Subscription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(nid),"novel_id",nid)
                .eq(StringUtils.isNotBlank(uid),"user_id",uid);
        Page<Subscription> page = new Page<>(pageNo, pageSize);
        return subscriptionMapper.selectPage(page, queryWrapper);
    }
    // 其他业务方法的实现
}

