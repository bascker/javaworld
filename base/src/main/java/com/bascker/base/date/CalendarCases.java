package com.bascker.base.date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Calendar 使用案例
 *
 * @author bascker
 */
public class CalendarCases {

    private static final Logger LOG = LoggerFactory.getLogger(CalendarCases.class);

    @Test
    public void base () throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar calendar = Calendar.getInstance();
        LOG.info(format.format(calendar.getTime()).toString());

        calendar.set(2017, 9, 10);
        final Date date = calendar.getTime();
        LOG.info(format.format(date).toString());

        calendar.setTime(format.parse("2017-07-10"));
        LOG.info(calendar.getTime().toString());
    }

}
