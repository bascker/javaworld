package com.bascker.library.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Table 案例
 * 1. 相当于一个二维表, 有 2 个 key，根据 row 和 column 进行取值
 * 2. 跟 Map(k, v) 一样 Table(row, column) 对应一个唯一值
 *
 * @author bascker
 */
@Test
public class TableCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableCases.class);

    private Table<String, String, String> table;

    @BeforeTest
    public void testCreate() {
        table = HashBasedTable.create();
        table.put("x", "y", "point(x, y)");
        table.put("x", "y", "point(x2, y2)");
        table.put("name", "age", "person(name, age)");

        LOGGER.info(table.toString());
        LOGGER.info(table.row("x").toString());
        LOGGER.info(table.row("name").toString());
        LOGGER.info(table.column("y").toString());
    }

}
