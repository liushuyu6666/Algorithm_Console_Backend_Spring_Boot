package com.algorithm.console.Label;

import jakarta.validation.constraints.Null;
import org.bson.types.ObjectId;

import java.util.List;

public class LabelDTO {
    private String labelId;

    private String name;

    private List<String> parents;

    private String description;

    private String userId;

    public LabelDTO() {
    }

    public LabelDTO(String labelId, String name, List<String> parents, String description, String userId) {
        this.labelId = labelId;
        this.name = name;
        this.parents = parents;
        this.description = description;
        this.userId = userId;
    }

    public LabelDTO(Label label) {
        this.labelId = label.getLabelId().toHexString();
        this.name = label.getName();
        this.parents = label.getParents().stream().map(ObjectId::toHexString).toList();
        this.description = label.getDescription();
        this.userId = label.getUserId().toHexString();
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
