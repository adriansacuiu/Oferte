package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import entities.Client;
import services.DAO.controller.SessionController;

public class ClientDao extends SessionController {
	private static final Logger logger = Logger.getLogger(ClientDao.class);
	
	public boolean addClient(Client client) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().save(client);
			result = true;

		} catch (Exception e) {
			logger.error("in addClient method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateClient(Client client) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().merge(client);
			result = true;

		} catch (Exception e) {
			logger.error("in updateClient method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteClient(Client client) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().delete(client);

		} catch (Exception e) {
			logger.error("in deleteClient method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() throws Exception {
		List<Client> clients = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllClients");
			clients = (List<Client>) query.list();

		} catch (Exception e) {
			logger.error("in getAllClients method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return clients;
	}

	public Client getClientById(Long id) throws Exception {
		Client client = null;

		try {
			client = (Client) getCurrentSession().get(Client.class, id);

		} catch (Exception e) {
			logger.error("in getClientById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return client;
	}
}
