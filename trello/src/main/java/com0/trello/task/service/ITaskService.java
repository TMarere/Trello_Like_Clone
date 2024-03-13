package com0.trello.task.service;

import com0.trello.task.model.TaskModel;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

public interface ITaskService {
    String createTask(TaskModel taskModel);
    List<TaskModel> getAllTasks();
    TaskModel updateStatus(TaskModel taskModel, String status);
    TaskModel findTaskById(long taskID);

    TaskModel createDeadlineOfTask(TaskModel task);

    TaskModel assignMemberOfWorkspace(long taskID, long userID, long workspaceID);

    TaskModel assignMember(long taskID, long userID);

    TaskModel createTaskOfBoard(long boardID, TaskModel taskModel);

    List<TaskModel> findTaskByBoardID(long boardID);

    TaskModel updateDeadline(long taskID, LocalDate deadline)throws ResponseStatusException;
}
