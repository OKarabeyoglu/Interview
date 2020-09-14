package com.trendyol.shoppingcard.request;

import com.trendyol.shoppingcard.dto.CampaignDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class CampaignDTORequest {
	@ApiModelProperty(value = "campaignDTO", dataType = "CampaignDTO")
	private CampaignDTO campaignDTO;
}
