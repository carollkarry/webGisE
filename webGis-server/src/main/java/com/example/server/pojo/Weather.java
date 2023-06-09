package com.example.server.pojo;

public class Weather {
    //城市
    private String city;
    //日期
    private String date;
    //最高气温
    private double CelsiusHigh;
    //最低气温
    private double CelsiusLow;
    //天气情况
    private String condition;

    public Weather(){};

    public Weather(String city, String date, double CelsiusHigh, double CelsiusLow, String condition){
        this.city=city;
        this.date=date;
        this.CelsiusHigh=CelsiusHigh;
        this.CelsiusLow=CelsiusLow;
        this.condition=condition;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public double getCelsiusHigh() {
        return CelsiusHigh;
    }

    public double getCelsiusLow() {
        return CelsiusLow;
    }

    public String getCondition() {
        return condition;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCelsiusHigh(double celsiusHigh) {
        CelsiusHigh = celsiusHigh;
    }

    public void setCelsiusLow(double celsiusLow) {
        CelsiusLow = celsiusLow;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
