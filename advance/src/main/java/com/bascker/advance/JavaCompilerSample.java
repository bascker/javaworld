package com.bascker.advance;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Compile Sample
 *
 * 1.JavaCompiler
 *  1.1 可用于编译 .Java 文件
 *  1.2 场景: OJ 系统
 *
 * @author bascker
 */
public class JavaCompilerSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(JavaCompilerSample.class);
    private final JavaCompiler mCompiler;

    public JavaCompilerSample() {
        mCompiler = ToolProvider.getSystemJavaCompiler();
    }

    public static void main(String[] args) {
        final JavaCompilerSample sample = new JavaCompilerSample();
        final String file = "F:\\workspace\\github\\java\\javaworld\\advance\\src\\main\\java\\com\\bascker\\advance\\DesktopCases.java";
        sample.start(file);
    }

    @Override
    public void start(final Object... args) {
        compile((String) args[0]);
    }

    private boolean compile (final String javaFile) {
        final int rs = mCompiler.run(null, null, null, javaFile);
        return rs == 0;
    }

}
