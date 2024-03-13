package com0.trello;

import com0.trello.board.model.BoardModel;
import com0.trello.board.service.BoardService;
import com0.trello.user.model.UserModel;
import com0.trello.user.service.UserService;
import com0.trello.workspace.model.WorkspaceModel;
import com0.trello.workspace.repository.WorkspaceRepository;
import com0.trello.workspace.service.WorkspaceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkspaceServiceTest {
    @InjectMocks
    WorkspaceService workspaceService;
    @Mock
    WorkspaceRepository workspaceRepository;
    @Mock
    BoardService boardService;
    @Mock
    UserService userService;

    @Test
    public void testCreateWorkspace(){
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspaceID(1L);
        workspace.setWorkspaceName("CSCI3130");
        workspace.setWorkspaceDescription("Software Engineering");

        when(this.workspaceRepository.save(workspace)).thenReturn(workspace);
        String savedWorkspaceMessage = this.workspaceService.createWorkspace(workspace);
        Assert.assertEquals("The workspace is created successfully", savedWorkspaceMessage);
        verify(this.workspaceRepository).save(workspace);

    }

    @Test
    public void testCreateSecondWorkspace(){
        WorkspaceModel workspace2 = new WorkspaceModel();
        workspace2.setWorkspaceID(2L);
        workspace2.setWorkspaceName("CSCI1105");

        when(this.workspaceRepository.save(workspace2)).thenReturn(workspace2);
        String savedWorkspaceMessage2 = this.workspaceService.createWorkspace(workspace2);
        Assert.assertEquals("The workspace is created successfully", savedWorkspaceMessage2);
        verify(this.workspaceRepository).save(workspace2);

    }

    @Test
    public void testFindById(){
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspaceID(1L);
        workspace.setWorkspaceName("CSCI3130");
        workspace.setWorkspaceDescription("Software Engineering");

        when(this.workspaceRepository.findById(1L)).thenReturn(Optional.of(workspace));
        WorkspaceModel savedWorkspace = this.workspaceService.findById(1L);
        Assert.assertEquals(workspace, savedWorkspace);
    }

    @Test
    //find the workspace which is not created
    public void testFindByIdNotCreated(){
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspaceID(1L);
        workspace.setWorkspaceName("CSCI3130");
        workspace.setWorkspaceDescription("Software Engineering");

        WorkspaceModel savedWorkspace = this.workspaceService.findById(2L);
        Assert.assertEquals(null, savedWorkspace);
    }

    @Test
    public void testUpdateWholeWorkspace(){
        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");
        workspaceInDb.setWorkspaceDescription("Software Engineering");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI2134");
        updatedWorkspace.setWorkspaceDescription("Software Development");

        when(this.workspaceRepository.findById(1L)).thenReturn(Optional.of(workspaceInDb));
        when(this.workspaceRepository.save(workspaceInDb)).thenReturn(updatedWorkspace);
        String savedWorkspaceMessage = this.workspaceService.updateWholeWorkspace(workspaceInDb);
        Assert.assertEquals("The workspace is updated successfully", savedWorkspaceMessage);
        verify(this.workspaceRepository).findById(1L);
        verify(this.workspaceRepository).save(workspaceInDb);
    }

    @Test
    //update the workspace which is not created
    public void testUpdateWholeWorkspaceNotCreated(){
        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");
        workspaceInDb.setWorkspaceDescription("Software Engineering");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(2L);
        updatedWorkspace.setWorkspaceName("CSCI2134");
        updatedWorkspace.setWorkspaceDescription("Software Development");

        String savedWorkspaceMessage = this.workspaceService.updateWholeWorkspace(updatedWorkspace);
        Assert.assertEquals("The workspace want to updated does not exist", savedWorkspaceMessage);

    }

    @Test
    public void testUpdateWorkspaceDetail(){
        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        updatedWorkspace.setWorkspaceDescription("Software Engineering");

        when(this.workspaceRepository.findById(1L)).thenReturn(Optional.of(workspaceInDb));
        when(this.workspaceRepository.save(workspaceInDb)).thenReturn(updatedWorkspace);
        String savedWorkspaceMessage = this.workspaceService.updateWorkspaceDetail(1L,"Software Engineering");
        Assert.assertEquals("The workspace description is updated successfully", savedWorkspaceMessage);
        verify(this.workspaceRepository).findById(1L);
        verify(this.workspaceRepository).save(workspaceInDb);
    }

    @Test
    // update the detail of a workspace which is not created
    public void testUpdateWorkspaceDetailNotCreated(){
        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        String savedWorkspaceMessage = this.workspaceService.updateWorkspaceDetail(2L,"Software Engineering");
        Assert.assertEquals("The workspace want to updated does not exist", savedWorkspaceMessage);

    }

    @Test
    public void testCreateBoard(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);
        updatedWorkspace.setBoards(boards);

        when(this.workspaceRepository.findById(1L)).thenReturn(Optional.of(workspaceInDb));
        when(this.workspaceRepository.save(workspaceInDb)).thenReturn(updatedWorkspace);
        WorkspaceModel savedWorkspace = this.workspaceService.createBoard(1L,board);
        Assert.assertEquals(updatedWorkspace, savedWorkspace);
        verify(this.workspaceRepository).findById(1L);
        verify(this.workspaceRepository).save(workspaceInDb);
    }

    @Test
    //create a board from a workspace which is not created
    public void testCreateBoardFromWorkspaceNotCreated(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);
        updatedWorkspace.setBoards(boards);

        WorkspaceModel savedWorkspace = this.workspaceService.createBoard(2L,board);
        Assert.assertEquals(null, savedWorkspace);

    }

    @Test
    public void testAssignBoard(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);
        updatedWorkspace.setBoards(boards);

        when(this.boardService.findById(1L)).thenReturn(board);
        when(this.workspaceRepository.findById(1L)).thenReturn(Optional.of(workspaceInDb));
        when(this.workspaceRepository.save(workspaceInDb)).thenReturn(updatedWorkspace);

        WorkspaceModel savedWorkspace = this.workspaceService.assignBoard(1L,1L);

        Assert.assertEquals(updatedWorkspace, savedWorkspace);

        verify(this.boardService).findById(1L);
        verify(this.workspaceRepository).findById(1L);
        verify(this.workspaceRepository).save(workspaceInDb);
    }

    @Test
    //assign a board from a workspace which is not created
    public void testAssignBoardFromWorkspaceNotCreated(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);
        updatedWorkspace.setBoards(boards);


        WorkspaceModel savedWorkspace = this.workspaceService.assignBoard(2L,1L);

        Assert.assertEquals(null, savedWorkspace);


    }

    @Test
    //assign a board to a workspace where the board want to assign is not created
    public void testAssignBoardFNotCreated(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);
        updatedWorkspace.setBoards(boards);


        WorkspaceModel savedWorkspace = this.workspaceService.assignBoard(1L,2L);

        Assert.assertEquals(null, savedWorkspace);

    }

    @Test
    public void testAddMembers(){
        UserModel user = new UserModel();
        user.setId(1L);
        user.setFirstName("Bruce");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<UserModel> users = new ArrayList<>();
        users.add(user);
        updatedWorkspace.setUsers(users);

        when(this.userService.findById(1L)).thenReturn(user);
        when(this.workspaceRepository.findById(1L)).thenReturn(Optional.of(workspaceInDb));
        when(this.workspaceRepository.save(workspaceInDb)).thenReturn(updatedWorkspace);

        WorkspaceModel savedWorkspace = this.workspaceService.addMembers(1L,1L);

        Assert.assertEquals(updatedWorkspace, savedWorkspace);

        verify(this.userService).findById(1L);
        verify(this.workspaceRepository).findById(1L);
        verify(this.workspaceRepository).save(workspaceInDb);
    }

    @Test
    //add a member from a workspace which is not created
    public void testAddMembersFromWorkspaceNotCreated(){
        UserModel user = new UserModel();
        user.setId(1L);
        user.setFirstName("Bruce");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<UserModel> users = new ArrayList<>();
        users.add(user);
        updatedWorkspace.setUsers(users);

        WorkspaceModel savedWorkspace = this.workspaceService.addMembers(2L,1L);
        Assert.assertEquals(null, savedWorkspace);

    }

    @Test
    //add a member to a workspace where the member want to add is not created
    public void testAddMembersFNotCreated(){
        UserModel user = new UserModel();
        user.setId(1L);
        user.setFirstName("Bruce");

        WorkspaceModel workspaceInDb = new WorkspaceModel();
        workspaceInDb.setWorkspaceID(1L);
        workspaceInDb.setWorkspaceName("CSCI3130");

        WorkspaceModel updatedWorkspace = new WorkspaceModel();
        updatedWorkspace.setWorkspaceID(1L);
        updatedWorkspace.setWorkspaceName("CSCI3130");
        List<UserModel> users = new ArrayList<>();
        users.add(user);
        updatedWorkspace.setUsers(users);

        WorkspaceModel savedWorkspace = this.workspaceService.addMembers(1L,2L);
        Assert.assertEquals(null, savedWorkspace);
    }

}
