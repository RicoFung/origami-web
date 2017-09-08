package app.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
	    registry.addViewController("/").setViewName("forward:/index.jsp"); // 设置默认首页(必须加入“forward:”, 否则会访问spring.mvc.view.prefix所指定的目录)
	    super.addViewControllers(registry);
	}
	
}
