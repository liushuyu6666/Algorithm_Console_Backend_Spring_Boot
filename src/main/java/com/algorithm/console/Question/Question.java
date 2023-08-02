package com.algorithm.console.Question;

import com.algorithm.console.Label.Label;
import com.algorithm.console.Solution.Solution;
import com.algorithm.console.Url.Url;
import com.algorithm.console.Utils.StringFieldProcess;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Questions")
public class Question {
    @Id
    private ObjectId questionId;

    @NotBlank
    private String from;

    private String section;

    private String stringName;

    private Integer numericName;

    @NotBlank
    @Indexed(unique = true)
    private String readableId;

    @NotBlank
    private List<ObjectId> labels;

    private List<ObjectId> parents;

    @NotBlank
    private Integer difficulty;

    @NotBlank
    private List<Url> questionUrls;

    private List<Url> readme;

    private List<Solution> solutions;

    @NotBlank
    private ObjectId userId;

    public Question() {
    }

    public Question(String from, String readableId, List<ObjectId> labels, Integer difficulty, List<Url> questionUrls, ObjectId userId) {
        this.from = from;
        this.readableId = readableId;
        this.labels = labels;
        this.difficulty = difficulty;
        this.questionUrls = questionUrls;
        this.userId = userId;
    }

    public Question(String from, String section, String stringName, Integer numericName, String readableId, List<ObjectId> labels, List<ObjectId> parents, Integer difficulty, List<Url> questionUrls, List<Url> readme, List<Solution> solutions, ObjectId userId) {
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
        this.solutions = solutions;
        this.userId = userId;
    }

    public ObjectId getQuestionId() {
        return questionId;
    }

    public void setQuestionId(ObjectId questionId) {
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
        return StringFieldProcess.normalizeField(readableId);
    }

    public void setReadableId(String readableId) {
        this.readableId = StringFieldProcess.normalizeField(readableId);
    }

    public List<ObjectId> getLabels() {
        return labels;
    }

    public void setLabels(List<ObjectId> labels) {
        this.labels = labels;
    }

    public List<ObjectId> getParents() {
        return parents;
    }

    public void setParents(List<ObjectId> parents) {
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

    public List<Url> getReadme() {
        return readme;
    }

    public void setReadme(List<Url> readme) {
        this.readme = readme;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }
}
