package com.trendyol.shoppingcard.request;

import com.trendyol.shoppingcard.dto.CampaignDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class CampaignDTORequest {
	@ApiModelProperty(value = "campaignDTO", dataType = "CampaignDTO")
	private CampaignDTO campaignDTO;
}
