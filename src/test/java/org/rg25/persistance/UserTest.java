package org.rg25.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.rg25.entity.User;

public class UserTest {

    Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<User> users = new GenericDao<>(User.class);
    @Test
    public void getUser() {
        users.getAll();
        logger.debug("userget: " + users.getAll());
    }

    @Test
    public void getNotes() {
        User user = users.getById(1);
        logger.info("User to get notes from: " + user);
        logger.info("Notes: " + user.getNotes());
    }

    @Test
    public void getByProperty() {
        User user;
        User testUser = users.getById(1);
        user = users.getByProperty("username", testUser.getUsername()).get(0);
        logger.info(user);

    }


}
