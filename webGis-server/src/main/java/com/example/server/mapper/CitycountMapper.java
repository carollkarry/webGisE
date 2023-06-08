package com.example.server.mapper;

import com.example.server.pojo.Citycount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author carollkarry
 * @since 2023-04-21
 */
public interface CitycountMapper extends BaseMapper<Citycount> {
    List<Citycount> getCityCount();
    //update


}
