package com.xiayule.service;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tan on 14-6-13.
 */
public class TodayInHistoryService {
    public static void main(String[] args) throws IOException {
        System.out.println(getTodayInHistory());
    }


    public static String getTodayInHistory() throws IOException {
        StringBuilder buffer = null;

        String html = "";
        buffer = new StringBuilder();

        buffer.append("==历史上的今天==\n");

        html = HttpService.get("http://www.rijiben.com/");

        Pattern p = Pattern.compile("(.*)<div class=\"listren\">(.*?)</div>(.*)");
        Matcher m = p.matcher(html);
        if (m.matches()) {
            for (String info : m.group(2).split("   ")) {
                info = info.replaceAll("</?[^>]+>", "").replace("（图）", "")
                        .replace("&nbsp;", "").trim();

                if (!info.equals(""))
                    buffer.append(info).append("\n");
            }
        }

        return buffer.toString();
    }
}
