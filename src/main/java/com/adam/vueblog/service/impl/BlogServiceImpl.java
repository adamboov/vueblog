package com.adam.vueblog.service.impl;

import com.adam.vueblog.entity.Blog;
import com.adam.vueblog.mapper.BlogMapper;
import com.adam.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author VAIO-adam
 * @since 2020-05-30
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
