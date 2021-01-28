package com.multidatabase.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {
    private final UserService userService;

    @GetMapping("/master")
    public void helloMaster() {
        userService.master();
    }

    @GetMapping("/slave")
    public void helloSlave() {
        userService.slave();
    }

}
