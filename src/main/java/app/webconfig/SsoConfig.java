package app.webconfig;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chok.sso.filter.AuthFilter;
import chok.sso.filter.LoginFilter;
import chok.sso.filter.LogoutFilter;
import chok.sso.filter.PasswordFilter;

//@Configuration
public class SsoConfig
{
	@Bean
	public FilterRegistrationBean loginFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new LoginFilter());
		filterRegBean.setUrlPatterns(Arrays.asList("/admin/*"));
		filterRegBean.addInitParameter("ssoURL", "http://localhost:8585/sso");
		filterRegBean.addInitParameter("ssoAuthURL", "http://localhost:8585/sso/auth");
		filterRegBean.addInitParameter("ignoreURL", "/noaccess.jsp");
		filterRegBean.setOrder(1);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean logoutFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new LogoutFilter());
		filterRegBean.setUrlPatterns(Arrays.asList("/sso/logout.action"));
		filterRegBean.setOrder(2);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean passwordFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new PasswordFilter());
		filterRegBean.setUrlPatterns(Arrays.asList("/sso/password.action"));
		filterRegBean.setOrder(3);
		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean authFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new AuthFilter());
		filterRegBean.setUrlPatterns(Arrays.asList("/admin/*"));
		filterRegBean.addInitParameter("ssoURL", "http://localhost:8585/sso");
		filterRegBean.addInitParameter("apiURL", "http://localhost:8585/api");
		filterRegBean.addInitParameter("ignoreURL", "/admin/logout.action,"+
													"/admin/home/query.action,"+
													"/admin/home/searchMenu.action,"+
													"/admin/category/imp.action,"+
													"/admin/category/imp2.action,"+
													"/admin/category/exp.action,"+
													"/admin/image/add.action,"+
													"/admin/image/add2.action,"+
													"/admin/image/get.action,"+
													"/admin/image/upd2.action,"+
													"/images/*,"+
													"/dict/*,"+
													"/noaccess.jsp");
		filterRegBean.addInitParameter("appId", "3");
		filterRegBean.addInitParameter("isNeedChkAct", "1");
		filterRegBean.setOrder(4);
		return filterRegBean;
	}
}
