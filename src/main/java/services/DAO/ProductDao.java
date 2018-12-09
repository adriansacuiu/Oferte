package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import entities.Product;
import services.DAO.controller.SessionController;

public class ProductDao extends SessionController {
	private static final Logger logger = Logger.getLogger(ProductDao.class);
	
	public boolean addProduct(Product product) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().save(product);
			result = true;

		} catch (Exception e) {
			logger.error("in addProduct method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateProduct(Product product) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().merge(product);
			result = true;

		} catch (Exception e) {
			logger.error("in updateProduct method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteProduct(Product product) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().delete(product);

		} catch (Exception e) {
			logger.error("in deleteProduct method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() throws Exception {
		List<Product> products = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllProducts");
			products = (List<Product>) query.list();

		} catch (Exception e) {
			logger.error("in getAllProducts method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return products;
	}

	public Product getProductById(Long id) throws Exception {
		Product product = null;

		try {
			product = (Product) getCurrentSession().get(Product.class, id);

		} catch (Exception e) {
			logger.error("in getProductById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return product;
	}
}
