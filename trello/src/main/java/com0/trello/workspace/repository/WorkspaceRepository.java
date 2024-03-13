package com0.trello.workspace.repository;

import com0.trello.workspace.model.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceModel, Long> {

}
