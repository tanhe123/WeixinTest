package com.xiayule.servlet;

import com.xiayule.message.TextMessage;
import com.xiayule.service.JokeService;
import com.xiayule.service.TodayInHistoryService;
import com.xiayule.service.WeatherService;
import com.xiayule.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "WeChatServlet",
        urlPatterns = "/WeChatServlet")
public class WeChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> data = MessageUtil.processRequestToMap(request);

        String toUserName = data.get("ToUserName");
        String fromUserName = data.get("FromUserName");
        String msgType = data.get("MsgType");
        String reqContent = data.get("Content");

        String respContent = null;

        if (msgType.equals(MessageType.REQ_MESSAGE_TEXT)) {
            reqContent = reqContent.trim();

            if (reqContent.equals("?")) {
                respContent = MessageUtil.getHelpMenu();
            } else if (reqContent.equals("1")) {// 历史上的今天
                respContent = TodayInHistoryService.getTodayInHistory();
            } else if (reqContent.equals("2")) {// 随机笑话
                respContent = JokeService.getJoke();
            } else if (reqContent.endsWith("天气")) {// 天气
                respContent = WeatherService.getWeather(reqContent.replace("天气", ""));
            } else {
                respContent = reqContent;
            }

            //respContent = reqContent;
         //  respContent = "消息文本";
        } else if (msgType.equals(MessageType.REQ_MESSAGE_EVENT)) {
            // 事件类型
            String eventType = data.get("Event");
            // 订阅
            if (eventType.equals(MessageType.EVENT_TYPE_SUBSCRIBE)) {
                respContent = "==谢谢您的关注==\n" + MessageUtil.getHelpMenu();
            }
        } else {
            respContent = "消息类型错误";
        }

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageType.REQ_MESSAGE_TEXT);
        textMessage.setContent(respContent);

        String respXml = MessageUtil.textMessageToXml(textMessage);
        response.getWriter().write(respXml);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String echoStr = request.getParameter("echostr");

        PrintWriter writer = response.getWriter();

        if (valid(request)) {
            writer.print(echoStr);
        }
    }


    // 验证
    private boolean valid(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        if (signature == null || timestamp == null || nonce == null) return false;

        return SignatureUtil.checkSignature(signature, timestamp, nonce);
    }
}
