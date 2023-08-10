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
    private LabelType labelType;

    private String url;

    private Set<ObjectId> questions;

    @NotBlank
    private ObjectId userId;

    public Label() {
    }

    public Label(String name, List<ObjectId> parents, LabelType labelType,String url, Set<ObjectId> questions, ObjectId userId) {
        this.name = name;
        this.parents = parents;
        this.labelType = labelType;
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

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    public List<ObjectId> getParents() {
        return parents;
    }

    public void setParents(List<ObjectId> parents) {
        this.parents = parents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
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
