package com.novel.api.novel.controllar;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.novel.api.novel.entity.Novel;
import com.novel.api.novel.entity.UserPreference;
import com.novel.api.novel.service.NovelService;
import com.novel.api.novel.service.UserPreferenceService;
import com.novel.api.novel.utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/novel")
public class NovelController {

    @Resource
    private NovelService novelService;

    @Resource
    private UserPreferenceService userPreferenceService;

    @GetMapping("/{id}")
    public Novel getNovelById(@RequestHeader("Authorization") String token,@PathVariable String id) {

        // 验证token的合法性并解析出用户ID
        DecodedJWT decodedJWT = JWTUtils.verify(token);
        String userId = decodedJWT.getClaim("userId").asString();
        Novel novel = novelService.getNovelById(id);
        userPreferenceService.getObjectByUidAndGid(userId,novel.getGenre());
        return novel;
    }

    @PostMapping("/save")
    public void saveNovel(@RequestBody Novel novel) {
        novel.setPublicationDate(LocalDate.now());
        novelService.saveNovel(novel);
    }

    @PutMapping("/update")
    public void updateNovel(@RequestBody Novel novel) {
        novelService.updateNovel(novel);
    }

    @DeleteMapping("/{id}")
    public void deleteNovelById(@PathVariable String id) {
        novelService.deleteNovelById(id);
    }

    @GetMapping("/all")
    public IPage<Novel> searchNovels(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int pageSize) {

        return novelService.searchNovels(title, author, gender,genre, current, pageSize);
    }

    // 获取喜欢列表
    @GetMapping("/recommend/{userId}")
    public List<Novel> getRecommendations(@PathVariable String userId) {
        // 查询用户喜好数据，假设存在一个user_preference表，包含user_id、genre和preference_weight字段
        List<UserPreference> userPreferences = userPreferenceService.list(new QueryWrapper<UserPreference>().eq("user_id", userId));

        if (!userPreferences.isEmpty()) {
            // 如果用户存在喜好数据，计算分类的总权重
            int totalWeight = userPreferences.stream().mapToInt(UserPreference::getPreferenceWeight).sum();

            Random random = new Random();

            // 初始化存储各个类别选择数量的Map
            Map<String, Integer> selectedGenresCount = new HashMap<>();
            for (UserPreference preference : userPreferences) {
                selectedGenresCount.put(preference.getGenreId(),0);
            }

            for (UserPreference preference : userPreferences) {
                double probability = (double) preference.getPreferenceWeight() / totalWeight;
                int count = (int) (probability * 10)+selectedGenresCount.get(preference.getGenreId()); // 按照权重比例规划数量
                selectedGenresCount.put(preference.getGenreId(), count);
            }

            // 根据选择的分类随机选择小说
            List<Novel> recommendedNovels = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : selectedGenresCount.entrySet()) {
                String genre = entry.getKey();
                int count = entry.getValue();

                List<Novel> novels = novelService.list(new QueryWrapper<Novel>().eq("genre", genre).last("LIMIT " + count));

                recommendedNovels.addAll(novels);
            }
            return recommendedNovels;
        } else {
            // 如果用户不存在喜好数据，直接随机选择10本小说
            return novelService.list(new QueryWrapper<Novel>().last("LIMIT 10 offset "+new Random().nextInt(10)));
        }
    }
}
// 其他业务方法的映射

