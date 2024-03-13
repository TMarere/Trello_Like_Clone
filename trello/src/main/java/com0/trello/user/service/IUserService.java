package com0.trello.user.service;

import com0.trello.user.model.UserModel;

import java.util.List;

public interface IUserService {

    UserModel createUser(UserModel userModel);

    UserModel checkEmail(String email);

    UserModel checkPassword(String password);

    UserModel checkSecurityAns(String securityAns);

    UserModel findById(long id);
    List<UserModel> getAllUser();

    UserModel checkLoginCredentials( String email, String password);
    UserModel forgotPassword( String email, String securityAns, String password);



//String updatePassword (String email, String securityAns, String password);
}
