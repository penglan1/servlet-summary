package pers.penglan.servletsummary.servlet.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 获取Attribute.java（/request/attribute)中request的属性
 * <pre>
 *      属性名：
 *           ① "pers.penglan.name"
 *           ② "pers.penglan.address"
 * </pre>
 *
 * @Author PENGL
 */
@WebServlet(value = "/request/attribute2")
public class Attribute2 extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Writer writer = response.getWriter();
        String name = (String) request.getAttribute("pers.penglan.name");
        String address = (String) request.getAttribute("pers.penglan.address");
        writer.write("{\"name\": \"" + name + "\",\"address\": \"" + address + "\"}");
        /*这个close如果执行了，意味着响应提交了*/
        //writer.close();
        logger.info("name:" + name);
        logger.info("address:" + address);
    }
}
