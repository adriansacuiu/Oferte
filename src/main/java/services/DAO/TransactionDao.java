package services.DAO;

import entities.Transaction;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import services.DAO.controller.SessionController;

import java.sql.Date;
import java.util.List;

@Repository
public class TransactionDao extends SessionController {
	private static final Logger logger = Logger.getLogger(TransactionDao.class);

	public boolean addTransaction(Transaction transaction) throws Exception {
		logger.info("Inside addTransaction method.");
		boolean result = false;

		try {
			getCurrentSession().save(transaction);
			result = true;

		} catch (Exception e) {
			logger.error("in addTransaction method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateTransaction(Transaction transaction) throws Exception {
		logger.info("Inside updateTransaction method.");
		boolean result = false;

		try {
			getCurrentSession().merge(transaction);
			result = true;

		} catch (Exception e) {
			logger.error("in updateTransaction method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteTransaction(Transaction transaction) throws Exception {
		logger.info("Inside deleteTransaction method.");
		boolean result = false;

		try {
			getCurrentSession().delete(transaction);
			result = true;

		} catch (Exception e) {
			logger.error("in deleteTransaction method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getAllTransactions() throws Exception {
		logger.info("Inside getAllTransactions method.");
		List<Transaction> transactions = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllTransactions");
			transactions = (List<Transaction>) query.list();
			
		} catch (HibernateException e) {
			logger.error("in getAllTransactions method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transactions;
	}

	public Transaction getTransactionById(Long id) throws Exception {
		logger.info("Inside getTransactionById method.");
		Transaction transaction = null;

		try {
			transaction = (Transaction) getCurrentSession().get(Transaction.class, id);
		} catch (Exception e) {
			logger.error("in getTransactionById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transaction;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactionsByStartDate(Date startDate) throws Exception {
		logger.info("Inside getTransactionsByStartDate method.");
		List<Transaction> transactions = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getTransactionsByStartDate");
			query.setParameter("startDate", startDate);

			transactions = (List<Transaction>) query.list();

		} catch (Exception e) {
			logger.error("in getTransactionsByStartDate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transactions;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactionsByEndDate(Date endDate) throws Exception {
		logger.info("Inside getTransactionsByEndDate method.");
		List<Transaction> transactions = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getTransactionsByEndDate");
			query.setParameter("endDate", endDate);

			transactions = (List<Transaction>) query.list();

		} catch (Exception e) {
			logger.error("in getTransactionsByEndDate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transactions;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactionsByStatus(String status) throws Exception {
		logger.info("Inside getTransactionsByStatus method.");
		List<Transaction> transactions = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getTransactionsByStatus");
			query.setParameter("status", status);

			transactions = (List<Transaction>) query.list();

		} catch (Exception e) {
			logger.error("in getTransactionsByStatus method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transactions;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactionsByUser(String username) throws Exception {
		logger.info("Inside getTransactionsByUser method.");
		List<Transaction> transactions = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getTransactionsByUser");
			query.setParameter("username", username);

			transactions = (List<Transaction>) query.list();

		} catch (Exception e) {
			logger.error("in getTransactionsByStatus method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transactions;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactionsByAsset(Long idAsset) throws Exception {
		logger.info("Inside getTransactionsByAsset method.");
		List<Transaction> transactions = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getTransactionsByAsset");
			query.setParameter("idAsset", idAsset);

			transactions = (List<Transaction>) query.list();

		} catch (Exception e) {
			logger.error("in getTransactionsByAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transactions;
	}
	
	public Transaction getPendingTransactionByUserAndAsset(Long idUser, Long idAsset) throws Exception {
		logger.info("Inside getPendingTransactionByUserAndAsset method.");
		Transaction transaction = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getPendingTransactionByUserAndAsset");
			query.setParameter("idUser", idUser);
			query.setParameter("idAsset", idAsset);

			transaction = (Transaction) query.uniqueResult();

		} catch (Exception e) {
			logger.error("in getPendingTransactionByUserAndAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return transaction;
	}
	
	public boolean declineTransactionsByIdAsset(Long idAsset) throws Exception {
		logger.info("Inside declineTransactionsByIdAsset method.");
		int updatedTransactions = 0;

		try {
			Query query = getCurrentSession().getNamedQuery("declineTransactionsByIdAsset");
			query.setParameter("idAsset", idAsset);

			updatedTransactions = query.executeUpdate();
			

		} catch (Exception e) {
			logger.error("in declineTransactionsByIdAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
		}

		return updatedTransactions > 0;
	}

}
