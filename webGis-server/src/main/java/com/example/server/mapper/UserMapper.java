package com.example.server.mapper;

import com.example.server.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-12
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> getAllUser();
}
