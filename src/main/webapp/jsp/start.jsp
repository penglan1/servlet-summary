
<%--
  @Author PENGL
  2020-02-29
--%>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Start JSP</title>
</head>
<body>
    <%!
        /*覆盖jspInit方法*/
        @Override
        public void jspInit() {
            ServletConfig servletConfig = getServletConfig();
            Enumeration<String> enumeration = servletConfig.getInitParameterNames();
            logger.info(">>" + this.getClass().getName() + "初始化....");
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                logger.info(name + " : " + servletConfig.getInitParameter(name));
            }
            logger.info(">>" + this.getClass().getName() + "初始化完成");
        }
    %>
    <%!
        /**
         * 这个标签中的代码等价于目标servlet中的全局声明，可以是属性域的声明，也可以是方法的声明
         * 注意：用到的外部的类，需要通过page指令中的import方式导入进来，就像是我们写java文件的代码一样
         */
        private Logger logger = LogManager.getLogger("console");
    %>

    <%
        /**
         * 这个标签中的代码会被直接放置在转换后的servlet的service方法中执行，注意：是当作语句来执行。
         */
        logger.info(">>begin");
        logger.info("你好，JSP，休想蒙我，你就是一个Servlet");
        logger.info(">>end");
    %>
    <%
        /**
         * 以下代码将转换为如下：
         * <code><pre>
         *     response.getWriter().write("<h1>这里面只能是一个java的表达式</h1>");
         * </pre></code>
         */
    %>
    <%="<h1>这里面只能是一个java的表达式</h1>"%>


</body>
</html>