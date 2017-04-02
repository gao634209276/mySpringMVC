Spring mvc常用注解
1. 注册注解处理器
命名空间<context:component-scan/>
	首先，如果要使注解工作，则必须配置component-scan。该配置的功能为：
	启动包扫描功能，以便注册带有@Controller、@Service、@repository、@Component等注解的类成为spring的bean。
	例：<context:component-scan base-package="com.tgb.web"/>
	base-package 属性指定了需要扫描的类包，类包及其递归子包中所有的类都会被处理。还允许定义过滤器将基包下的某些类纳入或排除。
Spring支持以下4种类型的过滤方式：
	1)注解org.example.SomeAnnotation 将所有使用SomeAnnotation注解的类过滤出来
	2)类名指定org.example.SomeClass 过滤指定的类
	3)正则表达式com.kedacom.spring.annotation.web..* 通过正则表达式过滤一些类
	4)AspectJ 表达式 org.example..*Service+ 通过AspectJ 表达式过滤一些类
	正则表达式的过滤方式举例：
		<context:component-scan base-package="spring.xxx">
			<context:exclude-filter type="regex" expression="spring.xx..*"/>
		<context:component-scan>
	注解的过滤方式举例：
		<context:component-scan base-package="spring.xxx">
			<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
			<context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
			<context:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
		<context:component-scan>
2.启动Spring MVC 的注解功能，完成请求和注解POJO 的映射
2.1 @Controller—表示控制器
	举例：
	@Controller
	public class SoftCreateController extendsSimpleBaseController {}
	或者：
	@Controller("softCreateController")
	说明：@Controller负责注册一个bean到spring上下文中，bean的ID默认为类名称开头字母小写
	注意：
	和Struts1一样，Spring的Controller是Singleton的。这就意味着会被多个请求线程共享。
	因此，我们将控制器设计成无状态类。
	在spring 3.0中，通过@controller标注即可将class定义为一个controller类。
	有以上可知，为使spring能找到定义为controller的bean,需要在spring-context配置文件中增加如下定义：
	<context:component-scan base-package="com.sxt.web"/>
	注：实际上，使用@component（下面有介绍），也可以起到@Controller同样的作用。

----------------------------------------------------------------------------------------------------------------------
2.2  @Service—表示业务处理层[一般在serviceImpl]
	举例：　　　　
	@Service
	public class SoftCreateServiceImpl implements ISoftCreateService{}
	或者：
	@Service("softCreateServiceImpl")
	说明：@Service负责注册一个bean到spring上下文中，bean的ID默认为类名称开头字母小写


----------------------------------------------------------------------------------------------------------------------
2.3 @Repository—表示持久层[一般在daoImpl]
	与@Controller、@Service类似，都是向spring上下文中注册bean，不在赘述。

----------------------------------------------------------------------------------------------------------------------
2.4 @Component（不推荐使用）—当你的类不清楚是哪一层的时候使用该注解
	@Component
	@Component是所有受Spring管理组件的通用形式，Spring还提供了更加细化的注解形式： @Repository、@Service、@Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。
	目前版本（2.5）中，这些注解与@Component的语义是一样的，完全通用，在Spring以后的版本中可能会给它们追加更多的语义。所以，我们推荐使用@Repository、@Service、@Controller来替代@Component。
----------------------------------------------------------------------------------------------------------------------
2.5 @Resource
	例如：
	@Resource
	private DataSource dataSource; // injectthe bean named 'dataSource'
	或者：
	@Resource(name="dataSource")
	@Resource(type=DataSource.class)
	说明：
	@Resource默认按bean的name进行查找，如果没有找到会按type进行查找，此时与@Autowired类似
	在没有为 @Resource注解显式指定 name属性的前提下，如果将其标注在BeanFactory类型、ApplicationContext类型、ResourceLoader类型、ApplicationEventPublisher类型、MessageSource类型上，那么 Spring 会自动注入这些实现类的实例，不需要额外的操作。此时name属性不需要指定(或者指定为"")，否则注入失败；
