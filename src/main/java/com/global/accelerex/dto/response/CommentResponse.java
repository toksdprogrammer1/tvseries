package com.global.accelerex.dto.response;

import com.global.accelerex.model.AuditModel;


public class CommentResponse extends AuditModel {

    private Long id;
    private String comment;
    private String ipAddressLocation;
    private Long episodeId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIpAddressLocation() {
        return ipAddressLocation;
    }

    public void setIpAddressLocation(String ipAddressLocation) {
        this.ipAddressLocation = ipAddressLocation;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }
}
