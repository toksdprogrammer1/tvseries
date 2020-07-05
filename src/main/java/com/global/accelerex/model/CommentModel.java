package com.global.accelerex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class CommentModel extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String comment;

    @NotNull
    private String ipAddressLocation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "episode_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EpisodeModel episode;

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

    public EpisodeModel getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeModel episode) {
        this.episode = episode;
    }
}
