package com.adcamp.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adcamp.AdCampaignException;
import com.adcamp.dao.AdCampaignDao;
import com.adcamp.domain.AdCampaign;
import com.adcamp.domain.Result;

@RestController
public class AdCampaignController {

	@Autowired
	private AdCampaignDao dao;

	public AdCampaignDao getDao() {
		return dao;
	}

	public void setDao(AdCampaignDao dao) {
		this.dao = dao;
	}

	private static final Logger logger = Logger.getLogger(AdCampaignController.class);

	
	@POST
	@RequestMapping("/ad")
	public Result createAdCampaign(@RequestBody AdCampaign camp) {

		Result campResult = new Result();
 		logger.info("Creating add for Partner ID :  [" + camp.getPartnerId() + "], Duration : ["+ camp.getDuration() + "], Content: [" + camp.getAdContent()+"]");
		
		if (dao.isCampaignActive(camp.getPartnerId())) {
			campResult
					.setMessage("Sorry an active campaign for partner ID " + camp.getPartnerId() + " already exists.");
			campResult.setErrorCode("DuplicateEntry");
		} else {
			dao.createCampaign(camp);
			campResult.setMessage("Congratulations! Your Ad Campaign was created successfully!");
			campResult.setErrorCode("SUCCESS");
		}

		return campResult;
	}

	@GET
	@RequestMapping("/ad/{partnerId}")
	public AdCampaign getCampaign(@PathVariable String partnerId) throws AdCampaignException {

		logger.info("Retrieving Campaign for " + partnerId);
		
		AdCampaign camp = dao.getCampaign(partnerId);
		if (camp == null || !dao.isCampaignActive(partnerId)) {
			throw new AdCampaignException("No active Campaigns exist for given partener ID : " + partnerId);
		}

		return camp;
	}

}
