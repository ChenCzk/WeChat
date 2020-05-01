package Servlet;

import Service.WXService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/*
     http://czkweixin.free.idcfengye.com/testWX2/czk
     3694086fecace8a8
* */
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");


        //将收到的信息封装到map
        Map map = WXService.parseRequest(request.getInputStream());
        //发送文本消息
        PrintWriter out = response.getWriter();
        out.write(WXService.respXML(request, map));
        out.flush();
        out.close();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signature微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        //timestamp	时间戳
        String timestamp = request.getParameter("timestamp");
        //nonce	随机数
        String nonce = request.getParameter("nonce");
        //echostr随机字符串
        String echostr = request.getParameter("echostr");

        if (WXService.check(timestamp, nonce, signature)) {
            //若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.flush();
            out.close();
            System.out.println("微信公众号接入成功");
        } else {
            System.out.println("微信公众号接入失败");
        }
        // http://106.53.120.98:8080/WX/czk?signature=11&timestamp=111&nonce=20&echostr=44
    }
}
