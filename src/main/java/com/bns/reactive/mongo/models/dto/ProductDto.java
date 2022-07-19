package com.bns.reactive.mongo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

	private String id;
	private String name;
	private int qty;
	private double price;
}
