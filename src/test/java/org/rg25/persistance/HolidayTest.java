package org.rg25.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apininja.Holiday;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HolidayTest {
    HolidayMapper dao = new HolidayMapper();

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void isNotNull() {
        ArrayList<Holiday> holidays = new ArrayList<>(Arrays.asList(dao.getHolidays(2024)));
        logger.info("Received holidays: " + holidays);
        assertNotNull(holidays);
    }

}
