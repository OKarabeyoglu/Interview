package com.trendyol.shoppingcard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.request.CampaignDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;
import com.trendyol.shoppingcard.util.DiscountType;

public class CampaignApiControllerTest {
	
	private CampaignService campaignService;

	private CampaignApiController controller;

	@Before
	public void setup() {
		this.campaignService = Mockito.mock(CampaignService.class);
		controller = new CampaignApiController(campaignService);
	}

	@Test
	public void testSaveCampaign() throws Exception {
		CampaignDTO campaignDTO = new CampaignDTO();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		categoryDTO.setId(1L);
		categoryDTOList.add(categoryDTO);
		campaignDTO.setCategoryDTOList(categoryDTOList);
		campaignDTO.setDiscountAmount(new BigDecimal(10));
		campaignDTO.setDiscountType(DiscountType.AMOUNT);
		campaignDTO.setMinNumberOfProducts(3);
		campaignDTO.setId(1L);
		Mockito.when(campaignService.createCampaign(campaignDTO)).thenReturn(1L);
		CampaignDTORequest request = new CampaignDTORequest();
		request.setCampaignDTO(campaignDTO);
		SaveResponse response = controller.saveCampaign(request);
		assertThat(response, is(notNullValue()));
	}
}
