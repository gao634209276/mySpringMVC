专题1：
接口及面向接口编程
什么是ioc
spring的Bean配置
Bean的初始化
Spring的常用注入方式
-----------------------------------------------
1,面向接口编程：
结构设计中，分清层次以及调用关系，每层只向外（上层）提供一组功能接口，各层间仅依赖接口而非实现类
接口实现的变动不影响各层间的调用，这一点在公共服务中尤为重要
“面向接口编程”中的“接口”是用于隐藏具体实现和实现多态性的组件
例子：See: spring.ioc.interfaces.OneInterfaceImpl.main
2,什么是ioc
IOC:控制反转，控制权的转移，应用程序本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护
DI：（依赖注入）是一种实现方式
目的：创建对象并且组装对象之间的关系
	Your Business Object(POJOs)-->
	[(configuration Metadata)-->[The Spring Container]]
	--(produces)-->Fully configured system Ready for Use
理解：获得以来对象的过程被控制反转了，控制反转之后，获得依赖对象的过程由自身管理变为了有IOC容器主动注入。
于是“控制反转”更合适的名字叫“依赖注入 Dependency Injection”。实际上是实现IOC的方法：注入。
所谓的依赖注入，就是由ioc容器在运行期间，动态将某种以来关系注入到对象中。
运行过程：
	读取xml获取Context-->找ioc容器-->容器返回对象-->使用对象
Bean配置：
See: resources/ioc/ioc-set.xml
	<bean id="oneInterface" class="spring.ioc.interfaces.OneInterfaceImpl"/>
测试类：
	See: test/java/spring/ioc/TestOneInterface.testSay()


