package com.prosvirnin.alphabackend.repository;

import com.prosvirnin.alphabackend.model.survey.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
