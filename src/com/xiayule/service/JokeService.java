package com.xiayule.service;

import java.io.IOException;

/**
 * Created by tan on 14-6-14.
 */
public class JokeService {
    public static String getJoke() {
  /*      参数
        type 可选 笑话类型

        type是一个数字，可选
        0 中国古代笑话
        1 交往篇
        2 交通篇
        3 体育篇
        4 农村标语
        5 医疗篇
        6 司法篇
        7 名人篇
        8 外国笑话
        9 婚姻篇
        10 宗教篇
        11 政治篇
        12 文化篇
        13 文学篇
        14 校园笑话
        15 物体篇
        16 短信
        17 综合篇
        18 艺术篇
        19 行为篇
        20 鬼话篇

        没有该参数时或者该参数为-1时随机返回一个笑话*/
        try {
            String s = HttpService.get("http://brisk.eu.org/api/joke.php");
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
