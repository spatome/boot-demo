package com.spatome.boot.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Prize {
    private Long id;

    private String enterpriseNo;

    private String shareLevel;

    private String prizeName;

    private String prizeIconName;

    private String prizeImageName;

    private Integer prizeCount;

    private Integer prizeCountBalance;

    private Integer prizeDayCount;

    private BigDecimal prizePr;

    private String prizeType;

    private String link;

    private String linkDescs;

    private String status;

    private Date beginDate;

    private Date endDate;

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

    public String getShareLevel() {
        return shareLevel;
    }

    public void setShareLevel(String shareLevel) {
        this.shareLevel = shareLevel == null ? null : shareLevel.trim();
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeIconName() {
        return prizeIconName;
    }

    public void setPrizeIconName(String prizeIconName) {
        this.prizeIconName = prizeIconName == null ? null : prizeIconName.trim();
    }

    public String getPrizeImageName() {
        return prizeImageName;
    }

    public void setPrizeImageName(String prizeImageName) {
        this.prizeImageName = prizeImageName == null ? null : prizeImageName.trim();
    }

    public Integer getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(Integer prizeCount) {
        this.prizeCount = prizeCount;
    }

    public Integer getPrizeCountBalance() {
        return prizeCountBalance;
    }

    public void setPrizeCountBalance(Integer prizeCountBalance) {
        this.prizeCountBalance = prizeCountBalance;
    }

    public Integer getPrizeDayCount() {
        return prizeDayCount;
    }

    public void setPrizeDayCount(Integer prizeDayCount) {
        this.prizeDayCount = prizeDayCount;
    }

    public BigDecimal getPrizePr() {
        return prizePr;
    }

    public void setPrizePr(BigDecimal prizePr) {
        this.prizePr = prizePr;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType == null ? null : prizeType.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getLinkDescs() {
        return linkDescs;
    }

    public void setLinkDescs(String linkDescs) {
        this.linkDescs = linkDescs == null ? null : linkDescs.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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