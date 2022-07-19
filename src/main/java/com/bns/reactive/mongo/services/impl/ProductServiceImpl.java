package com.bns.reactive.mongo.services.impl;

import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bns.reactive.mongo.models.dto.ProductDto;
import com.bns.reactive.mongo.models.entity.Product;
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

	@Override
	@Transactional
	public Mono<Void> findByStatusAndIfTrueThenPriceX5ElsePriceX2AndUpdateAll() {
		// TODO Auto-generated method stub
		Flux<Product> trueProducts = productRepository.findByStatus(true)
		.map(product -> {
			product.setPrice(product.getPrice() * 5);
			return product;
		}).log();
		
		Flux<Product> falseProducts = productRepository.findByStatus(false)
		.map(product -> {
			product.setPrice(product.getPrice() * 2);
			return product;
		}).log();
		
		return Mono.when(productRepository.saveAll(trueProducts), productRepository.saveAll(falseProducts));
	}
	
	@Override
	@Transactional
	public Mono<Void> findByStatusAndUpdateRact(Boolean status) {
		// TODO Auto-generated method stub
		Flux<Product> falseProducts = productRepository.findByStatus(status)
		.map(product -> {
			product.setPrice(product.getPrice() * 2);
			return product;
		}).log();
		
		return productRepository.saveAll(falseProducts).then();
	}
	
}
