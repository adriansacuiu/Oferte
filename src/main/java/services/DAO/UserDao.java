package services.DAO;

import entities.User;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import services.DAO.controller.SessionController;

import java.util.List;

@Repository
public class UserDao extends SessionController {
	private static final Logger logger = Logger.getLogger(UserDao.class);

	public boolean addUser(User user) throws Exception {
		logger.info("Inside addUser method.");
		boolean result = false;

		try {
			getCurrentSession().save(user);
			result = true;

		} catch (Exception e) {
			logger.error("in addUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateUser(User user) throws Exception {
		logger.info("Inside updateUser method.");
		boolean result = false;

		try {
			getCurrentSession().merge(user);
			result = true;

		} catch (Exception e) {
			logger.error("in updateUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteUser(User user) throws Exception {
		logger.info("Inside deleteUser method.");
		boolean result = false;

		try {
			getCurrentSession().delete(user);

		} catch (Exception e) {
			logger.error("in deleteUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws Exception {
		logger.info("Inside getAllUsers method.");
		List<User> users = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllUsers");
			users = (List<User>) query.list();

		} catch (Exception e) {
			logger.error("in getAllUsers method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	public User getUserById(Long id) throws Exception {
		logger.info("Inside getUserById method.");
		User user = null;

		try {
			user = (User) getCurrentSession().get(User.class, id);

		} catch (Exception e) {
			logger.error("in getUserById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return user;
	}

	public User getUserByUsername(String username) throws Exception {
		logger.info("Inside getUserByUsername method.");
		User user = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUserByUsername");
			query.setParameter("username", username);

			user = (User) query.uniqueResult();

		} catch (Exception e) {
			logger.error("in getUserByUsername method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByFirstName(String firstName) throws Exception {
		logger.info("Inside getUsersByFirstName method.");
		List<User> users = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByFirstName");
			query.setParameter("firstName", firstName);

			users = (List<User>) query.list();

		} catch (Exception e) {
			logger.error("in getUsersByFirstName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByLastName(String lastName) throws Exception {
		logger.info("Inside getUsersByLastName method.");
		List<User> users = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByLastName");
			query.setParameter("lastName", lastName);

			users = (List<User>) query.list();

		} catch (Exception e) {
			logger.error("in getUsersByLastName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByPassword(String password) throws Exception {
		logger.info("Inside getUsersByPassword method.");
		List<User> users = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByPassword");
			query.setParameter("password", password);

			users = (List<User>) query.list();

		} catch (Exception e) {
			logger.error("in getUsersByPassword method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	public User getUserByEmail(String email) throws Exception {
		logger.info("Inside getUserByEmail method.");
		User user = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUserByEmail");
			query.setParameter("email", email);

			user = (User) query.uniqueResult();

		} catch (Exception e) {
			logger.error("in getUserByEmail method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByRole(String role) throws Exception {
		logger.info("Inside getUsersByRole method.");
		List<User> users = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByRole");
			query.setParameter("role", role);

			users = (List<User>) query.list();

		} catch (Exception e) {
			logger.error("in getUsersByRole method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByDepartment(Long idDepartment) throws Exception {
		logger.info("Inside getUserByDepartment method.");
		List<User> users = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getUserByDepartment");
			query.setParameter("idDepartment", idDepartment);

			users = (List<User>) query.list();

		} catch (Exception e) {
			logger.error("in getUserByDepartment method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

}
