package services;

import entities.Transaction;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import services.DAO.TransactionDao;
import services.DAO.UserDao;
import util.TransactionComparator;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class TransactionService {
	private static final Logger logger = Logger.getLogger(TransactionService.class);

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private UserDao userDao;

	public boolean addTransaction(Transaction transaction) throws Exception {
		logger.info("in addTransaction method.");
		boolean result = false;

		try {
			transaction.setStartDate(new Date(System.currentTimeMillis()));
			transaction.setStatus("Pending");
			
			result = transactionDao.addTransaction(transaction);

		} catch (Exception e) {
			logger.error("in addTransaction method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateTransaction(Transaction transaction) throws Exception {
		logger.info("in updateTransaction method.");
		boolean result = false;

		try {
			result = transactionDao.updateTransaction(transaction);

		} catch (Exception e) {
			logger.error("in updateTransaction method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteTransaction(Transaction transaction) throws Exception {
		logger.info("in deleteTransaction method.");
		boolean result = false;

		try {
			result = transactionDao.deleteTransaction(transaction);

		} catch (Exception e) {
			logger.error("in deleteTransaction method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Transactional(readOnly = true)
	public List<Transaction> getAllTransactions() throws Exception {
		logger.info("in getAllTransactions method.");
		List<Transaction> transactions = null;

		try {
			transactions = transactionDao.getAllTransactions();
			Collections.sort(transactions, new TransactionComparator());

		} catch (Exception e) {
			logger.error("in getAllTransactions method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transactions;
	}

	@Transactional(readOnly = true)
	public Transaction getTransactionById(Long id) throws Exception {
		logger.info("in getTransactionById method.");
		Transaction transaction = null;

		try {
			transaction = transactionDao.getTransactionById(id);

		} catch (Exception e) {
			logger.error("in getTransactionById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transaction;
	}

	@Transactional(readOnly = true)
	public List<Transaction> getTransactionsByStartDate(Date startDate) throws Exception {
		logger.info("in getTransactionsByStatDate method.");
		List<Transaction> transactions = null;

		try {
			transactions = transactionDao.getTransactionsByStartDate(startDate);
			Collections.sort(transactions, new TransactionComparator());

		} catch (Exception e) {
			logger.error("in getTransactionsByStartDate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transactions;
	}
	
	@Transactional(readOnly = true)
	public List<Transaction> getTransactionsByEndDate(Date endDate) throws Exception {
		logger.info("in getTransactionsByEndDate method.");
		List<Transaction> transactions = null;

		try {
			transactions = transactionDao.getTransactionsByEndDate(endDate);
			Collections.sort(transactions, new TransactionComparator());

		} catch (Exception e) {
			logger.error("in getTransactionsByEndDate method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transactions;
	}

	@Transactional(readOnly = true)
	public List<Transaction> getTransactionsByStatus(String status) throws Exception {
		logger.info("in getTransactionsByStatus method.");
		List<Transaction> transactions = null;

		try {
			transactions = transactionDao.getTransactionsByStatus(status);
			Collections.sort(transactions, new TransactionComparator());

		} catch (Exception e) {
			logger.error("in getTransactionsByStatus method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transactions;
	}

	@Transactional(readOnly = true)
	public List<Transaction> getTransactionsByUser(String username) throws Exception {
		logger.info("Inside getTransactionsByUser method.");
		List<Transaction> transactions = null;

		try {
			transactions = transactionDao.getTransactionsByUser(username);
			Collections.sort(transactions, new TransactionComparator());

		} catch (Exception e) {
			logger.error("in getTransactionsByUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transactions;
	}
	
	@Transactional(readOnly = true)
	public List<Transaction> getTransactionsByAsset(Long idAsset) throws Exception {
		logger.info("Inside getTransactionsByAsset method.");
		List<Transaction> transactions = null;

		try {
			transactions = transactionDao.getTransactionsByAsset(idAsset);
			Collections.sort(transactions, new TransactionComparator());

		} catch (Exception e) {
			logger.error("in getTransactionsByAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transactions;
	}
	
	@Transactional(readOnly = true)
	public Transaction getPendingTransactionByUserAndAsset(Long idUser, Long idAsset) throws Exception {
		logger.info("Inside getPendingTransactionsByUserAndAsset method.");
		Transaction transaction = null;

		try {
			transaction = transactionDao.getPendingTransactionByUserAndAsset(idUser, idAsset);

		} catch (Exception e) {
			logger.error("in getPendingTransactionByUserAndAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return transaction;
	}
	
	public boolean declineTransactionsByIdAsset(Long idAsset) throws Exception {
		logger.info("Inside declineTransactionsByIdAsset method.");
		boolean result = false;

		try {
			result = transactionDao.declineTransactionsByIdAsset(idAsset);

		} catch (Exception e) {
			logger.error("in declineTransactionsByIdAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			throw e;
		}

		return result;
	}

}
