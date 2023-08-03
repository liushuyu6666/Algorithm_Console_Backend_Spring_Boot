package com.algorithm.console.Label;

import com.algorithm.console.Auth.AuthService;
import jakarta.validation.constraints.Null;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.algorithm.console.Payload.ResponseBody;


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
            ObjectId userId = this.authService.authenticateToken(authentication);

            String name = label.getName().trim();
            assert !name.equals("");
            try {
                LabelDTO newLabel = this.labelService.createLabel(label.getName(), label.getParents(), label.getDescription(), userId);
                ResponseBody<LabelDTO> responseBody = new ResponseBody<>(newLabel, "Succeeded to create the label.", null);

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
    public ResponseEntity<ResponseBody<LabelDTO>> retrieveLabelByLabelId(@PathVariable("labelId") ObjectId labelId) {
        LabelDTO labelDTO = this.labelService.retrieveLabelByLabelId(labelId);
        ResponseBody<LabelDTO> responseBody = new ResponseBody<>(labelDTO, "Get label successfully.", null);
        return ok(responseBody);
    }

    @GetMapping("/labels/name/{name}")
    public ResponseEntity<ResponseBody<LabelDTO>> retrieveLabelByName(@PathVariable("name") String name) {
        LabelDTO labelDTO = this.labelService.retrieveLabelByName(name);
        ResponseBody<LabelDTO> responseBody = new ResponseBody<>(labelDTO, "Get label successfully.", null);
        return ok(responseBody);
    }

    @GetMapping("/labels/users")
    public ResponseEntity<ResponseBody<?>> listLabelsByUserId(@RequestHeader("Authorization") String authentication) {
        try {
            ObjectId userId = this.authService.authenticateToken(authentication);

            List<LabelDTO> labels = this.labelService.listLabelsByUserId(userId);
            ResponseBody<List<LabelDTO>> responseBody = new ResponseBody<>(labels, "Get labels successfully.", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<List<Label>> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @GetMapping("/labels")
    public ResponseEntity<ResponseBody<List<LabelDTO>>> listAllLabels() {
        List<LabelDTO> labels = this.labelService.listAllLabels();
        ResponseBody<List<LabelDTO>> responseBody = new ResponseBody<>(labels, "Get labels successfully.", null);
        return ok(responseBody);
    }

    @PutMapping("/labels/{id}")
    public ResponseEntity<ResponseBody<?>> updateLabelByLabelId(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("id") ObjectId labelId,
            @RequestBody Label label
    ) {
        try {
            ObjectId userId = this.authService.authenticateToken(authentication);

            LabelDTO newLabel = this.labelService.updateLabelByLabelId(labelId, label, userId);
            ResponseBody<LabelDTO> responseBody = new ResponseBody<>(newLabel, "Succeeded to update label.", null);
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
            ObjectId userId = this.authService.authenticateToken(authentication);

            LabelDTO newLabel = this.labelService.updateLabelByName(name, label, userId);
            ResponseBody<LabelDTO> responseBody = new ResponseBody<>(newLabel, "Succeeded to update label.", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity<ResponseBody<?>> deleteLabelByLabelId(
            @RequestHeader("Authorization") String authentication,
            @PathVariable("id") ObjectId labelId
    ) {
        try {
            ObjectId userId = this.authService.authenticateToken(authentication);

            LabelDTO label = this.labelService.deleteLabelByLabelId(labelId, userId);
            ResponseBody<LabelDTO> responseBody = new ResponseBody<>(label, "Succeeded to delete the label", null);
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
            ObjectId userId = this.authService.authenticateToken(authentication);

            LabelDTO label = this.labelService.deleteLabelByName(name, userId);
            ResponseBody<LabelDTO> responseBody = new ResponseBody<>(label, "Succeeded to delete the label", null);
            return ok(responseBody);
        } catch (Exception e) {
            ResponseBody<Null> responseBody = new ResponseBody<>(null, e.getMessage(), e);
            return ok(responseBody);
        }
    }

}
