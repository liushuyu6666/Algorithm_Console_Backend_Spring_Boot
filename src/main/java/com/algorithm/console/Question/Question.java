package com.algorithm.console.Question;

import com.algorithm.console.Difficulty.Difficulty;
import com.algorithm.console.Solution.Solution;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(collection = "Questions")
public class Question {
    @Id
    private ObjectId questionId;

    @NotBlank
    @Indexed(unique = true)
    private String readableId;

    @NotBlank
    private Set<ObjectId> labels;

    @NotBlank
    private Set<ObjectId> parents;

    @NotBlank
    private Difficulty difficulty;

    private String from;

    @NotBlank
    private List<Solution> solutions;

    @NotBlank
    private ObjectId userId;

    public Question() {
    }

    public Question(String readableId, Set<ObjectId> labels, Set<ObjectId> parents, Difficulty difficulty, List<Solution> solutions, ObjectId userId) {
        this.readableId = readableId;
        this.labels = labels;
        this.parents = parents;
        this.difficulty = difficulty;
        this.solutions = solutions;
        this.userId = userId;
    }

    public Question(ObjectId questionId, String readableId, Set<ObjectId> labels, Set<ObjectId> parents, Difficulty difficulty, String from, List<Solution> solutions, ObjectId userId) {
        this.questionId = questionId;
        this.readableId = readableId;
        this.labels = labels;
        this.parents = parents;
        this.difficulty = difficulty;
        this.from = from;
        this.solutions = solutions;
        this.userId = userId;
    }

    public ObjectId getQuestionId() {
        return questionId;
    }

    public void setQuestionId(ObjectId questionId) {
        this.questionId = questionId;
    }

    public String getReadableId() {
        return readableId;
    }

    public void setReadableId(String readableId) {
        this.readableId = readableId;
    }

    public Set<ObjectId> getLabels() {
        return labels;
    }

    public void setLabels(Set<ObjectId> labels) {
        this.labels = labels;
    }

    public Set<ObjectId> getParents() {
        return parents;
    }

    public void setParents(Set<ObjectId> parents) {
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
