package Service;

import com.thoughtworks.xstream.XStream;
import pojo.*;

import java.util.Map;
/**
 * 转换成给微信服务器的消息。
 * 转换成XML格式的字符串,然后用于发送给微信服务器。
 * */
public class WXSend {

    // 发送文本对应的XML格式的字符传
    public static String sendText(Map<String, String> map, String text) {
        MyText myText = new MyText(map, text);  //将 map 封装成 Pojo
        //获取XML格式的文本
        XStream s = new XStream();  //Pojo->XML
        s.processAnnotations(ClassType.classes);
        String xml = s.toXML(myText);
        System.out.println("公众号回复:\n"+text);
        return xml;
    }
    /*
    * 返回图片对应的XML格式的字符传
    * */
    public static String  sendImage(Map<String, String> map, String message) {
        if (message.equals("莫梓琪")) {
            Image image = new Image();
            image.setMediaId("zjKyMEImW44lDeWuk9YedVR24LTLbhgeTMLT2eUsHsXXBWR06WHPdg9QWfkzudNZ");
            MyImage myImage = new MyImage(map,image);
            // 新建XStream对象
            XStream s = new XStream();
            // 扫描带有@XStreamAlias注解的类
            s.processAnnotations(ClassType.classes);
            // 调用toXML方法转换为XML格式的字符串
            String xml = s.toXML(myImage);//Pojo ->XML

            return xml;
        }
        return null;
    }

    // 返回视频对应的XML格式的字符传
    public static String sendVideo(Map<String, String> map, String message){
        if (message.equals("视频")){
            Video video = new Video();
            video.setMediaId("WiICxhxBNSNFDtNc0St5rQA6PM6CTErxvl5Fu8RVeuqFsi_cyI9i25gENHvhjQmK");
            video.setTitle("NBA");
            video.setDescription("威少");

            MyVideo myVideo = new MyVideo(map,video);

            XStream xs  = new XStream();
            xs.processAnnotations(ClassType.classes);
            String xml = xs.toXML(myVideo);

            return xml;
        }
        return null;
    }

}
