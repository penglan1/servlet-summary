package pers.penglan.servletsummary.servlet.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于显示业务处理之后的结果
 *
 * @Author PENGL
 */
@WebServlet("/dispatcher/display")
public class Display extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = (String) request.getAttribute("process");
        logger.info("process result: " + result);
        response.getWriter().write(result);
    }
}
