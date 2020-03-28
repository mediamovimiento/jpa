import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.codejava.hibernate.User;

public class UserDAOTest {

	/**
	 * Tutorial jpa
	 * https://www.codejava.net/frameworks/hibernate/java-hibernate-jpa-annotations-tutorial-for-beginners
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
		EntityManager entityManager = factory.createEntityManager();

		
		entityManager.getTransaction().begin();
		
//		User newUser = new User();
//		newUser.setEmail("billjoy@gmail.com");
//		newUser.setFullname("bill Joy");
//		newUser.setPassword("billi");
//		 
//		entityManager.persist(newUser);
		//------------------------------------
//		User existingUser = new User();
//		existingUser.setId(1);
//		existingUser.setEmail("bill.joy@gmail.com");
//		existingUser.setFullname("Bill Joy");
//		existingUser.setPassword("billionaire");
//		 
//		entityManager.merge(existingUser);
		//------------------------------------
		
//		Integer primaryKey = 1;
//		User user = entityManager.find(User.class, primaryKey);
//		System.out.println(user.getEmail());
//		System.out.println(user.getFullname());
//		System.out.println(user.getPassword());
		//-------------------------------------
		
		String sql = "SELECT u from User u where u.email = 'bill.joy@gmail.com'";
		Query query = entityManager.createQuery(sql);
		User user = (User) query.getSingleResult();
		 
		System.out.println(user.getEmail());
		System.out.println(user.getFullname());
		System.out.println(user.getPassword());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
