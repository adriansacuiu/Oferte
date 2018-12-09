package services.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import entities.Car;
import services.DAO.controller.SessionController;

public class CarDao extends SessionController {
	private static final Logger logger = Logger.getLogger(CarDao.class);
	
	public boolean addCar(Car car) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().save(car);
			result = true;

		} catch (Exception e) {
			logger.error("in addCar method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateCar(Car car) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().merge(car);
			result = true;

		} catch (Exception e) {
			logger.error("in updateCar method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteCar(Car car) throws Exception {
		boolean result = false;

		try {
			getCurrentSession().delete(car);

		} catch (Exception e) {
			logger.error("in deleteCar method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Car> getAllCars() throws Exception {
		List<Car> cars = null;

		try {
			Query query = getCurrentSession().getNamedQuery("getAllClients");
			cars = (List<Car>) query.list();

		} catch (Exception e) {
			logger.error("in getAllCars method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return cars;
	}

	public Car getCarById(Long id) throws Exception {
		Car car = null;

		try {
			car = (Car) getCurrentSession().get(Car.class, id);

		} catch (Exception e) {
			logger.error("in getCarById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return car;
	}
}
