package test;

import Service.WXService;
import com.sun.jnlp.ApiDialog;
import com.thoughtworks.xstream.XStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import pojo.MyToken;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class demo {
    @Test
    public void test() throws IOException {
        String APIKEY = "575840992afd970e3f67b37eb225917c";
        String INFO = URLEncoder.encode("傻逼", "utf-8");//这里可以输入问题
        String getURL = "http://op.juhe.cn/iRobot/index?info=" + INFO + "&key=" + APIKEY;
        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();

        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb);

        JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
        JSONObject result = jsonObject.getJSONObject("result");
        String text = result.getString("text");
        System.out.println(text);

        reader.close();
        // 断开连接
    }

    @Test
    public void test1() throws IOException {
        String key = "187be97a89cd2615d111bc928f16436b";
        String content = URLEncoder.encode("手机", "utf-8");//这里可以输入物品
        String url = "http://apis.juhe.cn/rubbish/search?key=" + key + "&" + "q=" + content;
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb);

        Map<String, String> map = new LinkedHashMap<String, String>();
        JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            System.out.println(object.getString("itemName"));
            System.out.println(object.getString("itemCategory"));
            map.put(object.getString("itemName"), object.getString("itemCategory"));
        }
        String key1 = null;
        System.out.println(map);

        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            key1 = iterator.next();
            System.out.println(key1);
            System.out.println(map.get(key1));

        }
        connection.disconnect();
    }

    @Test
    public void test03() {
        String token = WXService.getToken();
        String token1 = WXService.getToken();
        System.out.println(token);//26_j5ZU98GN0ssLQK_nc9TfIud7ef0-z8yNnuKUqDttIhbhi_jh4rE6v4gMPDk7CkYl5ogwrrdeYoSMNMzicRNGsAZcF681ptWgC6d3UK-02YBSjpUeWUw216ypxjILXDgAFAYKQ
        System.out.println(token1);
    }

}
