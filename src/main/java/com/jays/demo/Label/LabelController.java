package com.jays.demo.Label;

import com.jays.demo.Auth.AuthService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jays.demo.Payload.ResponseBody;


import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/v1/algorithm")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LabelController {
    @Autowired
    LabelService labelService;

    @Autowired
    AuthService authService;

    @PostMapping("/labels")
    public ResponseEntity<ResponseBody<?>> createLabel(
            @RequestHeader("Authorization") String authentication,
            @RequestBody Label label
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            String name = label.getName().trim();
            assert !name.equals("");
            try {
                Label newLabel = this.labelService.createLabel(label.getName(), label.getParents(), label.getDescription(), userId);
                ResponseBody<Label> responseBody = new ResponseBody<>(newLabel, "Succeeded to create the label.", null);

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

    @GetMapping("/labels/{labelId}")
    public ResponseEntity<ResponseBody<Label>> retrieveLabelByLabelId(@PathVariable("labelId") String labelId) {
        Label label = this.labelService.retrieveLabelByLabelId(labelId);
        ResponseBody<Label> responseBody = new ResponseBody<>(label, "Get label successfully.", null);
        return ok(responseBody);
    }

    @GetMapping("/labels/name/{name}")
    public ResponseEntity<ResponseBody<Label>> retrieveLabelByName(@PathVariable("name") String name) {
        Label label = this.labelService.retrieveLabelByName(name);
        ResponseBody<Label> responseBody = new ResponseBody<>(label, "Get label successfully.", null);
        return ok(responseBody);
    }

    @GetMapping("/labels/users")
    public ResponseEntity<ResponseBody<?>> listLabelsByUserId(@RequestHeader("Authorization") String authentication) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            List<Label> labels = this.labelService.listLabelsByUserId(userId);
            ResponseBody<List<Label>> responseBody = new ResponseBody<>(labels, "Get labels successfully.", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<List<Label>> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @GetMapping("/labels")
    public ResponseEntity<ResponseBody<List<Label>>> listAllLabels() {
        List<Label> labels = this.labelService.listAllLabels();
        ResponseBody<List<Label>> responseBody = new ResponseBody<>(labels, "Get labels successfully.", null);
        return ok(responseBody);
    }

    @PutMapping("/labels/{id}")
    public ResponseEntity<ResponseBody<?>> updateLabelByLabelId(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("id") String labelId,
            @RequestBody Label label
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            Label newLabel = this.labelService.updateLabelByLabelId(labelId, label, userId);
            ResponseBody<Label> responseBody = new ResponseBody<>(newLabel, "Succeeded to update label.", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @PutMapping("/labels/name/{name}")
    public ResponseEntity<ResponseBody<?>> updateLabelByName(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("name") String name,
            @RequestBody Label label
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            Label newLabel = this.labelService.updateLabelByName(name, label, userId);
            ResponseBody<Label> responseBody = new ResponseBody<>(newLabel, "Succeeded to update label.", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity<ResponseBody<?>> deleteLabelByLabelId(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("id") String labelId
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            Label label = this.labelService.deleteLabelByLabelId(labelId, userId);
            ResponseBody<Label> responseBody = new ResponseBody<>(label, "Succeeded to delete the label", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @DeleteMapping("/labels/name/{name}")
    public ResponseEntity<ResponseBody<?>> deleteLabelByName(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("name") String name
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            Label label = this.labelService.deleteLabelByName(name, userId);
            ResponseBody<Label> responseBody = new ResponseBody<>(label, "Succeeded to delete the label", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

}
