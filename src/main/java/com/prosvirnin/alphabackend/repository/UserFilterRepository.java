package com.prosvirnin.alphabackend.repository;

import com.prosvirnin.alphabackend.model.survey.Survey;
import com.prosvirnin.alphabackend.model.survey.filter.UserFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFilterRepository extends JpaRepository<UserFilter, Long> {
}
