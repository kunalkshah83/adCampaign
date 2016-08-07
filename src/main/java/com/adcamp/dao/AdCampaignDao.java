package com.adcamp.dao;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.adcamp.domain.AdCampaign;

@Component
public class AdCampaignDao {

	private static ConcurrentHashMap<String, AdCampaign> inMemoryDb = new ConcurrentHashMap<>();

	public void createCampaign(AdCampaign camp) {
		camp.setCreateTime(System.currentTimeMillis());
		inMemoryDb.put(camp.getPartnerId(), camp);
	}

	public AdCampaign getCampaign(String partnerId) {
		return inMemoryDb.get(partnerId);
	}

	public boolean doesCampaignExist(String partnerId) {
		return inMemoryDb.keySet().contains(partnerId);
	}

	public boolean isCampaignActive(String partnerId) {
		boolean active = false;

		if (doesCampaignExist(partnerId)) {
			long currentTime = System.currentTimeMillis();
			AdCampaign camp = inMemoryDb.get(partnerId);
			active = currentTime < camp.getCreateTime() + camp.getDuration();
		}

		return active;
	}

}
