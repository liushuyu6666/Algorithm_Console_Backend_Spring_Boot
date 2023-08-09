package com.algorithm.console.Label;

import com.algorithm.console.Utils.StringFieldProcess;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LabelService {
    @Autowired
    LabelRepository labelRepository;

    public LabelDTO createLabel(Label label, ObjectId userId) throws Exception{
        String normalizedName = StringFieldProcess.normalizeField(label.getName());
        if(this.labelRepository.existsByName(normalizedName)) {
            throw new Exception("Label name " + normalizedName + " already existed.");
        }

        Set<ObjectId> questions;
        if(label.getQuestions() == null) {
            questions = new HashSet<>();
        } else {
            questions = label.getQuestions();
        }

        Label newLabel = new Label(normalizedName, label.getParents(), label.getDescription(), label.getUrl(), questions, userId);

        return new LabelDTO(this.labelRepository.save(newLabel));
    }

    public LabelDTO retrieveLabelByLabelId(ObjectId labelId) {
        Label label = this.labelRepository.findByLabelId(labelId).orElse(null);
        if (label == null) return null;
        return new LabelDTO(label);
    }

    public LabelDTO retrieveLabelByName(String name) {
        String normalizedName = StringFieldProcess.normalizeField(name);
        Label label = this.labelRepository.findByName(normalizedName).orElse(null);
        if (label == null) return null;
        return new LabelDTO(label);
    }

    public List<LabelDTO> listLabelsByUserId(ObjectId userId) {
        return this.labelRepository.findByUserId(userId).stream().map(LabelDTO::new).toList();
    }

    public List<LabelDTO> listAllLabels() {
        return this.labelRepository.findAll().stream().map(LabelDTO::new).toList();
    }

    public LabelDTO updateLabelByLabelId(ObjectId labelId, Label newLabel, ObjectId userId) throws Exception {
        String normalizedName = StringFieldProcess.normalizeField(newLabel.getName());

        Label labelById = this.labelRepository.findByLabelId(labelId).orElse(null);
        Label labelByName = this.labelRepository.findByName(normalizedName).orElse(null);

        if (labelById != null) {
            // If the new label has a duplicated name with an existing label.
            if(labelByName != null && !labelByName.getLabelId().equals(labelId)) {
                throw new Exception("Label name " + normalizedName + " already existed.");
            }

            if (!labelById.getUserId().equals(userId)) {
                throw new IllegalAccessException("You can not access to others label");
            }

            Set<ObjectId> newQuestions = new HashSet<>();
            if(newLabel.getQuestions() != null) {
                newQuestions = newLabel.getQuestions();
            }
            labelById.setName(normalizedName);
            labelById.setParents(newLabel.getParents());
            labelById.setDescription(newLabel.getDescription());
            labelById.setUrl(newLabel.getUrl());
            labelById.setQuestions(newQuestions);
            labelById.setUserId(userId);

            return new LabelDTO(this.labelRepository.save(labelById));
        } else {
            throw new Exception("No such label");
        }
    }

    public LabelDTO updateLabelByName(String name, Label newLabel, ObjectId userId) throws Exception{
        String oldNormalizedName = StringFieldProcess.normalizeField(name);
        String newNormalizedName = StringFieldProcess.normalizeField(newLabel.getName());

        Label labelByOldName = this.labelRepository.findByName(oldNormalizedName).orElse(null);
        Label labelByNewName = this.labelRepository.findByName(newNormalizedName).orElse(null);

        if (labelByOldName != null) {
            // If the new label has a duplicated name with an existing label.
            if(labelByNewName != null && !labelByNewName.getLabelId().equals(labelByOldName.getLabelId())) {
                throw new Exception("Label name " + newNormalizedName + " already existed.");
            }

            if (!labelByOldName.getUserId().equals(userId)) {
                throw new IllegalAccessException("You can not access to others label");
            }

            labelByOldName.setName(newNormalizedName);
            labelByOldName.setParents(newLabel.getParents());
            labelByOldName.setDescription(newLabel.getDescription());
            labelByOldName.setUrl(newLabel.getUrl());
            labelByOldName.setQuestions(newLabel.getQuestions());
            labelByOldName.setUserId(newLabel.getUserId());

            return new LabelDTO(this.labelRepository.save(labelByOldName));
        } else {
            throw new Exception("No such label.");
        }
    }

    public LabelDTO deleteLabelByLabelId(ObjectId labelId, ObjectId userId) throws Exception {
        Label label = this.labelRepository.findByLabelId(labelId).orElse(null);

        if (label != null) {
            if (!label.getUserId().equals(userId)) {
                throw new IllegalAccessException("You can not access to others label");
            }

            this.labelRepository.deleteById(labelId);

            return new LabelDTO(label);
        } else {
            return null;
        }
    }

    public LabelDTO deleteLabelByName(String name, ObjectId userId) throws Exception {
        String normalizedName = StringFieldProcess.normalizeField(name);

        Label label = this.labelRepository.findByName(normalizedName).orElse(null);

        if (label != null) {
            if (!label.getUserId().equals(userId)) {
                throw new IllegalAccessException("You can not access to others label");
            }

            this.labelRepository.deleteByName(normalizedName);

            return new LabelDTO(label);
        } else {
            return null;
        }
    }
}
