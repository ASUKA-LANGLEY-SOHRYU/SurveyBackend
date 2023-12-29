package com.prosvirnin.alphabackend.model.survey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.survey.filter.UserFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Survey")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "picture")
    private String picture;

    @Column(name = "questions")
    private String questions;

    @ManyToOne
    @JsonBackReference(value = "company-survey")
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "survey")
    @JsonManagedReference(value = "survey-answers")
    private List<Answers> answers;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonManagedReference(value = "survey-filter")
    private UserFilter filter;

    public void addAnswers(Answers newAnswers) {
        if (answers == null)
            answers = List.of();
        answers.add(newAnswers);
        newAnswers.setSurvey(this);
    }
}
