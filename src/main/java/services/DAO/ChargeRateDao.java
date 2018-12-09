package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import entities.ChargeRate;
import services.DAO.controller.SessionController;

public class ChargeRateDao extends SessionController {
	private static final Logger logger = Logger.getLogger(ChargeRateDao.class);
	
	public boolean addChargeRate(ChargeRate chargeRate) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().save(chargeRate);
			result = true;

		} catch (Exception e) {
			logger.error("in addChargeRate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateChargeRate(ChargeRate chargeRate) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().merge(chargeRate);
			result = true;

		} catch (Exception e) {
			logger.error("in updateChargeRate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteChargeRate(ChargeRate chargeRate) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().delete(chargeRate);

		} catch (Exception e) {
			logger.error("in deleteChargeRate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ChargeRate> getAllChargeRates() throws Exception {
		List<ChargeRate> chargeRates = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllClients");
			chargeRates = (List<ChargeRate>) query.list();

		} catch (Exception e) {
			logger.error("in getAllChargeRates method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return chargeRates;
	}

	public ChargeRate getChargeRateById(Long id) throws Exception {
		ChargeRate chargeRate = null;

		try {
			chargeRate = (ChargeRate) getCurrentSession().get(ChargeRate.class, id);

		} catch (Exception e) {
			logger.error("in getChargeRateById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return chargeRate;
	}
}
