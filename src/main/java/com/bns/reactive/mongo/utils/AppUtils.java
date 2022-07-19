package com.bns.reactive.mongo.utils;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.bns.reactive.mongo.models.dto.ProductDto;
import com.bns.reactive.mongo.models.entity.Product;

public class AppUtils {

	public static ProductDto productToProductDto(Product product) {
		ProductDto productDto = new ProductDto();		
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}
	
	public static Product productDtoToProduct(ProductDto productDto) {
		Product product = new Product();
		productDto.setId(UUID.randomUUID().toString());
		BeanUtils.copyProperties(productDto, product);
		return product;
	}
}
