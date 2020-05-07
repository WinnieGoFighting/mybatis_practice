package com.ni.mybatis.mapper;

import com.ni.mybatis.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeMapper {
    List<Employee> query(
            @Param("minSalary") BigDecimal minSalary,
            @Param("maxSalary")BigDecimal maxSalary,
            @Param("deptId")Long deptId
    );

    void update(Employee e);
    void batchDelete(Long[] ids);
    void batchInsert(List<Employee>list);
}
