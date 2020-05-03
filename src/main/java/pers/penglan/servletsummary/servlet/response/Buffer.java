package pers.penglan.servletsummary.servlet.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Buffering
 *
 * <p>A servlet container is allowed, but not required, to buffer output going to the client
 * for efficiency purposes. Typically servers that do buffering make it the default, but
 * allow servlets to specify buffering parameters.</p>
 *
 * @Author PENGL
 */

@WebServlet("/response/buffer")
public class Buffer extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        logger.info(">>begin");
        int bufferSize = response.getBufferSize();
        logger.info("bufferSize: " + bufferSize);
        //response.setBufferSize(1000);
        bufferSize = response.getBufferSize();
        logger.info("bufferSize after setting: " + bufferSize);
        Writer writer = response.getWriter();
        writer.write("<h1>这是第一个数据到达了</h1>");
        /*先将第一个数据刷新到客户端*/
        response.flushBuffer();
        try {
            /*暂时睡眠1秒中*/
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*输出第二个数据*/
        writer.write("<h2>这是第二个数据到达了</h2>");
        response.flushBuffer();
    }
}
