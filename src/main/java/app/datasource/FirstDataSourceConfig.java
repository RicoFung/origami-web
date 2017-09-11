package app.datasource;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@PropertySource(value = "classpath:datasource.properties", ignoreResourceNotFound = true)
public class FirstDataSourceConfig 
{
    @Value("${first.datasource.unique-resource-name}")
    private String uniqueResourceName;
    @Value("${first.datasource.url}")
    private String url;
    @Value("${first.datasource.username}")
    private String user;
    @Value("${first.datasource.password}")
    private String password;
    @Value("${first.datasource.driver-class-name}")
    private String driverClass;
    @Value("${first.datasource.filters}")
    private String filters;
    @Value("${first.datasource.initialSize}")
    private int initialSize;
    @Value("${first.datasource.maxActive}")
    private int maxActive;
    @Value("${first.datasource.minIdle}")
    private int minIdle;
    @Value("${first.datasource.maxWait}")
    private int maxWait;
    @Value("${first.datasource.mapper-location}")
    private String mapperLocation;
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;
 
    @Bean(name = "firstDataSource")
    @Primary
    public DataSource firstDataSource() throws SQLException 
    {
        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setFilters(filters);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        
        AtomikosDataSourceBean adsBean = new AtomikosDataSourceBean();
        adsBean.setXaDataSource(dataSource);
        adsBean.setUniqueResourceName(uniqueResourceName);
        return adsBean;
    }

//	  打开后，分布式事务JTA失效
//    @Bean(name = "firstTransactionManager")
//    @Primary
//    public DataSourceTransactionManager firstTransactionManager() 
//    {
//        return new DataSourceTransactionManager(firstDataSource());
//    }
 
    @Bean(name = "firstSqlSessionFactory")
    @Primary
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource firstDataSource) throws Exception 
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(firstDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisConfigLocation));
        return sessionFactory.getObject();
    }

	@Bean(name = "firstSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception 
	{
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}