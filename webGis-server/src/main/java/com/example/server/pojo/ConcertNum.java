package com.example.server.pojo;

public class ConcertNum {
    //城市
    private String cityname;
    //城市对应的演唱会数
    private Integer concertnum;

    public String getCityname() {
        return cityname;
    }

    public Integer getConcertnum() {
        return concertnum;
    }
    public void setCityname(String cityname){
        this.cityname=cityname;
    }
    public void setConcertnum(Integer concertnum){
        this.concertnum=concertnum;
    }
}
