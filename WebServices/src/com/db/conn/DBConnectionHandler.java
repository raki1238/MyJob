package com.db.conn;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBConnectionHandler {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				//Load the configuration for hibernate 
				Configuration configuration = new Configuration().configure();
				ServiceRegistry serviceRegistry
				= new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

				//To build a new session factory
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
			}
		}catch(HibernateException e) {
			System.out.println("Exception in Connection Handler: "+e.getMessage());
			e.printStackTrace();
		}

		return sessionFactory;
	}
}
