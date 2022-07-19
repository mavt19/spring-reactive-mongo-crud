package com.bns.reactive.mongo.services;

import com.bns.reactive.mongo.models.dto.ProductDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	public Flux<ProductDto> findAll();
	
//	public Page<Product> findAll(Pageable pageable);
	
	public Mono<ProductDto> findById(String id);
	
	public Flux<ProductDto> findProductInRange(double min, double max);
	
	public Mono<ProductDto> save(Mono<ProductDto> product);
	
	public Mono<ProductDto> update(Mono<ProductDto> product, String id);
	
	public Mono<Void> deleteById(String id);
}
