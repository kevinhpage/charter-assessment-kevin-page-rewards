package com.kevinpage.charter.assessment.rewards.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinpage.charter.assessment.rewards.entities.RewardPointsMonthly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * These test are run against tests data in test/resources/sql/test_transactions_data.sql
 */
@WebMvcTest(controllers = RewardsQueryController.class)
class RewardsQueryControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getRewardsPointsMonthlyArbitraryDates() throws Exception {
        Map<Integer, List<RewardPointsMonthly>> expected = Map.of(
                101, List.of(
                        new RewardPointsMonthly(1, 2020, 90),
                        new RewardPointsMonthly(2, 2020, 295)
                ),
                102, List.of(
                        new RewardPointsMonthly(2, 2020, 25),
                        new RewardPointsMonthly(3, 2020, 460)
                ));

        MvcResult mvcResult = mockMvc.perform(get("/rewards/query/monthly")
                .param("startMonth", "1")
                .param("startYear", "2020")
                .param("endMonth", "3")
                .param("endYear", "2020")
        ).andExpect(status().isOk()).andReturn();

        Map<Integer, List<RewardPointsMonthly>> actual = objectMapper.reader().readValue(mvcResult.toString());
        assertEquals(expected, actual);
    }
}
