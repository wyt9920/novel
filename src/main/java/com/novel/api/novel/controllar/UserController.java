package com.novel.api.novel.controllar;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.User;
import com.novel.api.novel.service.UserService;
import com.novel.api.novel.utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        if (user.getAvatar() == null || user.getAvatar().isEmpty()) {
            user.setAvatar("default.jpg");
        }
        if (user.getIdentity() == null || user.getIdentity().isEmpty()) {
            user.setIdentity("user");
        }
        if (user.getRegistrationDate() == null) {
            user.setRegistrationDate(LocalDate.now());
        }
        if (user.getNickName() == null || user.getNickName().isEmpty()) {
            user.setNickName("阅读者" + Integer.toHexString(new Random().nextInt(1000)));
        }

        userService.saveUser(user);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/all")
    public Page<User> getAllUsers(@RequestParam(required = false) String username,
                                  @RequestParam(required = false) String nickName,
                                  @RequestParam(required = false) String identity,
                                  @RequestParam(defaultValue = "1",required = false) Integer pageNo,
                                  @RequestParam(defaultValue = "10",required = false) Integer pageSize) {
        return userService.getAllUsers(pageNo, pageSize,username,nickName,identity);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // 根据用户名查询用户
        User user = userService.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            // 用户不存在或密码错误
            return null;
        }

        // 登录成功，生成token
        Map<String, Object> response = new HashMap<>();
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", user.getId());
        userInfo.put("username", user.getUsername());
        String token = JWTUtils.getToken(userInfo);

        // 将 token 和用户信息添加到响应中
        response.put("token", token);
        response.put("user", user);

        return response;
    }
    // 其他业务方法的映射
}

