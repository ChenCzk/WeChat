package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
    /*
    *  get请求
    * */
    public static String get(String url){
        StringBuffer sb= null;
        try {
            // 发送
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();

            // 接收
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /*
    *  post请求
    * */
    public static String post(String url,String json){
        StringBuffer sb= null;
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();


            // 写数据
            connection.setDoOutput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            writer.write(json);
            writer.close();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
