package com.algorithm.console.Label;

import com.algorithm.console.Utils.StringFieldProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    LabelRepository labelRepository;

    public Label createLabel(String name, List<String> parents, String description, String userId) throws Exception{
        String normalizedName = StringFieldProcess.normalizeField(name);
        if(this.labelRepository.existsByName(normalizedName)) {
            throw new Exception("Label name " + normalizedName + " already existed.");
        }
        Label label = new Label(normalizedName, parents, description, userId);
        return this.labelRepository.save(label);
    }

    public Label retrieveLabelByLabelId(String labelId) {
        return this.labelRepository.findByLabelId(labelId).orElse(null);
    }

    public Label retrieveLabelByName(String name) {
        String normalizedName = StringFieldProcess.normalizeField(name);
        System.out.println("norma is " + normalizedName);

        return this.labelRepository.findByName(normalizedName).orElse(null);
    }

    public List<Label> listLabelsByUserId(String userId) {
        return this.labelRepository.findByUserId(userId);
    }

    public List<Label> listAllLabels() {
        return this.labelRepository.findAll();
    }

    public Label updateLabelByLabelId(String labelId, Label newLabel, String userId) throws Exception {
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

            labelById.setName(normalizedName);
            labelById.setParents(newLabel.getParents());
            labelById.setDescription(newLabel.getDescription());
            labelById.setUserId(newLabel.getUserId());

            return this.labelRepository.save(labelById);
        } else {
            throw new Exception("No such label");
        }
    }

    public Label updateLabelByName(String name, Label newLabel, String userId) throws Exception{
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
            labelByOldName.setUserId(newLabel.getUserId());

            return this.labelRepository.save(labelByOldName);
        } else {
            throw new Exception("No such label.");
        }
    }

    public Label deleteLabelByLabelId(String labelId, String userId) throws Exception {
        Label label = this.labelRepository.findByLabelId(labelId).orElse(null);

        if (label != null) {
            if (!label.getUserId().equals(userId)) {
                throw new IllegalAccessException("You can not access to others label");
            }

            this.labelRepository.deleteById(labelId);

            return label;
        } else {
            return null;
        }
    }

    public Label deleteLabelByName(String name, String userId) throws Exception {
        String normalizedName = StringFieldProcess.normalizeField(name);

        Label label = this.labelRepository.findByName(normalizedName).orElse(null);

        if (label != null) {
            if (!label.getUserId().equals(userId)) {
                throw new IllegalAccessException("You can not access to others label");
            }

            this.labelRepository.deleteByName(normalizedName);

            return label;
        } else {
            return null;
        }
    }
}
