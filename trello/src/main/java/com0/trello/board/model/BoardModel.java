package com0.trello.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com0.trello.task.model.TaskModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BoardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;

    private String boardName;
    private String boardDescription;

    @JsonIgnore
    @OneToMany(targetEntity = TaskModel.class,mappedBy = "board",fetch = FetchType.LAZY)
    List<TaskModel> tasks ;


    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    // constructor with no arguments
    public BoardModel() {
    }

    // constructor with all arguments
    public BoardModel(Long boardID, String boardName, String boardDescription, Long workspaceID, List<TaskModel> tasks) {
        this.boardID = boardID;
        this.boardName = boardName;
        this.boardDescription = boardDescription;
        this.tasks = tasks;
    }

    // getters and setters
    public Long getBoardID() {
        return boardID;
    }

    public void setBoardID(Long boardID) {
        this.boardID = boardID;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardDescription() {
        return boardDescription;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }

}
