package com.trendyol.shoppingcard.impl;

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
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.repositories.CampaignRepository;
import com.trendyol.shoppingcard.util.DiscountType;

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
		Campaign model = Campaign.toModel(campaignDTO);
		Mockito.when(campaignRepository.save(Mockito.any())).thenReturn(model);
		Long id = controller.createCampaign(campaignDTO);
		assertThat(id, is(notNullValue()));
	}
	
	@Test(expected = Exception.class)
	public void testCreateCampaignDTOIsNull() throws Exception {
		CampaignDTO campaignDTO = null;
		controller.createCampaign(campaignDTO);
	}
}
