package com.bns.reactive.mongo.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	   protected String email;
	   protected String username;
	   protected String password;
	   protected String phone;
}
