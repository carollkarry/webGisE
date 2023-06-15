package com.example.server.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;

/**
 * 字符串转换工具类
 */
public class PinyinUtil {
    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     * @param ChineseStr 汉字
     * @return 拼音字符串
     */
    public static String Chinese2Pinyin(String ChineseStr){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String output = "";
        if (!StringUtils.isEmpty(ChineseStr) && !"null".equals(ChineseStr)) {
            char[] input = ChineseStr.trim().toCharArray();
            try {
                String notChsTmp = "";
                for (int i = 0; i < input.length; i++) {
                    if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        if (!StringUtils.isEmpty(notChsTmp)){
                            output += notChsTmp;
                            output += " ";
                            notChsTmp = "";
                        }
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                        output += temp[0];
                        if (i < input.length - 1){
                            output += " ";
                        }
                    } else {
                        notChsTmp += java.lang.Character.toString(input[i]);
                    }
                }
                //循环外面如果最后一个不是中文，最后补加上非中文字符串
                if (!StringUtils.isEmpty(notChsTmp)){
                    output += notChsTmp;
                    notChsTmp = "";
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    /**
     * 天气状况中英文转换
     * @param weatherEN 天气状况英文描述
     * @return 天气状况中文描述
     */
    public static String weather_EN2CN(String weatherEN){
        String[] enWords = {"Clear", "Partially cloudy", "Mostly cloudy", "Overcast", "Rain", "Snow", "Freezing rain", "Sleet", "Hail", "Thunderstorm", "Fog", "Smoke", "Mist", "Haze"};
        String[] cnWords = {"晴朗", "局部多云", "大部分多云", "阴天", "有雨", "有雪", "有冻雨", "有雨夹雪", "有冰雹", "有雷暴", "有雾", "有浓烟", "有薄雾", "有雾霾"};

        String weatherCN = weatherEN;
        for(int i = 0; i < enWords.length; i++) {
            if(weatherCN.contains(enWords[i])) {
                weatherCN.replace(enWords[i], cnWords[i]);
            }
        }

        return weatherCN;
    }

    /**
     * 只保留第一个天气状况
     * @param weatherStr
     * @return
     */
    public static String SplitWeather(String weatherStr){
        int index = weatherStr.indexOf(",");
        if (index == -1) {
            return weatherStr;
        } else {
            return weatherStr.substring(0, index);
        }
    }

    public static String weather_EN2QI(String weatherEN){
        String[] enWords = {"Clear", "Partially cloudy", "Mostly cloudy", "Overcast", "Rain", "Snow", "Freezing rain", "Sleet", "Hail", "Thunderstorm", "Fog", "Smoke", "Mist", "Haze"};
        String[] qiWords = {"sunny", "few-clouds", "partly-cloudy", "overcast", "rain", "snow", "freeze", "rain-and-snow", "hail", "thundershower", "foggy", "sand", "mist", "haze"};

        String weatherQI = weatherEN;
        for(int i = 0; i < enWords.length; i++) {
            if(weatherQI.equals(enWords[i])) {
                weatherQI = "qi-"+qiWords[i];
            }
        }

        return weatherQI;
    }
}
