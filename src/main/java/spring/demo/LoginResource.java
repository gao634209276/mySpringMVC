package spring.demo;

/**
 */
public class LoginResource {
	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void login() {
		loginService.login();
	}
}
