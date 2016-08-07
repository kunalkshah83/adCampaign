package com.adcamp;

import org.junit.Assert;
import org.junit.Test;

import com.adcamp.controller.AdCampaignController;
import com.adcamp.dao.AdCampaignDao;
import com.adcamp.domain.AdCampaign;

public class AdCampaignControllerTest {

	
	AdCampaignController controller = new AdCampaignController();	
	
	AdCampaignDao cmpDao = new AdCampaignDao();
	
	@Test
	public void testcreateAdCampaign(){
	   
		AdCampaign cm = new AdCampaign();	
		cm.setActive(true);
		cm.setDuration(10000);
		cm.setPartnerId("P345");
		cm.setAdContent("this is test content!");
		controller.setDao(cmpDao);
		controller.createAdCampaign(cm);
	    
		AdCampaign createdCmp = cmpDao.getCampaign("P345");
		
		Assert.assertNotNull(createdCmp);
		
		Assert.assertEquals(10000, createdCmp.getDuration());
	}
	
}
