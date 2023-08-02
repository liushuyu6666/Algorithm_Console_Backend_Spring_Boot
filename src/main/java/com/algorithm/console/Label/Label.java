package com.algorithm.console.Label;

import com.algorithm.console.Utils.StringFieldProcess;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.util.List;

@Document(collection = "Labels")
public class Label {
    @Id
    private String labelId;
    @NotBlank
    private String name;
    @NotBlank
    private List<ObjectId> parents;
    @NotBlank
    private String description;

    @NotBlank
    private ObjectId userId;

    public Label(String name, List<ObjectId> parents, String description, ObjectId userId) {
        this.name = name;
        this.parents = parents;
        this.description = description;
        this.userId = userId;
    }

    public String getLabelId() {
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

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }
}
