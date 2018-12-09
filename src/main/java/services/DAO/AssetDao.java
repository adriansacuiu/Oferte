package services.DAO;

import entities.Asset;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import services.DAO.controller.SessionController;

import java.util.List;

@Repository
public class AssetDao extends SessionController {
	private static final Logger logger = Logger.getLogger(AssetDao.class);

	public boolean addAsset(Asset asset) throws Exception {
		logger.info("Inside addAsset method.");
		boolean result = false;

		try {
			getCurrentSession().save(asset);
			result = true;

		} catch (Exception e) {
			logger.error("in addAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateAsset(Asset asset) throws Exception {
		logger.info("Inside updateAsset method.");
		boolean result = false;

		try {
			getCurrentSession().merge(asset);
			result = true;

		} catch (Exception e) {
			logger.error("in updateAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteAsset(Asset asset) throws Exception {
		logger.info("Inside deleteAsset method.");
		boolean result = false;

		try {
			getCurrentSession().delete(asset);
			result = true;

		} catch (Exception e) {
			logger.error("in deleteAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Asset> getAllAssets() throws Exception {
		logger.info("Inside getAllAssets method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllAssets");
			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAllAssets method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	public Asset getAssetById(Long id) throws Exception {
		logger.info("Inside getAssetById method.");
		Asset asset = null;

		try {
			asset = (Asset) getCurrentSession().get(Asset.class, id);
		} catch (Exception e) {
			logger.error("in getAssetById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return asset;
	}

	@SuppressWarnings("unchecked")
	public List<Asset> getAvailableAssets() throws Exception {
		logger.info("Inside getAvailableAssets method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAvailableAssets");
			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAvailableAssets method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}
	
	@SuppressWarnings("unchecked")
	public List<Asset> getAssetsByName(String name) throws Exception {
		logger.info("Inside getAssetsByName method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAssetsByName");
			query.setParameter("name", name);

			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAssetsByName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@SuppressWarnings("unchecked")
	public List<Asset> getAssetsByType(String type) throws Exception {
		logger.info("Inside getAssetsByType method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAssetsByType");
			query.setParameter("type", type);

			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAssetsByType method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@SuppressWarnings("unchecked")
	public List<Asset> getAssetsByIsAvailable(boolean isAvailable) throws Exception {
		logger.info("Inside getAssetsByIsAvailable method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAssetsByIsAvailable");
			query.setParameter("isAvailable", isAvailable);

			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAssetsByIsAvailable method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@SuppressWarnings("unchecked")
	public List<Asset> getAssetsByUser(String username) throws Exception {
		logger.info("Inside getAssetsByUser method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAssetsByUser");
			query.setParameter("username", username);

			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAssetsByUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@SuppressWarnings("unchecked")
	public List<Asset> getAssetsByOrder(Long idOrder) throws Exception {
		logger.info("Inside getAssetsByOrder method.");
		List<Asset> assets = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAssetsByOrder");
			query.setParameter("idOrder", idOrder);

			assets = (List<Asset>) query.list();

		} catch (Exception e) {
			logger.error("in getAssetsByOrder method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}
}
