<%--
  @Author PENGL
  2020-02-29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Scope And Attribute</title>
</head>
<body>
    <%
        /**
         * 测试从pageContext, request, session, application四个作用域中获取属性对应的值
         * 先在不同的作用域中设置不同的值
         */
        pageContext.setAttribute("info", "我在设置的时候没有指定作用域");
        pageContext.setAttribute("info", "我在request中", PageContext.REQUEST_SCOPE);
        pageContext.setAttribute("info", "我在session中", PageContext.SESSION_SCOPE);
        pageContext.setAttribute("info", "我在application中", PageContext.APPLICATION_SCOPE);
        pageContext.setAttribute("notify", "我只在application中", PageContext.APPLICATION_SCOPE);
    %>
    <%--
    pageContext.findAttribute(paramName)
    Searches for the named attribute in page, request, session (if valid),
    and application scope(s) in order and returns the value associated or
    null.
    --%>
    <span>info:</span><%=pageContext.findAttribute("info")%><br/>
    <%--
    pageContext.getAttribute(paramName)
    Returns the object associated with the name in the page scope or null if not found.
    --%>
    <span>info:</span><%=pageContext.getAttribute("info")%><br/>
    <%--
    pageContext.getAttribute(paramName, scope)
    eturn the object associated with the name in the specified
    scope or null if not found.
    --%>
    <span>info:</span><%=pageContext.getAttribute("info", PageContext.REQUEST_SCOPE)%><br/>
    <span>info:</span><%=pageContext.getAttribute("info", PageContext.SESSION_SCOPE)%><br/>
    <span>info:</span><%=pageContext.getAttribute("info", PageContext.APPLICATION_SCOPE)%><br/>
    <span>notify:</span><%=pageContext.getAttribute("notify")%><br/>
    <span>notify:</span><%=pageContext.findAttribute("notify")%><br/>


</body>
</html>