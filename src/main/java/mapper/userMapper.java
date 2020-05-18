package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.SQL.User;
/**
 * 添加User
 * */
public interface userMapper {
    int exitUser(String openId);  // 是否存在该用户

    int addUser(User user);  // 增加用户

    void changeSubscribe(@Param("i") int i, @Param("openId") String openId);// 更改用户订阅状,1:订阅
    int IsSubscribe(String openId); // 查询用户是否订阅
}
