package pers.penglan.servletsummary.servlet.begin;

import javax.servlet.*;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/**
 * 通过实现Servlet接口的方式来生成一个Servlet
 * @author PENGL
 */
public class ServletImpl implements Servlet {
    /**
     * 初始化
     * @param servletConfig 配置对象允许 Servlet 访问由 Web 应用配置信息提供的键-值对的初始化参数。该配置对象也提
     *                      供给 Servlet去访问一个ServletContext对象，ServletContext描述了Servlet的运行时环境
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        boolean flag = false;
        /* 方法示例 */
        if (flag) {
            String servletName = servletConfig.getServletName();
            String initParameter = servletConfig.getInitParameter("username");
            Enumeration names = servletConfig.getInitParameterNames();
            ServletContext servletContext = servletConfig.getServletContext();
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        Writer writer = servletResponse.getWriter();
        writer.write("这是用实现Servlet接口的方式生成的Servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
