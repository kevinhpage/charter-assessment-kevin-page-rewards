package com.kevinpage.charter.assessment.rewards.controllers;

import com.kevinpage.charter.assessment.rewards.entities.RewardPointsMonthly;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController("/rewards/query/")
public class RewardsQueryController {
    @GetMapping(value = "/monthly/{userId}", produces = "application/json")
    public RewardPointsMonthly getRewardsPointsMonthly(
            HttpRequest request,
            @RequestParam int startMonth,
            @RequestParam int startYear,
            @RequestParam int endMonth,
            @RequestParam int endYear
    ) {
        return null;
    }
}
