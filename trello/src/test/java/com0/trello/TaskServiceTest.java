package com0.trello;

import com0.trello.board.model.BoardModel;
import com0.trello.board.service.BoardService;
import com0.trello.task.model.TaskModel;
import com0.trello.task.repository.TaskRepository;
import com0.trello.task.service.TaskService;
import com0.trello.user.model.UserModel;
import com0.trello.user.service.UserService;
import com0.trello.workspace.model.WorkspaceModel;
import com0.trello.workspace.service.WorkspaceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @InjectMocks
    TaskService taskService;
    @Mock
    TaskRepository taskRepository;
    @Mock
    UserService userService;
    @Mock
    WorkspaceService workspaceService;
    @Mock
    BoardService boardService;

    @Test
    public void create_first_task() {
        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("Group Project Release 3");
        taskInDb.setTaskDescription("Test cases for backend");
        taskInDb.setStatus("Not Assigned");

        when(this.taskRepository.save(taskInDb)).thenReturn(taskInDb);

        String savedTask = this.taskService.createTask(taskInDb);
        assertEquals("Task has been successfully created", savedTask, "Different task models");

        verify(this.taskRepository).save(taskInDb);
    }

    @Test
    public void create_second_task() {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(3L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        when(this.taskRepository.save(taskModel)).thenReturn(taskModel);

        String savedTask = this.taskService.createTask(taskModel);
        assertEquals(savedTask, "Task has been successfully created", "Different task models");

        verify(this.taskRepository).save(taskModel);
    }

    @Test
    public void update_taskStatus_successfully() {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        when(this.taskRepository.save(taskModel)).thenReturn(taskModel);
        TaskModel savedTask = this.taskService.updateStatus(taskModel, "In Progress");
        assertEquals(savedTask.getStatus(), "In Progress");

        verify(this.taskRepository).save(taskModel);
    }

    @Test
    public void invalid_taskStatus_expected() {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        when(this.taskRepository.save(taskModel)).thenReturn(taskModel);
        TaskModel savedTask = this.taskService.updateStatus(taskModel, "In Progress");
        assertNotEquals(savedTask.getStatus(), "Not Assigned");


        verify(this.taskRepository).save(taskModel);
    }

    @Test
    public void taskStatus_notUpdated() {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        assertNotEquals(taskModel.getStatus(), "In Progress");

    }

    @Test
    public void findTaskById_successfully() {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        when(this.taskRepository.findById(5L)).thenReturn(Optional.of(taskModel));
        TaskModel savedTask = this.taskService.findTaskById(5L);
        assertEquals(taskModel, savedTask);
        verify(this.taskRepository).findById(5L);
    }

    @Test
    public void invalid_taskID_passed() {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        TaskModel savedTask = this.taskService.findTaskById(1L);
        assertNotEquals(taskModel, savedTask);
    }

    @Test
    public void getAllTasks_successful(){
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("Group Project Release 3");
        taskInDb.setTaskDescription("Test cases for backend");
        taskInDb.setStatus("Not Assigned");

        List<TaskModel> taskModelList = new ArrayList<>();
        taskModelList.add(taskModel);
        taskModelList.add(taskInDb);

        when(this.taskRepository.findAll()).thenReturn(taskModelList);

        List<TaskModel> savedTasks = this.taskService.getAllTasks();
        assertEquals(savedTasks, taskModelList);
        verify(this.taskRepository).findAll();

    }

    @Test
    public void getAllTasks_missingTask(){
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskID(5L);
        taskModel.setTaskName("Task created");
        taskModel.setTaskDescription("Test cases for task creation");
        taskModel.setStatus("Not Assigned");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("Group Project Release 3");
        taskInDb.setTaskDescription("Test cases for backend");
        taskInDb.setStatus("Not Assigned");

        List<TaskModel> taskModelList = new ArrayList<>();
        taskModelList.add(taskInDb);

        List<TaskModel> savedTasks = this.taskService.getAllTasks();
        assertFalse(taskModelList.equals(savedTasks));

    }


    @Test
    public void testCreateValidDeadlineOFExistingTask() {
        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setDeadline(LocalDate.of(2023, 8, 25));

        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        when(this.taskRepository.save(taskInDb)).thenReturn(updatedTask);

        TaskModel savedTask =this.taskService.createDeadlineOfTask(updatedTask);
        Assert.assertEquals(savedTask.getDeadline(), updatedTask.getDeadline());

        verify(this.taskRepository).findById(1L);
        verify(this.taskRepository).save(taskInDb);    }

    @Test
    public void testCreateInvalidDeadlineOFExistingTask() throws Exception{
        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");

        updatedTask.setDeadline(LocalDate.of(2022, 7, 25));

        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));

        ResponseStatusException exception = Assert.assertThrows(
                ResponseStatusException.class,
                () -> {this.taskService.createDeadlineOfTask(updatedTask);}
        );

        Assert.assertEquals("Invalid deadline.", exception.getReason());
    }

    @Test
    //add user1 which from workspace to task, then the user list of task contains user1
    public void testAssignMemberOfWorkspaceToTask(){
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setFirstName("Bruce");

        UserModel user2 = new UserModel();
        user1.setId(2L);
        user1.setFirstName("John");

        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspaceID(1L);
        workspace.setWorkspaceName("CSCI3130");
        List<UserModel> workspaceUsers = new ArrayList<>();
        workspaceUsers.add(user1);
        workspace.setUsers(workspaceUsers);

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setUser(user1);

        when(this.userService.findById(1L)).thenReturn(user1);
        when(this.workspaceService.findById(1L)).thenReturn(workspace);
        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        when(this.taskRepository.save(taskInDb)).thenReturn(updatedTask);

        TaskModel newTask = this.taskService.assignMemberOfWorkspace(1L,1L,1L);

        Assert.assertEquals(updatedTask.getUser(),newTask.getUser());

        verify(this.userService).findById(1L);
        verify(this.workspaceService).findById(1L);
        verify(this.taskRepository).findById(1L);
        verify(this.taskRepository).save(taskInDb);
    }

    @Test
    //add user2 which is not from workspace to task, then the user list of task does not contain user2
    public void testAssignMemberNotOfWorkspaceToTask() throws Exception{
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setFirstName("Bruce");

        UserModel user2 = new UserModel();
        user1.setId(2L);
        user1.setFirstName("John");

        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspaceID(1L);
        workspace.setWorkspaceName("CSCI3130");
        List<UserModel> workspaceUsers = new ArrayList<>();
        workspaceUsers.add(user1);
        workspace.setUsers(workspaceUsers);

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");


        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setUser(user2);

        when(this.userService.findById(2L)).thenReturn(user2);
        when(this.workspaceService.findById(1L)).thenReturn(workspace);
        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        ResponseStatusException exception = Assert.assertThrows(
                ResponseStatusException.class,
                () -> {this.taskService.assignMemberOfWorkspace(1L,2L,1L);}
        );

        Assert.assertEquals("The user doesn't in the user list of workspace.", exception.getReason());

    }

    @Test
    //add user2 which is from workspace to task which has been assigned to user1
    public void testAssignMemberOfWorkspaceToTaskHaveAssigned ()throws Exception{
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setFirstName("Bruce");

        UserModel user2 = new UserModel();
        user1.setId(2L);
        user1.setFirstName("John");

        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspaceID(1L);
        workspace.setWorkspaceName("CSCI3130");
        List<UserModel> workspaceUsers = new ArrayList<>();
        workspaceUsers.add(user1);
        workspaceUsers.add(user2);
        workspace.setUsers(workspaceUsers);

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");
        taskInDb.setUser(user1);

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setUser(user1);


        when(this.workspaceService.findById(1L)).thenReturn(workspace);
        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        ResponseStatusException exception = Assert.assertThrows(
                ResponseStatusException.class,
                () -> {this.taskService.assignMemberOfWorkspace(1L,2L,1L);}
        );

        Assert.assertEquals("This task has been assigned.", exception.getReason());
    }

    @Test
    //add user1 which from workspace to task, then the user list of task contains user1
    public void testAssignMemberToTask(){
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setFirstName("Bruce");

        UserModel user2 = new UserModel();
        user1.setId(2L);
        user1.setFirstName("John");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setUser(user1);

        when(this.userService.findById(1L)).thenReturn(user1);
        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        when(this.taskRepository.save(taskInDb)).thenReturn(updatedTask);

        TaskModel newTask = this.taskService.assignMember(1L,1L);

        Assert.assertEquals(newTask.getUser(), updatedTask.getUser());

        verify(this.userService).findById(1L);
        verify(this.taskRepository).findById(1L);
        verify(this.taskRepository).save(taskInDb);
    }

    @Test
    //add user2 which is from workspace to task which has been assigned to user1
    public void testAssignMemberToTaskHaveAssigned ()throws Exception{
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setFirstName("Bruce");

        UserModel user2 = new UserModel();
        user1.setId(2L);
        user1.setFirstName("John");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");
        taskInDb.setUser(user1);

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setUser(user1);

        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        ResponseStatusException exception = Assert.assertThrows(
                ResponseStatusException.class,
                () -> {this.taskService.assignMember(1L,2L);}
        );

        Assert.assertEquals("This task has been assigned.", exception.getReason());

    }

    @Test
    public void testCreateTaskOfBoard(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setBoard(board);
        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(updatedTask);
        board.setTasks(tasks);

        when(this.boardService.findById(1L)).thenReturn(board);
        when(this.taskRepository.save(taskInDb)).thenReturn(updatedTask);

        TaskModel savedTask = this.taskService.createTaskOfBoard(1L,taskInDb);
        Assert.assertEquals(updatedTask,savedTask);

        verify(this.boardService).findById(1L);
        verify(this.taskRepository).save(taskInDb);
    }

    @Test
    //create task of board which does not exist
    public void testCreateTaskOfBoardNotExist() throws Exception{
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setBoard(board);

        ResponseStatusException exception = Assert.assertThrows(
                ResponseStatusException.class,
                () -> {this.taskService.createTaskOfBoard(2L,taskInDb);}
        );
        Assert.assertEquals("The board want to task does not exist.", exception.getReason());

    }

    @Test
    public void testFindTaskByBoardID(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setBoard(board);
        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(updatedTask);
        board.setTasks(tasks);

        when(this.boardService.findById(1L)).thenReturn(board);

        List<TaskModel> savedTasks = this.taskService.findTaskByBoardID(1L);
        Assert.assertEquals(tasks,savedTasks);

        verify(this.boardService).findById(1L);

    }

    @Test
    public void testFindTaskByBoardIDTwoTasks(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setBoard(board);

        TaskModel taskInDb2 = new TaskModel();
        taskInDb.setTaskID(2L);
        taskInDb.setTaskName("group project release 2");

        TaskModel updatedTask2 = new TaskModel();
        updatedTask.setTaskID(2L);
        updatedTask.setTaskName("group project release 2");
        updatedTask.setBoard(board);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(updatedTask);
        tasks.add(updatedTask2);
        board.setTasks(tasks);

        when(this.boardService.findById(1L)).thenReturn(board);

        List<TaskModel> savedTasks = this.taskService.findTaskByBoardID(1L);
        Assert.assertEquals(tasks,savedTasks);

        verify(this.boardService).findById(1L);

    }

    @Test
    //update the deadline of a task
    public void testUpdateDeadline(){

        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setDeadline(LocalDate.of(2023, 8, 25));

        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        when(this.taskRepository.save(taskInDb)).thenReturn(updatedTask);

        TaskModel newTask = this.taskService.updateDeadline(1L,LocalDate.of(2023, 8, 25));

        Assert.assertEquals(newTask.getDeadline(), updatedTask.getDeadline());
        verify(this.taskRepository).findById(1L);
        verify(this.taskRepository).save(taskInDb);
    }

    @Test
    //update the invalid deadline of a task
    public void testUpdateInvalidDeadline()throws Exception{
        TaskModel taskInDb = new TaskModel();
        taskInDb.setTaskID(1L);
        taskInDb.setTaskName("group project release 3");

        TaskModel updatedTask = new TaskModel();
        updatedTask.setTaskID(1L);
        updatedTask.setTaskName("group project release 3");
        updatedTask.setDeadline(LocalDate.of(2022, 8, 25));

        when(this.taskRepository.findById(1L)).thenReturn(Optional.of(taskInDb));
        ResponseStatusException exception = Assert.assertThrows(
                ResponseStatusException.class,
                () -> {this.taskService.updateDeadline(1L,LocalDate.of(2022, 8, 25));}
        );

        Assert.assertEquals("Invalid deadline.", exception.getReason());

    }



}
