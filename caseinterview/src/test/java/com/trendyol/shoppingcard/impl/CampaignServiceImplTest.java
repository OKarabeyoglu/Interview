package com.trendyol.shoppingcard.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.repositories.CampaignRepository;
import com.trendyol.shoppingcard.util.DiscountType;

@SpringBootTest
public class CampaignServiceImplTest {

	@Mock
	private CampaignRepository campaignRepository;

	@InjectMocks
	private CampaignService controller;
	
	@Test
	public void testCampaignCategory() throws Exception {
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
		Mockito.when(campaignRepository.save(model)).thenReturn(model);
		Long id = controller.createCampaign(campaignDTO);
	    assertEquals(1L, id);
	}
	
	@Test(expected = Exception.class)
	public void testCreateProductDTOIsNull() throws Exception {
		CampaignDTO campaignDTO = null;
		controller.createCampaign(campaignDTO);
	}
}
