package com.trendyol.shoppingcard.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.domain.Campaign;
import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.repository.CampaignRepository;

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
		Campaign model = Campaign.toModel(campaignDTO);
		return model != null ? campaignRepository.save(Campaign.toModel(campaignDTO)).getId() : null;
	}
	
	@Override
	public List<CampaignDTO> getAllCampaigns(){
		List<Campaign> campaignModelList = campaignRepository.findAll();
		return Campaign.toDTOList(campaignModelList);
	}

}
