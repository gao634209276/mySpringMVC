http://blog.csdn.net/ppt0501/article/details/46606745
1.4. 使用HTTP调用器暴露服务


与使用自身序列化机制的轻量级协议Burlap和Hessian相反，Spring HTTP调用器使用标准Java序列化机制来通过HTTP暴露业务。如果你的参数或返回值是复杂类型，并且不能通过Hessian和Burlap的序列化机制进行序列化，HTTP调用器就很有优势（参阅下一节，选择远程技术时的考虑）。

实际上，Spring可以使用J2SE提供的标准功能或Commons的HttpClient来实现HTTP调用。如果你需要更先进，更容易使用的功能，就使用后者。你可以参考jakarta.apache.org/commons/httpclient。
1.4.1. Exposing the service object

为服务对象设置HTTP调用器和你在Hessian或Burlap中使用的方式类似。就象为Hessian支持提供的 HessianServiceExporter，Spring的HTTP调用器提供了org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter。

为了在Spring Web MVC DispatcherServlet中暴露AccountService (如上所述)， 需要在dispatcher的应用上下文中使用以下配置：

<bean name="/AccountService" class="org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter">
    <property name="service" ref="accountService"/>
    <property name="serviceInterface" value="example.AccountService"/>
</bean>

和在Hessian章节讲的一样，这个exporter定义将通过 DispatcherServlet标准的映射工具暴露出来。

做为可选项, 在你的根应用上下文中(比如'WEB-INF/applicationContext.xml')创建一个HttpInvokerServiceExporter :

<bean name="accountExporter" class="org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter">
    <property name="service" ref="accountService"/>
    <property name="serviceInterface" value="example.AccountService"/>
</bean>

另外,在'web.xml'中为这个exporter定义一个相应的servlet，其名称与目标exporter bean的名称相匹配:

<servlet>
    <servlet-name>accountExporter</servlet-name>
    <servlet-class>org.springframework.web.context.support.HttpRequestHandlerServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>accountExporter</servlet-name>
    <url-pattern>/remoting/AccountService</url-pattern>
</servlet-mapping>

1.4.2. 在客户端连接服务


同样，从客户端连接业务与你使用Hessian或Burlap时所做的很相似。使用代理，Spring可以将你调用的HTTP POST请求转换成被暴露服务的URL。

<bean id="httpInvokerProxy" class="org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean">
    <property name="serviceUrl" value="http://remotehost:8080/remoting/AccountService"/>
    <property name="serviceInterface" value="example.AccountService"/>
</bean>

就象上面说的一样，你可以选择使用你想使用的HTTP客户端。缺省情况下，HttpInvokerProxy使用J2SE的HTTP功能，但是你也可以通过设置httpInvokerRequestExecutor属性选择使用Commons HttpClient：

<property name="httpInvokerRequestExecutor">
    <bean class="org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor"/>
</property>

