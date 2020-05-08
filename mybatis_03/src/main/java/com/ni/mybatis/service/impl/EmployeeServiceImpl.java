package com.ni.mybatis.service.impl;

import com.ni.mybatis.domain.Employee;
import com.ni.mybatis.mapper.EmployeeMapper;
import com.ni.mybatis.query.EmployeeQueryObject;
import com.ni.mybatis.query.PageResult;
import com.ni.mybatis.service.EmployeeService;
import com.ni.mybatis.util.MyBatisUtil;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper eMapper = MyBatisUtil.getMapper(EmployeeMapper.class);

    public PageResult query(EmployeeQueryObject qo) {
        //1 查询结果总数
        int ct = eMapper.queryForCount(qo);
        if (ct==0)
            return null;
        List<Employee> result = eMapper.queryForList(qo);
        return new PageResult(result,ct,qo.getCurrentPage(),qo.getPageSize());
    }
}
