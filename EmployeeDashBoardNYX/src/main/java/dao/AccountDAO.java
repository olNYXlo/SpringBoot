package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

import pojo.EmployeeAccount;
import pojo.GuestAccount;
import pojo.LoginAccount;
import service.EmailService;


public interface AccountDAO {
	

	
	
	default public boolean login(LoginAccount LA) throws SQLException {
		
		String password = null;
		boolean check = false;


		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<LoginAccount> c = cBuilder.createQuery(LoginAccount.class);
		Root<LoginAccount> ObjRoot = c.from(LoginAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("email"), LA.getEmail());
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO if it matches UID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		
		Query query = session.createQuery(c);

		System.out.println("Verifying Login");
		List<LoginAccount> results = query.getResultList();
		for (LoginAccount Acc : results) {
			password = Acc.getPassword();

		}
		session.close();
		return (LA.getPassword().equals(password) ? true : false);
		// Checks if the password matches that in DB
		// Better than checking password against input password
		// as if query returns null, will have some issues
	}
	
	default public int checkRole(LoginAccount LA) throws SQLException {
		
		int role = 0;
		

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<EmployeeAccount> c = cBuilder.createQuery(EmployeeAccount.class);
		Root<EmployeeAccount> ObjRoot = c.from(EmployeeAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("email"), LA.getEmail());
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UserID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);
		List<EmployeeAccount> results = query.getResultList();
		System.out.println("Retrieving User Account Bank Detials");
		for (EmployeeAccount Acc : results) {

			role = Acc.getRole();
		}
		session.close();		
		
		
		return role;
	}
	
	
	
	default public EmployeeAccount getEmployeeAcc(String email) throws SQLException {

		EmployeeAccount EA = new EmployeeAccount();
		

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<EmployeeAccount> c = cBuilder.createQuery(EmployeeAccount.class);
		Root<EmployeeAccount> ObjRoot = c.from(EmployeeAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("email"), email);
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UserID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);
		List<EmployeeAccount> results = query.getResultList();
		System.out.println("Retrieving User Account Bank Detials");
		for (EmployeeAccount Acc : results) {
			EA.setAccountStatus(Acc.getAccountStatus());
			EA.setDob(Acc.getDob());
			EA.setEmail(email);
			EA.setGender(Acc.getGender());
			EA.setId(Acc.getId());
			EA.setJavaTestId(Acc.getJavaTestId());
			EA.setJavaTestScore(Acc.getJavaTestScore());
			EA.setJavaTestStatus(Acc.getJavaTestStatus());
			EA.setMySQLTestId(Acc.getMySQLTestId());
			EA.setMySQLTestScore(Acc.getMySQLTestScore());
			EA.setMySQLTestStatus(Acc.getMySQLTestStatus());
			EA.setName(Acc.getName());
			EA.setProfileImage(Acc.getProfileImage());
			EA.setRole(Acc.getRole());
			EA.setTechnicalTestCompletion(Acc.getTechnicalTestCompletion());
		}
		session.close();
		return EA;
		
	}
	
	default public void forgotPassword(String email,HttpServletRequest request) {
		// Check return type is what
		// as have to reset password + send link to email address
		
		@Autowired
		private EmailService emailService;
		//might have to shift email service to another class
		// as interface cannot have attributes
		

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<LoginAccount> c = cBuilder.createQuery(LoginAccount.class);
		Root<LoginAccount> ObjRoot = c.from(LoginAccount.class);
		// Creates root object for query
		
		Predicate restrictions = cBuilder.equal(ObjRoot.get("email"), email);
		// Creates WHERE statement for query
		// So will compare the attribute of "userID" in POJO class if it matches UserID
		
		c.select(ObjRoot).where(restrictions);
		// Will query the DB for the SQL query
		// Expected to return 1 row only
		Query query = session.createQuery(c);
		List<LoginAccount> results = query.getResultList();
		System.out.println("Checking if Security Key Matches");
		for (LoginAccount Acc : results) {
			System.out.println("Printing Password");
			System.out.println("Your Password is : " + Acc.getPassword());
			System.out.println("======================================================================================");
		}
		
		CriteriaUpdate<LoginAccount> cUpdate = cBuilder.createCriteriaUpdate(LoginAccount.class);
		Root<LoginAccount> ObjRoot2 = cUpdate.from(LoginAccount.class);	
		String newPassword = UUID.randomUUID().toString();
		// randomly generates universally unique id from java UUID class
		
		cUpdate.set(ObjRoot2.get("password"), newPassword);
		// Creates UPDATE/ SET statement
		// So will update the value of column password to match the data given
		
		
		cUpdate.where(cBuilder.equal(ObjRoot2.get("email"), email));

		session.createQuery(cUpdate).executeUpdate();
		// Resets password in LoginAccount Table
		session.getTransaction().commit();	
		
		
		String URL = request.getScheme() + "://" + request.getServerName();
		
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setFrom("admin@optimum.com");
		passwordResetEmail.setTo(email);
		passwordResetEmail.setSubject("Password Reset Request");
		passwordResetEmail.setText("To reset your password, click the link below:\n" + URL
				+ "/reset?token=" + newPassword);
		
		emailService.sendEmail(passwordResetEmail);
		
		// Sends out a simple email with the link for the password reset		
		
		
		session.close();
		
		
		
	}

	public static boolean checkIDExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
