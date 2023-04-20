package com.example.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author carollkarry
 * @since 2022-06-25
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
