package com.adam.vueblog.controller;


import com.adam.vueblog.common.lang.Result;
import com.adam.vueblog.entity.Blog;
import com.adam.vueblog.service.BlogService;
import com.adam.vueblog.util.ShiroUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author VAIO-adam
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogList")
    public Result list(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "7") Integer limit) {

        Page page = new Page(offset, limit);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.success("获取成功", pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除！");

        return Result.success("获取成功！", blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        Blog temp;

        if (blog.getId() != null) {
            //  编辑
            temp = blogService.getById(blog.getId());
            //  只能编辑自己的文章
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑！");

        } else {
            //  添加
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtils.copyProperties(blog, temp, "id", "userId", "create", "status");
        blogService.saveOrUpdate(temp);

        return Result.success("添加成功！", null);
    }

}
