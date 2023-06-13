package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.mapper.ConcertMapper;
import com.example.server.pojo.Concert;
import com.example.server.service.IConcertService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carollkarry
 * @since 2023-06-08
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

    @ApiOperation(value = "按地点查找演唱会")
    @GetMapping("/getConcernByVenue")
    public List<Concert> getConcertByVenue(@RequestParam String venue){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("venue",venue);
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

    @ApiOperation(value = "按类型查找")
    @GetMapping("/getConcertByCategory")
    public List<Concert> getConcertByCategory(@RequestParam String category){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("categoryname",category);
        List<Concert> allConcert=concertMapper.selectList(queryWrapper);
        return allConcert;
    }

    @ApiOperation(value = "艺人轨迹展示")
    @GetMapping("/getActorRoutine")
    public List<Concert> getActorRoutine(@RequestParam String actor){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("actors",actor);
        List<Concert> allActor=concertMapper.selectList(queryWrapper);
        List<Concert> allActorTemp=allActor;
        for(Concert concert:allActorTemp){
            //正则表达式切割字符串
            String [] time=concert.getShowtime().split("\\.| |\\-");
            String tt="";
            /**
             * 处理时间，转换格式为YY-MM-DD
             */
            for(int i=0;i<2;i++){
                String temp=time[i]+"-";
                tt+=temp;
            }
            tt+=time[2];
            concert.setShowtime(tt);
        }
        /**
         * 根据时间进行排序
         */
        allActorTemp.sort((t1, t2) -> t1.getShowtime().compareTo(t2.getShowtime()));
        return allActorTemp;
    }

    @ApiOperation(value = "按价格范围查询")
    @GetMapping("/getConcertByPrice")
    public List<Concert> getConcertByPrice(@RequestParam int lowPrice,int topPrice){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        queryWrapper.ge("price",lowPrice).le("pricehigh",topPrice);
        List<Concert> allData=concertMapper.selectList(queryWrapper);
        return allData;
    }

    @ApiOperation(value = "按时间点查询")
    @GetMapping("/getConcertByTime")
    public List<Concert> getConcertByTime(@RequestParam String time){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String times=simpleDateFormat.format(time);
        String newTime= time.replace('-', '.');
        queryWrapper.like("showtime",newTime);
        List<Concert> allConcert=concertMapper.selectList(queryWrapper);
        return allConcert;
    }

    @ApiOperation(value = "获取所有演唱会")
    @GetMapping("/getAllConcert")
    public List<Concert> getAllConcert(){
        QueryWrapper<Concert> queryWrapper=new QueryWrapper<>();
        List<Concert> allConcert = concertMapper.selectList(queryWrapper);

        return allConcert;
    }

}
