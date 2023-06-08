package com.example.server.service;

import com.example.server.pojo.Citycount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Concert;
import com.example.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author carollkarry
 * @since 2023-04-21
 */
public interface ICitycountService extends IService<Citycount> {
    RespBean concertNum(Citycount citycount);
    List<Citycount> getCityCount();
}
