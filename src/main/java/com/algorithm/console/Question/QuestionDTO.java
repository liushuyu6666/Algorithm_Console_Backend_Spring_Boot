package com.algorithm.console.Question;

import com.algorithm.console.Code.Code;
import com.algorithm.console.Url.Url;
import org.bson.types.ObjectId;

import java.util.List;

public class QuestionDTO {
    private String questionId;

    private String from;

    private String section;

    private String stringName;

    private Integer numericName;

    private String readableId;

    private List<String> labels;

    private List<String> parents;

    private Integer difficulty;

    private List<Url> questionUrls;

    private Url readme;

    private List<Code> codes;

    private String description;

    private String userId;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionId, String from, String section, String stringName, Integer numericName, String readableId, List<String> labels, List<String> parents, Integer difficulty, List<Url> questionUrls, Url readme, List<Code> codes, String description, String userId) {
        this.questionId = questionId;
        this.from = from;
        this.section = section;
        this.stringName = stringName;
        this.numericName = numericName;
        this.readableId = readableId;
        this.labels = labels;
        this.parents = parents;
        this.difficulty = difficulty;
        this.questionUrls = questionUrls;
        this.readme = readme;
        this.codes = codes;
        this.description = description;
        this.userId = userId;
    }

    public QuestionDTO(Question question) {
        this.questionId = question.getQuestionId().toHexString();
        this.from = question.getFrom();
        this.section = question.getSection();
        this.stringName = question.getStringName();
        this.numericName = question.getNumericName();
        this.readableId = question.getReadableId();
        this.labels = question.getLabels().stream().map(ObjectId::toHexString).toList();
        this.parents = question.getParents().stream().map(ObjectId::toHexString).toList();
        this.difficulty = question.getDifficulty();
        this.questionUrls = question.getQuestionUrls();
        this.readme = question.getReadme();
        this.codes = question.getCodes();
        this.description = question.getDescription();
        this.userId = question.getUserId().toHexString();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public Integer getNumericName() {
        return numericName;
    }

    public void setNumericName(Integer numericName) {
        this.numericName = numericName;
    }

    public String getReadableId() {
        return readableId;
    }

    public void setReadableId(String readableId) {
        this.readableId = readableId;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public List<Url> getQuestionUrls() {
        return questionUrls;
    }

    public void setQuestionUrls(List<Url> questionUrls) {
        this.questionUrls = questionUrls;
    }

    public Url getReadme() {
        return readme;
    }

    public void setReadme(Url readme) {
        this.readme = readme;
    }

    public List<Code> getSolutions() {
        return codes;
    }

    public void setSolutions(List<Code> codes) {
        this.codes = codes;
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
