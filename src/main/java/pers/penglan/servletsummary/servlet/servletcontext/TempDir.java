package pers.penglan.servletsummary.servlet.servletcontext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * ServletContext的临时工作目录
 *
 * <p>每一个 servlet 上下文都需要一个临时的存储目录。Servlet 容器必须为每一个 servlet 上下文提供一个私有的
 * 临时目录，并将通过 <code>javax.servlet.context.tempdir</code> 上下文属性使其可用，关联该属性的对象必须是 java.io.File
 * 类型。该要求公认为在多个 servlet 引擎实现中提供一个通用的便利。当 servlet 容器重启时，它不需要去保
 * 持临时目录中的内容，但必须确保一个 servlet 上下文的临时目录中的内容对运行在同一个 servlet 容器的其
 * 他 Web 应用的上下文不可见。</p>
 *
 * @Author PENGL
 */
@WebServlet("/context/tempdir")
public class TempDir extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*查看临时工作目录的所在文件夹*/
        ServletContext context = request.getServletContext();
        File tempdir = (File) context.getAttribute("javax.servlet.context.tempdir");
        logger.info("javax.servlet.context.tempdir: " + tempdir);
    }
}
