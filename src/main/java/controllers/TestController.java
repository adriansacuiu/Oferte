package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entities.Asset;
import services.AssetService;

@Controller
public class TestController {
	private static final Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private AssetService assetService;
	
	@RequestMapping(value = "/testController")
	@ResponseBody
	public Map<String, Object> test(ModelAndView modelAndView){
		
		logger.info("Inside test method");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Asset> assets = null;
		
		try {
			assets = assetService.getAvailableAssets();
			resultMap.put("status", "true");
			resultMap.put("message", assets);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return resultMap;
	}
}
