package pers.penglan.servletsummary.servlet.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.penglan.servletsummary.util.servlet.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 测试Ajax异步请求
 * @Author PENGL
 */
@WebServlet(urlPatterns = "/request/ajax")
public class Ajax extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameters = request.getParameterMap();

        logger.info(">>begin");
        logger.info("Referer: " + request.getHeader("referer"));
        logger.info("Request URL: " + new RequestUtil(request).getDecodedURL(true));
        logger.info("Request Body: " + new RequestUtil(request).getBody("UTF-8"));
        for (Map.Entry param : parameters.entrySet()) {
            logger.info(param.getKey() + " : " + Arrays.toString((String[])param.getValue()));
        }
        logger.info(">>end");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().write("处理完毕：" + new Date().toString());
    }
}
