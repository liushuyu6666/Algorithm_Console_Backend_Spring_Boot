package com.algorithm.console.Label;

import com.algorithm.console.Url.Url;
import com.algorithm.console.Utils.StringFieldProcess;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

@Document(collection = "Labels")
public class Label {
    @Id
    private ObjectId labelId;
    @NotBlank
    private String name;
    @NotBlank
    private List<ObjectId> parents;
    @NotBlank
    private String description;

    private Url url;

    private Set<ObjectId> questions;

    @NotBlank
    private ObjectId userId;

    public Label() {
    }

    public Label(String name, List<ObjectId> parents, String description, Url url, Set<ObjectId> questions, ObjectId userId) {
        this.name = name;
        this.parents = parents;
        this.description = description;
        this.url = url;
        this.questions = questions;
        this.userId = userId;
    }

    public ObjectId getLabelId() {
        return labelId;
    }

    public String getName() {
        return StringFieldProcess.normalizeField(name);
    }

    public void setName(String name) {
        this.name = StringFieldProcess.normalizeField(name);
    }

    public List<ObjectId> getParents() {
        return parents;
    }

    public void setParents(List<ObjectId> parents) {
        this.parents = parents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Set<ObjectId> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<ObjectId> questions) {
        this.questions = questions;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }
}
