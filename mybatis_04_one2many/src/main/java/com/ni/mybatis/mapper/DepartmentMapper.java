package com.ni.mybatis.mapper;

import com.ni.mybatis.domain.Department;

public interface DepartmentMapper {
    void save(Department d);
    Department get(Long id);

}
