package com.wiremock.junit.billingservice.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.wiremock.junit.billingservice.BillingServiceApplication;
import com.wiremock.junit.billingservice.document.Invoice;
import com.wiremock.junit.billingservice.dto.InvoiceRequestDTO;
import com.wiremock.junit.billingservice.dto.PaymentRequestDTO;
import com.wiremock.junit.billingservice.repository.InvoiceRepository;
import com.wiremock.junit.billingservice.utils.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration tests for the {@link InvoiceController} REST controller.
 */
@SpringBootTest(classes = {BillingServiceApplication.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
@WireMockTest(httpPort = 8989)
@AutoConfigureMockMvc
class InvoiceControllerIT {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = "/invoice";

    private final String PAYMENT_URL = BASE_URL+"/payment";


    @Test
    @DisplayName("create invoice successful")
    void shouldCreateInvoiceSuccessful() throws Exception {
        int databaseSizeBeforeCreate = repository.findAll().size();

        // Create the invoice
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertJsonFileContentToJsonBytes(InvoiceRequestDTO.class, "invoice-request.json")))
                .andExpect(status().isCreated());

        // Validate the Invoice in the database
        List<Invoice> dataList = repository.findAll();
        assertThat(dataList).hasSize(databaseSizeBeforeCreate + 1);
        Invoice testInvoice = dataList.get(0);
        assertThat(testInvoice.getAmount()).isEqualTo(1000);
        assertThat(testInvoice.getCustomer().getCode()).isEqualTo("CUST01");

    }

    @Test
    @DisplayName("create invoice error Scenario")
    void shouldCreateInvoiceErrorScenario() throws Exception {

        // 404 error scenario
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertJsonFileContentToJsonBytes(InvoiceRequestDTO.class, "invoice-request-404.json")))
                .andExpect(status().isNotFound());

        // 400 error scenario
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertJsonFileContentToJsonBytes(InvoiceRequestDTO.class, "invoice-request-400.json")))
                .andExpect(status().isBadRequest());

        // 401 error scenario
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertJsonFileContentToJsonBytes(InvoiceRequestDTO.class, "invoice-request-401.json")))
                .andExpect(status().isUnauthorized());

    }



}