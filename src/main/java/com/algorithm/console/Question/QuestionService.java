package com.algorithm.console.Question;

import com.algorithm.console.Label.Label;
import com.algorithm.console.Label.LabelRepository;
import com.algorithm.console.Label.LabelService;
import com.algorithm.console.Label.SimplifiedLabel;
import com.algorithm.console.Utils.StringFieldProcess;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    LabelService labelService;

    public QuestionDTO createQuestion(Question newQuestion, ObjectId userId) throws Exception {
        String normalizedReadableId = StringFieldProcess.normalizeField(newQuestion.getReadableId());

        Question existingQuestion = this.questionRepository.findByReadableId(normalizedReadableId).orElse(null);
        if(existingQuestion != null) {
            throw new Exception("The question id " + normalizedReadableId + " is existed");
        } else {
            Question question = new Question(
                    normalizedReadableId,
                    newQuestion.getLabels(),
                    newQuestion.getParents(),
                    newQuestion.getDifficulty(),
                    newQuestion.getFrom(),
                    newQuestion.getSolutions(),
                    userId
            );

            List<SimplifiedLabel> labels = this.labelService.getLabels(question.getLabels());

            Question insertedQuestion = this.questionRepository.save(question);
            QuestionDTO questionDTO = new QuestionDTO(insertedQuestion, labels);

            for(ObjectId labelId : newQuestion.getLabels()) {
                Label label = this.labelRepository.findByLabelId(labelId).orElse(null);
                if(label == null) continue;
                label.getQuestions().add(insertedQuestion.getQuestionId());
                this.labelRepository.save(label);
            }

            return questionDTO;
        }
    }

    public QuestionDTO retrieveQuestionById(ObjectId id) throws Exception {
        Question question = this.questionRepository.findByQuestionId(id).orElse(null);

        if(question == null) {
            throw new Exception("No such question");
        }

        List<SimplifiedLabel> labels = this.labelService.getLabels(question.getLabels());

        return new QuestionDTO(question, labels);
    }

