package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import entities.Quote;
import services.DAO.controller.SessionController;

public class QuoteDao extends SessionController {
	private static final Logger logger = Logger.getLogger(QuoteDao.class);
	
	public boolean addQuote(Quote quote) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().save(quote);
			result = true;

		} catch (Exception e) {
			logger.error("in addQuote method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateQuote(Quote quote) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().merge(quote);
			result = true;

		} catch (Exception e) {
			logger.error("in updateQuote method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteQuote(Quote quote) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().delete(quote);

		} catch (Exception e) {
			logger.error("in deleteQuote method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Quote> getAllQuotes() throws Exception {
		List<Quote> quotes = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllQuotes");
			quotes = (List<Quote>) query.list();

		} catch (Exception e) {
			logger.error("in getAllQuotes method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return quotes;
	}

	public Quote getQuoteById(Long id) throws Exception {
		Quote quote = null;

		try {
			quote = (Quote) getCurrentSession().get(Quote.class, id);

		} catch (Exception e) {
			logger.error("in getQuoteById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return quote;
	}
}
