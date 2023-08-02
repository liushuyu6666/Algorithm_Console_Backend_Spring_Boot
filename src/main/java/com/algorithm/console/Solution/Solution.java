package com.algorithm.console.Solution;

import com.algorithm.console.Url.Url;

import java.util.List;

public class Solution {
    private String language;

    private String codeUrl;

    private String explanationUrl;

    public Solution() {
    }

    public Solution(String language, String codeUrl) {
        this.language = language;
        this.codeUrl = codeUrl;
    }

    public Solution(String language, String codeUrl, String explanationUrl) {
        this.language = language;
        this.codeUrl = codeUrl;
        this.explanationUrl = explanationUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getExplanationUrl() {
        return explanationUrl;
    }

    public void setExplanationUrl(String explanationUrl) {
        this.explanationUrl = explanationUrl;
    }
}
