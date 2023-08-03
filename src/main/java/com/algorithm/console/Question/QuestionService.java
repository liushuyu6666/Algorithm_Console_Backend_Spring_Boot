package com.algorithm.console.Question;

import com.algorithm.console.Utils.StringFieldProcess;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public Question createQuestion(Question newQuestion, ObjectId userId) throws Exception {
        String normalizedReadableId = StringFieldProcess.normalizeField(newQuestion.getReadableId());

        Question existingQuestion = questionRepository.findByReadableId(normalizedReadableId).orElse(null);
        if(existingQuestion != null) {
            throw new Exception("The question id " + normalizedReadableId + " is existed");
        } else {
            Question question = new Question(newQuestion.getFrom(), newQuestion.getSection(), newQuestion.getStringName(), newQuestion.getNumericName(), newQuestion.getReadableId(), newQuestion.getLabels(), newQuestion.getParents(), newQuestion.getDifficulty(), newQuestion.getQuestionUrls(), newQuestion.getReadme(), newQuestion.getSolutions(), newQuestion.getDescription() ,userId);
            return questionRepository.save(question);
        }
    }

    public Question retrieveQuestionById(ObjectId id) throws Exception {
        Question question = this.questionRepository.findByQuestionId(id).orElse(null);

        if(question == null) {
            throw new Exception("No such question");
        } else {
            return question;
        }
    }

    public Question retrieveQuestionByName(String readableId) throws Exception {
        Question question = this.questionRepository.findByReadableId(readableId).orElse(null);

        if(question == null) {
            throw new Exception("No such question");
        } else {
            return question;
        }
    }

    public List<Question> listQuestionsByUserId(ObjectId userId) {
        return this.questionRepository.findByUserId(userId);
    }

    public List<Question> listAllQuestions() {
        return this.questionRepository.findAll();
    }

    public Question updateQuestionById(ObjectId id, Question question, ObjectId userId) throws Exception {
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

            questionFromId.setFrom(question.getFrom());
            questionFromId.setSection(question.getSection());
            questionFromId.setStringName(question.getStringName());
            questionFromId.setNumericName(question.getNumericName());
            questionFromId.setReadableId(question.getReadableId());
            questionFromId.setLabels(question.getLabels());
            questionFromId.setParents(question.getParents());
            questionFromId.setDifficulty(question.getDifficulty());
            questionFromId.setQuestionUrls(question.getQuestionUrls());
            questionFromId.setReadme(question.getReadme());
            questionFromId.setSolutions(question.getSolutions());
            questionFromId.setUserId(userId);

            return this.questionRepository.save(questionFromId);
        } else {
            throw new Exception("Question with " + id + " does not exist.");
        }
    }

    public Question updateQuestionByName(String readableId, Question question, ObjectId userId) throws Exception {
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

            questionByOldName.setFrom(question.getFrom());
            questionByOldName.setSection(question.getSection());
            questionByOldName.setStringName(question.getStringName());
            questionByOldName.setNumericName(question.getNumericName());
            questionByOldName.setReadableId(question.getReadableId());
            questionByOldName.setLabels(question.getLabels());
            questionByOldName.setParents(question.getParents());
            questionByOldName.setDifficulty(question.getDifficulty());
            questionByOldName.setQuestionUrls(question.getQuestionUrls());
            questionByOldName.setReadme(question.getReadme());
            questionByOldName.setSolutions(question.getSolutions());
            questionByOldName.setUserId(userId);

            return this.questionRepository.save(questionByOldName);
        } else {
            throw new Exception("Question with " + normalizedOldName + " does not exist.");
        }
    }

    public Question deleteQuestionById(ObjectId id, ObjectId userId) throws Exception {
        Question question = this.questionRepository.findByQuestionId(id).orElse(null);

        if (question != null) {
            if(!question.getUserId().equals(userId)) {
                throw new Exception("You can not access to others question.");
            }

            this.questionRepository.deleteByQuestionId(id);
            return question;
        } else {
            throw new Exception("Cannot find " + id + " under the user.");
        }
    }

    public Question deleteQuestionByName(String name, ObjectId userId) throws Exception {
        String normalizedName = StringFieldProcess.normalizeField(name);

        Question question = this.questionRepository.findByReadableId(normalizedName).orElse(null);

        if (question != null) {
            if(!question.getUserId().equals(userId)) {
                throw new Exception("You can not access to others question.");
            }

            this.questionRepository.deleteByReadableId(normalizedName);
            return question;
        } else {
            throw new Exception("Cannot find " + normalizedName + " under the user.");
        }
    }
}
