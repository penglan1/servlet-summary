# REST风格
对于URI的命名参考了REST的风格，但是并不完全遵循REST风格，因为无法做到完全的单个资源化。
因此只是将其结合进来。例如:
```html
后台处理：
servlet: /request/parameter
前端请求测试上面Servlet的页面：
html: /html/test/request/parameter.html
```

# Jrebel热部署
Jrebel通过对JVM进行代理，从而达到热部署的效果。
> 注意：对于被类加载器加载进入了虚拟机的类才会被jrebel管控。对于已经被加载的某个类，如果对某个类的源码进行修改之后，且重新编译以后，jrebel可以获取
到这个类的.class文件的变化，从而重新加载这个类进入虚拟机，以保持虚拟机中的这个类是最新的类。

# HTML中的路径问题
使用相对路径的带来的问题：
* 问题一： 资源的位置不可以随意更改。
* 问题二： 需要使用../的形式进行文件夹的跳出，使用麻烦。

## 使用绝对路径：
1. 使用方式：/虚拟项目名/项目资源路径。例如：
   ```html
   <a href="/sm/request/paramter">parameter process</a>
   <a href="/sm/html/test/request/parameter.html">parameter input</a>
   ```
2. 使用base标签来使用（推荐）
   ```html
   <base href="http://localhost/sm">
   <!--效果和上面的相同-->
   <a href="request/paramter"></a>
   <a href="html/test/request/parameter.html"></a>
   ```

# 项目中的文件路径问题
除了web项目中的url地址，关于文件的真实路径问题需要注意的是：
* 使用绝对路径问题：绝对路径的缺陷是项目所在服务器不能改变。
* 使用相对路径：相对路径的根目录得清楚，是相对于Java.exe启动时所在的工作目录。一般在IDE中启动项目时，java.exe的启动时所在
  的工作目录是项目文件夹。
  
