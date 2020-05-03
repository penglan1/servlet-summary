package pers.penglan.servletsummary.servlet.response;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Response Header
 *
 * <p>Servlet programmers are responsible for ensuring that the Content-Type header is
 * appropriately set in the response object for the content the servlet is generating. The
 * HTTP 1.1 specification does not require that this header be set in an HTTP response.
 * Servlet containers must not set a default content type when the servlet programmer
 * does not set the type</p>
 *
 * @Author PENGL
 */
@WebServlet("/response/header")
public class Header extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /*这里不能使用LinkedHashMap, 否则转换为数组之后被gson解析时会
        报错java.lang.StackOverflowError，使用HashMap时却不会，我怀疑
        有可能是因为LinkedHashMap.entrySet().toArray()返回的数组与
        HashMap.entrySet().toArray()返回的数组内部数据结构是不一样的。
        */
        Map<String, String> map = new HashMap<>(20);
        Writer writer = response.getWriter();

        Collection<String> collection = response.getHeaderNames();
        for (String name : collection) {
            map.put(name, response.getHeader(name));
        }

        Object[] arr = map.entrySet().toArray();
        System.out.println(Arrays.toString(arr));
        /*将map中的entry转换为数组的形式之后再以json的方式发送到前台*/
        String json = new Gson().toJson(arr);
        writer.write(json);
        response.flushBuffer();

        System.out.println("map: " + map);
        System.out.println("json: " + json);
    }
}
