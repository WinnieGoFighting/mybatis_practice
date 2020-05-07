import com.ni.mybatis.domain.Employee;
import com.ni.mybatis.mapper.EmployeeMapper;
import com.ni.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestCRUD {
    //需求1：查询工资范围在1000-2000之间的员工
    @Test
    public void test1() throws Exception {
        EmployeeMapper eMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal(1000);
        BigDecimal maxSalary = new BigDecimal(2000);
        List<Employee> list = eMapper.query(minSalary,maxSalary,10l);
        for (Employee e: list)
            System.out.println(e);
    }

    //需求2：在需求1的基础上，查询指定部门员工的信息
    @Test
    public void test2() throws Exception {
        EmployeeMapper eMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal(1000);
        BigDecimal maxSalary = new BigDecimal(2000);
        List<Employee> list = eMapper.query(minSalary,maxSalary,20l);
        for (Employee e: list)
            System.out.println(e);
    }

    //需求3：更新员工信息
    @Test
    public void test3() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = session.getMapper(EmployeeMapper.class);

        Employee em = new Employee();
        em.setSalary(new BigDecimal(2000));
        em.setId(1l);
        eMapper.update(em);

        session.commit();
        session.close();
    }

    //需求4：批量删除id 在 Long[] ids 中的员工信息
    @Test
    public void test4() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = session.getMapper(EmployeeMapper.class);
        eMapper.batchDelete(new Long[]{88l,888l,8888l});
        session.commit();
        session.close();
    }

    //需求5：实现批量插入
    @Test
    public void test5() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper eMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> list = new ArrayList<Employee>();
        Employee e1 = new Employee();
        e1.setId(88l);
        e1.setName("test1");

        Employee e2 = new Employee();
        e2.setId(888l);
        e2.setName("test2");

        Employee e3 = new Employee();
        e3.setId(8888l);
        e3.setName("test3");

        list.add(e1);
        list.add(e2);
        list.add(e3);

        eMapper.batchInsert(list);
        session.commit();
        session.close();
    }

}
