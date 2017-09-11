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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

//@Configuration
//@PropertySource(value = "classpath:datasource.properties", ignoreResourceNotFound = true)
public class SecondDataSourceConfig 
{
    @Value("${second.datasource.unique-resource-name}")
    private String uniqueResourceName;
    @Value("${second.datasource.url}")
    private String url;
    @Value("${second.datasource.username}")
    private String user;
    @Value("${second.datasource.password}")
    private String password;
    @Value("${second.datasource.driver-class-name}")
    private String driverClass;
    @Value("${second.datasource.filters}")
    private String filters;
    @Value("${second.datasource.initialSize}")
    private int initialSize;
    @Value("${second.datasource.maxActive}")
    private int maxActive;
    @Value("${second.datasource.minIdle}")
    private int minIdle;
    @Value("${second.datasource.maxWait}")
    private int maxWait;
    @Value("${second.datasource.mapper-location}")
    private String mapperLocation;
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;
 
    @Bean(name = "secondDataSource")
    public DataSource secondDataSource() throws SQLException 
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
//    @Bean(name = "secondTransactionManager")
//    public DataSourceTransactionManager secondTransactionManager() 
//    {
//        return new DataSourceTransactionManager(secondDataSource());
//    }
 
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource secondDataSource) throws Exception 
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(secondDataSource);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(mybatisConfigLocation));
        return sessionFactory.getObject();
    }

	@Bean(name = "secondSqlSessionTemplate")
	public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception 
	{
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}