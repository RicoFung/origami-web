package app.webconfig;

import java.util.Arrays;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import chok.cas.client.filter.CasAccessFilter;
import chok.cas.client.filter.CasLogoutFilter;
import chok.cas.client.filter.CasPasswordFilter;

@Configuration
@PropertySource(value = "classpath:cas.properties", ignoreResourceNotFound = true)
public class CasConfig
{
	@Value("${httpServletRequestWrapperFilter_urlPatterns}")
	private String httpServletRequestWrapperFilter_urlPatterns;
	@Value("${authenticationFilter_urlPatterns}")
	private String authenticationFilter_urlPatterns;
	@Value("${authenticationFilter_casServerLoginUrl}")
	private String authenticationFilter_casServerLoginUrl;
	@Value("${authenticationFilter_serverName}")
	private String authenticationFilter_serverName;
	@Value("${ticketValidationFilter_urlPatterns}")
	private String ticketValidationFilter_urlPatterns;
	@Value("${ticketValidationFilter_casServerUrlPrefix}")
	private String ticketValidationFilter_casServerUrlPrefix;
	@Value("${ticketValidationFilter_serverName}")
	private String ticketValidationFilter_serverName;
	@Value("${singleSignOutFilter_urlPatterns}")
	private String singleSignOutFilter_urlPatterns;
	@Value("${singleSignOutFilter_casServerUrlPrefix}")
	private String singleSignOutFilter_casServerUrlPrefix;
	@Value("${casLogoutFilter_urlPatterns}")
	private String casLogoutFilter_urlPatterns;
	@Value("${casLogoutFilter_logoutURL}")
	private String casLogoutFilter_logoutURL;
	@Value("${casPasswordFilter_urlPatterns}")
	private String casPasswordFilter_urlPatterns;
	@Value("${casPasswordFilter_passwordURL}")
	private String casPasswordFilter_passwordURL;
	@Value("${casAccessFilter_urlPatterns}")
	private String casAccessFilter_urlPatterns;
	@Value("${casAccessFilter_apiURL}")
	private String casAccessFilter_apiURL;
	@Value("${casAccessFilter_appId}")
	private String casAccessFilter_appId;
	@Value("${casAccessFilter_isNeedChkAct}")
	private String casAccessFilter_isNeedChkAct;
	@Value("${casAccessFilter_ignoreURL}")
	private String casAccessFilter_ignoreURL;
	
	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registerCustomListener()
	{
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> l = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>(new SingleSignOutHttpSessionListener());
		l.setOrder(4);
		return l;
	}
	
	@Bean
	public FilterRegistrationBean httpServletRequestWrapperFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new HttpServletRequestWrapperFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(httpServletRequestWrapperFilter_urlPatterns));
		filterRegBean.setOrder(1);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean authenticationFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new AuthenticationFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(authenticationFilter_urlPatterns));
		filterRegBean.addInitParameter("casServerLoginUrl", authenticationFilter_casServerLoginUrl);
		filterRegBean.addInitParameter("serverName", authenticationFilter_serverName);
		filterRegBean.setOrder(2);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean cas30ProxyReceivingTicketValidationFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new Cas30ProxyReceivingTicketValidationFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(ticketValidationFilter_urlPatterns));
		filterRegBean.addInitParameter("casServerUrlPrefix", ticketValidationFilter_casServerUrlPrefix);
		filterRegBean.addInitParameter("serverName", ticketValidationFilter_serverName);
		filterRegBean.setOrder(3);
		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean singleSignOutFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new SingleSignOutFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(singleSignOutFilter_urlPatterns));
		filterRegBean.addInitParameter("casServerUrlPrefix", singleSignOutFilter_casServerUrlPrefix);
		filterRegBean.setOrder(4);
		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean casLogoutFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new CasLogoutFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(casLogoutFilter_urlPatterns));
		filterRegBean.addInitParameter("logoutURL", casLogoutFilter_logoutURL);
		filterRegBean.setOrder(5);
		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean casPasswordFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new CasPasswordFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(casPasswordFilter_urlPatterns));
		filterRegBean.addInitParameter("passwordURL", casPasswordFilter_passwordURL);
		filterRegBean.setOrder(6);
		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean casAccessFilter()
	{
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean(new CasAccessFilter());
		filterRegBean.setUrlPatterns(Arrays.asList(casAccessFilter_urlPatterns));
		filterRegBean.addInitParameter("apiURL", casAccessFilter_apiURL);
		filterRegBean.addInitParameter("ignoreURL", casAccessFilter_ignoreURL);
		filterRegBean.addInitParameter("appId", casAccessFilter_appId);
		filterRegBean.addInitParameter("isNeedChkAct", casAccessFilter_isNeedChkAct);
		filterRegBean.setOrder(7);
		return filterRegBean;
	}
}
