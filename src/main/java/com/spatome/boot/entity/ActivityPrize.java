package com.spatome.boot.entity;

import java.math.BigDecimal;

public class ActivityPrize {
    private Long id;

    private Long activityId;

    private Long prizeId;

    private BigDecimal prizePr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public BigDecimal getPrizePr() {
        return prizePr;
    }

    public void setPrizePr(BigDecimal prizePr) {
        this.prizePr = prizePr;
    }
}