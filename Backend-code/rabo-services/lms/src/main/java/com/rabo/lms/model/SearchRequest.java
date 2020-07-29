package com.rabo.lms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequest {
	
	private String firstName;
	private String lastName;
	private String loanNumber;
	
}
