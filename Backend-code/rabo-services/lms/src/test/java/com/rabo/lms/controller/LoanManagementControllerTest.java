package com.rabo.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.CustomerDetails;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.service.LoanManagementService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
    @Test
    void SearchTest() throws Exception {

        SearchRequest searchRequest = prepareSearchRequest();
        List<LoanDetail> loanDetails = prepareLoanDetails();
        when(loanManagementService.search(searchRequest)).thenReturn(loanDetails);

        mockMvc.perform(get("/secure/search")
                .param("firstName", searchRequest.getFirstName())
                .param("lastName", searchRequest.getLastName())
                .param("loanNumber", searchRequest.getLoanNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("ravi")))
                .andExpect(jsonPath("$[0].lastName", is("shankar")));
    }


    @WithMockUser
    @Test
    void getLoanDetailsTest() throws Exception {

        LoanDetail loanDetail = prepareLoanDetails().get(0);
        when(loanManagementService.getLoanDetails(loanDetail.getLoanNumber()))
                .thenReturn(loanDetail);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/secure/getloandetails/" + loanDetail.getLoanNumber());

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanNumber", is(loanDetail.getLoanNumber())));
    }

    @WithMockUser
    @Test
    void addLoanTest() throws Exception {
        CustomerDetails request = getCustomerDetails();

        when(loanManagementService.addLoan(request))
                .thenReturn(new LoanDetail());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/secure/addloan")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(request));

        mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    void updateLoan() throws Exception {
        CustomerDetails request = getCustomerDetails();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/secure/updateloan")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(request));

        mockMvc.perform(builder)
                .andExpect(status().isOk());
        verify(loanManagementService, times(1)).updateLoan(Mockito.any());
    }

    private SearchRequest prepareSearchRequest() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFirstName("ravi");
        searchRequest.setLastName("shankar");
        searchRequest.setLoanNumber("1001");
        return searchRequest;
    }


    private List<LoanDetail> prepareLoanDetails() {
        LoanDetail loanDetail = LoanDetail.builder().firstName("ravi")
                .loanNumber("1001")
                .lastName("shankar")
                .address1("addr1")
                .address2("addr2")
                .city("tnj")
                .amount(1000L)
                .build();

        List<LoanDetail> loanDetails = new ArrayList<>();
        loanDetails.add(loanDetail);
        return loanDetails;
    }

    private CustomerDetails getCustomerDetails() {
        CustomerDetails request = new CustomerDetails();
        request.setFirstName("ravi");
        request.setLastName("shankar");
        request.setLoanNumber("HL-3000");
        request.setAddress1("Pristine");
        request.setAddress2("PBKM");
        request.setCity("tnj");
        return request;
    }

    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
