package com.kevinpage.charter.assessment.rewards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinpage.charter.assessment.rewards.entities.RewardPointsMonthly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class RewardsApplicationTest {

	@Autowired private MockMvc mockMvc;

	@Autowired private ObjectMapper objectMapper;

//	@Autowired private

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void getRewardsPointsMonthlyArbitraryDates() throws Exception {
		Map<Integer, List<RewardPointsMonthly>> expected = Map.of(
				101, List.of(
						new RewardPointsMonthly(1, 2020, 20),
						new RewardPointsMonthly(2, 2020, 17),
						new RewardPointsMonthly(4, 2020, 28),
						new RewardPointsMonthly(1, 2021, 3)),
				102, List.of(
						new RewardPointsMonthly(3, 2020, 18),
						new RewardPointsMonthly(10, 2020, 10),
						new RewardPointsMonthly(2, 2020, 15)
				));

		MvcResult mvcResult = mockMvc.perform(get("/rewards/query/monthly")
				.param("startMonth", "1")
				.param("startYear", "2020")
				.param("endMonth", "2")
				.param("endYear", "2021")
		).andExpect(status().isOk()).andReturn();

		Map<Integer, List<RewardPointsMonthly>> actual = objectMapper.reader().readValue(mvcResult.toString());
		assertEquals(expected, actual);
	}
}
