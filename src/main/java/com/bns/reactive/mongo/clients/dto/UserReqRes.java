package com.bns.reactive.mongo.clients.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqRes {

	   private Integer per_page;
	   private Integer total;
	   private Integer page;
	   private Integer total_pages;
	   @JsonProperty("data")
	   private List<UserD> user;
}
