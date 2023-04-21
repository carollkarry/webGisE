package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.mapper.ConcertMapper;
import com.example.server.pojo.Concert;
import com.example.server.pojo.ConcertNum;
import com.example.server.service.IConcertService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carollkarry
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/concert")
public class ConcertController {
    @Autowired
    private IConcertService iConcertService;

    @Autowired
    private ConcertMapper concertMapper;

    @ApiOperation(value = "按城市名查询演唱会信息")
    @GetMapping("/getConcertByCity")
    public List<Concert> getConcertByCity(@RequestParam String cityname){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cityname",cityname);
        List<Concert> allConcert=concertMapper.selectList(queryWrapper);
        return allConcert;
    }

    @ApiOperation(value = "按艺人查询演唱会信息")
    @GetMapping("/getConcernByActor")
    public List<Concert> getConcertByActor(@RequestParam String actors){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("actors",actors);
        System.out.println(actors);
        List<Concert> allConcertActor=concertMapper.selectList(queryWrapper);
        return allConcertActor;
    }


}
