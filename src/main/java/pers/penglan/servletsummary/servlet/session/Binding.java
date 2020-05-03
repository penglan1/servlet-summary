package pers.penglan.servletsummary.servlet.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Binding attributes into a session
 *
 * <p>一 些对象可能需要在它们被放进会话或从会话中移除时得到通知。这些信息可以
 * 从HttpSessionBindingListener 接口实现的对象中获取。这个接口定义了以下方法，用于标识
 * 一个对象被绑定到会话或从会话解除绑定时</p>
 *
 * <p>HttpSessionBindingListener和HttpSessionListener之间的最大区别：HttpSessionListener只需
 * 要设置到web.xml中就可以监听整个应用中的所有session。HttpSessionBindingListener必须实例化后放入
 * 某一个session中（解释：例如 session.setAttribute("onlineUserBindingListener", new OnlineUserBindingListener(username));
 * 把我们自定义的监听类存储到session中，这个监听器就像“寄生虫”一样赋于该session上），才可以进行监听，绑定之后就会调用valueBound方法</p>
 *
 * <p>valueUnbound的触发条件是以下三种情况:
 * 1. 执行session.invalidate()时。
 * 2. session超时，自动销毁时。
 * 3. 执行session.setAttribute(“onlineUserListener”, “其他对象”), 或session.removeAttribute(“onlineUserListener”)将
 * listener从session中删除时。因此，只要不将listener从session中删除，就可以监听到session的销毁。）。</p>
 *
 * @Author PENGL
 */

public class Binding implements HttpSessionBindingListener {
    private Logger logger = LogManager.getLogger("console");

    /**
     * 调用HttpSession.setAttribute(...)之前，这个方法会被先调用
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        logger.info("session属性绑定--》" + event.getName() + ":" + event.getValue());
    }

    /**
     * 调用HttpSession.removeAttribute(...)之后，这个方法会被调用
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logger.info("session属性解绑--》" + event.getName() + ":" + event.getValue());
    }
}
