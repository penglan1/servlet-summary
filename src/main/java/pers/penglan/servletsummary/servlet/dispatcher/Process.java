package pers.penglan.servletsummary.servlet.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 专门用于异步处理业务
 *
 * @Author PENGL
 */
public class Process implements Runnable{
    private Logger logger = LogManager.getLogger("console");

    private AsyncContext asyncContext = null;
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    public Process(AsyncContext asyncContext) {
        logger.info("Process构造执行");
        this.asyncContext = asyncContext;
        request = (HttpServletRequest) asyncContext.getRequest();
        response = (HttpServletResponse) asyncContext.getResponse();
        logger.info("构造完成");
    }

    @Override
    public void run() {
        logger.info(">>begin");
        logger.info("开始处理业务...");
        request.setAttribute("process", "今天的任务已经完成了，明天继续加油！！！");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(">>end");
        try {
            response.getWriter().write("业务逻辑中的输出<br/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //这里的flag参数具有优先被权，既可以覆盖其他的同名参数，所以这个flag参数一定可以被获取到
        asyncContext.dispatch("/dispatcher/display?flag=1");
    }
}
