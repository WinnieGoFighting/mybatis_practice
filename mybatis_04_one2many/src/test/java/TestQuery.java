import com.ni.mybatis.domain.Department;
import com.ni.mybatis.domain.Employee;
import com.ni.mybatis.mapper.DepartmentMapper;
import com.ni.mybatis.mapper.EmployeeMapper;
import com.ni.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class TestQuery {

    /**
     * 保存一个部门和两个员工
     * 总结：
     * 设计对象的时候：我们一般考虑使用单向的many2one就可以了，一般不会使用单向的one2many
     * 即使要使用one2many,也应该做成双向关联，或者在many方存储one方的id
     */
    @Test
    public void testSave() throws Exception {
        Department d = new Department();
        d.setName("开发部");

        Employee e1 = new Employee();
        e1.setName("张三");


        Employee e2 = new Employee();
        e2.setName("李四");

        //维护对象的关系
        d.getEmps().add(e1);
        d.getEmps().add(e2);

        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = (EmployeeMapper) session.getMapper(EmployeeMapper.class);
        DepartmentMapper dMapper = (DepartmentMapper)session.getMapper(DepartmentMapper.class);

        //保存操作
        dMapper.save(d);
        e1.setDeptId(d.getId());
        e2.setDeptId(d.getId());
        eMapper.save(e1);
        eMapper.save(e2);
        session.commit();

    }
    //查询10号部门的信息和包含的员工信息
    @Test
    public void testGet() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        DepartmentMapper dMapper = (DepartmentMapper)session.getMapper(DepartmentMapper.class);
        Department d = dMapper.get(10l);
        System.out.println(d);
        System.out.println(d.getEmps());
    }


}
