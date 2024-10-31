package com.qa.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)

/***
 * this is Pojo Class for User and create a Object of class and use method to add user details
 * @author windo
 *
 */

public class User {

	private String id;
	private String name;
	private String email;
	private String gender;
	private String status;

}
