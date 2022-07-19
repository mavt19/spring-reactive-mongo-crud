package com.bns.reactive.mongo.services.impl;

import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.bns.reactive.mongo.models.dto.ProductDto;
import com.bns.reactive.mongo.models.repository.ProductRepository;
import com.bns.reactive.mongo.services.ProductService;
import com.bns.reactive.mongo.utils.AppUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

	@Override
	public Flux<ProductDto> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll().map(AppUtils::productToProductDto);
	}

	@Override
	public Mono<ProductDto> findById(String id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map(AppUtils::productToProductDto).switchIfEmpty(Mono.empty());
	}

	@Override
	public Flux<ProductDto> findProductInRange(double min, double max) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceBetween(Range.closed(min, max))
				.map(AppUtils::productToProductDto);
	}
	
	@Override
	public Mono<ProductDto> save(Mono<ProductDto> productDto) {
		// TODO Auto-generated method stub
		return productDto.map(AppUtils::productDtoToProduct)			
				.flatMap(productRepository::insert)
				.map(AppUtils::productToProductDto);
	}
	@Override
	public Mono<ProductDto> update(Mono<ProductDto> product, String id){
		return productRepository.findById(id)
								.flatMap(p-> product.map(AppUtils::productDtoToProduct))
								.doOnNext(e-> e.setId(id))
								.flatMap(productRepository::save)
								.map(AppUtils::productToProductDto)
								.switchIfEmpty(Mono.empty());
	}
	
	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return productRepository.deleteById(id);
	}
	
}
