package pers.penglan.servletsummary.servlet.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试：调用另一个线程来异步处理业务逻辑
 *
 * @Author PENGL
 */
@WebServlet(value = "/dispatcher/async", asyncSupported = true)
public class Async extends HttpServlet {
    private Logger logger = LogManager.getLogger("console");

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync(request, response);
        logger.info("这里通过了");
        /*增加监听*/
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                logger.info("onComplete调用");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                logger.info("onTimeout调用，超时了");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                logger.info("onError调用，出错了");
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                logger.info("onStartAsync调用，这个也有机会啊！");
            }
        });
        asyncContext.setTimeout(4000);
        logger.info("这里也通过了");
        asyncContext.start(new Thread(new Process(asyncContext)));
        logger.info("处理工作已经交付给了业务线程");
    }
}
