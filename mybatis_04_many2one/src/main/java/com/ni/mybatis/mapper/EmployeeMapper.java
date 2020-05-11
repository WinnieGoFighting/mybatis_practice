package com.ni.mybatis.mapper;

import com.ni.mybatis.domain.Employee;

import java.util.List;

public interface EmployeeMapper {
    void save(Employee e);
    Employee get(Long id);
    List<Employee> listAll();
}
