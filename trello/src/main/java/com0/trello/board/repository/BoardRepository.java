package com0.trello.board.repository;

import com0.trello.board.model.BoardModel;
import com0.trello.workspace.model.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardModel, Long> {
}
