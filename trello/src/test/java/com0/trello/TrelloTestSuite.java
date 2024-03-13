package com0.trello;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        com0.trello.TaskServiceTest.class,
        com0.trello.WorkspaceServiceTest.class,
        com0.trello.BoardServiceTest.class,
        com0.trello.UserServiceTest.class,

})
public class TrelloTestSuite {
}
