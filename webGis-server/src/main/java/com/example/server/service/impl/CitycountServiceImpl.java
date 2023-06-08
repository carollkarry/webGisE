package com.example.server.service.impl;

import com.example.server.pojo.Citycount;
import com.example.server.mapper.CitycountMapper;
import com.example.server.pojo.RespBean;
import com.example.server.service.ICitycountService;
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
 * @since 2023-04-21
 */
@Service
public class CitycountServiceImpl extends ServiceImpl<CitycountMapper, Citycount> implements ICitycountService {

    @Autowired
    private CitycountMapper citycountMapper;

    @Override
    public RespBean concertNum(Citycount citycount) {
        if(citycount.getName()=="null"){
            return RespBean.error("城市名为空");
        }
        if(citycount.getCount()=="null"){
            return RespBean.error("数量为空");
        }
        citycountMapper.insert(citycount);
        return RespBean.success("插入成功！");
    }

    @Override
    public List<Citycount> getCityCount() {
        return citycountMapper.getCityCount();
    }

}
