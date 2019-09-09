package com.shby.message.entity;

public class SysConfig {
    private String keyId;

    private String keyValue;

    private String keyGroup;

    private String descs;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId == null ? null : keyId.trim();
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue == null ? null : keyValue.trim();
    }

    public String getKeyGroup() {
        return keyGroup;
    }

    public void setKeyGroup(String keyGroup) {
        this.keyGroup = keyGroup == null ? null : keyGroup.trim();
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }
}