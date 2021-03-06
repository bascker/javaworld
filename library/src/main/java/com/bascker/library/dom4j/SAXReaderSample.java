package com.bascker.library.dom4j;

import com.bascker.bsutil.Sample;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Objects;

/**
 * SAXReader 实例
 *
 * 1.SAXReader
 *  1.1 主要用于 XML 文件解析，是业界较流行的 XML 解决方案
 *
 */
public class SAXReaderSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(SAXReaderSample.class);
    private static final String CONF_NAME = "dom4j.xml";
    private final SAXReader mReader = new SAXReader();

    public static void main(String[] args) {
        final SAXReaderSample sample = new SAXReaderSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        LOG.info("SAXReaderSample start");
        InputStream in = null;
        try {
            in = SAXReaderSample.class.getClassLoader().getResourceAsStream(CONF_NAME);
            if (Objects.isNull(in)) {
                LOG.error("get {} stream failed", CONF_NAME);
                return;
            }

            final Document document = mReader.read(in);
            final Element root = document.getRootElement();
            if (Objects.isNull(root)) {
                LOG.error("{} not config", CONF_NAME);
                return;
            }

            LOG.info("conf{name: {}, version: {}}", getValue(root, "jdk.name"), getValue(root, "jdk.version"));
        } catch (DocumentException e) {
            LOG.error("read {} failed", CONF_NAME, e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        LOG.info("SAXReaderSample end");
    }

    private String getValue(final Element root, final String key) {
        return root.elementText(key).trim();
    }

}