----------------------------------------------------------------------------------------------------------------------
2.6 @Autowired
	例如：
	@Autowired
	private ISoftPMService softPMService;
	或者：
	@Autowired(required=false)
	private ISoftPMService softPMService = newSoftPMServiceImpl();
	说明：
	Ø @Autowired根据bean类型从spring上线文中进行查找，注册类型必须唯一，否则报异常。与@Resource的区别在于，@Resource允许通过bean名称或bean类型两种方式进行查找@Autowired(required=false)表示，如果spring上下文中没有找到该类型的bean时，才会使用new SoftPMServiceImpl();
	Ø @Autowired标注作用于 Map类型时，如果 Map的 key 为 String类型，则 Spring会将容器中所有类型符合 Map的 value 对应的类型的Bean增加进来，用 Bean的 id或 name作为 Map的 key。
	Ø @Autowired还有一个作用就是，如果将其标注在 BeanFactory类型、ApplicationContext类型、ResourceLoader类型、ApplicationEventPublisher类型、MessageSource类型上，那么 Spring会自动注入这些实现类的实例，不需要额外的操作。
----------------------------------------------------------------------------------------------------------------------
2.7 @RequestMapping
	类：
		@Controller
		@RequestMapping("/bbtForum.do")
		public class BbtForumController{
			@RequestMapping(params = "method=listBoardTopic")
			public String listBoardTopic(int topicId,User user){}
		}
	方法：
		@RequestMapping("/softpg/downSoftPg.do")
		@RequestMapping(value="/softpg/downSoftPg.do",method = POST)
		@RequestMapping(value="/osu/product/detail.do",params = {"modify=false"},method=POST)
	说明：
	@RequestMapping可以声明到类或方法上
----------------------------------------------------------------------------------------------------------------------
2.8 @RequestParam
	说明：一般用于将指定的请求参数付给方法中形参。
	@RequestMapping(params="method=reg5")
	public String reg5(@RequestParam("name")String uname,ModelMap map) {}
	这样，就会将name参数的值付给uname。当然，如果请求参数名称和形参名称保持一致，则不需要这种写法。

----------------------------------------------------------------------------------------------------------------------
2.9 @Scope
	例如：
	@Scope("session")
	@Repository()
	public class UserSessionBean implementsSerializable{}
	说明：在使用XML定义Bean时，可以通过bean的scope 属性来定义一个Bean的作用范围，同样可以通过@Scope注解来完成。
	@Scope中可以指定如下值：
		singleton:定义bean的范围为每个spring容器一个实例（默认值）
		prototype:定义bean可以被多次实例化（使用一次就创建一次）
		request:定义bean的范围是http请求（springMVC中有效）
		session:定义bean的范围是http会话（springMVC中有效）
		global-session:定义bean的范围是全局http会话（portlet中有效）

----------------------------------------------------------------------------------------------------------------------
2.10 @SessionAttributes
	说明：Spring允许我们有选择地指定 ModelMap中的哪些属性需要转存到 session中，以便下一个请求属对应的 ModelMap的属性列表中还能访问到这些属性。
	这一功能是通过类定义处标注 @SessionAttributes注解来实现的。@SessionAttributes只能声明在类上，而不能声明在方法上。
	例如：
	@SessionAttributes("currUser") //将ModelMap中属性名为currUser的属性
	@SessionAttributes({"attr1","attr2"})
	@SessionAttributes(types = User.class)
	@SessionAttributes(types = {User.class,Dept.class})
	@SessionAttributes(types ={User.class,Dept.class},value={"attr1","attr2"})
	实例：
		@Controller
		@RequestMapping("/user.do")
		@SessionAttributes({"u","a"})//将ModelMap中属性名字为u,a的再放入session中。
		public class UserController{
			@RequestMapping(params="method=reg4")
			public String reg4(ModelMap map){
				System.out.println("HelloController.handleRequest()");
				map.addAttribute("u","uuuu");//将u放入request作用域中，这样转发页面也可以取到这个数据。
				return "index";
			}
		}
		<body>
		<h1>xxxx${requestScope.u.uname}</h1>
		<h1>xxxx${requestScope.u.uname}</h1>
		</body>
----------------------------------------------------------------------------------------------------------------------
2.11 @ModelAttribute
	这个注解可以跟@SessionAttributes配合在一起用。可以将ModelMap中属性的值通过该注解自动赋给指定变量。
	示例代码如下：
		@Controller
		@RequestMapping("/user.do")
		@SessionAttributes({"u","a"})
		public class UserController  {

			@RequestMapping(params="method=reg4")
			public String reg4(ModelMap map) {
			   System.out.println("HelloController.handleRequest()");
			   map.addAttribute("u","kobe");
			   return "index";
			}

			@RequestMapping(params="method=reg5")
		//将属性u的值赋给形参uname
		public String reg5(@ModelAttribute("u")String uname,ModelMap map) {
			   System.out.println("HelloController.handleRequest()");
			   System.out.println(uname);
			   return "index";
			}
		}
