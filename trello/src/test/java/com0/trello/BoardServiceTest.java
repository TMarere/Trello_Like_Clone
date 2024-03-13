package com0.trello;

import com0.trello.board.model.BoardModel;
import com0.trello.board.repository.BoardRepository;
import com0.trello.board.service.BoardService;
import com0.trello.workspace.model.WorkspaceModel;
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
public class BoardServiceTest {
    @InjectMocks
    BoardService boardService;
    @Mock
    BoardRepository boardRepository;

    @Test
    public void testCreateBoard(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        when(this.boardRepository.save(board)).thenReturn(board);
        String savedBoardMessage = this.boardService.createBoard(board);
        Assert.assertEquals("The board is created successfully", savedBoardMessage);
        verify(this.boardRepository).save(board);

    }

    @Test
    public void testCreateSecondBoard(){
        BoardModel board2 = new BoardModel();
        board2.setBoardID(2L);
        board2.setBoardName("Assignments");

        when(this.boardRepository.save(board2)).thenReturn(board2);
        String savedBoardMessage = this.boardService.createBoard(board2);
        Assert.assertEquals("The board is created successfully", savedBoardMessage);
        verify(this.boardRepository).save(board2);
    }

    @Test
    public void testFindById(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        when(this.boardRepository.findById(1L)).thenReturn(Optional.of(board));
        BoardModel savedBoard = this.boardService.findById(1L);
        Assert.assertEquals(board, savedBoard);
        verify(this.boardRepository).findById(1L);
    }

    @Test
    //find the board which is not created
    public void testFindByIdNotCreated(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        BoardModel savedBoard = this.boardService.findById(2L);
        Assert.assertEquals(null, savedBoard);
    }

    @Test
    public void testDeleteBoardById(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        when(this.boardRepository.findById(1L)).thenReturn(Optional.of(board));
        String savedBoardMessage = this.boardService.deleteBoard(1L);
        Assert.assertEquals("The board is deleted successfully", savedBoardMessage);
        verify(this.boardRepository).findById(1L);

    }

    @Test
    //delete the board which is not created
    public void testDeleteBoardByIdNotCreated(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        String savedBoardMessage = this.boardService.deleteBoard(2L);
        Assert.assertEquals("The board want to deleted does not exist", savedBoardMessage);
    }

    @Test
    public void testDeleteBoard(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        when(this.boardRepository.findById(1L)).thenReturn(Optional.of(board));
        String savedBoardMessage = this.boardService.deleteBoard(board);
        Assert.assertEquals("The board is deleted successfully", savedBoardMessage);
        verify(this.boardRepository).findById(1L);

    }

    @Test
    //delete the board which is not created
    public void testDeleteBoardNotCreated(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        BoardModel board2 = new BoardModel();
        board2.setBoardID(2L);
        board2.setBoardName("Assignments");

        when(this.boardRepository.save(board)).thenReturn(board);
        String createBoardMessage = this.boardService.createBoard(board);
        String savedBoardMessage = this.boardService.deleteBoard(board2);
        Assert.assertEquals("The board want to deleted does not exist", savedBoardMessage);
        verify(this.boardRepository).save(board);
    }

    @Test
    public void testGetAllBoards(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);

        when(this.boardRepository.findAll()).thenReturn(boards);
        List<BoardModel> savedBoards = this.boardService.getAllBoard();
        Assert.assertEquals(boards, savedBoards);
        verify(this.boardRepository).findAll();
    }

    @Test
    public void testGetAllBoardsTwo(){
        BoardModel board = new BoardModel();
        board.setBoardID(1L);
        board.setBoardName("Group Project");
        board.setBoardDescription("Trello Project");

        BoardModel board2 = new BoardModel();
        board2.setBoardID(2L);
        board2.setBoardName("Assignments");

        List<BoardModel> boards = new ArrayList<>();
        boards.add(board);
        boards.add(board2);

        when(this.boardRepository.findAll()).thenReturn(boards);
        List<BoardModel> savedBoards = this.boardService.getAllBoard();
        Assert.assertEquals(boards, savedBoards);
        verify(this.boardRepository).findAll();
    }
}
