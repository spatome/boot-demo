package com.spatome.boot.common.config.druid;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * 两个数据库的事务
 * 由atomikos同一事务管理
 */
@Configuration
@MapperScan(basePackages = "com.spatome.boot.dao.basic", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class DataSourceMasterConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

	@Primary
	@Bean(name="masterDataSource")
	public DataSource masterDataSource(){
		DruidXADataSource dataSource = new DruidXADataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		try {
			dataSource.setFilters("stat, wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 将本地事务注册到创 Atomikos全局事务
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName("masterDataSource");
		atomikosDataSourceBean.setXaDataSource(dataSource);
		atomikosDataSourceBean.setTestQuery("SELECT 1");

		return atomikosDataSourceBean;
	};

	@Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/basic/*.xml"));
        return bean.getObject();
    }

    /**
     * 所有的数据源只有一个事务管理器
     */
    @Bean
    public JtaTransactionManager transactionManager(){
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
