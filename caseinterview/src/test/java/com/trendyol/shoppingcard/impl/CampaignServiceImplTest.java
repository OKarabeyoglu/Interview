package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.repositories.CampaignRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class CampaignServiceImplTest {

	private CampaignRepository campaignRepository;

	private CampaignService controller;

	@Before
	public void setup() {
		this.campaignRepository = Mockito.mock(CampaignRepository.class);
		controller = new CampaignServiceImpl(campaignRepository);
	}

	@Test
	public void testCreateCampaign() throws Exception {
		CampaignDTO campaignDTO = MockDataGenerator.createCampaignDTO();
		Campaign model = Campaign.toModel(campaignDTO);
		Mockito.when(campaignRepository.save(Mockito.any())).thenReturn(model);
		Long id = controller.createCampaign(campaignDTO);
		assertThat(id).isNotNull();
	}

	@Test
	public void testCreateCampaignDTOIsNull() throws Exception {
		CampaignDTO campaignDTO = null;
		controller.createCampaign(campaignDTO);
	}
	
	@Test
	public void testGetAllCampaignsReturnNull() throws Exception {
		Mockito.when(campaignRepository.findAll()).thenReturn(null);
		List<CampaignDTO> result= controller.getAllCampaigns();
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetAllCampaigns() throws Exception {
		Mockito.when(campaignRepository.findAll()).thenReturn(MockDataGenerator.toCampaignModelList());
		List<CampaignDTO> result= controller.getAllCampaigns();
		assertThat(result).isNotNull();
	}
	
}
