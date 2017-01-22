Spring为各种远程访问技术的集成提供了整合类。
Spring使得开发具有远程访问功能的服务变得相当容易，而这些远程访问服务由普通Spring POJO实现。
目前，Spring支持四种远程技术：
	1,远程方法调用（RMI）
		通过使用 RmiProxyFactoryBean 和 RmiServiceExporter，
		Spring同时支持传统的RMI（使用java.rmi.Remote接口和java.rmi.RemoteException）
		和通过RMI调用器实现的透明远程调用（支持任何Java接口）。
	2,Spring的HTTP调用器
		Spring提供了一种允许通过HTTP进行Java串行化的特殊远程调用策略，它支持任意Java接口（就像RMI调用器）。
		相对应的支持类是HttpInvokerProxyFactoryBean 和 HttpInvokerServiceExporter。
	3,Hessian
		通过 HessianProxyFactoryBean 和 HessianServiceExporter，
		可以使用Caucho提供的基于HTTP的轻量级二进制协议来透明地暴露服务。
	4,Burlap
		Burlap是Caucho基于XML用来替代Hessian的项目。
		Spring提供了诸如 BurlapProxyFactoryBean 和 BurlapServiceExporter 的支持类。
	5,JAX RPC
		Spring通过JAX-RPC（J2EE 1.4's wweb service API）为Web services提供远程服务支持。
	6,JAX-WS.
		Spring通过(在Java EE 5和Java 6中引入的JAX-RPC继承)为远程Web Services提供支持。
	7,JMS
		通过JmsInvokerServiceExporter和JmsInvokerProxyFactoryBean使用JMS做为底层协议提供远程服务.

在讨论Spring对远程访问的支持时，我们将使用下面的域模型和对应的服务：

public class Account implements Serializable{

    private String name;

    public String getName();

    public void setName(String name) {
      this.name = name;
    }
}

public interface AccountService {

    public void insertAccount(Account account);

    public List getAccounts(String name);
}

public interface RemoteAccountService extends Remote {

    public void insertAccount(Account account) throws RemoteException;

    public List getAccounts(String name) throws RemoteException;
}

// 该实现目前什么事情也不做
public class AccountServiceImpl implements AccountService {

    public void insertAccount(Account acc) {
        //  做一些事情……
    }

    public List getAccounts(String name) {
        // 做一些事情……
    }
}

我们将从使用RMI把服务暴露给远程客户端开始，同时探讨RMI的一些缺点。然后我们将演示一个使用Hessian的例子。