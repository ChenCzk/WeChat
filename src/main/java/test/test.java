package test;

import Service.WXService;
import Service.one;
import com.baidu.aip.ocr.AipOcr;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import pojo.Button.*;
import util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test {
    //设置APPID/AK/SK
    public static final String APP_ID = "17576421";
    public static final String API_KEY = "5dxFpbjpETpPxPYTDogDqQMK";
    public static final String SECRET_KEY = "eLSmGAosBSRuC2BY1PY5Qbaiq4CGX3gY";

    /**
     * 设置桌面布局
     * */
    @Test
    public void test() {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WXService.checkToken());
        List mylist = new ArrayList();

        //一级菜单
        clickButton click = new clickButton("点击按钮", "1");

        //一级菜单
        clickButton subClick = new clickButton("点击按钮", "2");      //二级菜单
        viewButton subView = new viewButton("百度", "http://www.baidu.com");//二级菜单
        List<AbstractButton> list = new ArrayList<>();
        list.add(subClick);
        list.add(subView);
        menuButton menu = new menuButton("我的菜单", list);

        //总的
        Button button = new Button();
        mylist.add(click);
        mylist.add(menu);

        button.setButton(mylist);
        JSONObject jsonObject = JSONObject.fromObject(button);
        System.out.println(jsonObject.toString());

        System.out.println(Util.post(url, jsonObject.toString()));

    }

    /*
     * 百度测试
     * */
    @Test
    public void test2() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        String path = "http://mmbiz.qpic.cn/mmbiz_jpg/DBc6RhFYMcCbGmdQkS1svO4u1YI3XQXwx9xRxWWbymCT4N9FkzbdktfKkJniaKWO0XEicyXpPYBT9JE6w4G3gibqA/0";
//        org.json.JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        org.json.JSONObject res = client.basicGeneralUrl(path, new HashMap<String, String>());

        System.out.println(res.toString(2));

    }

    @Test
    public void test03() {
        String path = "F:\\idea\\testWX2\\src\\main\\Resource\\kmj2.jpg";
        String jpg = one.uploadMedia(path, "image");
        System.out.println(jpg);//zjKyMEImW44lDeWuk9YedVR24LTLbhgeTMLT2eUsHsXXBWR06WHPdg9QWfkzudNZ
    }

    @Test
    public void test04() {
        String id = "zjKyMEImW44lDeWuk9YedVR24LTLbhgeTMLT2eUsHsXXBWR06WHPdg9QWfkzudNZ";
        String media = one.getMedia(id);
        System.out.println(media);
        System.out.println(WXService.checkToken());
    }
    @Test
    public void test05() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        // 阻塞
        Socket socket = serverSocket.accept();
        OutputStream out = socket.getOutputStream();
        out.write("helloword".getBytes());
        out.close();
        serverSocket.close();
    }
    @Test
    public void test06() throws IOException {
        Socket  socket = new Socket("127.0.0.1",9999);
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[100];
        inputStream.read(bytes);
        System.out.println(new  String(bytes));
        inputStream.close();
        socket.close();

    }
}
