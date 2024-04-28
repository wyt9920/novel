package com.novel.api.novel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Novel;
import com.novel.api.novel.entity.User;
import com.novel.api.novel.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    public void saveUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    public void deleteUserById(String id) {
        userMapper.deleteById(id);
    }

    // 分页数据
    public Page<User> getAllUsers(Integer pageNo, Integer pageSize, String username,String nickNAme, String identity) {
        Page<User> page = new Page<>(pageNo, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 使用 lambda 表达式判断参数是否为空，并添加对应的查询条件
        queryWrapper.like(StringUtils.isNotBlank(username), "username", username)
                .like(StringUtils.isNotBlank(nickNAme), "nick_name", nickNAme)
                .eq(StringUtils.isNotBlank(identity), "identity", identity);

        return userMapper.selectPage(page, queryWrapper);
    }

    // 登录
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }


    // 其他业务方法的实现
}

