package com.trendyol.shoppingcard.intf;

import java.util.List;

import com.trendyol.shoppingcard.dto.CampaignDTO;

public interface CampaignService {
	public Long createCampaign(CampaignDTO campaignDTO);

	List<CampaignDTO> getAllCampaigns();
}
