package com.algorithm.console.Solution;

public class Solution {
    private String language;

    private String url;

    private String description;

    public Solution() {
    }

    public Solution(String language, String url, String description) {
        this.language = language;
        this.url = url;
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
