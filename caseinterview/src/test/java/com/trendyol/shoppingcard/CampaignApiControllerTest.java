package com.trendyol.shoppingcard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.request.CampaignDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;

@RunWith(BlockJUnit4ClassRunner.class)
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
		CampaignDTO campaignDTO = MockDataGenerator.createCampaignDTO();
		Mockito.when(campaignService.createCampaign(campaignDTO)).thenReturn(1L);
		CampaignDTORequest request = new CampaignDTORequest();
		request.setCampaignDTO(campaignDTO);
		SaveResponse response = controller.saveCampaign(request);
		assertThat(response).isNotNull();
	}
}
