package com.example.server.controller;


import com.example.server.pojo.Weather;
import com.example.server.util.HttpClientUtil;
import com.example.server.util.PinyinUtil;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 使用第三方API获取城市天气，当日运势等信息
 */
@RestController
@RequestMapping("/cityVisual")
public class OtherController {
    //天气的key
    //private static String WeatherKey = "2PUMP8GM88G734D5BFXJPS8D4";
    private static String WeatherKey = "7TA36MNL99QV2PB36DMXCTQEW";
    //天气查城市15日天气预报的url
    private static String WeatherCity = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    /**
     * 获取某城市15日天气预报
     * @param CityName 城市中文名
     * @return 结果天气列表
     */
    @ApiOperation(value = "查询天气")
    @GetMapping("/getWeather")
    public List<Weather> GetCityWeather(@RequestParam String CityName){
        List<Weather> weathers = new ArrayList<>();
        //将中文城市名转为拼音
        String CityPinyin = PinyinUtil.Chinese2Pinyin(CityName);
        //拼接url
        String WeatherUrl = WeatherCity+CityPinyin;
        //Date不为空，查询15天天气预报；否则查询Date对应日天气
//        if(!Date.equals("")){
//            WeatherUrl = WeatherUrl+"/"+Date+"/"+Date;
//        }

        //查询天气
        HashMap<String, String> kvs = new HashMap<>();
        kvs.put("unitGroup", "metric");
        kvs.put("key", WeatherKey);
        kvs.put("include", "days");
        String jsonData;//返回对json数据
        try {
            jsonData = HttpClientUtil.doGet(WeatherUrl, kvs);

            JSONObject jsonObject = JSONObject.fromObject(jsonData);
            JSONArray daysJsonArray = jsonObject.getJSONArray("days");
            for (int i = 0; i < daysJsonArray.size(); i++) {
                JSONObject dayValue = daysJsonArray.getJSONObject(i);

                String date=dayValue.getString("datetime");
                double maxtemp=dayValue.getDouble("tempmax");
                double mintemp=dayValue.getDouble("tempmin");
                String condition=PinyinUtil.weather_EN2QI(PinyinUtil.SplitWeather(dayValue.getString("conditions")));

                System.out.println("天气："+condition);
                Weather weather = new Weather(CityName, date, maxtemp, mintemp, condition);
                weathers.add(weather);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weathers;
    }

//    public static void main(String[] args){
//        GetCityWeather("北京", "2022-03-13");
//    }
}
