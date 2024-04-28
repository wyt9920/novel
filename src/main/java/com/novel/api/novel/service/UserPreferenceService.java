package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Genre;
import com.novel.api.novel.entity.UserPreference;
import com.novel.api.novel.mapper.UserPreferenceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserPreferenceService {

    @Resource
    private UserPreferenceMapper userPreferenceMapper;

    public UserPreference getUserPreferenceById(String id) {
        return userPreferenceMapper.selectById(id);
    }

    public void saveUserPreference(UserPreference userPreference) {
        userPreferenceMapper.insert(userPreference);
    }

    public void updateUserPreference(UserPreference userPreference) {
        userPreferenceMapper.updateById(userPreference);
    }

    public void deleteUserPreferenceById(String id) {
        userPreferenceMapper.deleteById(id);
    }
    public Page<UserPreference> getAllUserPreferences(Integer pageNo, Integer pageSize) {
        Page<UserPreference> page = new Page<>(pageNo, pageSize);
        return userPreferenceMapper.selectPage(page, null);
    }

    // 存储权重信息
    public void getObjectByUidAndGid(String uid, String gid) {
        QueryWrapper<UserPreference> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uid).eq("genre_id", gid);
        UserPreference userPreference =  userPreferenceMapper.selectOne(queryWrapper);
        if (userPreference == null) {
            // 如果不存在则插入一条新数据
            UserPreference newPreference = new UserPreference();
            newPreference.setUserId(uid);
            newPreference.setGenreId(gid);
            newPreference.setPreferenceWeight(1); // 设置默认值为1
            userPreferenceMapper.insert(newPreference);
        } else {
            // 如果存在则更新现有数据的preferenceWeight字段
            userPreference.setPreferenceWeight(userPreference.getPreferenceWeight() + 1);
            userPreferenceMapper.updateById(userPreference);
        }
    }

    public List<UserPreference> list(QueryWrapper<UserPreference> user_id) {
        return userPreferenceMapper.selectList(user_id);
    }
    // 其他业务方法的实现
}

