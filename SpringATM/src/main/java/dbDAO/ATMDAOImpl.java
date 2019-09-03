package dbDAO;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbPOJO.BankAccount;
import dbPOJO.OnlineLoginAccount;

@Repository
public class ATMDAOImpl implements ATMDAO {

	// StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	// Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	// SessionFactory factory = meta.getSessionFactoryBuilder().build();
	// Cannot declare these attributes as static even though every transaction/ service
	// has to create these objects as will run into error
	
	
	
	// EntityManagerFactory emf =
	// Persistence.createEntityManagerFactory("atmaccount");
	// EntityManager em = emf.createEntityManager();




	public static boolean checkBankAccExists(String BankAccNo) throws ClassNotFoundException, SQLException {
		// Checks if Bank Account exists in records
		
		
		boolean check = false;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BankAccount> c = cBuilder.createQuery(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("BankAccNo"), BankAccNo);
		// Creates WHERE statement for query
		// So will compare the attribute of "BankAccNo" of POJO class if it matches BankAccNo
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);

		try {
			System.out.println("Checking if BankAcc already exists");
			List<BankAccount> results = query.getResultList();
			// checks if resultset is empty
			if (results.isEmpty()) {
				System.out.println("No existing Bank Account with that number. Please Try Again");
				System.out.println("======================================================================================");
			}
			else {
				System.out.println("Existing Bank Account with that number found");
				System.out.println("======================================================================================");
				check = true;
			}
		} finally {}
		session.close();
		return check;
		
		
		
		

	}

	public static boolean checkSK(String SK, String UID) throws SQLException, ClassNotFoundException {
		// Checks if input NRIC matches that in database
		// Done to link bank account to online account
		
		
		String SecurityKey = null;
		boolean check = false;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OnlineLoginAccount> c = cBuilder.createQuery(OnlineLoginAccount.class);
		Root<OnlineLoginAccount> ObjRoot = c.from(OnlineLoginAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("userID"), UID);
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);

		try {
			System.out.println("Checking if SK matches");
			List<OnlineLoginAccount> results = query.getResultList();
			for (OnlineLoginAccount Acc : results) {
				SecurityKey = Acc.getSecurityKey();
			}

		} finally {
		}
		session.close();
		return (SK.equals(SecurityKey) ? true : false);
		// Checks if the security key matches that in DB		
		


	}

	public static boolean checkNRIC(String NRIC, String BANo) throws SQLException, ClassNotFoundException {
		// Checks if input NRIC matches that in database
		// Done to link bank account to online account
		// takes in NRIC and bank account number
		
		
		String ICNo = null;
		boolean check = false;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BankAccount> c = cBuilder.createQuery(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("BankAccNo"), BANo);
		// Creates WHERE statement for query
		// So will compare the attribute of "BankAccNo" in POJO class if it matches BANo
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);

		try {
			System.out.println("Checking if NRIC matches");
			List<BankAccount> results = query.getResultList();
			for (BankAccount BA : results) {
				ICNo = BA.getAccHolderNRIC();
			}

		} finally {
		}
		session.close();
		return (NRIC.equals(ICNo) ? true : false);
		// Checks if the NRIC matches that in DB		
		


	}

	public static boolean checkWithdraw(double amt, BankAccount BA) throws SQLException, ClassNotFoundException {
		// Checks if valid withdrawl amount is supplied

		
		double balance;
		boolean result = false;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BankAccount> c = cBuilder.createQuery(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("BankAccNo"), BA.getBankAccNo());
		// Creates WHERE statement for query
		// So will compare the attribute of "BankAccNo" in POJO class if it matches BANo
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);

		try {
			List<BankAccount> results = query.getResultList();
			System.out.println("Checking if Withdrawal Amount is Valid");
			for (BankAccount BAcc : results) {
				balance = BAcc.getBankBalance();
				if (balance >= amt && amt >= 0) {
					result = true;
				} // end of if case
				else if (amt < 0) {
					System.out.println("Amount Can't Be Negative");
					System.out.println(
							"======================================================================================");
					result = false;
				} // end of else if case
				else if (balance < amt && amt > 0) {
					result = false;
					System.out.println("Insufficient Balance");
					System.out.println(
							"======================================================================================");
				} // end of else if case
			}

		} finally {
		}
		session.close();
		return result;
		// Checks if the withdrawal amount is valid		
		


	}

	public static boolean checkBankAccLinked(String BankAccNo) throws ClassNotFoundException, SQLException {

		// Checks ATM records if the bank account has already been linked to an online
		// account

		boolean check = false;
		String UserName = null;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BankAccount> c = cBuilder.createQuery(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("BankAccNo"), BankAccNo);
		// Creates WHERE statement for query
		// So will compare the attribute of "BankAccNo" of POJO class if it matches BankAccNo
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);

		try {
			System.out.println("Checking if Bank Account is already linked");
			List<BankAccount> results = query.getResultList();
			for (BankAccount BA : results) {
				UserName = BA.getUserID();
				// returns the value of column UserID in BankAccount table
			}
			if (UserName == null) {
				System.out.println("No Online Account Linked to this Bank Account Yet");
				System.out.println("======================================================================================");
			}
			else {
				System.out.println("Bank Account is already linked to another Online account");
				System.out.println("======================================================================================");
				check = true;
			}
		} finally {}
		session.close();
		return check;
		
		


	}

	public static boolean checkIDExists(String UID) throws ClassNotFoundException, SQLException {

		String UserName = null;
		boolean check = false;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OnlineLoginAccount> c = cBuilder.createQuery(OnlineLoginAccount.class);
		Root<OnlineLoginAccount> ObjRoot = c.from(OnlineLoginAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("userID"), UID);
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);

		try {
			System.out.println("Checking if UserID already exists");
			List<OnlineLoginAccount> results = query.getResultList();
			for (OnlineLoginAccount Acc : results) {
				UserName = Acc.getUserID();
			}

		} finally {
		}
		session.close();
		return (UID.equals(UserName) ? true : false);
		// Checks if the password matches that in DB


	}

	@Override
	public void register(OnlineLoginAccount OLA, BankAccount BA) throws SQLException {
		
		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(OLA);
		
		// Inserting new User Account into table
		
		
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<BankAccount> c = cBuilder.createCriteriaUpdate(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);	
		c.set(ObjRoot.get("UserID"), OLA.getUserID());
		// Creates UPDATE/ SET statement
		// So will update the value of column UserID to match the data given
		
		c.where(cBuilder.equal(ObjRoot.get("AccHolderNRIC"), BA.getAccHolderNRIC()));
		c.where(cBuilder.equal(ObjRoot.get("BankAccNo"), BA.getBankAccNo()));
		
		session.createQuery(c).executeUpdate();
		// updating Bank Account Table to link with User Account
		session.getTransaction().commit();	
		session.close();
		
		
		
		
		

	}

	@Override
	public boolean login(OnlineLoginAccount OLA) throws SQLException {
		// getConnection(); // starts up DB connection

		String password = null;
		boolean check = false;

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OnlineLoginAccount> c = cBuilder.createQuery(OnlineLoginAccount.class);
		Root<OnlineLoginAccount> ObjRoot = c.from(OnlineLoginAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("userID"), OLA.getUserID());
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO if it matches UID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		
		Query query = session.createQuery(c);

		System.out.println("Verifying Login");
		List<OnlineLoginAccount> results = query.getResultList();
		for (OnlineLoginAccount Acc : results) {
			password = Acc.getPassword();
		}
		session.close();
		return (OLA.getPassword().equals(password) ? true : false);
		// Checks if the password matches that in DB



	}

	@Override
	public BankAccount getBANo(OnlineLoginAccount OLA) throws SQLException {
		
		BankAccount BA = new BankAccount();
		
		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BankAccount> c = cBuilder.createQuery(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("UserID"), OLA.getUserID());
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UserID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);
		List<BankAccount> results = query.getResultList();
		System.out.println("Retrieving User Account Bank Detials");
		for (BankAccount BAcc : results) {
			BA.setBankAccNo(BAcc.getBankAccNo());
			BA.setUserID(OLA.getUserID());
			// BA.setBankBalance(BAcc.getBankBalance());
		}
		session.close();
		return BA;
		
		
		
		
		


	}

	@Override
	public void ForgotPassword(OnlineLoginAccount OLA) throws SQLException {


		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OnlineLoginAccount> c = cBuilder.createQuery(OnlineLoginAccount.class);
		Root<OnlineLoginAccount> ObjRoot = c.from(OnlineLoginAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("userID"), OLA.getUserID());
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UserID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);
		List<OnlineLoginAccount> results = query.getResultList();
		System.out.println("Checking if Security Key Matches");
		for (OnlineLoginAccount Acc : results) {
			System.out.println("Printing Password");
			System.out.println("Your Password is : " + Acc.getPassword());
			System.out.println("======================================================================================");
		}
		session.close();
		

		


	}

	@Override
	public void withdraw(BankAccount BA, double val) throws SQLException {
		
		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();		
		
		session.beginTransaction();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<BankAccount> c = cBuilder.createCriteriaUpdate(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);	
		c.set(ObjRoot.get("BankBalance"), checkbalance(BA) - val);
		c.where(cBuilder.equal(ObjRoot.get("UserID"), BA.getUserID()));
		
		session.createQuery(c).executeUpdate();
		// updating Bank Balance	
		session.getTransaction().commit();
		session.close();
		

	}

	@Override
	public void deposit(BankAccount BA, double val) throws SQLException {

		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();		
		
		session.beginTransaction();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<BankAccount> c = cBuilder.createCriteriaUpdate(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);	
		c.set(ObjRoot.get("BankBalance"), checkbalance(BA) + val);
		c.where(cBuilder.equal(ObjRoot.get("UserID"), BA.getUserID()));
		
		session.createQuery(c).executeUpdate();
		// updating Bank Balance
		session.getTransaction().commit();
		session.close();
		
		


	}

	@Override
	public double checkbalance(BankAccount BA) throws SQLException {
		
		double Balance = 0;
		
		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(OnlineLoginAccount.class).buildSessionFactory();
		// Session session = factory.getCurrentSession();
		
		// SessionFactory factory = meta.getSessionFactoryBuilder().build();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BankAccount> c = cBuilder.createQuery(BankAccount.class);
		Root<BankAccount> ObjRoot = c.from(BankAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("BankAccNo"), BA.getBankAccNo());
		// Creates WHERE statement for query
		// So will compare the attribute of "BankAccNo" in POJO class if it matches BANo
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);
		List<BankAccount> results = query.getResultList();
		System.out.println("Checking of Bank Balance");
		for (BankAccount BAcc : results) {
			Balance = BAcc.getBankBalance();
			System.out.println("Printing Bank Balance");
			System.out.println("Bank Balance for : " + BA.getBankAccNo() + " is SGD " + Balance);
			System.out.println("======================================================================================");
		}	
		session.close();
		return Balance;
		


	}
	

}
