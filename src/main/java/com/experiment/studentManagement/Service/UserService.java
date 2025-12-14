package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.userLoginDTO;
import com.experiment.studentManagement.DTO.ChangePasswordDTO;
import com.experiment.studentManagement.VO.LoginVO;
import com.experiment.studentManagement.model.User;

public interface UserService {
    LoginVO login(userLoginDTO dto);

    void changePassword(Integer userId, ChangePasswordDTO dto);

    User getUserById(Integer userId);

    void updateUserInfo(User user);
}


