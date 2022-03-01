package com.example.statnett.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "CatFacts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatFact {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty(value = "user")
    private String userId;
    private String text;
    private String source;
    private ZonedDateTime updatedAt;
    private String type;
    private ZonedDateTime createdAt;

    protected CatFact() {
    }

    public CatFact(String user, String text, String source, ZonedDateTime updatedAt, String type, ZonedDateTime createdAt) {
        this.userId = user;
        this.text = text;
        this.source = source;
        this.updatedAt = updatedAt;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatFact catFact = (CatFact) o;
        return Objects.equals(id, catFact.id) && Objects.equals(userId, catFact.userId) && Objects.equals(text, catFact.text) && Objects.equals(source, catFact.source) && Objects.equals(updatedAt, catFact.updatedAt) && Objects.equals(type, catFact.type) && Objects.equals(createdAt, catFact.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text, source, updatedAt, type, createdAt);
    }

    @Override
    public String toString() {
        return "CatFact{" +
                "id=" + id +
                ", user='" + userId + '\'' +
                ", text='" + text + '\'' +
                ", source='" + source + '\'' +
                ", updatedAt=" + updatedAt +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
