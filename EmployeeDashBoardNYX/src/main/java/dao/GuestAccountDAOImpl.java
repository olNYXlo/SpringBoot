package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import pojo.EmployeeAccount;
import pojo.GuestAccount;
import pojo.LoginAccount;

public class GuestAccountDAOImpl extends AccountDAO {

	public void register(GuestAccount GA) throws SQLException {
		
		// All checks & validation done in Service layer
		// DAO layer assumes all input parameters are correct
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(GA);
		
		// Inserting new Guest Account into table

		session.getTransaction().commit();	
		session.close();
				

	}



}
