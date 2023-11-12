package com.prosvirnin.alphabackend.repository;

import com.prosvirnin.alphabackend.model.survey.Answers;
import com.prosvirnin.alphabackend.model.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
}
