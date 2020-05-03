package pers.penglan.servletsummary.logs.log4j2;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 当Log4j2的配置文件没有放置在默认初始位置时，可以使用此类来告诉Log4j2配置文件的位置，并将配置文件传递给Log4j2
 * 注意：此类的作用主要是先初始化Log4j2,初始化之后，后续可以直接使用Logger来打印日志
 * 获取Logger的步骤：
 * <pre>
 *     // 第一步，初始化
 *     // 初始化在整个项目运行期间只需要调用一次就可以了，除非想要更改使用另一个配置文件，则需要重新调用一次初始化
 *     Log4j2Config.init(parameter);
 *     // 第二部，在使用出获得Logger
 *     Logger logger = LogManager.getLogger(parameter);
 * </pre>
 * @Author PENGL
 */
public class Log4j2Config {
    /*配置文件的位置*/
    private static File configFile = null;

    public static void init(String path) {
        configFile = new File(path);
        init01();
    }

    public static void init(File configFile) {
        Log4j2Config.configFile = configFile;
        init01();
    }

    private static void init01() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(configFile));
            ConfigurationSource configurationSource = new ConfigurationSource(bufferedInputStream, configFile);
            Configurator.initialize(null, configurationSource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
