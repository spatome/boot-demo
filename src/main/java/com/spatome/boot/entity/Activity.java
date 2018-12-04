package com.spatome.boot.entity;

import java.util.Date;

public class Activity {
    private Long id;

    private String enterpriseNo;

    private String activityName;

    private Integer allowDayCount;

    private Date beginTime;

    private Date endTime;

    private String activityDescs;

    private String playDescs;

    private Long gameTypeId;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String descs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseNo() {
        return enterpriseNo;
    }

    public void setEnterpriseNo(String enterpriseNo) {
        this.enterpriseNo = enterpriseNo == null ? null : enterpriseNo.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getAllowDayCount() {
        return allowDayCount;
    }

    public void setAllowDayCount(Integer allowDayCount) {
        this.allowDayCount = allowDayCount;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivityDescs() {
        return activityDescs;
    }

    public void setActivityDescs(String activityDescs) {
        this.activityDescs = activityDescs == null ? null : activityDescs.trim();
    }

    public String getPlayDescs() {
        return playDescs;
    }

    public void setPlayDescs(String playDescs) {
        this.playDescs = playDescs == null ? null : playDescs.trim();
    }

    public Long getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(Long gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }
}