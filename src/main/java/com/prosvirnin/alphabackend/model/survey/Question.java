package com.prosvirnin.alphabackend.model.survey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prosvirnin.alphabackend.model.survey.filter.StringListConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer max;
    private Integer min;

    @Convert(converter = StringListConverter.class)
    private List<String> answers;

    @ManyToOne
    //@JsonBackReference(value = "company-question")
    private Survey survey;
}
