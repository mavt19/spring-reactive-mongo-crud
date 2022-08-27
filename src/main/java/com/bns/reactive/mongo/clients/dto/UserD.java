package com.bns.reactive.mongo.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public
class UserD{
	   protected String email;
	   protected String username;
	   protected String password;
	   protected String phone;
	   private String last_name;
	   private Integer id;
	   private String avatar;
	   private String first_name;
}
