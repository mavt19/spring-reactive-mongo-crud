package com.bns.reactive.mongo.models.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bns.reactive.mongo.models.entity.Product;

import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String>{

	Flux<Product> findByPriceBetween(Range<Double> closed);

	Flux<Product> findByStatus(boolean status);
}
