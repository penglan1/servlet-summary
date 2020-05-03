package pers.penglan.servletsummary.servlet.session;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpSession
 *
 * @Author PENGL
 */
@WebServlet("/session/create")
public class Create extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session，若没有，则新建一个*/
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        Map<String, String> map = new HashMap<>();
        if (session.isNew()) {
            logger.info("新创建的Session");
            map.put("jsessionid", sessionId + "（新创建的）");
        } else {
            map.put("jsessionid", sessionId);
        }
        logger.info("current sessionId: " + sessionId);
        response.getWriter().write(new Gson().toJson(map.entrySet().toArray()));
    }
}
