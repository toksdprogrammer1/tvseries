package com.global.accelerex.model;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "episodes")
public class EpisodeModel extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime releaseDate;
    @NotNull
    private String episodeCode;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "episodes")
    private Set<CharacterModel> characters = new HashSet<>();

    @OneToMany(mappedBy = "episode", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<CommentModel> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getEpisodeCode() {
        return episodeCode;
    }

    public void setEpisodeCode(String episodeCode) {
        this.episodeCode = episodeCode;
    }

    public Set<CharacterModel> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<CharacterModel> characters) {
        this.characters = characters;
    }

    public Set<CommentModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentModel> comments) {
        this.comments = comments;
    }
}
