package com.ni.mybatis.service;

import com.ni.mybatis.query.EmployeeQueryObject;
import com.ni.mybatis.query.PageResult;

public interface EmployeeService {
    PageResult query(EmployeeQueryObject qo);
}
