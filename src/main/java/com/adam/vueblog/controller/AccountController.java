package com.adam.vueblog.controller;

import com.adam.vueblog.common.lang.Result;
import com.adam.vueblog.dto.LoginDTO;
import com.adam.vueblog.entity.User;
import com.adam.vueblog.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDTO.getUsername()));

        Assert.notNull(user, "用户不存在！");

        return null;
    }
}
