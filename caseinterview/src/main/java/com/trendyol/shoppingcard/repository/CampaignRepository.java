package com.trendyol.shoppingcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.domain.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{

	Campaign findOneById(Long id);
	
}
