package com.ni.mybatis.mapper;

import com.ni.mybatis.domain.Employee;
import com.ni.mybatis.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeMapper {
    //分页查询需要从数据库查出的两个数据
    //1 查询结果总数
    //2 查询结果集
    List<Employee> queryForList(EmployeeQueryObject qo);
    int queryForCount(EmployeeQueryObject qo);

}
