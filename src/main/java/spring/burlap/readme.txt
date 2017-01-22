http://blog.csdn.net/zhu_tianwei/article/details/44025309
1.3.4. 使用Burlap
我们将不会详细讨论Burlap，它是一个基于XML的Hessian替代方案。它的配置和构建方法和上述Hessian的一样。只要把 Hessian 换成 Burlap 就行了。
1.3.5. 对通过Hessian或Burlap暴露的服务使用HTTP Basic认证


Hessian和Burlap的一个优势是我们可以容易的使用HTTP Basic认证，因为二者都是基于HTTP的。例如，普通HTTP Server安全机制可以通过使用 web.xml 安全特性来应用。通常，你不会为每个用户都建立不同的安全证书，而是在Hessian/BurlapProxyFactoryBean级别共享安全证书（类似一个JDBC DataSource）。

<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
    <property name="interceptors" ref="authorizationInterceptor"/>
</bean>

<bean id="authorizationInterceptor"
      class="org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor">
    <property name="authorizedRoles" value="administrator,operator"/>
</bean>

这个例子里我们显式使用了BeanNameUrlHandlerMapping，并设置了一个拦截器，只允许管理员和操作员调用这个应用上下文中提及的bean。
	Note

当然，这个例子没有演示灵活的安全设施。考虑更多有关安全的问题时，请参阅 http://acegisecurity.sourceforge.net Acegi Security System for Spring
。