//    public QuestionDTO retrieveQuestionByName(String readableId) throws Exception {
//        Question question = this.questionRepository.findByReadableId(readableId).orElse(null);
//
//        if(question == null) {
//            throw new Exception("No such question");
//        } else {
//            return new QuestionDTO(question);
//        }
//    }

    public List<QuestionDTO> listAllQuestions() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : this.questionRepository.findAll()) {
            List<SimplifiedLabel> labelList = this.labelService.getLabels(question.getLabels());
            QuestionDTO questionDTO = new QuestionDTO(question, labelList);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    private void removeOneQuestionIdFromLabel(ObjectId labelId, ObjectId questionId) {
        Label label = this.labelRepository.findByLabelId(labelId).orElse(null);

        if(label == null) return;

        label.getQuestions().remove(questionId);
        this.labelRepository.save(label);
    }

    private void addOneQuestionIdInLabel(ObjectId labelId, ObjectId questionId) {
        Label label = this.labelRepository.findByLabelId(labelId).orElse(null);

        if(label == null) return;

        label.getQuestions().add(questionId);
        this.labelRepository.save(label);
    }

    private void updateQuestionIdsInLabels(ObjectId questionId, Set<ObjectId> oldLabelIds, Set<ObjectId> newLabelIds) {

        Set<ObjectId> onlyInOlds = new HashSet<>(oldLabelIds);
        onlyInOlds.removeAll(newLabelIds);

        Set<ObjectId> onlyInNews = new HashSet<>(newLabelIds);
        onlyInNews.removeAll(oldLabelIds);

        for(ObjectId labelId : onlyInOlds) {
            this.removeOneQuestionIdFromLabel(labelId, questionId);
        }

        for(ObjectId labelId: onlyInNews) {
            this.addOneQuestionIdInLabel(labelId, questionId);
        }


    }

    public QuestionDTO updateQuestionById(ObjectId id, Question question, ObjectId userId) throws Exception {
        String normalizedName = StringFieldProcess.normalizeField(question.getReadableId());

        Question questionFromId = this.questionRepository.findByQuestionId(id).orElse(null);
        Question questionFromName = this.questionRepository.findByReadableId(normalizedName).orElse(null);

        if(questionFromId != null) {
            if(questionFromName != null && !questionFromName.getQuestionId().equals(id)) {
                throw new Exception("Question readable name " + question.getReadableId() + " is existed.");
            }

            if(!questionFromId.getUserId().equals(userId)) {
                throw new Exception("You can not access to others label.");
            }

            this.updateQuestionIdsInLabels(id, questionFromId.getLabels(), question.getLabels());

            questionFromId.setReadableId(question.getReadableId());
            questionFromId.setLabels(question.getLabels());
            questionFromId.setParents(question.getParents());
            questionFromId.setDifficulty(question.getDifficulty());
            questionFromId.setFrom(question.getFrom());
            questionFromId.setSolutions(question.getSolutions());
            questionFromId.setUserId(userId);
            List<SimplifiedLabel> labels = this.labelService.getLabels(questionFromId.getLabels());

            return new QuestionDTO(this.questionRepository.save(questionFromId), labels);
        } else {
            throw new Exception("Question with " + id + " does not exist.");
        }
    }

    public QuestionDTO updateQuestionByName(String readableId, Question question, ObjectId userId) throws Exception {
        String normalizedOldName = StringFieldProcess.normalizeField(readableId);
        String normalizedNewName = StringFieldProcess.normalizeField(question.getReadableId());

        Question questionByOldName = this.questionRepository.findByReadableId(normalizedOldName).orElse(null);
        Question questionByNewName = this.questionRepository.findByReadableId(normalizedNewName).orElse(null);

        if(questionByOldName != null) {
            if(questionByNewName != null && !questionByNewName.getQuestionId().equals(questionByOldName.getQuestionId())) {
                throw new Exception("Question readable name " + normalizedNewName + " is existed.");
            }

            if(!questionByOldName.getUserId().equals(userId)) {
                throw new Exception("You can not access to others label.");
            }

            this.updateQuestionIdsInLabels(questionByOldName.getQuestionId(), questionByOldName.getLabels(), question.getLabels());

            questionByOldName.setReadableId(question.getReadableId());
            questionByOldName.setLabels(question.getLabels());
            questionByOldName.setParents(question.getParents());
            questionByOldName.setDifficulty(question.getDifficulty());
            questionByOldName.setFrom(question.getFrom());
            questionByOldName.setSolutions(question.getSolutions());
            questionByOldName.setUserId(userId);
            List<SimplifiedLabel> labels = this.labelService.getLabels(questionByOldName.getLabels());


            return new QuestionDTO(this.questionRepository.save(questionByOldName), labels);
        } else {
            throw new Exception("Question with " + normalizedOldName + " does not exist.");
        }
    }

    public QuestionDTO deleteQuestionById(ObjectId id, ObjectId userId) throws Exception {
        Question question = this.questionRepository.findByQuestionId(id).orElse(null);

        if (question != null) {
            if(!question.getUserId().equals(userId)) {
                throw new Exception("You can not access to others question.");
            }

            Set<ObjectId> labelIds = question.getLabels();
            for(ObjectId labelId : labelIds) {
                this.removeOneQuestionIdFromLabel(labelId, question.getQuestionId());
            }

            List<SimplifiedLabel> labels = this.labelService.getLabels(question.getLabels());

            this.questionRepository.deleteByQuestionId(id);
            return new QuestionDTO(question, labels);
        } else {
            throw new Exception("Cannot find " + id + " under the user.");
        }
    }

    public QuestionDTO deleteQuestionByName(String name, ObjectId userId) throws Exception {
        String normalizedName = StringFieldProcess.normalizeField(name);

        Question question = this.questionRepository.findByReadableId(normalizedName).orElse(null);

        if (question != null) {
            if(!question.getUserId().equals(userId)) {
                throw new Exception("You can not access to others question.");
            }

            Set<ObjectId> labelIds = question.getLabels();
            for(ObjectId labelId : labelIds) {
                this.removeOneQuestionIdFromLabel(labelId, question.getQuestionId());
            }

            List<SimplifiedLabel> labels = this.labelService.getLabels(question.getLabels());

            this.questionRepository.deleteByReadableId(normalizedName);
            return new QuestionDTO(question, labels);
        } else {
            throw new Exception("Cannot find " + normalizedName + " under the user.");
        }
    }
}
