package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.mapper.CitycountMapper;
import com.example.server.pojo.Citycount;
import com.example.server.service.ICitycountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/citycount")
public class CitycountController {
    @Autowired
    private ICitycountService iCitycountService;

    @Autowired
    private CitycountMapper citycountMapper;
    @ApiOperation(value = "各市演唱会数量")
    @GetMapping("/getConcertNumByCity")
    public List<Citycount> getConcertNumByCity(){
        QueryWrapper<Citycount> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("name","count");
        List<Citycount> allConcertActor=citycountMapper.selectList(queryWrapper);
        return allConcertActor;
    }
    @ApiOperation(value = "获取所有城市")
    @GetMapping("/getConcertCity")
    public List<String> getConcertCity(){
        QueryWrapper<Citycount> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("name");
        List<Citycount> allConcertActor=citycountMapper.selectList(queryWrapper);
        List<String> listCity=new ArrayList<>();
        for(int i=0;i<allConcertActor.size();i++){
            listCity.add(allConcertActor.get(i).getName());
        }
        return listCity;
    }
}
