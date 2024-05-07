package org.rg25.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rg25.entity.Note;
import org.rg25.entity.User;
import org.rg25.util.Database;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<User> users;
    GenericDao<Note> notes;

    /**
     * Test setup
     */
    @BeforeEach
    public void setUp() {
        Database database = Database.getInstance();
        users = new GenericDao<>(User.class);
        notes = new GenericDao<>(Note.class);
        database.runSQL("newDump.sql");
        database.runSQL("userDump.sql");
        database.runSQL("noteDump.sql");
        logger.info("--test start--");
    }

    /**
     * Tests getting users
     */
    @Test
    public void getAllUser() {
        List<User> userList = users.getAll();
        logger.debug("userget: " + userList);
        assertFalse(userList.isEmpty());
        assertEquals(1, userList.size());
    }

    /**
     * Tests getting notes
     */
    @Test
    public void getNotes() {
        User user = users.getById(1);
        logger.info("User to get notes from: " + user);
        logger.info("Notes: " + user.getNotes());

    }

    /**
     * Tests db relationships
     */
    @Test
    public void testRelationship() {
        User user = users.getById(1);
        List<Note> noteList = user.getNotes();
        logger.info("noteList: " + noteList);
        logger.info("noteList size: " + noteList.size());

        users.delete(user);
        assertNull(users.getById(1));
        assertTrue(notes.getByProperty("user", user).isEmpty());
    }

    /**
     * Test db relationship the other way
     */
    @Test
    public void testRelationshipReverse() {
        Note note = notes.getById(1);
        User user = note.getUser();
        logger.info("Note's user: " + user);
        user.setLastName("Doe");
        users.update(user);
        logger.info(users.getById(1) + " new user");
        note.setTitle("newTitle");
        notes.update(note);
        logger.info(note);

        assertTrue(user.equals(note.getUser()));

    }

    /**
     * Test getting by property
     */
    @Test
    public void getByProperty() {
        User user;
        User testUser = users.getById(1);
        user = users.getByProperty("username", testUser.getUsername()).get(0);
        assertTrue(user.equals(testUser));

    }


}
