package pers.penglan.servletsummary.servlet.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 过滤器
 *
 * <p>使用部署文件的方式来部署这个过滤器</p>
 *
 * @Author PENGL
 */
public class CommonSetting implements Filter {
    private Logger logger = LogManager.getLogger("console");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> enumeration = filterConfig.getInitParameterNames();
        logger.info(">>begin");
        logger.info("过滤器" + this.getClass().getName() + "初始化中....");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            logger.info(name + ": " + filterConfig.getInitParameter(name));
        }
        logger.info(">>end");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*设置请求体的编码，默认统一设置为 UTF-8 */
        request.setCharacterEncoding("UTF-8");
        /*设置响应头，默认统一设置为 "text/html;charset=UTF-8" */
        response.setContentType("text/html;charset=UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("过滤器" + this.getClass().getName() + "销毁");
    }
}
