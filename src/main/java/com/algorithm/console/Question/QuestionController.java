package com.algorithm.console.Question;

import com.algorithm.console.Auth.AuthService;
import com.algorithm.console.Label.Label;
import com.algorithm.console.Label.LabelService;
import com.algorithm.console.Payload.ResponseBody;
import jakarta.validation.constraints.Null;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            System.out.println(userId);
            String name = question.getReadableId().trim();
            assert !name.equals("");
            try {
                Question newQuestion = this.questionService.createQuestion(question, userId);
                ResponseBody<Question> responseBody = new ResponseBody<>(newQuestion, "Succeeded to create the label.", null);

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
}
