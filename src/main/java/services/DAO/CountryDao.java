package services.DAO;

import entities.Country;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import services.DAO.controller.SessionController;

import java.util.List;

@Repository
public class CountryDao extends SessionController {
	private static final Logger logger = Logger.getLogger(CountryDao.class);

	public boolean addCountry(Country country) throws Exception {
		logger.info("Inside addCountry method.");
		boolean result = false;

		try {
			getCurrentSession().save(country);
			result = true;

		} catch (Exception e) {
			logger.error("in addCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateCountry(Country country) throws Exception {
		logger.info("Inside updateCountry method.");
		boolean result = false;

		try {
			getCurrentSession().merge(country);
			result = true;

		} catch (Exception e) {
			logger.error("in updateCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteCountry(Country country) throws Exception {
		logger.info("Inside deleteCountry method.");
		boolean result = false;

		try {
			getCurrentSession().delete(country);
			result = true;

		} catch (Exception e) {
			logger.error("in deleteCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Country> getAllCountries() throws Exception {
		logger.info("Inside getAllCountries method.");
		List<Country> countries = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllCountries");
			countries = (List<Country>) query.list();

		} catch (Exception e) {
			logger.error("in getAllCountrys method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return countries;
	}

	public Country getCountryById(Long id) throws Exception {
		logger.info("Inside getCountryById method.");
		Country country = null;

		try {
			country = (Country) getCurrentSession().get(Country.class, id);
		} catch (Exception e) {
			logger.error("in getCountryById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return country;
	}

	public Country getCountryByName(String name) throws Exception {
		logger.info("Inside getCountryByName method.");
		Country country = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getCountryByName");
			query.setParameter("name", name);

			country = (Country) query.uniqueResult();

		} catch (Exception e) {
			logger.error("in getCountryByName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return country;
	}

	public Country getCountryByCountryCode(String countryCode) throws Exception {
		logger.info("Inside getCountryByCountryCode method.");
		Country country = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getCountryByCountryCode");
			query.setParameter("countryCode", countryCode);

			country = (Country) query.uniqueResult();

		} catch (Exception e) {
			logger.error("in getCountryByCountryCode method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return country;
	}

}
