package com.prosvirnin.alphabackend.repository;

import com.prosvirnin.alphabackend.model.survey.Answers;
import com.prosvirnin.alphabackend.model.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository
        extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {
}
