package pers.penglan.servletsummary.servlet.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Send Redirect
 *
 * <p>The sendRedirect method will set the appropriate headers and content body to
 * redirect the client to a different URL. It is legal to call this method with a relative
 * URL path, however the underlying container must translate the relative path to a
 * fully qualified URL for transmission back to the client. If a partial URL is given and,
 * for whatever reason, cannot be converted into a valid URL, then this method must
 * throw an IllegalArgumentException.</p>
 *
 * @Author PENGL
 */
@WebServlet("/response/redirect")
public class Redirect extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        /*客户端可以通过QueryString来测试三种不同的重定向的路径使用方式*/
        String value = request.getParameter("flag");
        value = value == null ? "" : value;
        logger.info("value=" + value);
        switch (value) {
            case "1":
                /*If the location is relative without a leading '/' the container
                interprets it as relative to the current request URI*/
                response.sendRedirect("buffer");
                break;
            case "2":
                /*If the location is relative with a leading '/' the container
                interprets it as relative to "the servlet container root"*/
                response.sendRedirect("/sm/html/test/request/path.html");
                break;
            case "3":
                response.getWriter().write("没啥效果啊，forward碰上了sendRedirect，咋回事啊？？？");
                /*其他任意网址*/
                response.sendRedirect("https://note.youdao.com/yws/public/resource/177c2b39fdafc03e55d1685a10b72aa6/xmlnote/6940275A144F49FFBF28D8107016CE83/30844");
                break;
                default: response.sendRedirect("/sm/html/test/request/path.html");
                break;
        }
        logger.info("结束");
    }
}
