package pers.penglan.servletsummary.servlet.begin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * 通过继承HttpServlet类的方式来生成Servlet
 * @author PENGL
 */
public class HttpServlet3 extends HttpServlet {
    /**
     * 不一定非要覆盖这个方法，某些情况下，覆盖doGet,doPost方法等比较好
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write("这个是通过继承HttpServlet类的方式生成的Servlet,Jrebel");
        writer.close();
        System.out.println(new Date().toString());
    }
}
