spring核心组件包括IOC和AOP，我们主要学习的就是这俩个模块

(1)、Spring Core(IOC) 核心容器,提供组件的创建、装备、销毁
(2)、Spring Context Spring上下文,是一个接口ApplicationContext(继承自BeanFactory接口)的实现
(3)、Spring Web容器,web应用上下文,是webApplicationContext接口的实现
(4)、Spring DAO容器,是SpringDAO 支持模块,是为了简化DAO的使用
(5)、Spring ORM
(6)、Spring AOP,对AOP编程支持的模块
(7)、Spring MVC,类似于Spring表示层的一个框架

我今天学习的就是IOC模块~

现在简单的来介绍一下IOC~
IOC:Inversion of Control 控制反转
	一种说法:对象之间的依赖关系,由容器在运行时依据配置文件动态的建立
	另一种说法:对象的控制器转移了,转到外部容器了,避免了代码的纠缠,代码更容易被维护,模板之间的耦合性降低,容易测试
	IoC 控制反转意味着将你设计好的类交给容器去控制,而不是在类的内部进行控制,即控制权由应用代码中转到了外部容器

IOC包括两部分内容
	DI:Dependency Injection依赖注入,组件不做定位查询,只提供相应方法,由容器创建对象,并调用相应方法设置所需对象需要的组件
	DL:Dependency Loopup依赖查找,容器创建对象并提供回调接口和上下文环境给组件,需要时通过接口从容器中查找对象
	依赖查找,现在使用不太多。(EJB使用的更多,将对象创建好后,放到容器中。)

IOC解决:对象谁来创建的问题
	DI解决:对象间的关系如何建立的问题。
	org.springframework.beans及org.springframework.context包是IOC容器的基础

SpringIOC核心api
	1.BeanFactory接口和容器
	BeanFactory是Spring中Bean容器,IOC的核心接口,主要用于处理Bean的初始化和配置,建立对象间的依赖关系
	定义了如下方法:
		Object getBean(String name) //根据指定名称返回一个Bean实例
		<T> T getBean(Class<T> requiredType)
								   //返回一个与给定Class唯一匹配的Bean实例
		<T> T getBean(String name, Class<T> requiredType)
		Object getBean(String name, Object... args)
		Class<?> getType(String name)       //得到名称为name的Bean的Class对象
		boolean isPrototype(String name)   //判断名称为name的Bean是否是原型,
		boolean isSingleton(String name)   //判断名称为name的Bean是否是单例
		boolean containsBean(String name)  //判断是否包含给定名称的Bean实例
		boolean isTypeMatch(String name, Class<?> targetType)
							  //判断名称为name的Bean实例是否为targetType类型
		String[] getAliases(String name)  //如果名称为name的Bean有别名返回
	通过getBean方法便可以得到相应的类实例,但是最好永远不调用,而使用注入,避免对Spring API的依赖
	在Spring中,同一Spring容器中的bean默认情况下是Singleton(单例),将在bean的作用域介绍.

	2.ApplicationContext接口
	该接口继承于BeanFactory,增强了BeanFactory,提供了事务处理AOP,国际化,事件传递
	所以在代码中我们一般会使用ApplicationContext接口,以及这个接口相应的实现类来创建spring的容器对象。
	例如:
	String path = "com/briup/ioc/set/set.xml";
	ApplicationContext container = new ClassPathXmlApplicationContext(path);

--------------------------------------------------------------------------------------
IOC注入的方式有好几种，现在就来学习一下set方式注入~
可以注入的内容有：
1.基本类型(8中基本类型+字符串)的装配
2.对象类型的装配
3.集合的装配
现在就来讲讲怎么用set方式注入的吧~
1.基本类型的装配：
配置文件 see: spring-ioc.xml:
	id是Bean的唯一标识,要求在整个配置文件中要唯一,也可使用name属性,
		bean标签里面的id和name属性都可以用来标识这个配置的对象,
		但是id会帮我们检查给对象起的名字是否规范(名字不能重复、不能用数字开头、不能有空格等等),如果检查出来了那么就会报错。
		name属性不会帮检查这些东西。
	annotation.property 对于所有用set方式来注入的必须该标签
	value是对以基本类型,都用value(标签/属性)来注入,可以实现自动的数据类型转换

2.对象类型的装配：
	(1)、<ref local=" "/> 用于涉及的对象的id在当前配置文件中(用于在本配置文件中配置了bean的引入,同ref="..")
	(2)、<ref ioc.bean=" "/>  用于涉及的对象的id不在本配置文件中(用于引用不在本配置文件中配置的bean)
	(3)、使用property的ref属性引用

3.集合的装配：
方式:配置元素<list> <set> <map> <props>
--------------------------------------------------------------------------------------
上一篇写了IOC注入方法中的set方法，这一篇就说一下基于构造器的注入~
基于构造器注入的方式: 配置<constructor-arg>元素
注意：在Bean中不用写set方法,但是要有相应的构造器

构造器注入有俩种形式 ,一个是根据参数类型 ,一个是根据参数位置的下标
<constructor-arg type="int" value="">
<constructor-arg  index="0" value="">
那就先开始说第一种形式：根据参数类型的构造器注入
1.先写Bean类-Student类：

http://blog.csdn.net/qq_33642117/article/category/6302171