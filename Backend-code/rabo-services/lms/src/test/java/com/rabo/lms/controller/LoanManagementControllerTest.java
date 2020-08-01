package com.rabo.lms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.CustomerDetails;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.service.LoanManagementService;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class LoanManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext context;

	
	 @MockBean
	 private LoanManagementService loanManagementService;
	 
	 @BeforeEach
	 public void setup() {
		 mockMvc = MockMvcBuilders.webAppContextSetup(context)
				 .apply(springSecurity())
				 .build();
	 }
	 
	 
	 @WithMockUser
	 //@Test
	 void SearchTest()throws Exception {
		 
		 
		 List<LoanDetail> loanDetailList = new ArrayList<LoanDetail>();
			LoanDetail loanDetail = new LoanDetail();
			loanDetail.setFirstName("Rahul");
			loanDetail.setLastName("Dravid");
			loanDetail.setLoanNumber("HL-1001");
			loanDetail.setCity("Chennai");
			loanDetail.setAmount(50000L);
			
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.setFirstName("Rahul");
			searchRequest.setLastName("Dravid");
			searchRequest.setLoanNumber("HL-1001");
			
			loanDetailList.add(loanDetail);	
			
			String expected = "{\"firstName\":\"Dravid\",\"lastName\":\"Dravid\",\"loanNumber\":\"HL-1001\""
					+ ",\"city\":\"Chennai\",\"amount\":5000L\"}";
			
			Mockito.when(loanManagementService.search(searchRequest)).thenReturn(loanDetailList);
			
			
			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/search")
	                   // .contentType(MediaType.APPLICATION_JSON_VALUE)
	                    .param("firstName", "Rahul")
	           		  .param("lastName", "Dravid") .param("loanNumber", "HL-1001");
	                    //.content("{\"firstName\":\"Rahul\",\"lastName\":\"Dravid\",\\\"loanNumber\\\":\\\"HL-1001\\\"}");
			
			
			 mockMvc.perform(builder).andExpect(status().isOk());
		
		 
	 }
	 
	 
	 @WithMockUser
	 //@Test
	 void getLoanDetailsTest()throws Exception {
		 
		 
		 LoanDetail loanDetail = LoanDetail.builder().firstName("ravi")
				 .lastName("shankar")
				 .address1("addr1")
				 .address2("addr2")
				 .city("tnj")
				 .amount(1000L)
				 .build();
		 Mockito.when(loanManagementService.getLoanDetails("HL-1001")).thenReturn(loanDetail);
		 
		 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/search/HL-1001")
                 .contentType(MediaType.APPLICATION_JSON_VALUE);
		 
		 mockMvc.perform(builder).andExpect(status().isOk());
		 
                 
	 }
	 
	 @WithMockUser
	 //@Test
	 void addLoanTest()throws Exception {
		 CustomerDetails request = new CustomerDetails();
		 request.setFirstName("ravi");
		 request.setLastName("shankar");
		 request.setLoanNumber("HL-3000");
		 request.setAddress1("Pristine");
		 request.setAddress2("PBKM");
		 request.setCity("tnj");
		 
		 //Mockito.doNothing().when(loanManagementService.addLoan(request));
		 Mockito.when(loanManagementService.addLoan(request)).thenReturn(new LoanDetail());
		 
		 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/addloan")
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content("{\"firstName\":\"ravi\",\"lastName\":\"shankar\",\"loanNumber\":\"HL-3000\""
                 		+ ",\\\"address1\\\":\\\"pristine\\\""
                 		+ ",\\\"address2\\\":\\\"PBKM\\\""
                 		+ ",\\\"city\\\":\\\"tnj\\\"}");
	        
	        mockMvc.perform(builder).andExpect(status().isOk());
		 
		 
		 
	 }
	 
	
}
