package app.webconfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import chok.sso.filter.AccessFilter;
import chok.sso.filter.LoginFilter;
import chok.sso.filter.LogoutFilter;
import chok.sso.filter.PasswordFilter;

@Configuration
@PropertySource(value = "classpath:sso.properties", ignoreResourceNotFound = true)
public class SsoConfig
{
	@Value("${loginFilter_urlPatterns}")
	private String loginFilter_urlPatterns;
	@Value("${loginFilter_ssoURL}")
	private String loginFilter_ssoURL;
	@Value("${loginFilter_ssoAuthURL}")
	private String loginFilter_ssoAuthURL;
	@Value("${loginFilter_ignoreURL}")
	private String loginFilter_ignoreURL;
	@Value("${logoutFilter_urlPatterns}")
	private String logoutFilter_urlPatterns;
	@Value("${passwordFilter_urlPatterns}")
	private String passwordFilter_urlPatterns;
	@Value("${accessFilter_urlPatterns}")
	private String accessFilter_urlPatterns;
	@Value("${accessFilter_apiURL}")
	private String accessFilter_apiURL;
	@Value("${accessFilter_appId}")
	private String accessFilter_appId;
	@Value("${accessFilter_isNeedChkAct}")
	private String accessFilter_isNeedChkAct;
	@Value("${accessFilter_ignoreURL}")
	private String accessFilter_ignoreURL;
	
	@Bean
	public FilterRegistrationBean loginFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new LoginFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(loginFilter_urlPatterns));
		filterRegBean.addInitParameter("ssoURL", loginFilter_ssoURL);
		filterRegBean.addInitParameter("ssoAuthURL", loginFilter_ssoAuthURL);
		filterRegBean.addInitParameter("ignoreURL", loginFilter_ignoreURL);
		filterRegBean.setOrder(1);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean logoutFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new LogoutFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(logoutFilter_urlPatterns));
		filterRegBean.setOrder(2);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean passwordFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new PasswordFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(passwordFilter_urlPatterns));
		filterRegBean.setOrder(3);
		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean accessFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new AccessFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(accessFilter_urlPatterns));
		filterRegBean.addInitParameter("appId", accessFilter_appId);
		filterRegBean.addInitParameter("isNeedChkAct", accessFilter_isNeedChkAct);
		filterRegBean.addInitParameter("apiURL", accessFilter_apiURL);
		filterRegBean.addInitParameter("ignoreURL", accessFilter_ignoreURL);
		filterRegBean.setOrder(4);
		return filterRegBean;
	}
}
