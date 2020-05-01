package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/*
*  base
* */
public class base {
    private String ToUserName;   // 开发者微信号
    private String FromUserName; // 发送方帐号（一个OpenID）
    private String CreateTime;   // 消息创建时间
    private String MsgType;      // 消息类型



    public base(Map<String,String> requestMap){
        this.setFromUserName(requestMap.get("ToUserName"));
        this.setToUserName(requestMap.get("FromUserName"));
        this.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
    }



    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @Override
    public String toString() {
        return "base{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }
}
