package com0.trello.user.service;

import com0.trello.board.model.BoardModel;
import com0.trello.task.model.TaskModel;
import com0.trello.user.model.UserModel;
import com0.trello.user.repository.UserRepository;
import com0.trello.user.service.IUserService;
import com0.trello.workspace.model.WorkspaceModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    public UserModel createUser(UserModel userModel) {
        /*you can perform some validations before connecting to
         the repository class to save customer data e.g password validation*/

        return userRepository.save(userModel);
    }
    public UserModel checkEmail (String email){
        return userRepository.findByEmail(email);
    }


    public UserModel checkPassword (String password){
        return userRepository.findByPassword(password);
    }

    public UserModel checkSecurityAns (String securityAns){
        return userRepository.findBySecurityAns(securityAns);
    }

    public UserModel checkLoginCredentials( String email, String password){
        UserModel emailUser = userRepository.findByEmail(email);
        if(emailUser != null) {
            if (emailUser.getPassword().equals(password)) {
                return emailUser;
            }
        }
        return null;
    }
    public UserModel forgotPassword( String email, String securityAns, String password){
        UserModel emailUser = userRepository.findByEmail(email);
        if(emailUser != null){
            if(emailUser.getSecurityAns().equals(securityAns)){
                emailUser.setPassword(password);
                userRepository.save(emailUser);
                return emailUser;
            }
        }
        return null;
    }

    public UserModel findById(long id){
        Optional<UserModel> userModel = userRepository.findById(id);
        return userModel.orElse(null);
    }

    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }

}
