package pers.penglan.servletsummary.servlet.begin;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 通过继承GennericServlet类的方式来生成Servlet
 * @author PENGL
 */
public class GenericServlet2 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        Writer writer = servletResponse.getWriter();
        writer.write("这是用继承GenericServlet类的方式生成的Servlet");
        writer.close();
    }
}
