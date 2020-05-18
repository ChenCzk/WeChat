import Service.WXService;
import Service.WXUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import mapper.FirstMapper;
import mapper.userMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.SQL.User;
import pojo.SQL.person;
import util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class test {
    static SqlSession sqlSession;
    static Logger logger;

    @BeforeAll
    public static void before(){
        try {
            InputStream stream = Resources.getResourceAsStream("MyBatis.xml");
            // 获取property文件
            InputStream in = test.class.getClassLoader().getResourceAsStream("jdbc2.property"); //使用类加载器加载
            Properties properties = new Properties();
            properties.load(in);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream, properties);
            sqlSession = sqlSessionFactory.openSession();

            logger  = Logger.getLogger(test.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterAll
    public static void after(){
        if (sqlSession!=null){
            sqlSession.commit();
            sqlSession.close();
        }
    }
    //  GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    //  OPENID 获取用户信息
    @Test
    public void test() throws IOException {

        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        String OPENID = "oSpekwbWqcmdIYPLemwEZS_k5Poc";
        url = url.replace("ACCESS_TOKEN", WXService.checkToken()).replace("OPENID", OPENID);

        String data = Util.get(url);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(data, User.class);
        System.out.println(user);

        userMapper m = sqlSession.getMapper(userMapper.class);
        logger.info(m);
        int i = m.addUser(user);
        System.out.println(i);


    }

    @Test
    public void test1() throws IOException {
        WXUser.changeSubscribe(0,"oSpekwbWqcmdIYPLemwEZS_k5Poc");
    }
    @Test
    public void test2(){
        FirstMapper mapper = sqlSession.getMapper(FirstMapper.class);
        person p =new person();
        p.setId(2);
        p.setName("lmx");
        p.setSex("girl");

        mapper.add(p);


    }
}
