package util;

import Service.WXService;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.SQL.User;

import java.io.IOException;

public class UserUtil {
    /**
     * 通过OpenId获取用户信息
     * */
    public static User getUserByOpenID(String OpenID) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", WXService.checkToken()).replace("OPENID", OpenID);

        String data = Util.get(url);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(data, User.class);
        return user;
    }
}
