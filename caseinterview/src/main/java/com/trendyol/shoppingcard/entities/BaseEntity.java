package com.trendyol.shoppingcard.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class BaseEntity<ID extends Serializable> extends BaseDataModel<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private ID id;

}
