package org.rg25.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.rg25.entity.User;

public class userTest {

    Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<User> users = new GenericDao<>(User.class);
    @Test
    public void getUser() {
        users.getAll();
        logger.debug("userget: " + users.getAll());
    }
}
