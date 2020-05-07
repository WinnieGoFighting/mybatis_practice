import com.ni.mybatis.domain.User;
import com.ni.mybatis.mapper.UserMapper;
import com.ni.mybatis.util.MyBatisUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TestCRUD {

    //查询某条数据
    @Test
    public void testGet() throws Exception{
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User u = userMapper.get(1l);
            System.out.println(u.getName());
        } finally {
            session.close();
        }
    }

    //查询所有数据
    @Test
    public void listAll() throws Exception{
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> list = userMapper.listAll();
        session.close();
        for (User u:list)
            System.out.println(u);
    }

    //修改操作
    @Test
    public void testUpdate() throws Exception{
        User u = new User();
        u.setName("叶孤城");
        u.setSalary(new BigDecimal(900));
        u.setId(4L);
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.update(u);
        //手动提交事务
        session.commit();
        session.close();
    }

    //删除操作
    @Test
    public void testDelete() throws Exception{
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.delete(4L);
        session.commit();
        session.close();
    }

    //增加信息
    //在开发中，我们经常会有这样的需求：保存一条数据之后，需要得到刚刚保存数据生成的主键的值
    @Test
    public void testSave() throws Exception{
        User u = new User();
        u.setName("楚留香");
        u.setSalary(new BigDecimal(800));

        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.save(u);

        System.out.println(u);
        session.commit();
        session.close();
    }

    //返回类型用别名的例子1：查询t_user表中的结果总数
//    @Test
//    public void testgetCount() throws Exception{
//        SqlSession session = MyBatisUtil.getSession();
//        int rows = session.selectOne("com.ni.mybatis.domain.UserMapper.getCount");
//        session.close();
//        System.out.println(rows);
//    }

    ////返回类型用别名的例子2:查询每条数据的名字和id,不要salary,结果可以用map封装
//    @Test
//    public void testGetMap() throws Exception{
//        SqlSession session = MyBatisUtil.getSession();
//        List<Map<String, Object>> list = session.selectList("com.ni.mybatis.domain.UserMapper.getMap");
//        session.close();
//        for (Map<String, Object> map:list)
//            System.out.println(map);
//    }
}
