package com.trendyol.shoppingcard.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.shoppingcard.ApiEndpoints;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.request.CampaignDTORequest;
import com.trendyol.shoppingcard.response.CampaignListResponse;
import com.trendyol.shoppingcard.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiEndpoints.CAMPAIGN_API, produces = { ApiEndpoints.RESPONSE_CONTENTTYPE },
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Api(value = "campaign-api")
public class CampaignApiController {
	
	private CampaignService campaignService;

	@Autowired
	public CampaignApiController(CampaignService campaignService) {
		this.campaignService = campaignService;
	}
	
	@PostMapping(value = "/save")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "saveCampaign", notes = "save campaign")
	public SaveResponse saveCampaign(@RequestBody @Valid @NotNull CampaignDTORequest request) {
		return new SaveResponse(campaignService.createCampaign(request.getCampaignDTO()));
	}
	
	@GetMapping(value = "/all", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "findAll", notes = "find all campaign")
	public CampaignListResponse findAll() {
		return new CampaignListResponse(campaignService.getAllCampaigns());
	}

}
