package org.rg25.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rg25.entity.Todo;
import org.rg25.util.Database;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {

    private GenericDao<Todo> dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Setup before each test
     */
    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        dao = new GenericDao<>(Todo.class);
        database.runSQL("newDump.sql");
        database.runSQL("userDump.sql");
        database.runSQL("todoDump.sql");
    }

    /**
     * Tests getting by id
     */
    @Test
    void getByIDSuccess() {
        Todo retrievedTodo = dao.getById(1);
        assertEquals("code", retrievedTodo.getTitle());
        logger.info(retrievedTodo);
    }

    /**
     * Tests update
     */
    @Test
    void testUpdate() {
        Todo todo = dao.getById(1);
        Todo oldTodo = new Todo(todo);
        todo.setTitle("codee");
        dao.update(todo);
        logger.info(dao.getById(1) + "");
        logger.info(oldTodo);
        assertFalse(oldTodo.equals(dao.getById(1)));
    }

    /**
     * Tests getting all
     */
    @Test
    void testGetAll() {
        ArrayList<Todo> todos = (ArrayList<Todo>) dao.getAll();
        assertTrue(todos.size() == 1);
    }


    /**
     * Tests inserting then deleting
     */
//    @Test
//    void insertAndDeleteSuccess() {
//        Todo newTodo = new Todo("testy", "Real Person", 111,theaterDao.getById(1));
//        int result = dao.insert(newMovie);
//        assertTrue(dao.getById(result).equals(newMovie));
//
//        dao.delete(newMovie);
//        assertNull(dao.getById(result));
//    }

    /**
     * Tests getting by property
     */
    @Test
    void testGetProperty() {
        ArrayList<Todo> todos = (ArrayList<Todo>) dao.getByProperty("title", "code");
        logger.info(todos);
        logger.info(dao.getById(1) + "");
        assertTrue(todos.get(0).equals(dao.getById(1)));
    }

    @Test
    void testGetPropertyLike() {
        ArrayList<Todo> todos = (ArrayList<Todo>) dao.getByPropertyLike("title", "co");
        logger.info(todos);
        logger.info(dao.getById(1) + "");
        assertTrue(!todos.isEmpty());
    }
}
