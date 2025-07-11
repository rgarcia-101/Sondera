package org.rg25.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rg25.entity.Note;
import org.rg25.entity.User;
import org.rg25.util.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests note
 */
public class NoteTest {
    private GenericDao<Note> dao;
    private GenericDao<User> userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Setup before each test
     */
    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        dao = new GenericDao<>(Note.class);
        userDao = new GenericDao<>(User.class);
//        database.runSQL("newDump.sql");
//        database.runSQL("userDump.sql");
//        database.runSQL("noteDump.sql");
        logger.info("--test start--");
    }

//
//    /**
//     * Tests getting by id
//     */
//    @Test
//    void getByIDSuccess() {
//        Note retrievedNote = dao.getById(1);
//        assertEquals("code", retrievedNote.getTitle());
//        assertEquals("do", retrievedNote.getContent());
//        logger.info(retrievedNote);
//    }
//
//    /**
//     * Tests update
//     */
//    @Test
//    void testUpdate() {
//        Note note = dao.getById(1);
//        Note oldTodo = new Note(note);
//        note.setTitle("newTitle");
//        dao.update(note);
//        logger.info(dao.getById(1) + "");
//        logger.info(oldTodo);
//        assertFalse(oldTodo.equals(dao.getById(1)));
//        assertTrue(note.getTitle().equals("newTitle"));
//    }
//
//    /**
//     * Tests getting all
//     */
//    @Test
//    void testGetAll() {
//        ArrayList<Note> notes = (ArrayList<Note>) dao.getAll();
//        assertTrue(!notes.isEmpty());
//        assertTrue(notes.size() == 7);
//    }
//
//
//    /**
//     * Tests inserting then deleting
//     */
//    @Test
//    void insertAndDeleteSuccess() {
//        int allRecords = dao.getAll().size();
//        Note newNote = new Note(userDao.getById(1),"Note to self", "do hw");
//        int result = dao.insert(newNote);
//        assertTrue(dao.getById(result).equals(newNote));
//        assertTrue((allRecords+1) == dao.getAll().size());
//
//        dao.delete(newNote);
//        assertNull(dao.getById(result));
//        assertTrue((allRecords) == dao.getAll().size());
//    }
//
//    /**
//     * Tests getting by property
//     */
//    @Test
//    void testGetProperty() {
//        ArrayList<Note> notes = (ArrayList<Note>) dao.getByProperty("title", "new");
//        logger.info(notes.get(0));
//        logger.info(dao.getById(1) + "");
//        assertFalse(notes.isEmpty());
//        assertEquals(notes.get(0), dao.getById(2));
//    }
//
//    /**
//     * Tests getting by property, up to a limit
//     */
//    @Test
//    void testGetWithLimit() {
//        int limit = 2;
//        ArrayList<Note> notes = (ArrayList<Note>) dao.getByPropertyUpTo("content","note",2);
//        logger.info(notes.size());
//        assertTrue(notes.size() == limit);
//    }
//
//    /**
//     * Tests getting by like property
//     */
//    @Test
//    void testGetPropertyLike() {
//        ArrayList<Note> notes = (ArrayList<Note>) dao.getByPropertyLike("title", "code");
//        logger.info(notes);
//        logger.info(dao.getById(1) + "");
//        assertFalse(notes.isEmpty());
//        assertEquals(notes.get(0), dao.getById(1));
//    }
}
