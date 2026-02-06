package com.buddha.ordersystem.controller;

import com.buddha.ordersystem.dto.UserDto;
import com.buddha.ordersystem.dto.UserRequest;
import com.buddha.ordersystem.entity.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/users")
public class UserController {



    public UserDto createUser(UserRequest request){


    }


}
