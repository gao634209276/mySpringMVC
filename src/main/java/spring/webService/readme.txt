Web Services
Spring为标准Java web服务API提供了全面的支持:
	使用JAX-RPC暴露web服务
	使用JAX-RPC访问web服务
	使用JAX-WS暴露web服务
	使用JAX-WS访问web服务
Note
为什么有2个标准的Java web服务APIs?
	JAX-RPC 1.1 在J2EE 1.4 中是标准的web服务API。正像其名称所示，它关注于RPC绑定而且在最近几年越来越不流行。
	最终被Java EE 5中的JAX-WS 2.0所取代，JAX-WS 2.0不但在绑定方面更灵活，而且也是完全基于annotation的。
	JAX-WS 2.1也被包含在Java 6中(更详细的说是在Sun JDK 1.6.0_04和更高版本中，低版本的Sun JDK 1.6.0包含JAX-WS 2.0)，它与JDK内置的HTTP服务器集成。

	Spring 同时支持两个标准Java web服务API。选择谁主要看运行平台：
		在JDK 1.4 / J2EE 1.4上，唯一的选择是JAX-RPC。
		在Java EE 5 / Java 6上显然应该选JAX-WS。
		运行Java 5的J2EE 1.4环境上，你可以选择插入一个JAX-WS provider；请查看你的J2EE服务器文档。

	除了在Spring Core中支持JAX-RPC and JAX-WS，Spring portfolio也提供了一种特性Spring Web Services，
	一个为优先授权和文档驱动的web服务所提供的方案 - 非常建议用来创建高级并具有前瞻性的web服务。
	XFire是最后但不是唯一的Spring内置支持可以让你将Spring管理的bean暴露为web服务的方式。
