package com.bascker.library.testng;

import org.testng.annotations.DataProvider;

/**
 * 供跨类调用的 DataProvider
 *
 * @author bascker
 */
public class DataProviders {

    @DataProvider
    public static Object[][] names() {
        return new Object[][]{
                {"paul"},
                {"jack"},
                {"bascker"}
        };
    }

}
