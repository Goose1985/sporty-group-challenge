package com.sporty.group.challenge.bet.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BetApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void when_eventId_is_missing_then_returns_400_problem_detail_without_extra_data() throws Exception {
        mockMvc.perform(get("/bets").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value("Invalid request"))
                .andExpect(jsonPath("$.detail").value("Query parameter 'eventId' is required."))
                .andExpect(jsonPath("$.bets").doesNotExist());
    }

    @Test
    void when_eventId_is_blank_then_returns_400_problem_detail_without_extra_data() throws Exception {
        mockMvc.perform(get("/bets")
                        .param("eventId", "   ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value("Invalid request"))
                .andExpect(jsonPath("$.detail").value("Query parameter 'eventId' is required."))
                .andExpect(jsonPath("$.bets").doesNotExist());
    }

    @Test
    void when_eventId_is_valid_then_returns_200_and_bets_for_that_event() throws Exception {
        mockMvc.perform(get("/bets")
                        .param("eventId", "event-100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].eventId").value("event-100"))
                .andExpect(jsonPath("$[1].eventId").value("event-100"))
                .andExpect(jsonPath("$[0].betId").exists())
                .andExpect(jsonPath("$[0].userId").exists())
                .andExpect(jsonPath("$[0].eventMarketId").exists())
                .andExpect(jsonPath("$[0].eventWinnerId").exists())
                .andExpect(jsonPath("$[0].betAmount").exists());
    }
}
