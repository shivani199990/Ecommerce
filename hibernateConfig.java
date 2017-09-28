package com.hibernateConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.DaO.UserDaO;
import com.DaOImpl.UserDaOImpl;
import com.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan("com")
public class hibernateConfig 
{
	@Autowired
	@Bean(name="datasource")
	public DataSource getH2()
	{
		System.out.println("Hibernate Initiated");
		DriverManagerDataSource dt=new DriverManagerDataSource();
		dt.setDriverClassName("org.h2.Driver");
		dt.setUrl("jdbc:h2:tcp://localhost/~/mt");
		dt.setUsername("sa");
		dt.setPassword("");
		System.out.println("Connection established");
		return dt;
	}
	private Properties getHiberProps()
	{
		Properties p=new Properties();
		p.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		p.put("hibernate.show_sql",  "true");
		p.put("hibernate.hbmddl.auto", "update");
		return p;
	}
	
	@Autowired
	@Bean(name="UserDaO")
	public  UserDaO getUserData(SessionFactory sessionFac)
	{
		return new UserDaOImpl(sessionFac);
	}
	
	@Autowired
	@Bean(name= "sessionFactory")
	public SessionFactory getSession(DataSource datasource){
		LocalSessionFactoryBuilder lsfb=new LocalSessionFactoryBuilder(datasource);
		lsfb.addProperties(getHiberProps());
		lsfb.addAnnotatedClass(User.class);
		return lsfb.buildSessionFactory();
		
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransaction(SessionFactory sessionFac)
	{
		HibernateTransactionManager tm=new HibernateTransactionManager(sessionFac);
		return tm;
	}

}
