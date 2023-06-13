package com.example.server.service.impl;

import com.example.server.pojo.Concert;
import com.example.server.mapper.ConcertMapper;
import com.example.server.pojo.RespBean;
import com.example.server.service.IConcertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-08
 */
@Service
public class ConcertServiceImpl extends ServiceImpl<ConcertMapper, Concert> implements IConcertService {

    @Autowired
    private ConcertMapper concertMapper;

    @Override
    public RespBean concert(Concert concert) {
        if(concert.getName()==null){
            return RespBean.error("演唱会名为空");
        }
        if(concert.getCityname()==null){
            return RespBean.error("城市名为空");
        }
        if(concert.getShowtime()=="null"){
            return RespBean.error("时间为空");
        }
        concertMapper.insert(concert);
        return RespBean.success("插入成功！");
    }
    @Override
    public List<Concert> getAllConcert() {
        return concertMapper.getAllConcert();
    }
}
