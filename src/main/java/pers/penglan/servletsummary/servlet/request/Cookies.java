package pers.penglan.servletsummary.servlet.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookies
 *
 * @Author PENGL
 */
@WebServlet("/request/cookies")
public class Cookies extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            logger.info(">>begin");
            for (Cookie cookie : cookies) {
                /*对于获取到的cookie, 需要对其进行解码，将非Ascii字符解码回来*/
                logger.info(cookie.getName() + ":" + URLDecoder.decode(cookie.getValue(), "UTF-8"));
            }
            logger.info(">>end");
        }
        //添加Cookie到客户端
        Cookie cookie = new Cookie("name", "PENGL");
        /*cookie.setDomain();*/
        /*path包含了servletContext*/
        cookie.setPath("/sm/request");
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(true);
        cookie.setComment("这个用来保存用户的名字");
        /*对于非Ascii，不能直接对其进行存储，需要先对其进行编码*/
        Cookie cookie1 = new Cookie("address", URLEncoder.encode("江西省赣州市赣县区吉埠镇","UTF-8"));
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        response.addCookie(cookie1);
    }
}
