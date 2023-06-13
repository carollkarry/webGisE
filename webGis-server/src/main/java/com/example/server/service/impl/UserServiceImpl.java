package com.example.server.service.impl;

import com.example.server.pojo.User;
import com.example.server.mapper.UserMapper;
import com.example.server.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
