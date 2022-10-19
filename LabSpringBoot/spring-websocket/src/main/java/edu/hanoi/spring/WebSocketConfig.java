package edu.hanoi.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebSocketMessageBroker
@PropertySource("classpath:/application.properties")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private org.springframework.core.env.Environment env;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/message").withSockJS();
        registry.addEndpoint("/user").withSockJS();
        registry.addEndpoint("/listUser").withSockJS();
        registry.addEndpoint("/group").withSockJS();
        registry.addEndpoint("/listGroup").withSockJS();
    }

    @Bean
    public DataSource dataSource(){
        String url = env.getProperty("jdbc.url");
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("edu.*");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;

    }

    Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        return properties;
    }
}
