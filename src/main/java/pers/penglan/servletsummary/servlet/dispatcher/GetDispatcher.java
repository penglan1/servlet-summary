package pers.penglan.servletsummary.servlet.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取到RequestDispatcher的方法
 *
 * @Author PENGL
 */
@WebServlet("/dispatcher/getdispatcher")
public class GetDispatcher extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("include...");
        /*方法一：通过ServletContext的方式获取，path参数只能以"/"开头*/
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/request/attribute");
        dispatcher.include(request, response);

        logger.info("forward...");
        /*方法二： 通过request来获取*/
        dispatcher = request.getRequestDispatcher("/response/redirect?flag=3");
        /*注意：在使用forward，得先将原来缓冲区中的内容清理掉，否则出错*/
        response.resetBuffer();
        dispatcher.forward(request, response);
        return;

        /*还可以分别以getNamedDispatcher的方法来获取*/
    }
}
