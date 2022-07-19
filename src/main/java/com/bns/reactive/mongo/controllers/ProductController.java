package com.bns.reactive.mongo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bns.reactive.mongo.exceptions.NotFoundException;
import com.bns.reactive.mongo.models.dto.ProductDto;
import com.bns.reactive.mongo.services.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Flux<ProductDto>> findall() {
		Flux<ProductDto> products = productService.findAll();
		return ResponseEntity.ok().body(products);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Mono<ResponseEntity<ProductDto>> findById(@PathVariable("id") String id) {
		return  productService.findById(id)
				.map(x-> ResponseEntity.ok().body(x))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	
	}
	
	@GetMapping("/range")
	public ResponseEntity<Flux<ProductDto>> findProductInRange(@RequestParam("min") double min, @RequestParam("max") double max) {
		Flux<ProductDto> productsIRange = productService.findProductInRange(min, max);
		return ResponseEntity.ok().body(productsIRange);
	
	}
	
	@PostMapping
	public ResponseEntity<Mono<ProductDto>> saveProduct(@RequestBody Mono<ProductDto> product) {
		Mono<ProductDto> productDB = productService.save(product);
		return new ResponseEntity<Mono<ProductDto>>(productDB, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ProductDto>> updateProduct(@RequestBody Mono<ProductDto> product, @PathVariable("id") String id) {
		return productService.update(product, id)
		.map(x-> ResponseEntity.ok().body(x))
		.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable("id") String id) {
//		return productService.findById(id).
//		flatMap(x-> productService.deleteById(id))
//		.map(x-> ResponseEntity.ok().body(x));
//		return  productService.deleteById(id)
//		.map(x-> ResponseEntity.noContent().build())
//		.defaultIfEmpty(ResponseEntity.notFound().build());
		
		return productService.findById(id)
		.log()
		.switchIfEmpty( Mono.error(new NotFoundException("No product found for productId : " + id)))
		.flatMap(x-> productService.deleteById(id))
		.map(x-> ResponseEntity.noContent().build());
		
//		return productService.deleteById(id)
//				.map(x-> ResponseEntity.ok().body(x));
	}
}
