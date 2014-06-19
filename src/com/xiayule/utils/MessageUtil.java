package com.xiayule.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xiayule.message.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MessageUtil {
    private static XStream xstream = new XStream(new DomDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    public static Map<String, String> processRequestToMap(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        Map<String, String> map = null;
        try {
            map = XmlParser.parseXmlToMap(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return map;
    }

    public static String getHelpMenu() {
        StringBuilder helpMenu = new StringBuilder();

        helpMenu.append("帮助菜单:\n")
                .append("1.历史上的今天\n")
                .append("2.随机笑话\n")
                .append("=============\n")
                .append("查询天气: 例如北京天气\n")
                .append("? 帮助\n");
        return helpMenu.toString();
    }

}
