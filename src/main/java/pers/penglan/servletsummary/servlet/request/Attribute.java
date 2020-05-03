package pers.penglan.servletsummary.servlet.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Attribute
 *
 * <pre>
 *     It is suggested that all attributes placed in the attribute set benamed
 *     in accordance with the reverse domain name convention suggested by the
 *     Java Programming Language Specification 1 for package naming
 * </pre>
 *
 * @Author PENGL
 */
@WebServlet(value = "/request/attribute")
public class Attribute extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入了Attribute Servlet");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("pers.penglan.name", "PENGL");
        request.setAttribute("pers.penglan.address", "江西省赣州市赣县区吉埠镇石含村");
        request.getRequestDispatcher("/request/attribute2").forward(request, response);
    }
}
