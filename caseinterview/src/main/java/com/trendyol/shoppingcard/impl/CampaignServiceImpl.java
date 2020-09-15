package com.trendyol.shoppingcard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.repositories.CampaignRepository;

@Transactional
@Service
public class CampaignServiceImpl implements CampaignService  {
	
	private CampaignRepository campaignRepository;

	@Autowired
	public CampaignServiceImpl(CampaignRepository campaignRepository) {
		this.campaignRepository = campaignRepository;
	}

	@Override
	public Long createCampaign(CampaignDTO campaignDTO) {
		return campaignRepository.save(Campaign.toModel(campaignDTO)).getId();
	}

}
