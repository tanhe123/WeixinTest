package com.xiayule.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XmlParser {
/*    public static void main(String[] args) throws DocumentException {
        String xml = "<xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[this is a test]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";

        Map<String, String> map = parseXmlToMap(xml);
        Iterator<String> ita = map.keySet().iterator();
        while (ita.hasNext()) {
            String s = ita.next();
            System.out.println(s + ":" + map.get(s));
        }
        System.out.println("...end");
    }*/

    public static Map<String, String> parseXmlToMap(String xml) throws DocumentException {
        Map<String, String> res = new HashMap<String, String>();

        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();

        Iterator ita = root.elementIterator();

        while (ita.hasNext()) {
            Element e = (Element) ita.next();
            System.out.println(e.getName());
            res.put(e.getName(), e.getText());
        }

        return res;
    }

    // 从名为 rootName 的二级结点开始解析
    public static Map<String, String> parseXmlToMap(String xml, String rootName) throws DocumentException {
        Map<String, String> res = new HashMap<String, String>();

        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();

        Element weather = root.element(rootName);

        Iterator ita = weather.elementIterator();

        while (ita.hasNext()) {
            Element e = (Element) ita.next();
            res.put(e.getName(), e.getText());
        }

        return res;
    }

    public static Map<String, String> parseXmlToMap(InputStream inputStream) throws DocumentException {
        Map<String, String> res = new HashMap<String, String>();

        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        Element root = document.getRootElement();
        Iterator ita = root.elementIterator();
        while (ita.hasNext()) {
            Element e = (Element) ita.next();
            res.put(e.getName(), e.getText());
        }
        return res;
    }
}
