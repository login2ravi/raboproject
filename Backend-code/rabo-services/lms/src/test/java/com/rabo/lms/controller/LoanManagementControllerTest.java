package com.rabo.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.service.LoanManagementService;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = LmsApplication.class)
//@WebMvcTest(value = LoanManagementController.class)
//@AutoConfigureMockMvc
//@WithMockUser
//@WebMvcTest

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
//@ContextConfiguration(classes = {LoanManagementController.class, LoanManagementService.class})
//@SpringBootApplication(scanBasePackages = {"com.rabo.lms"})
@WebMvcTest
//@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ LmsApplication.class })

class LoanManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	
	 @Mock 
	 private LoanManagementService loanManagementService;
	 
	
	//@Test
	void searchTest() {
		
		//LoanManagementService loanManagementService = mock(LoanManagementService.class);
		
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
		
		
		
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/search").param("firstName", "Rahul")
										.param("lastName", "Dravid")
										.param("loanNumber", "HL-1001")
										.contentType("application/json")
										;
				//.andExpect(MockMvcResultMatchers.status().isOk());
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
	}

}
