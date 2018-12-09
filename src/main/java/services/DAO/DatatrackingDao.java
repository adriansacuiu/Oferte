package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import entities.Datatracking;
import services.DAO.controller.SessionController;

public class DatatrackingDao extends SessionController {

	private static final Logger logger = Logger.getLogger(DatatrackingDao.class);
	
	public boolean addDatatracking(Datatracking datatracking) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().save(datatracking);
			result = true;

		} catch (Exception e) {
			logger.error("in addDatatracking method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateDatatracking(Datatracking datatracking) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().merge(datatracking);
			result = true;

		} catch (Exception e) {
			logger.error("in updateDatatracking method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteDatatracking(Datatracking datatracking) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().delete(datatracking);

		} catch (Exception e) {
			logger.error("in deleteDatatracking method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Datatracking> getAllDatatrackings() throws Exception {
		List<Datatracking> datatrackings = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllDatatrackings");
			datatrackings = (List<Datatracking>) query.list();

		} catch (Exception e) {
			logger.error("in getAllDatatrackings method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return datatrackings;
	}

	public Datatracking getDatatrackingById(Long id) throws Exception {
		Datatracking datatracking = null;

		try {
			datatracking = (Datatracking) getCurrentSession().get(Datatracking.class, id);

		} catch (Exception e) {
			logger.error("in getDatatrackingById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return datatracking;
	}
}
