package pers.penglan.servletsummary.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 通过set,get,remove属性来测试Bind这个监听器
 *
 * @Author PENGL
 */
@WebServlet("/session/testbinding")
public class TestBinding extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("flag");
        HttpSession session = request.getSession();
        /*session.setMaxInactiveInterval(60);*/
        Binding binding = new Binding();
        value = value == null ? "" : value;
        switch (value) {
            case "1":
                session.setAttribute("session", binding);
                break;
            case "2":
                session.getAttribute("session");
                break;
            case "3":
                session.removeAttribute("session");
                break;
            default:
                session.invalidate();
                break;
        }

    }
}
