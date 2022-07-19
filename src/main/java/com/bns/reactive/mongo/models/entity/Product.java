package com.bns.reactive.mongo.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "products")
public class Product {

	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private int qty;
	private double price;
	private boolean status;
}
