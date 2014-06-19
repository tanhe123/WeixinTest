package com.xiayule.service;

import com.xiayule.utils.XmlParser;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by tan on 14-6-12.
 */
public class WeatherService {
    public static void main(String[] args) throws IOException {
        String s = getWeather("上海");
        System.out.println(s);
    }

    public static String getWeather(String city) throws IOException {
        String addr = "http://php.weather.sina.com.cn/xml.php?city=" + URLEncoder.encode(city, "gbk") + "&password=DJOYnieT8234jlsK&day=0";

        //System.out.println("connecting...");
        String xml = HttpService.get(addr);

        //System.out.println(xml);

//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!-- published at 2014-06-13 23:07:53 --><Profiles><Weather><city>济宁</city><status1>晴</status1><status2>多云</status2><figure1>qing</figure1><figure2>duoyun</figure2><direction1>无持续风向</direction1><direction2>南风</direction2><power1>≤3</power1><power2>≤3</power2><temperature1>34</temperature1><temperature2>23</temperature2><ssd>8</ssd><tgd1>30</tgd1><tgd2>30</tgd2><zwx>5</zwx><ktk>3</ktk><pollution>3</pollution><xcz>4</xcz><zho></zho><diy></diy><fas></fas><chy>1</chy><zho_shuoming>暂无</zho_shuoming><diy_shuoming>暂无</diy_shuoming><fas_shuoming>暂无</fas_shuoming><chy_shuoming>短袖衫、短裙、短裤、薄型T恤衫、敞领短袖棉衫</chy_shuoming><pollution_l>轻度</pollution_l><zwx_l>很强</zwx_l><ssd_l>较热</ssd_l><fas_l>暂无</fas_l><zho_l>暂无</zho_l><chy_l>薄短袖类</chy_l><ktk_l>较适宜开启(制冷)</ktk_l><xcz_l>不太适宜</xcz_l><diy_l>暂无</diy_l><pollution_s>对空气污染物扩散无明显影响</pollution_s><zwx_s>紫外线很强</zwx_s><ssd_s>户外活动不适宜在中午前后展开。</ssd_s><ktk_s>比较适宜开启空调</ktk_s><xcz_s>洗车后未来1-2天内有降水、大风或沙尘天气，不太适宜洗车</xcz_s><gm>2</gm><gm_l>易发期</gm_l><gm_s>天气闷热，注意预防热伤风；</gm_s><yd>5</yd><yd_l>不适宜</yd_l><yd_s>天气闷热，不适宜户外运动；</yd_s><savedate_weather>2014-06-13</savedate_weather><savedate_life>2014-06-13</savedate_life><savedate_zhishu>2014-06-13</savedate_zhishu></Weather></Profiles>";

        StringBuilder respContent = new StringBuilder();

        Map<String, String> data = null;

        try {
            System.out.println("analyzing...");
            data = XmlParser.parseXmlToMap(xml, "Weather");
/*
            for (String key : data.keySet()) {
                System.out.println(key + ":" + data.get(key));
            }
*/
            respContent.append("城市: "+data.get("city")).append("\n")
                    .append("天气: " + data.get("status1") + " 转 " + data.get("status2")).append("\n")
                    .append("温度: " + data.get("temperature2") + "~" + data.get("temperature1")).append("\n");


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return respContent.toString();
    }
}
