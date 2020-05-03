package pers.penglan.servletsummary.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

/**
 * Http Protocol Parameters
 *
 * <pre>
 *     the container populates the parameters from the URI query string and POST-ed data.
 *     Data from the query string and the post body are aggregated into the request
 *     parameter set. Query string data is presented before post body data. For example, if
 *     a request is made with a query string of a=hello and a post body of a=goodbye&a=world ,
 *     the resulting parameter set would be ordered a=(hello, goodbye, world) .
 * </pre>
 *
 * <pre>
 *     If a servlet wants to get the parameters from a form data, the attribute enctype of the form must
 *     be 'application/x-www-form-urlencoded' and must use the "post" method.
 * </pre>
 *
 * @Author PENGL
 */
@WebServlet(urlPatterns = "/request/parameter")
public class Parameter extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Writer writer = response.getWriter();
        /*搞错了，Ajax.java才是HTTP Protocol Parameter的测试处理，这里的是请求头的测试处理*/
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            writer.write( name + ":" + request.getHeader(name) + "<br/>");
            /*将数据先刷新到客户端之后再睡眠一会（注意：如果客户端使用的异步请求获取数据，则客户端必须等
            整个响应结束之后才会对数据进行处理）*/
            response.flushBuffer();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writer.write(new Date().toString());
        writer.close();
    }
}
