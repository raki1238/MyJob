package com.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.db.conn.DBConnectionHandler;
import com.db.models.GcdNumbers;

public class GcdNumbersDAOImpl implements GcdNumbersDAO{

	SessionFactory sessionFactory = null;

	public GcdNumbersDAOImpl() {
		// TODO Auto-generated constructor stub
		sessionFactory = DBConnectionHandler.getSessionFactory();
	}

	@Override
	public String storeNumsAndGcd(GcdNumbers gcdNumbers) {
		// TODO Auto-generated method stub
		String res = "fail";
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(gcdNumbers);
				if(gcdNumbers.getSno()>0) {
					System.out.println("Inserted into db. Commiting the transaction");
				}
				session.getTransaction().commit();
				res="success";
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GcdNumbers> getGcdNumbers() {
		// TODO Auto-generated method stub
		List<GcdNumbers> gcdNumbers = new ArrayList<GcdNumbers>();
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				
				//Query query = session.createQuery("from ").addEntity(GcdNumbers.class);
				
				String queryStr = "select * from gcd_numbers";
				Query query = session.createSQLQuery(queryStr).addEntity(GcdNumbers.class);
				
				gcdNumbers=(List<GcdNumbers>) query.list();
				
				//gcdNumbers = (List<GcdNumbers>) session.createQuery("from gcd_numbers").list();
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return gcdNumbers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getGcdValues() {
		// TODO Auto-generated method stub
		List<Integer> gcdNumbers = new ArrayList<Integer>();
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				
				//Query query = session.createQuery("from ").addEntity(GcdNumbers.class);
				
				String queryStr = "select gcd_val from gcd_numbers";
				Query query = session.createSQLQuery(queryStr);
				
				gcdNumbers=(List<Integer>) query.list();
				
				//gcdNumbers = (List<GcdNumbers>) session.createQuery("from gcd_numbers").list();
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return gcdNumbers;
	}

	@Override
	public int gcdSum() {
		// TODO Auto-generated method stub
		int res =0 ;
		if(sessionFactory!= null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				
				//Query query = session.createQuery("from ").addEntity(GcdNumbers.class);
				
				String queryStr = "select sum(gcd_val) from gcd_numbers";
				Query query = session.createSQLQuery(queryStr);
				
				@SuppressWarnings("rawtypes")
				List listResult = query.list();
				Number num  = (Number) listResult.get(0);
				res = num.intValue();
				
				//gcdNumbers = (List<GcdNumbers>) session.createQuery("from gcd_numbers").list();
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return res;
	}


}
