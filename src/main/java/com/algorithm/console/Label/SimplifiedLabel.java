package com.algorithm.console.Label;

import org.bson.types.ObjectId;

public class SimplifiedLabel {
    private String labelId;

    private String name;

    private String url;

    public SimplifiedLabel() {
    }

    public SimplifiedLabel(Label label) {
        this.labelId = label.getLabelId().toHexString();
        this.name = label.getName();
        this.url = label.getUrl();
    }

    public SimplifiedLabel(String labelId, String name, String url) {
        this.labelId = labelId;
        this.name = name;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
