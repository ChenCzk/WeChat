package Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mapper.userMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pojo.SQL.User;
import test.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static util.UserUtil.getUserByOpenID;

/**
 * 用户管理
 */
public class WXUser {
    private static SqlSession sqlSession;
    private static Logger logger;


    // 添加用户
    public static void addUser(String OpenID) {
        try {
            InputStream stream = Resources.getResourceAsStream("MyBatis.xml");
            // 获取property文件
            InputStream in = test.class.getClassLoader().getResourceAsStream("jdbc.property"); //使用类加载器加载
            Properties properties = new Properties();
            properties.load(in);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream, properties);
            sqlSession = sqlSessionFactory.openSession();
            logger = Logger.getLogger(test.class);

            // 用户存储
            User user = getUserByOpenID(OpenID);
            userMapper m = sqlSession.getMapper(userMapper.class);
            m.addUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.commit();
                sqlSession.close();
            }
        }
    }

    // 更改用户订阅状态
    public static void changeSubscribe(int state, String openId) throws IOException {
        try {
            InputStream stream = Resources.getResourceAsStream("MyBatis.xml");
            // 获取property文件
            InputStream in = test.class.getClassLoader().getResourceAsStream("jdbc.property"); //使用类加载器加载
            Properties properties = new Properties();
            properties.load(in);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream, properties);
            sqlSession = sqlSessionFactory.openSession();
            logger = Logger.getLogger(test.class);

            // 更改
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            mapper.changeSubscribe(state,openId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.commit();
                sqlSession.close();
            }
        }


    }

    // 查询是否订阅
    public static int IsSubscribe(String openId) {
        int i = 0;
        try {
            InputStream stream = Resources.getResourceAsStream("MyBatis.xml");
            // 获取property文件
            InputStream in = test.class.getClassLoader().getResourceAsStream("jdbc.property"); //使用类加载器加载
            Properties properties = new Properties();
            properties.load(in);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream, properties);
            sqlSession = sqlSessionFactory.openSession();
            logger = Logger.getLogger(test.class);

            // 更改
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            i = mapper.IsSubscribe(openId);
            System.out.println("test:IsSubscribe的结果："+i);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.commit();
                sqlSession.close();
            }
        }
        return i;
    }

    // 是否存在该用户
    public static int exitUser(String openId) {
        int i = 0;
        try {
            InputStream stream = Resources.getResourceAsStream("MyBatis.xml");
            // 获取property文件
            InputStream in = test.class.getClassLoader().getResourceAsStream("jdbc.property"); //使用类加载器加载
            Properties properties = new Properties();
            properties.load(in);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream, properties);
            sqlSession = sqlSessionFactory.openSession();
            logger = Logger.getLogger(test.class);

            // 更改
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            i = mapper.exitUser(openId);
            System.out.println("test:exitUSer的结果："+i);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.commit();
                sqlSession.close();
            }
        }
        return i;
    }
}
