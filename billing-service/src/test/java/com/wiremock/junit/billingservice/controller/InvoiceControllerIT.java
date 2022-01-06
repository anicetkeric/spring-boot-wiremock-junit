package com.wiremock.junit.billingservice.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.wiremock.junit.billingservice.BillingServiceApplication;
import com.wiremock.junit.billingservice.dto.InvoiceRequestDTO;
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
    @DisplayName("create successful new record")
    void shouldCreateSuccessful() throws Exception {
        int databaseSizeBeforeCreate = repository.findAll().size();

        // Create the invoice
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertJsonFileContentToJsonBytes(InvoiceRequestDTO.class, "invoice-request.json")))
                .andExpect(status().isCreated());


    }


}