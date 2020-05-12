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
        e1.setDept(d);
        e2.setDept(d);

        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = (EmployeeMapper) session.getMapper(EmployeeMapper.class);
        DepartmentMapper dMapper = (DepartmentMapper)session.getMapper(DepartmentMapper.class);

        //保存操作
        dMapper.save(d);
        eMapper.save(e1);
        eMapper.save(e2);
        session.commit();

    }
    @Test
    public void testGet() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = (EmployeeMapper) session.getMapper(EmployeeMapper.class);
        //DepartmentMapper dMapper = (DepartmentMapper)session.getMapper(DepartmentMapper.class);

        Employee e =eMapper.get(1L);
        System.out.println(e);

        //拥有了员工所在部门的id,但是期望得到的是Department对象
        //解决方案：根据部门id,在额外的发送一条sql语句查询出部门对象，把部门对象设置给员工
        //这种方法叫做 额外的sql查询，问题：有没有办法让mybatis帮我们发送这条额外语句？
//        Long deptId = e.getDept().getId();
//        Department d = dMapper.get(deptId);
//        e.setDept(d);
       // System.out.println(e.getDept());
    }

    /**
     * n+1问题重现
     * 解决方案：使用多表查询（join），一条sql搞定
     * select * from emp e join dept d on e.dept_id = d.id
     * 如何处理多表查询的结果集：内联映射
     * 总结：
     *  对象关联的查询方式
     *  1.额外sql
     *  2.内联映射（多表查询,多对一查询时一般选择这个 ✔）
     *  如果我们需要在列表中显示关联对象的数据，此时用内联映射，否则会出现n+1问题
     *  如果需要进入另一个页面显示更详细信息的时候，选用额外sql
     */
    @Test
    public void testListAll() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = (EmployeeMapper) session.getMapper(EmployeeMapper.class);
        List<Employee> list = eMapper.listAll();
        for (Employee e: list) {
            System.out.println(e);
        }
    }

}
