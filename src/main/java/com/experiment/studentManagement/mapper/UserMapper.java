package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select user_id, username, password, user_type, role_id, student_id, real_name, created_time, updated_time from `user_account` where username = #{username} limit 1")
    User findByUsername(@Param("username") String username);

    void insert(User user);

    void deleteByStudentId(@Param("studentId") Integer studentId);

    @Select("select user_id, username, password, user_type, role_id, student_id, real_name, created_time, updated_time from `user_account` where student_id = #{studentId} limit 1")
    User findByStudentId(@Param("studentId") Integer studentId);

    void updateById(User user);

    @Select("select user_id, username, password, user_type, role_id, student_id, real_name, created_time, updated_time from `user_account` where user_id = #{userId} limit 1")
    User findById(@Param("userId") Integer userId);
}


