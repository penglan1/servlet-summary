package pers.penglan.servletsummary.servlet.filter;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 理解DispatcherFilter
 *
 * @Author PENGL
 */

@WebServlet("/filter/dispatcher")
public class Dispatcher extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取当前request的dispatcher类型，这是一个枚举值，就是在web.xml的过滤器配置中的那个dispatcher元素*/
        DispatcherType dispatcherType = request.getDispatcherType();
        /*获取特定资源的过滤器链*/
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/response/buffer");
        requestDispatcher.include(request, response);

        /**
         * 还可以用ServletContext来获取dispatcher，但有区别，区别就是其path参数必须是以"/"开头
         */
        /*ServletContext servletContext = request.getServletContext();
        servletContext.getRequestDispatcher("/response/buffer");*/

    }
}
