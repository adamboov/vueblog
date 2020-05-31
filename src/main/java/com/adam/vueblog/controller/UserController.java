package com.adam.vueblog.controller;


import com.adam.vueblog.common.lang.Result;
import com.adam.vueblog.entity.User;
import com.adam.vueblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public Object index() {
        User user = userService.getById(1L);
        return Result.success(200, "操作成功", user);
    }
}
