package pers.penglan.servletsummary.servlet.session;

import com.google.gson.Gson;

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
 * Change SessionId
 *
 * @Author PENGL
 */
@WebServlet("/session/changeid")
public class ChangeId extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Map<String, String> map = new HashMap<>();
        if (session == null) {
            map.put("警告", "请先创建session");
            response.getWriter().write(new Gson().toJson(map.entrySet().toArray()));
        } else {
            request.changeSessionId();
            session = request.getSession(false);
            map.put("jsessionid", session.getId() + "（改变后的）");
            response.getWriter().write(new Gson().toJson(map.entrySet().toArray()));
        }

    }
}
