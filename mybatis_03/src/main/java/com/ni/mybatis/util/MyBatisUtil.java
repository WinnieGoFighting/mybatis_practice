package com.ni.mybatis.util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    //SqlSessionFactory 是线程安全的，一个应用只需要一个
    private static SqlSessionFactory factory;
    static {
        try {
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //创建SqlSessionFactory对象，好比是DataSource
            factory = builder.build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() {
        return factory.openSession();
    }

    public static <T>T getMapper(Class<T> mapperClass) {
        return getSession().getMapper(mapperClass);
    }

}
