package com.bascker.bsutil.office;

import com.bascker.bsutil.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Excel 工具类
 * @author bascker
 */
public class ExcelUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String XLS = "xls";        // Excel 2003
    private static final String XLSX = "xlsx";      // Excel 2007/2010

    private static FileInputStream in = null;

    private ExcelUtils() {}

    public static Workbook load(final String excelPath) {
        try {
            final Path path = FileUtils.getPath(excelPath);
            final File excel = FileUtils.getFile(path);
            final String fileType = FileUtils.getFileType(path);
            in = new FileInputStream(excel);

            if (StringUtils.equals(XLS, fileType)) {
                LOGGER.info("load XLS");
                return new HSSFWorkbook(in);
            } else if (StringUtils.equals(XLSX, fileType)) {
                LOGGER.info("load XLSX");
                return new XSSFWorkbook(in);
            } else {
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("load file failure, cause by ", e.getMessage());
        }

        return null;
    }

    /**
     * 获取 Excel 文件输入流
     * @return
     */
    public static InputStream getInputStream() {
        return in;
    }

    /**
     * 关闭文本输入流
     */
    public static void close() {
        if (Objects.nonNull(in)) {
            IOUtils.closeQuietly(in);
        }
    }

}
