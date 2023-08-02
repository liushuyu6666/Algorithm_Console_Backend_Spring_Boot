package com.jays.demo.Label;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends MongoRepository<Label, String> {
    List<Label> findAll();
    List<Label> findByUserId(String userId);

    Optional<Label> findByLabelId(String labelId);

    Optional<Label> findByName(String name);

    Boolean existsByLabelId(String labelId);

    Boolean existsByName(String name);

    void deleteByName(String name);
}
