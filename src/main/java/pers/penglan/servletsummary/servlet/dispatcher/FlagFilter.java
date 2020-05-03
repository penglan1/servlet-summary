package pers.penglan.servletsummary.servlet.dispatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author PENGL
 * 2020-03-08
 */
@WebFilter(urlPatterns = "/*", dispatcherTypes = {
        DispatcherType.REQUEST, DispatcherType.ASYNC,
        DispatcherType.INCLUDE, DispatcherType.FORWARD,
        DispatcherType.ERROR}, asyncSupported = true)
public class FlagFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FlagFilter 初始化....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FlagFilter--进入---被执行...");
        chain.doFilter(request, response);
        System.out.println("FlagFilter--出去---被执行...");
    }

    @Override
    public void destroy() {
        System.out.println("FlagFilter 销毁....");
    }
}
