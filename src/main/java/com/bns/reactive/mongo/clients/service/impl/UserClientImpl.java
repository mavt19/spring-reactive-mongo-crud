package com.bns.reactive.mongo.clients.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bns.reactive.mongo.clients.dto.UserFakeApi;
import com.bns.reactive.mongo.clients.dto.UserPlaceHolder;
import com.bns.reactive.mongo.clients.dto.UserReqRes;
import com.bns.reactive.mongo.clients.dto.UserResponse;
import com.bns.reactive.mongo.clients.service.UserClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class UserClientImpl implements UserClient{

	private final WebClient client = WebClient.create();
	
	@Override
	public Flux<UserResponse> getUsers() {
		// TODO Auto-generated method stub
		return Flux.zip(getUsersReqRes(), getUsersPlaceholder().collectList(), getUsersFake().collectList()).
		flatMap(tuple-> {
			return responseFinal(tuple.getT1(), tuple.getT2(), tuple.getT3());
		}).log();
	}

//	public Flux<UserResponse> getUsers1() {
//		// TODO Auto-generated method stub
//		Flux<UserResponse> response = Flux.empty();
//		Flux.zip(getUsersReqRes(), getUsersPlaceholder(), getUsersFake()).
//		flatMap(tuple-> {
//			response.just(tuple.getT1()).map(x-> UserResponse.builder().)
//		}).log();
//	}
	private Flux<UserResponse> responseFinal(UserReqRes t1, List<UserPlaceHolder> t2,
			List<UserFakeApi> t3) {
		// TODO Auto-generated method stub
		
		System.out.println(t1);
		List<UserResponse> reqRes =t1.getUser().stream().map(x-> {
			return UserResponse.builder().email(x.getEmail()).build();
		})
		.toList()
		;
		
		List<UserResponse> placeholder =t2.stream().map(x-> {
			return UserResponse.builder().email(x.getEmail()).build();
		}).toList();
		List<UserResponse> fake = t3.stream().map(x-> {
			return UserResponse.builder().email(x.getEmail().toString()).build();
		}).toList();
		Flux<UserResponse> res1 = Flux.fromIterable(reqRes);
		Flux<UserResponse> res2 =Flux.fromIterable(placeholder);
		Flux<UserResponse> res3 =Flux.fromIterable(fake);
		
		return Flux.concat(res1, res2, res3);

	}


	@Override
	public Mono<UserReqRes> getUsersReqRes() {
		// TODO Auto-generated method stub
		return client.get()
		.uri("https://reqres.in/api/users?page=2")
		.retrieve()
		.bodyToMono(UserReqRes.class);

	}

	@Override
	public Flux<UserPlaceHolder> getUsersPlaceholder() {
		// TODO Auto-generated method stub
		return client.get()
		.uri("https://jsonplaceholder.typicode.com/users")
		.retrieve()
		.bodyToFlux(UserPlaceHolder.class);
	}

	@Override
	public Flux<UserFakeApi> getUsersFake() {
		// TODO Auto-generated method stub
		return client.get()
		.uri("https://fakestoreapi.com/users")
		.retrieve()
		.bodyToFlux(UserFakeApi.class);
	}

}
