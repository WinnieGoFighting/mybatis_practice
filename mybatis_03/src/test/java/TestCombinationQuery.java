import com.ni.mybatis.domain.Employee;
import com.ni.mybatis.mapper.EmployeeMapper;
import com.ni.mybatis.query.EmployeeQueryObject;
import com.ni.mybatis.query.PageResult;
import com.ni.mybatis.service.EmployeeService;
import com.ni.mybatis.service.impl.EmployeeServiceImpl;
import com.ni.mybatis.util.MyBatisUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class TestCombinationQuery {

    //需求：按照员工的关键字，工资范围，以及所属部门来查询
    @Test
    public void test1() throws Exception {
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setKeyword("   ");
//        qo.setMinSalary(new BigDecimal(1000));
//        qo.setMaxSalary(new BigDecimal(1500));
        //qo.setDeptId(20l);
        EmployeeMapper eMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
        List<Employee> list = eMapper.queryForList(qo);

        for (Employee e:list)
            System.out.println(e);
    }

    @Test
    public void test2() throws Exception {
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setKeyword("   ");
        qo.setMinSalary(new BigDecimal(1000));
        qo.setMaxSalary(new BigDecimal(1500));
        EmployeeMapper eMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
        int ct = eMapper.queryForCount(qo);
        System.out.println(ct);
    }

    /**
     * 测试分页
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        EmployeeService service = new EmployeeServiceImpl();
        //qo封装的是前端传递过来的数据
        EmployeeQueryObject qo = new EmployeeQueryObject();
        qo.setMinSalary(new BigDecimal(1000));
        qo.setMaxSalary(new BigDecimal(1500));
        PageResult pageRes = service.query(qo);
        System.out.println(pageRes);
    }
}
