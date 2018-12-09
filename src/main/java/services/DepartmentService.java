package services;

import entities.Department;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import services.DAO.DepartmentDao;

import java.util.List;

@Transactional
@Service
public class DepartmentService {
	private static final Logger logger = Logger.getLogger(DepartmentService.class);

	@Autowired
	private DepartmentDao departmentDao;

	public boolean addDepartment(Department department) throws Exception {
		logger.info("Inside addDepartment method.");
		boolean result = false;

		try {
			result = departmentDao.addDepartment(department);

		} catch (Exception e) {
			logger.error("in addDepartment method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean updateDepartment(Department department) throws Exception {
		logger.info("Inside updateDepartment method.");
		boolean result = false;

		try {
			result = departmentDao.updateDepartment(department);

		} catch (Exception e) {
			logger.error("in updateDepartment method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public boolean deleteDepartment(Department department) throws Exception {
		logger.info("Inside deleteDepartment method.");
		boolean result = false;

		try {
			result = departmentDao.deleteDepartment(department);

		} catch (Exception e) {
			logger.error("in deleteDepartment method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Transactional(readOnly = true)
	public List<Department> getAllDepartments() throws Exception {
		logger.info("Inside getAllDepartments method.");
		List<Department> departments = null;

		try {
			departments = departmentDao.getAllDepartments();

		} catch (Exception e) {
			logger.error("in getAllDepartments method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return departments;
	}

	@Transactional(readOnly = true)
	public Department getDepartmentById(Long id) throws Exception {
		logger.info("Inside getDepartmentById method.");
		Department department = null;

		try {
			department = departmentDao.getDepartmentById(id);

		} catch (Exception e) {
			logger.error("in getDepartmentById method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return department;
	}

	@Transactional(readOnly = true)
	public List<Department> getDepartmentsByName(String name) throws Exception {
		logger.info("Inside getDepartmentsByName method.");
		List<Department> departments = null;

		try {
			departments = departmentDao.getDepartmentsByName(name);

		} catch (Exception e) {
			logger.error("in getDepartmentsByName method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return departments;
	}

	@Transactional(readOnly = true)
	public List<Department> getDepartmentsByLocation(String location) throws Exception {
		logger.info("Inside getDepartmentsByLocation method.");
		List<Department> departments = null;

		try {
			departments = departmentDao.getDepartmentsByLocation(location);

		} catch (Exception e) {
			logger.error("in getDepartmentsByLocation method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return departments;
	}

	@Transactional(readOnly = true)
	public List<Department> getDepartmentsByAddress(String address) throws Exception {
		logger.info("Inside getDepartmentsByAddress method.");
		List<Department> departments = null;

		try {
			departments = departmentDao.getDepartmentsByAddress(address);

		} catch (Exception e) {
			logger.error("in getDepartmentsByAddress method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return departments;
	}
	
	@Transactional(readOnly = true)
	public List<Department> getDepartmentsByCountry(Long idCountry) throws Exception {
		logger.info("Inside getDepartmentsByCountry method.");
		List<Department> departments = null;

		try {
			departments = departmentDao.getDepartmentsByCountry(idCountry);

		} catch (Exception e) {
			logger.error("in getDepartmentsByCountry method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		}

		return departments;
	}
}
