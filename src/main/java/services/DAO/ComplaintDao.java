package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import entities.Complaint;
import services.DAO.controller.SessionController;

@Repository
public class ComplaintDao extends SessionController {
	private static final Logger logger = Logger.getLogger(ComplaintDao.class);

	public boolean addComplaint(Complaint complaint) throws Exception {
		logger.info("Inside addComplaint method.");
		boolean result = false;

		try {
			getCurrentSession().save(complaint);
			result = true;

		} catch (Exception e) {
			logger.error("in addComplaint method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateComplaint(Complaint complaint) throws Exception {
		logger.info("Inside updateComplaint method.");
		boolean result = false;

		try {
			getCurrentSession().merge(complaint);
			result = true;

		} catch (Exception e) {
			logger.error("in updateComplaint method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteComplaint(Complaint complaint) throws Exception {
		logger.info("Inside deleteComplaint method.");
		boolean result = false;

		try {
			getCurrentSession().delete(complaint);
			result = true;

		} catch (Exception e) {
			logger.error("in deleteComplaint method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Complaint> getAllComplaints() throws Exception {
		logger.info("Inside getAllComplaints method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllComplaints");
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getAllComplaints method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}

	public Complaint getComplaintById(Long id) throws Exception {
		logger.info("Inside getComplaintById method.");
		Complaint complaint = null;

		try {
			complaint = (Complaint) getCurrentSession().get(Complaint.class, id);

		} catch (Exception e) {
			logger.error("in getComplaintById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaint;
	}

	@SuppressWarnings("unchecked")
	public List<Complaint> getComplaintsByTitle(String title) throws Exception {
		logger.info("Inside getComplaintsByTitle method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getComplaintsByTitle");
			query.setParameter("title", title);
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getComplaintsByTitle method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}

	@SuppressWarnings("unchecked")
	public List<Complaint> getComplaintsByDescription(String description) throws Exception {
		logger.info("Inside getComplaintsByDescription method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getComplaintsByTitle");
			query.setParameter("description", description);
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getComplaintsByDescription method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}

	@SuppressWarnings("unchecked")
	public List<Complaint> getComplaintsByPriority(String priority) throws Exception {
		logger.info("Inside getComplaintsByPriority method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getComplaintsByPriority");
			query.setParameter("priority", priority);
			
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getComplaintsByPriority method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}

	@SuppressWarnings("unchecked")
	public List<Complaint> getComplaintsByUser(String username) throws Exception {
		logger.info("Inside getComplaintsByUser method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getComplaintsByUser");
			query.setParameter("username", username);
			
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getComplaintsByUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}
	
	@SuppressWarnings("unchecked")
	public List<Complaint> getComplaintsByStatus(String status) throws Exception {
		logger.info("Inside getComplaintsByStatus method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getComplaintsByStatus");
			query.setParameter("status", status);
			
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getComplaintsByStatus method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}
	
	@SuppressWarnings("unchecked")
	public List<Complaint> getComplaintsByAsset(Long idAsset) throws Exception {
		logger.info("Inside getComplaintsByAsset method.");
		List<Complaint> complaints = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getComplaintsByAsset");
			query.setParameter("idAsset", idAsset);
			
			complaints = (List<Complaint>) query.list();

		} catch (Exception e) {
			logger.error("in getComplaintsByAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return complaints;
	}
}
