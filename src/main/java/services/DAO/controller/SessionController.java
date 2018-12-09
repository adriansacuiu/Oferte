package services.DAO.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionController {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionController() {
		super();
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
