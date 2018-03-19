package com.bascker.advance.jvm;

import org.junit.Test;

/**
 * EscapeAnalysis Unit Test
 *
 * @author bascker
 */
public class EscapeAnalysisTest {

    @Test
    public void test () {
        for (int i = 0; i < 50; i ++) {
            EscapeAnalysis.noEscape();
            EscapeAnalysis.escape();
        }
    }

}
