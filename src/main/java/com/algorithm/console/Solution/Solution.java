package com.algorithm.console.Solution;

import com.algorithm.console.Language.ELanguage;

public class Solution {
    private ELanguage language;

    private String url;

    private String description;

    public Solution() {
    }

    public Solution(ELanguage language, String url, String description) {
        this.language = language;
        this.url = url;
        this.description = description;
    }

    public ELanguage getLanguage() {
        return language;
    }

    public void setLanguage(ELanguage language) {
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
