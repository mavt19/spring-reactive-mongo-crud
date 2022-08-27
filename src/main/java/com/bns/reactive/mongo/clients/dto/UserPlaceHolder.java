package com.bns.reactive.mongo.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlaceHolder {
	   private String website;
	   private String name;
	   private Integer id;
	   protected String email;
	   protected String username;
	   protected String password;
	   protected String phone;
}
