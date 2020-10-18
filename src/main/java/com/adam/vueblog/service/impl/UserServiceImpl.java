package com.adam.vueblog.service.impl;

import com.adam.vueblog.entity.User;
import com.adam.vueblog.mapper.UserMapper;
import com.adam.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @since 2020-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
