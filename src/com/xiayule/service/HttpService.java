package com.xiayule.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tan on 14-6-13.
 */
public class HttpService {

    public static String get(String addr) throws IOException {
        return get(addr, "UTF-8");
    }

    /**
     * get the content of the url
     * @param addr the url of the content to get
     * @param charsetName 网页的编码, such as gbk
     * @return the content of the url
     * @throws IOException
     */
    public static String get(String addr, String charsetName) throws IOException {;
        URL url = new URL(addr);

        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charsetName));

        String tmp;
        while((tmp = reader.readLine()) != null) {
            content.append(tmp);
        }

        reader.close();

        return content.toString();
    }
}
