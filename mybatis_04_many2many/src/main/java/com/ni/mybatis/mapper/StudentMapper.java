package com.ni.mybatis.mapper;

import com.ni.mybatis.domain.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    void save(Student s);
    void insertRelationshipWithTeacher(@Param("sid")long sid,@Param("tid")Long tid);
    void delete(Long id);
    void deleteRelationshipWithTeacher(Long id);
    Student get(Long id);

}
