package com.trendyol.shoppingcard.response;

import java.io.Serializable;
import java.util.List;

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
public class CampaignListResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "campaignDTOList", dataType = "List<CampaignDTO>")
	private List<CampaignDTO> campaignDTOList;

	public CampaignListResponse(List<CampaignDTO> campaignDTOList) {
		this.campaignDTOList = campaignDTOList;
	}
}
