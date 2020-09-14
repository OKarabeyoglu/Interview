package com.trendyol.shoppingcard.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDTO {
	
	private Long id;
	private LocalDate createDate;
	private LocalDate modifiedDate;

}
