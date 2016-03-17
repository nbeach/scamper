package com.nicholasbeach.scamper.configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "database")
@MapperScan(basePackages = "com.nicholasbeach.scamper.persistence")
public class PersistenceConfig {

    private String url;
    private String username;
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.nicholasbeach.scamper.domain");
        return sessionFactory.getObject();
    }

}
