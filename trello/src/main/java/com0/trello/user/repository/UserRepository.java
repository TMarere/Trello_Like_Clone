package com0.trello.user.repository;

import com0.trello.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);
    UserModel findByPassword(String password);
    UserModel findBySecurityAns (String securityAns);

    List<UserModel> findAll();

}
