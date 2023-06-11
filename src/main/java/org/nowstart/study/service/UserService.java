package org.nowstart.study.service;

import org.nowstart.study.data.dto.user.UserSaveDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void saveUser(UserSaveDto userSaveDto);
}
