package services;

import entities.Country;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import services.DAO.CountryDao;

import java.util.List;

@Transactional
@Service
public class CountryService {
	private static final Logger logger = Logger.getLogger(CountryService.class);

	@Autowired
	private CountryDao countryDao;

	public boolean addCountry(Country country) throws Exception {
		logger.info("in addCountry method.");
		boolean result = false;

		try {
			result = countryDao.addCountry(country);

		} catch (Exception e) {
			logger.error("in addCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateCountry(Country country) throws Exception {
		logger.info("in updateCountry method.");
		boolean result = false;

		try {
			result = countryDao.updateCountry(country);

		} catch (Exception e) {
			logger.error("in updateCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteCountry(Country country) throws Exception {
		logger.info("in deleteCountry method.");
		boolean result = false;

		try {
			result = countryDao.deleteCountry(country);

		} catch (Exception e) {
			logger.error("in deleteCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Transactional(readOnly = true)
	public List<Country> getAllCountries() throws Exception {
		logger.info("in getAllCountries method.");
		List<Country> countries = null;

		try {
			countries = countryDao.getAllCountries();

		} catch (Exception e) {
			logger.error("in getAllCountries method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return countries;
	}

	@Transactional(readOnly = true)
	public Country getCountryById(Long id) throws Exception {
		logger.info("in getCountryById method.");
		Country country = null;

		try {
			country = countryDao.getCountryById(id);

		} catch (Exception e) {
			logger.error("in getCountryById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return country;
	}

	@Transactional(readOnly = true)
	public Country getCountryByName(String name) throws Exception {
		logger.info("in getCountryByName method.");
		Country country = null;

		try {
			country = countryDao.getCountryByName(name);

		} catch (Exception e) {
			logger.error("in getCountryByName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return country;
	}

	@Transactional(readOnly = true)
	public Country getCountryByCountryCode(String countryCode) throws Exception {
		logger.info("in getCountryByCountryCode method.");
		Country country = null;

		try {
			country = countryDao.getCountryByCountryCode(countryCode);

		} catch (Exception e) {
			logger.error("in getCountryByCountryCode method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return country;
	}
}
