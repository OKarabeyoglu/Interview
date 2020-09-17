package com.trendyol.shoppingcard.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDataModel<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedDate
    private LocalDate createDate;

    @LastModifiedDate
    private LocalDate modifiedDate;

}