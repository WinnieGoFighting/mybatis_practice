import com.ni.mybatis.domain.Student;
import com.ni.mybatis.domain.Teacher;
import com.ni.mybatis.mapper.StudentMapper;
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
        Teacher t1 = new Teacher();
        t1.setName("老师1");
        Teacher t2 = new Teacher();
        t2.setName("老师2");

        Student s1 = new Student();
        s1.setName("小明");
        Student s2 = new Student();
        s2.setName("小丽");

        //维护关系
        s1.getTeacherList().add(t1);
        s1.getTeacherList().add(t2);

        s2.getTeacherList().add(t1);
        s2.getTeacherList().add(t2);

        SqlSession session = MyBatisUtil.getSession();
        StudentMapper sMapper = session.getMapper(StudentMapper.class);
        TeacherMapper tMapper = session.getMapper(TeacherMapper.class);

        sMapper.save(s1);
        sMapper.save(s2);
        tMapper.save(t1);
        tMapper.save(t2);

        //维护中间表的数据
        for(Teacher t:s1.getTeacherList())
            sMapper.insertRelationshipWithTeacher(s1.getId(),t.getId());
        for (Teacher t:s2.getTeacherList())
            sMapper.insertRelationshipWithTeacher(s2.getId(),t.getId());

        session.commit();
        session.close();
    }

    @Test
    public void testDelete() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper sMapper = session.getMapper(StudentMapper.class);

        sMapper.deleteRelationshipWithTeacher(1l);
        sMapper.delete(1l);
        session.commit();
        session.close();
    }

    @Test
    public void testGet() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper sMapper = session.getMapper(StudentMapper.class);
        Student s = sMapper.get(1l);
        System.out.println(s);
        //System.out.println(s.getTeacherList());
    }

}
