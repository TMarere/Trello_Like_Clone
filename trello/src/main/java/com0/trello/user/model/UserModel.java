package com0.trello.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com0.trello.task.model.TaskModel;
import com0.trello.workspace.model.WorkspaceModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String securityAns;

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    @JsonIgnore
    @ManyToMany(targetEntity = WorkspaceModel.class, mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkspaceModel> workspaces;

    public List<WorkspaceModel> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WorkspaceModel> workspaces) {
        this.workspaces = workspaces;
    }

    @JsonIgnore
    @OneToMany(targetEntity = TaskModel.class,mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskModel> tasks ;
    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public UserModel(long id, String firstName, String lastName, String email, String password, String securityAns,List<WorkspaceModel> workspaces,List<TaskModel> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.securityAns = securityAns;
        this.workspaces = workspaces;
        this.tasks = tasks;
    }

    public UserModel() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
