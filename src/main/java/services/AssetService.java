package services;

import entities.Asset;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import services.DAO.AssetDao;
import services.DAO.UserDao;

import java.util.List;

@Transactional
@Service
public class AssetService {
	private static final Logger logger = Logger.getLogger(AssetService.class);

	@Autowired
	private AssetDao assetDao;

	@Autowired
	private UserDao userDao;

	public boolean addAsset(Asset asset) throws Exception {
		logger.info("in addAsset method.");
		boolean result = false;

		try {
			asset.setIsAvailable(true);
			result = assetDao.addAsset(asset);

		} catch (Exception e) {
			logger.error("in addAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateAsset(Asset asset) throws Exception {
		logger.info("in updateAsset method.");
		boolean result = false;

		try {
			result = assetDao.updateAsset(asset);

		} catch (Exception e) {
			logger.error("in updateAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteAsset(Asset asset) throws Exception {
		logger.info("in deleteAsset method.");
		boolean result = false;

		try {
			result = assetDao.deleteAsset(asset);

		} catch (Exception e) {
			logger.error("in deleteAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Transactional(readOnly = true)
	public List<Asset> getAllAssets() throws Exception {
		logger.info("in getAllAssets method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAllAssets();

		} catch (Exception e) {
			logger.error("in getAllAssets method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@Transactional(readOnly = true)
	public Asset getAssetById(Long id) throws Exception {
		logger.info("in getAssetById method.");
		Asset asset = null;

		try {
			asset = assetDao.getAssetById(id);

		} catch (Exception e) {
			logger.error("in getAssetById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return asset;
	}

	@Transactional(readOnly = true)
	public List<Asset> getAvailableAssets() throws Exception {
		logger.info("in getAssetsByName method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAvailableAssets();

		} catch (Exception e) {
			logger.error("in getAvailableAssets method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}
	
	@Transactional(readOnly = true)
	public List<Asset> getAssetsByName(String name) throws Exception {
		logger.info("in getAssetsByName method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAssetsByName(name);

		} catch (Exception e) {
			logger.error("in getAssetsByName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@Transactional(readOnly = true)
	public List<Asset> getAssetsByType(String type) throws Exception {
		logger.info("in getAssetsByType method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAssetsByType(type);

		} catch (Exception e) {
			logger.error("in getAssetsByType method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@Transactional(readOnly = true)
	public List<Asset> getAssetsByIsAvailable(boolean isAvailable) throws Exception {
		logger.info("in getAssetsByIsAvailable method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAssetsByIsAvailable(isAvailable);

		} catch (Exception e) {
			logger.error("in getAssetsByIsAvailable method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}

	@Transactional(readOnly = true)
	public List<Asset> getAssetsByUser(String username) throws Exception {
		logger.info("Inside getAssetsByUser method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAssetsByUser(username);

		} catch (Exception e) {
			logger.error("in getAssetsByUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}
	
	@Transactional(readOnly = true)
	public List<Asset> getAssetsByOrder(Long idOrder) throws Exception {
		logger.info("Inside getAssetsByOrder method.");
		List<Asset> assets = null;

		try {
			assets = assetDao.getAssetsByOrder(idOrder);

		} catch (Exception e) {
			logger.error("in getAssetsByOrder method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return assets;
	}
}
