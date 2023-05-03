package com.kevinpage.charter.assessment.rewards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinpage.charter.assessment.rewards.entities.RewardPointsMonthly;
import com.kevinpage.charter.assessment.rewards.repositories.TransactionsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the end-to-end retrieval of data from the database.
 */
@SpringBootTest
class RewardsApplicationTest {
	@Autowired TransactionsRepository transactionsRepository;
	@Autowired TestRestTemplate testRestTemplate;
	@Autowired private ObjectMapper objectMapper;

	@Test
	@Sql("classpath:sql/test_transactions_data.sql")
	public void getRewardsPointsMonthlyForThreeMonths() throws Exception {
		Map<Integer, List<RewardPointsMonthly>> expected = Map.of(
				101, List.of(
						new RewardPointsMonthly(1, 2020, 90),
						new RewardPointsMonthly(2, 2020, 295)
				),
				102, List.of(
						new RewardPointsMonthly(2, 2020, 25),
						new RewardPointsMonthly(3, 2020, 460)
				));

		ResponseEntity<String> response = testRestTemplate.getForEntity(
				"/rewards/query/monthly/1?startMonth=1&startYear=2020&endMonth=3&endYear=2020", String.class);

		Map<Integer, List<RewardPointsMonthly>> actual = objectMapper.reader().readValue(response.getBody());
		assertEquals(expected, actual);
	}
}
