package pers.penglan.servletsummary.logs.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;

/**
 * 测试Log4j2Config是否起作用
 * @Author PENGL
 */
public class TestLog {

    @Test
    public void test01() {
        /*使用相对位置来获取文件时，其实目录为java.exe启动的工作目录，一般都是项目的目录*/
        Log4j2Config.init("src\\main\\resources\\log4j2\\config.xml");
        Logger logger = LogManager.getLogger("console");
        logger.info("这是使用Log4j2打印的日志文件");
    }

    /**
     * Reader和Writer都要已指定的编码来操作，否则会使用平台指定的编码，
     * 这很有可能因为与追加的文件编码不一致而产生乱码
     */
    @Test
    public void test02() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filePath"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
