package com.example.server.service;

import com.example.server.pojo.Concert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-08
 */
public interface IConcertService extends IService<Concert> {
    RespBean concert(Concert concert);
    List<Concert> getAllConcert();
}
