package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select user_id, username, password, user_type, role_id, student_id, real_name, created_time, updated_time from `user_account` where username = #{username} limit 1")
    User findByUsername(@Param("username") String username);
}


