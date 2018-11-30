package com.spatome.boot.common.config.druid;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.xa.DruidXADataSource;

@Configuration
@MapperScan(basePackages = "com.spatome.boot.dao.zjd", sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class DataSourceSlaveConfig {

    @Value("${spring.datasource-1.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource-1.url}")
    private String url;
    @Value("${spring.datasource-1.username}")
    private String userName;
    @Value("${spring.datasource-1.password}")
    private String password;

	@Bean(name="slaveDataSource")
	public DataSource slaveDataSource(){
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
		atomikosDataSourceBean.setUniqueResourceName("slaveDataSource");
		atomikosDataSourceBean.setXaDataSource(dataSource);
		atomikosDataSourceBean.setTestQuery("SELECT 1");

		return atomikosDataSourceBean;
	};

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/zjd/*.xml"));
        return bean.getObject();
    }
}