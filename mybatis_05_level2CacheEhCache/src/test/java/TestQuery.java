
import com.ni.mybatis.domain.Teacher;
import com.ni.mybatis.mapper.TeacherMapper;
import com.ni.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class TestQuery {

    /**
        保存两个老师，两个学生
     */
    @Test
    public void testSave() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        TeacherMapper tMapper = session.getMapper(TeacherMapper.class);
        tMapper.get(1l);
        session.close();
        System.out.println("---------------------------------------------");
        Thread.sleep(3000);
        session = MyBatisUtil.getSession();
        tMapper = session.getMapper(TeacherMapper.class);
        tMapper.get(1l);
        session.close();

    }
}
