package com.trendyol.shoppingcard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.repositories.CampaignRepository;
import com.trendyol.shoppingcard.util.ExceptionDefinition;

@Transactional
@Service
public class CampaignServiceImpl implements CampaignService  {
	
	private CampaignRepository campaignRepository;

	@Autowired
	public CampaignServiceImpl(CampaignRepository campaignRepository) {
		this.campaignRepository = campaignRepository;
	}

	@Override
	public Long createCampaign(CampaignDTO campaignDTO) throws Exception {
		if (campaignDTO == null) {
			throw new Exception(ExceptionDefinition.PRODUCT_DTO_CAN_NOT_BE_NULL_ERROR.getExcetionDefinition());
		}
		return campaignRepository.save(Campaign.toModel(campaignDTO)).getId();
	}

}
