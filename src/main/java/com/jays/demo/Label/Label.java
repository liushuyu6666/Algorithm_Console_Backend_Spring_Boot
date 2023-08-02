package com.jays.demo.Label;

import com.jays.demo.Utils.StringFieldProcess;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Labels")
public class Label {
    @Id
    private String labelId;
    @NotBlank
    private String name;
    @NotBlank
    private List<String> parents;
    @NotBlank
    private String description;

    @NotBlank
    private String userId;

    public Label(String name, List<String> parents, String description, String userId) {
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

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parent) {
        this.parents = parent;
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
