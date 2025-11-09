package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.userLoginDTO;
import com.experiment.studentManagement.VO.LoginVO;

public interface UserService {
    LoginVO login(userLoginDTO dto);
}


