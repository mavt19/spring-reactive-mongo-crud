package com.bns.reactive.mongo.clients.service;

import com.bns.reactive.mongo.clients.dto.UserFakeApi;
import com.bns.reactive.mongo.clients.dto.UserPlaceHolder;
import com.bns.reactive.mongo.clients.dto.UserReqRes;
import com.bns.reactive.mongo.clients.dto.UserResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserClient {
	Mono<UserReqRes> getUsersReqRes();
	Flux<UserPlaceHolder> getUsersPlaceholder();
	Flux<UserFakeApi> getUsersFake();
	Flux<UserResponse> getUsers();
}
