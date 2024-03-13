package com0.trello.workspace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com0.trello.board.model.BoardModel;
import com0.trello.user.model.UserModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class WorkspaceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceID;
    private String workspaceName;
    private String workspaceDescription;

    @OneToMany(targetEntity = BoardModel.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_id", referencedColumnName = "workspaceID")
    List<BoardModel> boards ;

    @ManyToMany(targetEntity = UserModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="workspace_user",
            joinColumns=
            @JoinColumn( name="workspacemodel_id", referencedColumnName="workspaceID"),
            inverseJoinColumns=@JoinColumn(name="usermodel_id", referencedColumnName="id"))
    List<UserModel> users;


    // constructor with no arguments
    public WorkspaceModel() {
    }

    public List<BoardModel> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardModel> boards) {
        this.boards = boards;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    // constructor with all arguments
    public WorkspaceModel(Long workspaceID, String workspaceName, String workspaceDescription,List<BoardModel> boards,List<UserModel> users) {
        this.workspaceID = workspaceID;
        this.workspaceName = workspaceName;
        this.workspaceDescription = workspaceDescription;
        this.boards = boards;
        this.users = users;
    }

    // getters and setters

    public Long getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(Long workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public String getWorkspaceDescription() {
        return workspaceDescription;
    }

    public void setWorkspaceDescription(String workspaceDescription) {
        this.workspaceDescription = workspaceDescription;
    }
}
