package pers.penglan.servletsummary.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author PENGL
 */
/*使用了这个，将无法访问静态资源*/
//@WebServlet("/")
public class DefaultServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("跑到这里来了");
        response.getWriter().write("你是不是走错路了");
        request.getRequestDispatcher("/response/buffer").include(request, response);
        /*response已经提交了，不能够再重定向了*/
        //response.sendRedirect("/index.html");
    }
}
