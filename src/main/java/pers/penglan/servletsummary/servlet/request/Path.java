package pers.penglan.servletsummary.servlet.request;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.penglan.servletsummary.util.servlet.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/request/path/*")
public class Path extends HttpServlet {
    /*注意，引入的是org.apache.logging.log4j.Logger，这个是支持2.x的新接口*/
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求体的编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        RequestUtil requestUtil = new RequestUtil(request);

        Map<String, String> map = new HashMap<>(20);
        String contextPath = request.getContextPath();  // 经过了解码的
        map.put("ContextPath", contextPath);
        String servletPath = request.getServletPath();  // 经过了解码的
        map.put("ServletPath", servletPath);
        String pathInfo = request.getPathInfo();  // 经过了解码的
        map.put("pathInfo", pathInfo);
        String requestURI = request.getRequestURI();  // 没有经过解码
        map.put("RequestURI", requestURI +"（未解码的）");
        String decodedURI = requestUtil.getDecodedURI(false);
        map.put("RequestURI2", decodedURI + "（已解码的）");
        String decodedURI1 = requestUtil.getDecodedURI(true);
        map.put("RequestURI3", decodedURI1 + "（已解码的，且带有QueryString）");
        String requestURL = request.getRequestURL().toString();  // 没有经过解码
        map.put("RequestURL", requestURL + "（未解码的）");
        String decodedURL = requestUtil.getDecodedURL(false);
        map.put("RequestURL2", decodedURL + "（已解码的）");
        String decodedURL1 = requestUtil.getDecodedURL(true);
        map.put("RequestURL3", decodedURL1 + "（已解码的，且带有QueryString）");
        String queryString = request.getQueryString();  // 没有经过解码
        map.put("QueryString", queryString + "（未解码的）");
        String decodedQryString = requestUtil.getDecodedQueryString();
        map.put("QueryString2", decodedQryString + "（已解码的）");

        logger.info(">>begin");
        logger.info("---------------");
        logger.info("ContextPath:" + contextPath);
        logger.info("ServletPath:" + servletPath);
        logger.info("PathInfo:" + pathInfo);
        logger.info("RequestURI:" + requestURI);
        logger.info("DecodedURI:" + decodedURI);
        logger.info("DecodedURI:" + decodedURI1);
        logger.info("RequestURL:" + requestURL);
        logger.info("DecodedURL:" + decodedURL);
        logger.info("DecodedURL:" + decodedURL1);
        logger.info("QueryString:" + queryString);
        logger.info("DecodedQryString:" + decodedQryString);

        String URI = contextPath + servletPath + pathInfo;  // pathInfo 有可能为 null
        boolean isRight = requestURI.equals(URI);  // 如果pathInfo为null, 则为 false
        logger.info("URI = contextPath + servletPath + pathInfo==> " + URI +"==> " + isRight);
        map.put("test", "URI = contextPath + servletPath + pathInfo==> " + URI +"==> " + isRight);

        Gson gson = new Gson();
        String json = gson.toJson(map.entrySet().toArray());
        logger.info("json: " + json);
        logger.info(">>end");
        response.getWriter().write(json);
    }
}
