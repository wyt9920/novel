package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Subscription;
import com.novel.api.novel.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Resource
    private SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    public Subscription getSubscriptionById(@PathVariable String id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PostMapping("/save")
    public void saveSubscription(@RequestBody Subscription subscription) {
        subscriptionService.saveSubscription(subscription);
    }

    @PutMapping("/update")
    public void updateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.updateSubscription(subscription);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscriptionById(@PathVariable String id) {
        subscriptionService.deleteSubscriptionById(id);
    }

    @GetMapping("/all")
    public Page<Subscription> getAllSubscriptions(@RequestParam(required = false) String novelId,
                                                  @RequestParam(required = false) String userId,
                                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return subscriptionService.getAllSubscriptions(pageNo, pageSize,novelId,userId);
    }
    // 其他业务方法的映射
}

