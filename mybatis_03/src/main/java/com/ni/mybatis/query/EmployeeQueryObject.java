package com.ni.mybatis.query;

import java.math.BigDecimal;

//封装员工的高级查询信息
public class EmployeeQueryObject {
    //员工的名字或者员工的编号中包含的关键字
    private String keyword;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private Long deptId = -1l; //部分id, 缺省为-1，表示所有部门

    //用户传递过来的
    private int pageSize = 3;
    private int currentPage = 1;

    /**
     * 计算分页查询的其实数据条数
     * limit start pageSize
     * 固定公式：（当前页-1）*pageSize
     * @return
     */
    public int getStart(){
        return (currentPage-1)*pageSize;
    }

    public String getKeyword() {
        if (keyword!=null){
            if ("".equals(keyword.trim()))
                return null;
            return keyword;
        }
        return null;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
