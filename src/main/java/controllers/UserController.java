package controllers;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import services.AssetService;
import services.ComplaintService;
import services.RequestService;
import services.TransactionService;
import services.UsersService;
import entities.Asset;
import entities.Complaint;
import entities.Request;
import entities.Transaction;
import entities.User;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UsersService usersService;

	@Autowired
	private AssetService assetService;

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private TransactionService transactionService;

	@PreAuthorize("(hasRole('ROLE_USER') and #username == principal.username)")
	@RequestMapping(value = "{username}/createRequest", produces = "application/json")
	@ResponseBody
	public Map<String, Object> createRequest(@PathVariable String username, HttpServletRequest request) {
		logger.info("Inside createRequest method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Request newRequest = null;
		Long idAsset = 0L;

		try {
			idAsset = Long.parseLong(request.getParameter("idAsset"));

			User user = usersService.getUserByUsername(username);
			Asset asset = assetService.getAssetById(idAsset);

			newRequest = requestService.getNewRequestByUserAndAsset(user.getIdUser(), idAsset);
			
			if(newRequest == null){
				newRequest = new Request();
				newRequest.setUser(user);
				newRequest.setAsset(asset);
				newRequest.setDate(new Date(System.currentTimeMillis()));
				newRequest.setStatus("New");
	
				boolean result = requestService.addRequest(newRequest);
				if (result) {
					resultMap.put("status", "true");
					resultMap.put("message", "Request created successfully!");
	
				} else {
					logger.error("Error creating new request!");
					resultMap.put("status", "false");
					resultMap.put("message", "Error creating new request!");
				}
			}

		} catch (Exception e) {
			logger.error("in createRequest method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error creating new request!");
		}

		return resultMap;
	}

	@PreAuthorize("hasRole('ROLE_USER') and #username == principal.username")
	@RequestMapping(value = "userAssets/{username}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUserAssets(@PathVariable String username){
		logger.info("Inside getUserAssets method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Asset> userAssets = null;
		
		try{
			userAssets = assetService.getAssetsByUser(username);
			resultMap.put("status", "true");
			resultMap.put("message", userAssets);
			
		} catch(Exception e){
			logger.error("in getUserAssets method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting user assets!");
		}
		
		return resultMap;
	}
	
	@PreAuthorize("hasRole('ROLE_USER') and #username == principal.username")
	@RequestMapping(value = "userComplaints/{username}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUserComplaints(@PathVariable String username){
		logger.info("Inside getUserComplaints method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Complaint> userComplaints = null;
		
		try{
			userComplaints = complaintService.getComplaintsByUser(username);
			resultMap.put("status", "true");
			resultMap.put("message", userComplaints);
			
		} catch(Exception e){
			logger.error("in getUserComplaints method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting user complaints!");
		}
		
		return resultMap;
	}
	
	@PreAuthorize("hasRole('ROLE_USER') and #username == principal.username")
	@RequestMapping(value = "userRequests/{username}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUserRequests(@PathVariable String username){
		logger.info("Inside getUserRequests method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Request> userRequests = null;
		
		try{
			userRequests = requestService.getRequetsByUser(username);
			resultMap.put("status", "true");
			resultMap.put("message", userRequests);
			
		} catch(Exception e){
			logger.error("in getUserRequests method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting user requests!");
		}
		
		return resultMap;
	}
	
	@PreAuthorize("hasRole('ROLE_USER') and #username == principal.username")
	@RequestMapping(value = "userTransactions/{username}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUserTransactions(@PathVariable String username){
		logger.info("Inside getUserTransactions method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Transaction> userTransactions = null;
		
		
		try{
			userTransactions = transactionService.getTransactionsByUser(username);
			resultMap.put("status", "true");
			resultMap.put("message", userTransactions);
			
		} catch(Exception e){
			logger.error("in getUserTransactions method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting user transactions!");
		}
		
		return resultMap;
	}
	
	@PreAuthorize("(hasRole('ROLE_USER') and #username == principal.username)")
	@RequestMapping(value = "{username}/createComplaint", produces = "application/json")
	@ResponseBody
	public Map<String, Object> createComplaint(@PathVariable String username, HttpServletRequest request) {
		logger.info("Inside createComplaint method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Complaint complaint = new Complaint();
		Long idAsset = 0L;

		try {
			complaint.setTitle(request.getParameter("title"));
			complaint.setDescription(request.getParameter("description"));
			complaint.setPriority(request.getParameter("priority"));
			idAsset = Long.parseLong(request.getParameter("idAsset"));

			User user = usersService.getUserByUsername(username);
			Asset asset = assetService.getAssetById(idAsset);

			complaint.setUser(user);
			complaint.setAsset(asset);

			boolean result = complaintService.addComplaint(complaint);
			if (result) {
				resultMap.put("status", "true");
				resultMap.put("message", "Complaint created successfully!");

			} else {
				logger.error("Error creating new request!");
				resultMap.put("status", "false");
				resultMap.put("message", "Error creating new complaint!");
			}

		} catch (Exception e) {
			logger.error("in createComplaint method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error creating new complaint!");
		}

		return resultMap;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "availableAssets", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAvailableAssets(){
		logger.info("Inside getAvailableAssets method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Asset> assets = null;
		
		try{
			assets = assetService.getAvailableAssets();
			resultMap.put("status", "true");
			resultMap.put("message", assets);
			
		} catch(Exception e){
			logger.error("in getAvailableAssets method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting available assets!");
		}
		
		return resultMap;
	}
	
	@PreAuthorize("(hasRole('ROLE_USER') and #username == principal.username)")
	@RequestMapping(value = "{username}/removeAsset", produces = "application/json")
	@ResponseBody
	public Map<String, String> removeAsset(@PathVariable String username, HttpServletRequest request) {
		logger.info("Inside removeAsset method");
		Map<String, String> resultMap = new HashMap<String, String>();
		Long idAsset = 0L;

		try {
			idAsset = Long.parseLong(request.getParameter("idAsset"));
			Asset asset = assetService.getAssetById(idAsset);
			if (asset.getUser().getUsername().equals(username)) {

				asset.setUser(null);
				asset.setIsAvailable(true);

				boolean result = assetService.updateAsset(asset);
				if (result) {
					resultMap.put("status", "true");
					resultMap.put("message", "Asset removed successfully!");

				} else {
					logger.error("Error creating new request!");
					resultMap.put("status", "false");
					resultMap.put("message", "Error removing asset!");
				}
				
			} else {
				logger.error("Error creating new request!");
				resultMap.put("status", "false");
				resultMap.put("message", "Error removing asset! This asset doesn't belong to you!");
			}

		} catch (Exception e) {
			logger.error("in removeAsset method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error removing asset!");
		}

		return resultMap;
	}
	
	@PreAuthorize("(hasRole('ROLE_USER') and #username == principal.username)")
	@RequestMapping(value = "{username}/viewRequestUser/{idRequest}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> viewRequestUser(@PathVariable String username, @PathVariable Long idRequest) {
		logger.info("Inside viewRequestUser method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Request request = null;
		Asset asset = null;

		try {
			request = requestService.getRequestById(idRequest);
			Long idAsset = request.getAsset().getIdAsset();
			asset = assetService.getAssetById(idAsset);
			
			resultMap.put("status", "true");
			resultMap.put("request", request);
			resultMap.put("asset", asset);

		} catch (Exception e) {
			logger.error("in viewRequestUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting request!");
		}

		return resultMap;
	}
	
	@PreAuthorize("(hasRole('ROLE_USER') and #username == principal.username)")
	@RequestMapping(value = "{username}/viewTransactionUser/{idTransaction}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> viewTransactionUser(@PathVariable String username, @PathVariable Long idTransaction) {
		logger.info("Inside viewTransactionUser method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Transaction transaction = null;
		Asset asset = null;

		try {
			transaction = transactionService.getTransactionById(idTransaction);
			Long idAsset = transaction.getAsset().getIdAsset();
			asset = assetService.getAssetById(idAsset);
			
			resultMap.put("status", "true");
			resultMap.put("transaction", transaction);
			resultMap.put("asset", asset);

		} catch (Exception e) {
			logger.error("in viewTransactionUser method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting transaction!");
		}

		return resultMap;
	}
	
	@PreAuthorize("(hasRole('ROLE_USER') and #username == principal.username)")
	@RequestMapping(value = "{username}/userViewComplaint/{idComplaint}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> userViewComplaint(@PathVariable String username, @PathVariable Long idComplaint) {
		logger.info("Inside userViewComplaint method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Complaint complaint = null;
		Asset asset = null;
		
		try {
			complaint = complaintService.getComplaintById(idComplaint);
			asset = complaint.getAsset();
			
			resultMap.put("status", "true");
			resultMap.put("complaint", complaint);
			resultMap.put("asset", asset);

		} catch (Exception e) {
			logger.error("in userViewComplaint method Exception: " + e.getMessage() + "; Cause: " + e.getCause());
			e.printStackTrace();
			resultMap.put("status", "false");
			resultMap.put("message", "Error getting complaint!");
		}

		return resultMap;
	}

}
