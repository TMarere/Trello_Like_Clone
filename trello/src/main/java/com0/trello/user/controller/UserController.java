package com0.trello.user.controller;

import java.util.List;

import com0.trello.board.model.BoardModel;
import com0.trello.user.service.IUserService;
import com0.trello.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    IUserService iUserService;

    @PostMapping("/save")
    public UserModel createUser(@RequestBody UserModel userModel) {
        return iUserService.createUser(userModel);
    }


    @GetMapping("/check/{email}")
    public UserModel checkLoginCredentials(@PathVariable("email") String email, @RequestParam String password){
        return iUserService.checkLoginCredentials(email, password);
    }

    @PutMapping("/update/{email}")
    public UserModel forgotPassword(@PathVariable("email") String email, @RequestParam String securityAns, @RequestParam String password){
        return iUserService.forgotPassword(email, securityAns, password);
    }

    @GetMapping("/getAll")
    public List<UserModel> getAllUser() {return iUserService.getAllUser();}


}
