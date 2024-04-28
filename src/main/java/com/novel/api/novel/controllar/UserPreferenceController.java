package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.UserPreference;
import com.novel.api.novel.service.UserPreferenceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userPreference")
public class UserPreferenceController {

    @Resource
    private UserPreferenceService userPreferenceService;

    @GetMapping("/{id}")
    public UserPreference getUserPreferenceById(@PathVariable String id) {
        return userPreferenceService.getUserPreferenceById(id);
    }

    @PostMapping("/save")
    public void saveUserPreference(@RequestBody UserPreference userPreference) {
        userPreferenceService.saveUserPreference(userPreference);
    }

    @PutMapping("/update")
    public void updateUserPreference(@RequestBody UserPreference userPreference) {
        userPreferenceService.updateUserPreference(userPreference);
    }

    @DeleteMapping("/{id}")
    public void deleteUserPreferenceById(@PathVariable String id) {
        userPreferenceService.deleteUserPreferenceById(id);
    }

    @GetMapping("/all")
    public Page<UserPreference> getAllUserPreferences(@RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return userPreferenceService.getAllUserPreferences(pageNo, pageSize);
    }
    // 其他业务方法的映射
}

