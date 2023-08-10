package com.algorithm.console.Question;

import com.algorithm.console.Difficulty.Difficulty;
import com.algorithm.console.Solution.Solution;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionDTO {
    private String questionId;

    private String readableId;

    private Set<String> labels;

    private Set<String> parents;

    private Difficulty difficulty;

    private String from;

    private String content;

    private List<Solution> solutions;

    private String userId;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionId, String readableId, Set<String> labels, Set<String> parents, Difficulty difficulty, String from, String content, List<Solution> solutions, String userId) {
        this.questionId = questionId;
        this.readableId = readableId;
        this.labels = labels;
        this.parents = parents;
        this.difficulty = difficulty;
        this.from = from;
        this.content = content;
        this.solutions = solutions;
        this.userId = userId;
    }

    public QuestionDTO(Question question) {
        this.questionId = question.getQuestionId().toHexString();
        this.readableId = question.getReadableId();
        this.labels = question.getLabels().stream().map(ObjectId::toHexString).collect(Collectors.toSet());
        this.parents = question.getParents().stream().map(ObjectId::toHexString).collect(Collectors.toSet());
        this.difficulty = question.getDifficulty();
        this.from = question.getFrom();
        this.content = question.getContent();
        this.solutions = question.getSolutions();
        this.userId = question.getUserId().toHexString();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getReadableId() {
        return readableId;
    }

    public void setReadableId(String readableId) {
        this.readableId = readableId;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public Set<String> getParents() {
        return parents;
    }

    public void setParents(Set<String> parents) {
        this.parents = parents;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
