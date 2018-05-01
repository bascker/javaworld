package com.bascker.base.date;

import org.junit.Assert;
import org.junit.Before;
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
    private Calendar mCalendar;

    @Before
    public void before () {
        mCalendar = Calendar.getInstance();
    }

    @Test
    public void base () throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LOG.info(format.format(mCalendar.getTime()).toString());

        mCalendar.set(2017, 9, 10);
        final Date date = mCalendar.getTime();
        LOG.info(format.format(date).toString());

        mCalendar.setTime(format.parse("2017-07-10"));
        LOG.info(mCalendar.getTime().toString());
    }

    @Test
    public void testGet () throws ParseException {
        final int year = mCalendar.get(Calendar.YEAR);
        LOG.info("Year: " + year);

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = format.parse("2018-1-1 00:00:00");
        mCalendar.setTime(date);
        Assert.assertEquals(1, mCalendar.get(Calendar.MONTH) + 1);
    }

}
