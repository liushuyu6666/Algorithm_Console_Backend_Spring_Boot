package com.algorithm.console.Label;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends MongoRepository<Label, ObjectId> {
    List<Label> findAll();
    List<Label> findByUserId(ObjectId userId);

    Optional<Label> findByLabelId(ObjectId labelId);

    Optional<Label> findByName(String name);

    Boolean existsByLabelId(String labelId);

    Boolean existsByName(String name);

    void deleteByName(String name);
}