----------------------------------------------------------------------------------------------------------------------
2.12 @InitBinder
	说明：如果希望某个属性编辑器仅作用于特定的 Controller，可以在 Controller中定义一个标注 @InitBinder注解的方法，可以在该方法中向 Controller了注册若干个属性编辑器
	例如：
		@InitBinder
		pulic void initBinder(WebDataBinder binder){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
		}
----------------------------------------------------------------------------------------------------------------------
2.13 @Required
	例如：
	@required
	public setName(String name){}
	说明：
	@ required负责检查一个bean在初始化时其声明的 set方法是否被执行，当某个被标注了 @Required的 Setter方法没有被调用，则 Spring在解析的时候会抛出异常，以提醒开发者对相应属性进行设置。 @Required注解只能标注在 Setter方法之上。因为依赖注入的本质是检查 Setter方法是否被调用了，而不是真的去检查属性是否赋值了以及赋了什么样的值。如果将该注解标注在非 setXxxx()类型的方法则被忽略。


 ----------------------------------------------------------------------------------------------------------------------
2.14 @Qualifier
	例如：
	@Autowired
	@Qualifier("softService")
	private ISoftPMService softPMService;
	说明：
	使用@Autowired时，如果找到多个同一类型的bean，则会抛异常，此时可以使用 @Qualifier("beanName")，明确指定bean的名称进行注入，此时与 @Resource指定name属性作用相同。

----------------------------------------------------------------------------------------------------------------------
2.15 @ResponseBody
	这个注解可以直接放在方法上，表示返回类型将会直接作为HTTP响应字节。流输出(不被放置在Model，也不被拦截为视图页面名称)。可以用于ajax。

3.    常用点
3.1 Controller类中方法返回值的处理
	1.返回string(建议)
	a)根据返回值找对应的显示页面。路径规则为：prefix前缀+返回值+suffix后缀组成
	b)代码如下：
		@RequestMapping(params="method=reg4")
		public String reg4(ModelMap map){
			System.out.println("HelloController.handleRequest()");
			return "index";
		}
		前缀为：/WEB-INF/jsp/ 后缀为：.jsp
		再转发到：/WEB-INF/jsp/index.jsp
	2.也可以返回ModelMap、ModelAndView、map、List、Set、Object、无返回值。一般建议返回字符串！
3.2 请求转发和重定向
代码：
	@Controller
	@RequestMapping("/user.do")
	public class UserController{
		@RequestMapping(params="method=reg4")
		public String reg4(ModelMap map){
			System.out.println("HelloController.handlerRequest()");
			//return "forward:index.jsp";
			//return "forward:user.do?method=reg5";//转发
			return "redirect:http://www.baidu.com";//重定向
		}
		@RequestMapping(params="method=reg5")
		public String reg5(String uname,ModelMap map){
			System.out.println("HelloController.handlerRequest()");
			System.out.println(uname);
			return "index";
		}
	}
	访问reg4方法，既可以看到效果。

3.3 获得request对象、session对象
普通的Controller类，示例代码如下：
	@Controller
	@RequestMapping("/user.do")
	public class UserController{
		@RequestMapping(params="method=reg2")
		public String reg2(String uname,HttpServletRequest req,ModelMap map){
			req.setAttribute("a","aa");
			req.getSession().setAttribute("b","bb");
			return "index";
		}
	}

4.总结
SpringMVC是一个基于DispatcherServlet的MVC框架，每一个请求最先访问的都是DispatcherServlet，
DispatcherServlet负责转发每一个Request请求给相应的Handler，
Handler处理以后再返回相应的视图(View)和模型(Model)，返回的视图和模型都可以不指定，即可以只返回Model或只返回View或都不返回。
在使用注解的SpringMVC中，处理器Handler是基于@Controller和@RequestMapping这两个注解的，
@Controller声明一个处理器类，@RequestMapping声明对应请求的映射关系，这样就可以提供一个非常灵活的匹配和处理方式。




