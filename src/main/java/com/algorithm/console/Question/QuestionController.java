package com.algorithm.console.Question;

import com.algorithm.console.Auth.AuthService;
import com.algorithm.console.Payload.ResponseBody;
import jakarta.validation.constraints.Null;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/v1/algorithm")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    AuthService authService;

    @PostMapping("/questions")
    public ResponseEntity<ResponseBody<?>> createQuestion(
            @RequestHeader("Authorization") String authentication,
            @RequestBody Question question
    ) {
        try {
            ObjectId userId = this.authService.authenticateToken(authentication);

            String name = question.getReadableId().trim();
            assert !name.isEmpty();
            try {
                QuestionDTO newQuestion = this.questionService.createQuestion(question, userId);
                ResponseBody<QuestionDTO> responseBody = new ResponseBody<>(newQuestion, "Succeeded to create the label.", null);

                return ok(responseBody);
            } catch (Exception e) {
                ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
                return ok(responseBody);
            }

        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<ResponseBody<?>> retrieveQuestionById(@PathVariable("id") ObjectId id) {
        try {
            QuestionDTO questionDTO = this.questionService.retrieveQuestionById(id);
            ResponseBody<QuestionDTO> responseBody = new ResponseBody<>(questionDTO, "Get question successfully.", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }

    }

    @GetMapping("/questions")
    public ResponseEntity<ResponseBody<List<QuestionDTO>>> listAllQuestions() {
        List<QuestionDTO> questions = this.questionService.listAllQuestions();
        ResponseBody<List<QuestionDTO>> responseBody = new ResponseBody<>(questions, "Get labels successfully.", null);
        return ok(responseBody);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<ResponseBody<?>> updateQuestionById(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("id") ObjectId questionId,
            @RequestBody Question question
    ) {
        try {
            ObjectId userId = this.authService.authenticateToken(authentication);

            QuestionDTO questionDTO = this.questionService.updateQuestionById(questionId, question, userId);
            ResponseBody<QuestionDTO> responseBody = new ResponseBody<>(questionDTO, "Succeeded to update question", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @PutMapping("/questions/name/{name}")
    public ResponseEntity<ResponseBody<?>> updateQuestionByName(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("name") String name,
            @RequestBody Question question
    ) {
        try {
            ObjectId userId = this.authService.authenticateToken(authentication);

            QuestionDTO questionDTO = this.questionService.updateQuestionByName(name, question, userId);
            ResponseBody<QuestionDTO> responseBody = new ResponseBody<>(questionDTO, "Succeeded to update question", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

}