# 有关MultipartConfig注解中location的问题
MultipartConfig注解由Servlet容器来管理，所以使用相对位置时，其位置是相对于当前应用的ServletContext的临时工作目录（最好保证目标位置文件夹已存在，
否则可能报错，这个视具体的容器而定：
![tomcat中的相对位置](https://note.youdao.com/yws/public/resource/177c2b39fdafc03e55d1685a10b72aa6/xmlnote/A75C94AE17024385961387BFFFB5BD19/33036)

> The location attribute of the javax.servlet.annotation.MultipartConfig and the <location> element of the
 <multipart-config> is interpreted as an absolute path and defaults to the value of the javax.servlet.context.tempdir. 
 If a relative path is specified, it will be relative to the tempdir location. 

# 使用Gson
利用Gson来对json进行转换，通常还可以对类对象进行序列化和反序列化。

# 字符输入输出流的编码问题
* Log4j2的输出信息时使用的编码？
  如果没有在配置文件中指定日志输出到文件时的编码，默认将会使用平台的编码。为了防止不同的平台编码不一致造成中文乱码问题，需要在
  配置文件指定输出日志时的编码。
* Servlet中的输出流使用的编码？
  如果在response中设定了响应的编码，则将使用指定的编码将内容输出到相应流中。如果没有设定响应的编码，则默认使用ISO-8859-1。
  > If the servlet does not specify a character encoding before the getWriter method of
    the ServletResponse interface is called or the response is committed, the default
    ISO-8859-1 is used.

# ServletContext获取资源
直接看图，也可以看源码中的注释
![大栗子来了哦](https://note.youdao.com/yws/public/resource/177c2b39fdafc03e55d1685a10b72aa6/xmlnote/D2ED9666C2F944859F5C2F7F10242609/33039)

# ServletResponse的flushBuffer方法
此方法可以将缓冲区中的数据刷新到客户端，如果客户端此时使用的不是异步请求获取方式，则可以直接看到刷新过来的数据，如果使用的是
异步请求的方式，则需要等到整个响应结束之后客户端才会对数据进行处理。

# Gson转换报错java.lang.StackOverFlowError[待解决]
```
//这个会报错
String json = new Gson().toJson(LinkedHashMap.entrySet().toArray());

//同样的内容，这个却不会报错
String json = new Gson().toJson(LinkedHashMap.entrySet().toArray());
```

# HttpSessionBindingListener的使用
此监听必须绑定到session属性中才可生效。绑定的方式（使用方式）：
* 实现此接口，创建对象
* 将该对象以属性的形式放入到需要监听的session中，一旦调用HttpSession.setAttribute方法绑定该对象之后，将触发其内部
  的valueBound方法

触发valueUnbound方法的条件如下：
* 调用了session的invalidate方法
* session超时，自动销毁
* 调用了session的setAttribute方法，将该对象给替换了
* 调用了session的removeAttribute方法，将该属性给移除了

# HttpSession的多线程问题
> 在同一时间多个 Servlet 执行请求的线程可能都有到同一会话的活跃访问。容器必须确保，以一种线程安全的方式维护表示会话属性
的内部数据结构。开发人员负责线程安全的访问属性对象本身。这样将防止并发访问 HttpSession 对象内的属性集合，消除了应用程
序导致破坏集合的机会。

# forward碰到了sendRedirect
如果forward指向的目标servlet中含有sendRedirect，**sendRedirect不起作用**，感觉被无视了一样

# 异步处理
如果servlet要进行异步处理，则需要添加配置从而支持异步处理，而且，沿途被request/response用过的过滤器也要被配置为支持异步处理。

# web应用的资源结构
原来WEB-INF/lib下的jar包中的META-INF/resources文件夹中的资源也可以直接访问，它也是静态文件存放位置的一部分，可以直接通过
请求来访问获得。
> A Web application exists as a structured hierarchy of directories. The root of this
  hierarchy serves as the document root for files that are part of the application. For
  example, for a Web application with the context path /catalog in a Web container,
  the index.html file at the base of the Web application hierarchy or in a JAR file
  inside WEB-INF/lib that includes the index.html under META-INF/resources
  directory can be served to satisfy a request from /catalog/index.html . If an
  index.html is present both in the root context and in the META-INF/resources
  directory of a JAR file in the WEB-INF/lib directory of the application, then the file
  that is available in the root context MUST be used.

> The /WEB-INF/lib/*.jar area for Java ARchive files. These files contain servlets,
  beans, static resources and JSPs packaged in a JAR file and other utility classes
  useful to the Web application.
  
> The Web application class loader must load classes from the WEB-INF/ classes
  directory first, and then from library JARs in the WEB-INF/lib directory. Also, except
  for the case where static resources are packaged in JAR files, any requests from the
  client to access the resources in WEB-INF/ directory must be returned with a
  SC_NOT_FOUND (404) response.
  
# welcome-file
原来如此，welcome-file-list标签还是有点作用的。哈哈
> If a Web container receives a valid partial request, the Web container must examine
  the welcome file list defined in the deployment descriptor. The welcome file list is an
  ordered list of partial URLs with no trailing or leading /. The Web server must
  append each welcome file in the order specified in the deployment descriptor to the
  partial request and check whether a static resource in the WAR is mapped to that
  request URI. If no match is found, the Web server MUST again append each
  welcome file in the order specified in the deployment descriptor to the partial
  request and check if a servlet is mapped to that request URI. The Web container
  must send the request to the first resource in the WAR that matches. The container
  may send the request to the welcome resource with a forward, a redirect, or a
  container specific mechanism that is indistinguishable from a direct request.
  
> If no matching welcome file is found in the manner described, the container may
  handle the request in a manner it finds suitable. For some configurations this may
  mean returning a directory listing or for others returning a 404 response.
  
# Use of URL Paths
> Upon receipt of a client request, the Web container determines the Web application 
  to which to forward it. The Web application selected must have the longest context 
  path that matches the start of the request URL. The matched part of the URL is the 
  context path when mapping to servlets. 
  The Web container next must locate the servlet to process the request using the path 
  mapping procedure described below. 
  The path used for mapping to a servlet is the request URL from the request object 
  minus the context path and the path parameters. The URL path mapping rules 
  below are used in order. The first successful match is used with no further matches 
  attempted: 
  
  1. The container will try to find an exact match of the path of the request to the path 
  of the servlet. A successful match selects the servlet. 
  2. The container will recursively try to match the longest path-prefix. This is done 
  by stepping down the path tree a directory at a time, using the ’ / ’ character as a 
  path separator. The longest match determines the servlet selected. 
  3. **If the last segment in the URL path contains an extension (e.g. .jsp ), the servlet 
  container will try to match a servlet that handles requests for the extension. An 
  extension is defined as the part of the last segment after the last ’ . ’ character.** 
  4. **If neither of the previous three rules result in a servlet match, the container will 
  attempt to serve content appropriate for the resource requested. If a "default" 
  servlet is defined for the application, it will be used.** Many containers provide an 
  implicit default servlet for serving content. 
  The container must use case-sensitive string comparisons for matching. 
  