package pers.penglan.servletsummary.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 测试Resource获取方式
 *
 * <p>根据request请求中指定的资源名称，返回指定的资源</p>
 *
 * <p>注意：该资源名称未路径名称，客户端输入资源名称时，必须知道服务器中的资源位置位于哪里，以“/”开头，
 * getResource 和 getResourceAsStream 方法需要一个以“/”开头的 String 字符串作为参数，给定的资源路径
 * 是相对于上下文的根，或者相对于 web 应用的 WEB-INF/lib 目录下的 JAR 文件中的 META-INF/resources
 * 目录。这两个方法首先根据请求的资源查找 web 应用上下文的根，然后查找所有 WEB-INF/lib 目录下的 JAR
 * 文件</p>
 *
 * <p><Strong>此处暂时只能获取文本资源</Strong></p>
 *
 * @Author PENGL
 */
@WebServlet("/context/resource")
public class GetResource extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取资源路径
        String path = request.getParameter("resourcePath");
        //返回资源
        ServletContext servletContext = request.getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream(path);
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 1000);
            BufferedWriter bufferedWriter = new BufferedWriter(response.getWriter(), 1000);
            while (bufferedReader.ready()) {
                bufferedWriter.write(bufferedReader.readLine() + "\n");
            }
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();
        } else {
            response.getWriter().write("指定的文本资源不存在");
        }

        System.out.println(servletContext.getAttribute("javax.servlet.context.tempdir"));
    }
}
