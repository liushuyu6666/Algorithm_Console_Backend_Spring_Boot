package com.algorithm.console.Code;

public class Code {
    private String language;

    private String codeUrl;

    private String explanationUrl;

    public Code() {
    }

    public Code(String language, String codeUrl) {
        this.language = language;
        this.codeUrl = codeUrl;
    }

    public Code(String language, String codeUrl, String explanationUrl) {
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
