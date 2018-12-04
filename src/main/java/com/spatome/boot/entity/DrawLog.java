package com.spatome.boot.entity;

import java.util.Date;

public class DrawLog {
    private Long id;

    private String oneKey;

    private Long activityId;

    private Long prizeId;

    private String activityEnterpriseNo;

    private String prizeEnterpriseNo;

    private Date createDate;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOneKey() {
        return oneKey;
    }

    public void setOneKey(String oneKey) {
        this.oneKey = oneKey == null ? null : oneKey.trim();
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

    public String getActivityEnterpriseNo() {
        return activityEnterpriseNo;
    }

    public void setActivityEnterpriseNo(String activityEnterpriseNo) {
        this.activityEnterpriseNo = activityEnterpriseNo == null ? null : activityEnterpriseNo.trim();
    }

    public String getPrizeEnterpriseNo() {
        return prizeEnterpriseNo;
    }

    public void setPrizeEnterpriseNo(String prizeEnterpriseNo) {
        this.prizeEnterpriseNo = prizeEnterpriseNo == null ? null : prizeEnterpriseNo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}