package com.company.Dto;

import java.awt.*;

public class Artist {
    String id;
    String adamid;
    String avatar;
    String name;
    Boolean verified;
    String weburl;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdamid() {
        return adamid;
    }

    public void setAdamid(String adamid) {
        this.adamid = adamid;
    }
}
