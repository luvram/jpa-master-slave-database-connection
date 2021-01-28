package com.multidatabase.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
class UserService {
    private UserRepository userRepository;

    @Transactional
    public void master() {
        List<User> userList = userRepository.findAll();
        userList.stream()
                .forEach(u -> System.out.println(u));
    }

    public void slave() {
        List<User> userList = userRepository.findAll();
        userList.stream()
                .forEach(u -> System.out.println(u));
    }
}
