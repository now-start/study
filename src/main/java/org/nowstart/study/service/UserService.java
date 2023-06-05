package org.nowstart.study.service;

import org.nowstart.study.data.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void saveUser(UserDto userDto);
}
