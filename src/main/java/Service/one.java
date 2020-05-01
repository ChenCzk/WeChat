package Service;


import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import pojo.Button.*;
import util.Util;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class one {
    /*
     *  菜单创建
     * */
    @Test
    public void createMenu() {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WXService.getToken());

        //一级菜单
        clickButton click = new clickButton("点击按钮", "1");

        //一级菜单
        pic_photo_or_album_button subClick = new pic_photo_or_album_button("图片识别文字", "2");
        viewButton subView = new viewButton("百度", "http://www.baidu.com");//二级菜单
        List<AbstractButton> list = new ArrayList<>();
        list.add(subClick);
        list.add(subView);
        menuButton menu = new menuButton("我的菜单", list);

        //总的
        Button button = new Button();
        button.getButton().add(click);
        button.getButton().add(menu);


        JSONObject jsonObject = JSONObject.fromObject(button);
        System.out.println(jsonObject.toString());

        System.out.println(Util.post(url, jsonObject.toString()));
    }

    /*
     * 设置行业信息
     *
     *  */
    @Test
    public void createIndustry() {
        String str = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
        String token = WXService.getToken();
        str = str.replace("ACCESS_TOKEN", token);
        String data = "{\n" +
                "    \"industry_id1\":\"1\",\n" +
                "    \"industry_id2\":\"2\"\n" +
                "}\n";
        String s = Util.post(str, data);
        System.out.println(s);
    }

    /*
     * 获取行业信息
     * */
    @Test
    public void getIndusty() {
        String str = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
        str = str.replace("ACCESS_TOKEN", WXService.getToken());

        String s = Util.get(str);
        System.out.println(s);
    }

    /*
     * 获取所有的模板信息
     *
     * */
    @Test
    public void getAllTemplate() {
        String str = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
        str = str.replace("ACCESS_TOKEN", WXService.getToken());

        String s = Util.get(str);
        System.out.println(s);
    }

    /*
     * 获得模板ID
     * */
    @Test
    public void geetTemplateId() {
        String str = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
        String token = WXService.getToken();
        str = str.replace("ACCESS_TOKEN", token);
        String data = "{\n" +
                "    \"template_id_short\":\"TM00015\"\n" +   //模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
                " }";
        String s = Util.post(str, data);//返回template_id
        System.out.println(s);
    }

    /*
     * 删除模板ById
     * */
    public void deleteTemplateByTemplateId() {
        String str = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
        String token = WXService.getToken();
        str = str.replace("ACCESS_TOKEN", token);
        String data = " {\n" +
                "     \"template_id\" : \"Dyvp3-Ff0cnail_CDSzk1fIc6-9lOkxsQE7exTJbwUE\"\n" +
                " }";
        String s = Util.post(str, data);//返回结果
        System.out.println(s);
    }

    /*
     *  发送模板
     *
     * */
    @Test
    public void postTemplate() {
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
            url = url.replace("ACCESS_TOKEN", WXService.getToken());
            String s = FileUtils.readFileToString(new File("F:\\idea\\testWX2\\src\\main\\Resource\\template.JSON"), "UTF-8");
            System.out.println(s);
            String post = Util.post(url, s);

            System.out.println(post);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    * 上传临时素材
    * path:素材路径
    * type：类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
    * */
    public  static   String uploadMedia(String path,String type) {
        File file = new File(path);
        //地址
        String url = "http://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN",WXService.getToken()).replace("TYPE",type);
        try {
            URL urlObj= new URL(null, url, new sun.net.www.protocol.https.Handler());
            // 强转为安全连接 https
            HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();
            // 设置连接的信息
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);//禁止缓存
            // 设置请求头信息
            con.setRequestProperty("Connection","Keep-Alive");
            con.setRequestProperty("Charset","utf8");

            // 数据的边界
            String boundary  = "-----"+ System.currentTimeMillis();
            con.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

            // 获取流
            OutputStream out = con.getOutputStream();
            FileInputStream is  =new FileInputStream(file);

            // 第一部分：头部信息
            // 准备头部信息
            StringBuilder sb= new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename="+"\""+file.getName()+"\""+"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            System.out.println(sb.toString());
            out.write(sb.toString().getBytes());//写入头信息

            // 第二部分: 文件内容
            byte[] bytes = new byte[1024];
            int len;
            while ((len=is.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            // 第三部分: 尾部信息
            String foot  = "\r\n--"+boundary+"--\r\n";
            out.write(foot.getBytes()); //写入尾部信息
            out.flush();
            out.close();

            // 读取数据
            InputStream in = con.getInputStream();
            StringBuilder sb1 = new StringBuilder();
            while ((len= in.read(bytes))!=-1){
                sb1.append(new String(bytes,0,len));
            }
            return sb1.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /*
    * 获取临时素材
    * */
    public static String getMedia(String id){
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        url = url.replace("ACCESS_TOKEN",WXService.getToken()).replace("MEDIA_ID",id);
        String s = Util.get(url);
        return s;

    }

}
