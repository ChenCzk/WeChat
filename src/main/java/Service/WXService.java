package Service;

import com.baidu.aip.ocr.AipOcr;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pojo.MyToken;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WXService {
    private static MyToken token = null;
    private static final String TOKEN = "czk";
    private static final String APPID = "wxb80d980b59ca0366";
    private static final String APPSECREY = "8951b44adb834a58c812bed287652b72";

    //图灵机器人
    private static final String APIKEY = "575840992afd970e3f67b37eb225917c";

    //设置百度图片识别APPID/AK/SK
    public static final String APP_ID = "17576421";
    public static final String API_KEY = "5dxFpbjpETpPxPYTDogDqQMK";
    public static final String SECRET_KEY = "eLSmGAosBSRuC2BY1PY5Qbaiq4CGX3gY";

    /*
     *  开发者通过检验signature对请求进行校验
     * */
    public static boolean check(String timestamp, String nonce, String signature) {

        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] str = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(str);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String s = str[0] + str[1] + str[2];
        String s1 = sha1(s); //s1位加密处理后的字符串
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信

        System.out.println(s1);
        System.out.println(signature);
        return s1.equalsIgnoreCase(signature);

    }


    /*
     * sha1 加密
     * */
    private static String sha1(String src) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                //处理高4位
                sb.append(chars[(b >> 4) & 15]);
                //处理低4位
                sb.append(chars[b & 15]);
            }
            return sb.toString(); //返回处理加密结果
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     *  获取XML信息，包装成map类型，方便后面获取。
     * */
    public static Map parseRequest(InputStream inputStream) {
        //检查token
        getToken();
        Map<String, String> map = new HashMap<>();

        SAXReader sax = new SAXReader();
        try {
            // 获取文档对象
            Document document = sax.read(inputStream);
            // 获取根节点
            Element rootElement = document.getRootElement();
            // 遍历子节点
            List<Element> elements = rootElement.elements();
            // 将XML 转化位键值对
            for (Element element : elements) {
                map.put(element.getName(), element.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
     *  设置应答内容
     * */
    public static String respXML(HttpServletRequest request, Map<String, String> map) {
        String msgType = map.get("MsgType"); //判断信息类型
        String message = map.get("Content"); //用户信息内容
        System.out.println("用户信息发送" + msgType + "类型的信息");
        System.out.println(map);

        switch (msgType) {
            case "text":  //如果是文本，回复文本
                if (message == "莫梓琪" || message.equals("莫梓琪")) {
                    return WXSend.sendImage(map, message);
                }
                if (message == "视频" || message.equals("视频")) {
                    return WXSend.sendVideo(map, message);
                }

                String str = TulinRoot(message);    //回复消息
                return WXSend.sendText(map, str);  //将文本装换为XML格式的字符串
            case "image": //如果是图片，回复文本
                String s  =dealImage(map);
                return WXSend.sendText(map, s);

            case "voice": //如果是声音，回复文本
                str = "voice";
                return WXSend.sendText(map, str);  //将文本装换为XML格式的字符串
            case "video": //如果是视频，回复文本
                str = "video";
                return WXSend.sendText(map, str);  //将文本装换为XML格式的字符串
            case "link": //如果是链接，回复文本
                str = "link";
                return WXSend.sendText(map, str);  //将文本装换为XML格式的字符串
            case "shortvideo": //如果是小视频，回复文本
                str = "shortvideo";
                return WXSend.sendText(map, str);  //将文本装换为XML格式的字符串
            case "location":  //如果是图文信息，回复文本
                str = "location";
                return WXSend.sendText(map, str);  //将文本装换为XML格式的字符串
            case "event":
                String str1 = dealEvent(map);
                return str1;
        }
        return null;
    }
    /*
    * 处理图像->文字
    * 远程URL: basicGeneralUrl
    * 本地URL: basicGeneral
    * */
    private static String dealImage(Map<String, String> map) {
        String url = map.get("PicUrl");
        System.out.println(url);
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        org.json.JSONObject res = client.basicGeneralUrl(url, new HashMap<String, String>());
        System.out.println(res.toString(2));
        String s = res.toString(2);
        JSONObject jsonObject =JSONObject.fromObject(s);
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        StringBuffer sb = new StringBuffer();
        Iterator<JSONObject> iterator = words_result.iterator();
        while (iterator.hasNext()){
            JSONObject next = iterator.next();
            sb.append(next.getString("words"));
        }

        return sb.toString();
    }

    /*
    * 处理事件
    * */
    private static String dealEvent(Map<String, String> map) {
        String event = map.get("Event");
        switch (event){
            case "CLICK":
                return dealClickEvent(map);
        }
        return null;
    }
    /*
    * 处理点击事件
    * */
    private static String dealClickEvent(Map<String, String> map) {
        String eventKey = map.get("EventKey");
        switch (eventKey){
            case "1"://一级菜单的按钮
                String s = WXSend.sendText(map, "你点击了一级点击按钮");
                return s;
            case "2":
                String s2 = WXSend.sendText(map, "你点击了二级点击按钮");
                return s2;
        }

        return null;
    }

    /*
     * 图灵机器人
     * */
    private static String TulinRoot(String message) {
        try {
            String INFO = URLEncoder.encode(message, "utf-8");//这里可以输入问题
            String getURL = "http://op.juhe.cn/iRobot/index?info=" + INFO + "&key=" + APIKEY;
            String s = Util.get(getURL);
            JSONObject jsonObject = JSONObject.fromObject(s);
            JSONObject result = jsonObject.getJSONObject("result");
            String text = result.getString("text");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
     *  获取Access_token
     * */
    private static String getMyToken() {
        try {
            String URL = " https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECREY;
            String s = Util.get(URL);

            JSONObject jsonObject1 = JSONObject.fromObject(s);
            String new_access_token = jsonObject1.getString("access_token");
            Long new_expires_in = Long.valueOf(jsonObject1.getString("expires_in")) * 1000;
            //更新token
            token = new MyToken();
            token.setACCESS_TOKEN(new_access_token);
            token.setExpiredtime(new_expires_in);
            return new_access_token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /*
     * 获得token
     * */
    public static String getToken() {
        if (token == null || token.isExpired()) {
            String t = getMyToken();     //过期，重新获得token
            return t;
        } else {
            return token.getACCESS_TOKEN();
        }
    }

}
