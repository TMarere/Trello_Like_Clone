package com0.trello;
import com0.trello.board.model.BoardModel;
import com0.trello.task.model.TaskModel;
import com0.trello.user.model.UserModel;
import com0.trello.user.repository.UserRepository;
import com0.trello.user.service.UserService;
import com0.trello.workspace.model.WorkspaceModel;
import com0.trello.workspace.service.WorkspaceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void create_user() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setFirstName("Katherine");
        userModel.setLastName("Pierce");
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");

        when(this.userRepository.save(userModel)).thenReturn(userModel);

        UserModel userModel1 =this.userService.createUser(userModel);
        assertEquals(userModel,userModel1, "Mismatching User Model");

        verify(this.userRepository).save(userModel);
    }

    @Test
    public void create_second_user() {
        UserModel userModel = new UserModel();
        userModel.setId(3L);
        userModel.setFirstName("Caroline");
        userModel.setLastName("Forbes");
        userModel.setEmail("cforbes@gmail.com");
        userModel.setPassword("CForbes123");
        userModel.setSecurityAns("CForbes");

        when(this.userRepository.save(userModel)).thenReturn(userModel);

        UserModel savedUser =this.userService.createUser(userModel);
        assertEquals(userModel,savedUser, "Mismatching User Model");

        verify(this.userRepository).save(userModel);
    }

    @Test
    public void check_email_successful() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");

        when(this.userRepository.findByEmail("kpierce@gmail.com")).thenReturn(userModel);

        UserModel savedUser =this.userService.checkEmail("kpierce@gmail.com");
        assertEquals(userModel,savedUser, "Mismatching User Model");

        verify(this.userRepository).findByEmail("kpierce@gmail.com");
    }

    @Test
    public void invalid_email_passed() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");

        UserModel savedUser =this.userService.checkEmail("katherinepierce@gmail.com");
        assertNotEquals(userModel,savedUser, "Mismatching User Model");
    }

    @Test
    public void check_password_successful() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");

        when(this.userRepository.findByPassword("KPierce123")).thenReturn(userModel);

        UserModel savedUser = this.userService.checkPassword("KPierce123");
        assertEquals(userModel,savedUser, "Mismatching User Model");

        verify(this.userRepository).findByPassword("KPierce123");
    }

    @Test
    public void invalid_password_passed() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");
        userModel.setSecurityAns("KPierce");

        UserModel savedUser =this.userService.checkPassword("katherinepierce");
        assertNotEquals(userModel,savedUser, "Mismatching User Model");
    }

    @Test
    public void check_securityAns_successful() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");

        when(this.userRepository.findBySecurityAns("KPierce")).thenReturn(userModel);

        UserModel savedUser = this.userService.checkSecurityAns("KPierce");
        assertEquals(userModel,savedUser, "Mismatching User Model");

        verify(this.userRepository).findBySecurityAns("KPierce");
    }

    @Test
    public void invalid_securityAns_passed() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");
        userModel.setSecurityAns("KPierce");

        UserModel savedUser =this.userService.checkSecurityAns("katherinepierce");
        assertNotEquals(userModel,savedUser, "Mismatching User Model");
    }

    @Test
    public void findByID_successful() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setFirstName("Katherine");
        userModel.setLastName("Pierce");
        userModel.setEmail("kpierce@gmail.com");

        when(this.userRepository.findById(1L)).thenReturn(Optional.of(userModel));

        UserModel savedUser = this.userService.findById(1L);
        assertEquals(userModel,savedUser, "Mismatching User Model");

        verify(this.userRepository).findById(1L);
    }

    @Test
    public void invalid_userID_passed() {
        UserModel userModel = new UserModel();
        userModel.setId(3L);
        userModel.setFirstName("Katherine");
        userModel.setLastName("Pierce");
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");


        UserModel savedUser =this.userService.findById(1L);
        assertNotEquals(userModel,savedUser, "Mismatching User Model");

    }

    @Test
    public void testGetAllUsers(){
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setFirstName("Katherine");
        userModel.setLastName("Pierce");
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");


        List<UserModel> users = new ArrayList<>();
        users.add(userModel);

        when(this.userRepository.findAll()).thenReturn(users);
        List<UserModel> savedUsers = this.userService.getAllUser();
        Assert.assertEquals(users, savedUsers);
        verify(this.userRepository).findAll();
    }

    @Test
    public void testGetAllBoardsTwo(){
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setFirstName("Katherine");
        userModel.setLastName("Pierce");
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");
        userModel.setSecurityAns("KPierce");

        UserModel userModel2 = new UserModel();
        userModel2.setId(2L);
        userModel2.setFirstName("Bruce");
        userModel2.setLastName("Eric");
        userModel.setEmail("eric@gmail.com");


        List<UserModel> users = new ArrayList<>();
        users.add(userModel);
        users.add(userModel2);

        when(this.userRepository.findAll()).thenReturn(users);
        List<UserModel> savedUsers = this.userService.getAllUser();
        Assert.assertEquals(users, savedUsers);
        verify(this.userRepository).findAll();

    }
    @Test
    public void checkLoginCredentials_successful() {
        UserModel userModel = new UserModel();
        userModel.setId(13L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");

        when(userRepository.findByEmail("kpierce@gmail.com")).thenReturn(userModel);

        UserModel userModel1 = this.userService.checkLoginCredentials("kpierce@gmail.com", "KPierce123");

        assertEquals(userModel, userModel1, "Mismatching User Model");

        verify(userRepository).findByEmail("kpierce@gmail.com");
    }

    @Test
    public void checkLoginCredentials_incorrectPassword() {
        UserModel userModel = new UserModel();
        userModel.setId(13L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce123");

        when(userRepository.findByEmail("kpierce@gmail.com")).thenReturn(userModel);

        UserModel userModel1 = this.userService.checkLoginCredentials("kpierce@gmail.com", "KPierce");

        assertNotEquals(userModel, userModel1, "Mismatching User Model");

        verify(userRepository).findByEmail("kpierce@gmail.com");
    }

    @Test
    public void checkLoginCredentials_invalidEmail() {

        when(userRepository.findByEmail("kpierce@gmail.com")).thenReturn(null);

        UserModel userModel1 = this.userService.checkLoginCredentials("kpierce@gmail.com", "KPierce123");

        assertNull(userModel1, "Mismatching User Model");

        verify(userRepository).findByEmail("kpierce@gmail.com");
    }

    @Test
    public void forgetPassword_successful() {
        UserModel userModel = new UserModel();
        userModel.setId(13L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce");
        userModel.setSecurityAns("KPierce");

        when(userRepository.findByEmail("kpierce@gmail.com")).thenReturn(userModel);

        UserModel userModel1 = this.userService.forgotPassword("kpierce@gmail.com", "KPierce", "KPierce123");

        assertEquals(userModel1.getPassword(), "KPierce123", "Mismatching User Model");

        verify(userRepository).findByEmail("kpierce@gmail.com");
    }

    @Test
    public void forgetPassword_invalidPasswordExpected() {
        UserModel userModel = new UserModel();
        userModel.setId(13L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce");
        userModel.setSecurityAns("KPierce");

        when(userRepository.findByEmail("kpierce@gmail.com")).thenReturn(userModel);

        UserModel userModel1 = this.userService.forgotPassword("kpierce@gmail.com", "KPierce", "KPierce123");

        assertNotEquals(userModel1.getPassword(), "KPierce", "Mismatching User Model");

        verify(userRepository).findByEmail("kpierce@gmail.com");
    }

    @Test
    public void forgetPassword_passwordNotUpdated() {
        UserModel userModel = new UserModel();
        userModel.setId(13L);
        userModel.setEmail("kpierce@gmail.com");
        userModel.setPassword("KPierce");
        userModel.setSecurityAns("KPierce");

        when(userRepository.save(userModel)).thenReturn(userModel);

        UserModel userModel1 = this.userService.createUser(userModel);

        assertNotEquals(userModel1.getPassword(), "KPierce123", "Mismatching User Model");

        verify(userRepository).save(userModel);
    }

}
