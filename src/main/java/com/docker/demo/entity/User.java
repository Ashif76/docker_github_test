package com.docker.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String userId;
    private int rewardPoints;
    private int redemptionAmount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(final int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getRedemptionAmount() {
        return redemptionAmount;
    }

    public void setRedemptionAmount(final int redemptionAmount) {
        this.redemptionAmount = redemptionAmount;
    }
}
