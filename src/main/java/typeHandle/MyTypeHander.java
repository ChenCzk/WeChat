package typeHandle;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
*
* 自定义类型转换器
*
* */
// @MappedJdbcTypes 指定jdbcType,
// @MappedTypes 指定javaType(如果没有使用注解指定，那么我们就需要在配置文件中配置)
@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHander implements TypeHandler<List> { //泛型约束为java类型


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, list.toString()); //在预编译的时候进行类型装换，将sexEnum->int

    }

    @Override
    public List getResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        List<String> list = Arrays.asList(str.split(","));
        return list;
    }

    @Override
    public List getResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        List<String> list = Arrays.asList(str.split(","));
        return list;
    }

    @Override
    public List getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
