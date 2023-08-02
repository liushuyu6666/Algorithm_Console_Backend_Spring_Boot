package com.algorithm.console.Question;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findAll();

    List<Question> findByUserId(ObjectId userId);

    Optional<Question> findByQuestionId(ObjectId questionId);

    Optional<Question> findByReadableId(String readableId);

    void deleteByQuestionId(ObjectId questionId);

    void deleteByReadableId(String readableId);
}
