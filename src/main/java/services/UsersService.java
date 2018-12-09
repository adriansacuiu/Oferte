package services;

import entities.Asset;
import entities.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import services.DAO.AssetDao;
import services.DAO.UserDao;
import util.Constants;
import util.OperationsUtils;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class UsersService {
	private static final Logger logger = Logger.getLogger(UsersService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private AssetDao assetDao;

	public boolean addUser(User user) throws Exception {
		logger.info("in addUser method.");
		boolean result = false;

		try {
			String password = user.getPassword();

			if (password != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(password);
				user.setPassword(hashedPassword);
			}

			result = userDao.addUser(user);

		} catch (Exception e) {
			logger.error("in addUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateUser(User user) throws Exception {
		logger.info("in updateUser method.");
		boolean result = false;

		try {
			String password = user.getPassword();
			User oldUser = userDao.getUserById(user.getIdUser());
			String oldPassword = oldUser.getPassword();

			if (password != null && !"".equals(password)) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(password);

				if (oldPassword == null || !oldPassword.equals(hashedPassword)) {
					user.setPassword(hashedPassword);
				}

			} else {
				user.setPassword(oldPassword);
			}

			result = userDao.updateUser(user);

		} catch (Exception e) {
			logger.error("in updateUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteUser(User user) throws Exception {
		logger.info("in deleteUser method.");

		boolean result = false;
		List<Asset> assets = null;

		try {
			assets = assetDao.getAssetsByUser(user.getUsername());
			if (!assets.isEmpty()) {
				for (Asset asset : assets) {
					asset.setIsAvailable(true);
					asset.setUser(null);
					assetDao.updateAsset(asset);
				}
			}
			result = userDao.deleteUser(user);

			if (result) {
				File userFolder = new File(Constants.IMAGES_FOLDER + user.getUsername());
				boolean deleteSuccess = OperationsUtils.deleteDir(userFolder);
				if (deleteSuccess) {
					logger.info("User images folder deleted successfully!");
				} else {
					logger.info("Couldn't delete user images folder!");
				}
			}

		} catch (Exception e) {
			logger.error("in deleteUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsers() throws Exception {
		logger.info("in getAllUsers method.");
		List<User> users = null;

		try {
			users = userDao.getAllUsers();

		} catch (Exception e) {
			logger.error("in getAllUsers method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	@Transactional(readOnly = true)
	public User getUserById(Long id) throws Exception {
		logger.info("in getUserById method.");
		User user = null;

		try {
			user = userDao.getUserById(id);

		} catch (Exception e) {
			logger.error("in getUserById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return user;
	}

	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws Exception {
		logger.info("in getUserByUsername method.");
		User user = null;

		try {
			user = userDao.getUserByUsername(username);

		} catch (Exception e) {
			logger.error("in getUserByUsername method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return user;
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByFirstName(String firstName) throws Exception {
		logger.info("in getUsersByFirstName method.");
		List<User> users = null;

		try {
			users = userDao.getUsersByFirstName(firstName);

		} catch (Exception e) {
			logger.error("in getUsersByFirstName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByLastName(String lastName) throws Exception {
		logger.info("in getUsersByLastName method.");
		List<User> users = null;

		try {
			users = userDao.getUsersByLastName(lastName);

		} catch (Exception e) {
			logger.error("in getUsersByLastName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	@Transactional(readOnly = true)
	public User getUserByEmail(String email) throws Exception {
		logger.info("in getUserByEmail method.");
		User user = null;

		try {
			user = userDao.getUserByEmail(email);

		} catch (Exception e) {
			logger.error("in getUserByEmail method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return user;
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByRole(String role) throws Exception {
		logger.info("in getUsersByRole method.");
		List<User> users = null;

		try {
			users = userDao.getUsersByRole(role);

		} catch (Exception e) {
			logger.error("in getUsersByRole method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}

	@Transactional(readOnly = true)
	public List<User> getUserByDepartment(Long idDepartment) throws Exception {
		logger.info("in getUserByDepartment method.");
		List<User> users = null;

		try {
			users = userDao.getUserByDepartment(idDepartment);

		} catch (Exception e) {
			logger.error("in getUserByDepartment method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return users;
	}
}
